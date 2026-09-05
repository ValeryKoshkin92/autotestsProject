package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static config.AppConfig.APP_MAIN_PAGE_URL;

public class MainPage {

    WebDriver webDriver;

    //кнопка скрытия согласия
    public By closeCookiesButton = By.id("rcc-confirm-button");

    //кнопка заказа в хедере
    public By orderButtonTop = By.xpath(".//div[@class='Header_Nav__AGCXC']/button[text()='Заказать']");

    //Кнопка заказа на странице
    public By orderButtonDown = By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button");

    //кнопка статуса заказа
    public By orderStatusButton = By.xpath(".//button[text()='Статус заказа']");

    //инпут ввода номера заказа
    public By orderNumberInput = By.xpath(".//input[@class='Input_Input__1iN_Z Header_Input__xIoUq']");

    //Лого Яндекса
    public By yandexLogo = By.className("Header_LogoYandex__3TSOI");

    //Лого Самоката
    public By samokatLogo = By.className("Header_LogoScooter__3lsAR");

    //Кнопка Go!
    public By goButton = By.xpath(".//button[text()='Go!']");


    public MainPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        webDriver.get(APP_MAIN_PAGE_URL);
    }

    //Скролл до вопросов
    @Step("Скроллим до блока FAQ")
    public MainPage scrollToAccordion() {
        WebElement element = webDriver.findElement(By.className("accordion"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", element);
        return this;
    }

    //Скролл до кнопка заказа
    public void scrollToOrderButton() {
        WebElement element = webDriver.findElement(By.xpath(".//div[@class='Home_FinishButton__1_cWm']/button"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView();", element);
    }

    //Клик по созданию заказа, аргументом выбираем одну из двух кнопок заказа на странице
    @Step("Клик по кнопке заказа")
    public OrderPage clickOrderButton(String orderButton) {
        if (orderButton == "orderButtonDown") {
            scrollToOrderButton();
            webDriver.findElement(orderButtonDown).click();
        } else if (orderButton == "orderButtonTop") {
            webDriver.findElement(orderButtonTop).click();
        }
        return new OrderPage(webDriver);
    }

    //Клик по кнопке в аккордеоне
    public MainPage clickAccordionButton(String questionButton) {
        webDriver.findElement(By.id(questionButton)).click();
        return this;
    }

    //Получаем текст ответа в аккордеоне
    public String getAnswerTextInAccordion(String answerLocator) {
        return webDriver.findElement(By.id(answerLocator)).getText();
    }

    //Клик по лого яндекса
    public void clickYandexLogo() {
        webDriver.findElement(yandexLogo).click();
    }

    //Клик по статусу заказа
    public MainPage clickOrderStatusButton() {
        webDriver.findElement(orderStatusButton).click();
        return this;
    }

    //Заполняем инпут номера заказа
    public MainPage fillOrderNumberInput(String nonexistentOrder) {
        new WebDriverWait(webDriver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(orderNumberInput));
        webDriver.findElement(orderNumberInput).sendKeys(nonexistentOrder);
        return this;
    }

    //Клик по кнопке Go
    public OrderStatusPage clickGoButton() {
        webDriver.findElement(goButton).click();
        return new OrderStatusPage(webDriver);
    }
}