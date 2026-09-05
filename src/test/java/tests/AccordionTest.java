package tests;

import config.BaseTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MainPage;

import java.util.stream.Stream;

import static java.time.Duration.ofSeconds;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@DisplayName("Проверка FAQ")
public class AccordionTest extends BaseTest {


    static Stream<Arguments> getTestData() {
        return Stream.of(
                arguments("0", "0", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."),
                arguments("1", "1", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."),
                arguments("2", "2", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."),
                arguments("3", "3", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."),
                arguments("4", "4", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."),
                arguments("5", "5", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."),
                arguments("6", "6", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."),
                arguments("7", "7", "Да, обязательно. Всем самокатов! И Москве, и Московской области.")
        );
    }

    @ParameterizedTest
    @MethodSource("getTestData")
    @DisplayName("Проверка работы FAQ")
    public void accordionTest(String question, String answer, String expected) {

        MainPage mainPage = new MainPage(webDriver)
                .scrollToAccordion()
                .clickAccordionButton("accordion__heading-" + question);

        new WebDriverWait(webDriver, ofSeconds(5))
                .until(visibilityOfElementLocated(By.id("accordion__panel-" + answer)));

        assertEquals(expected, mainPage.getAnswerTextInAccordion("accordion__panel-" + answer));
    }
}