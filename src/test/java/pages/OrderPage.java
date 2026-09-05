package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderPage {
    private WebDriver webDriver;

    public OrderPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    //Инпут имя
    public By firstName = By.xpath(".//div[@class='Order_Form__17u6u']/div[1]/input");

    //Инпут фамилия
    public By lastName = By.xpath(".//div[@class='Order_Form__17u6u']/div[2]/input");

    //Инпут адрес
    public By address = By.xpath(".//div[@class='Order_Form__17u6u']/div[3]/input");

    //Инпут метро
    public By metro = By.xpath(".//div[@class='select-search']");

    //Инпут телефон
    public By phone = By.xpath(".//div[@class='Order_Form__17u6u']/div[5]/input");

    //Станция метро
    public By metroStation = By.xpath(".//div[@class='select-search__select']/ul/li[3]");

    //Кнопка далее
    public By nextButton = By.xpath(".//button[text()='Далее']");

    //Инпут с датой
    public By dateInput = By.xpath(".//div[@class='react-datepicker__input-container']/input");

    //Инпут срок аренды
    public By rentPeriodInput = By.className("Dropdown-control");

    //Чекбокс выбора цвета
    public By isBlackColorCheckbox = By.id("black");

    //Инпут комментарий курьеру
    public By commentToCourier = By.xpath(".//input[@class='Input_Input__1iN_Z Input_Responsible__1jDKN'][1]");

    //Кнопка заказать
    public By orderButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");

    //Кнопка подтверждения заказа
    public By positiveButton = By.xpath(".//div[@class='Order_Buttons__1xGrp']/button[text()='Да']");

    //Попап с текстом успеха заказа
    public By successPopupHeader = By.xpath(".//div[@class='Order_ModalHeader__3FDaJ'][text()='Заказ оформлен']");

    //Лого Самоката
    public By samokatLogo = By.className("Header_LogoScooter__3lsAR");

    //Ошибка инпута Имя
    public By firstNameError = By.xpath(".//div[text()='Введите корректное имя']");

    //Ошибка инпута Фамилия
    public By lastNameError = By.xpath(".//div[text()='Введите корректную фамилию']");

    //Ошибка инпута Адрес
    public By addressError = By.xpath(".//div[text()='Введите корректный адрес']");

    //Ошибка инпута Телефон
    public By phoneError = By.xpath(".//div[text()='Введите корректный номер']");

    //Заполняем данные пользователя
    public OrderPage setUserData(String userFirstName, String userLastName, String userAddress, String userPhone) {
        webDriver.findElement(firstName).sendKeys(userFirstName);
        webDriver.findElement(lastName).sendKeys(userLastName);
        webDriver.findElement(address).sendKeys(userAddress);
        webDriver.findElement(phone).sendKeys(userPhone);
        return this;
    }

    //Выбираем станцию метро из списка
    public OrderPage clickMetroStation() {
        webDriver.findElement(metro).click();
        webDriver.findElement(metroStation).click();
        return this;
    }

    //Клик по Далее
    public OrderPage nextClick() {
        webDriver.findElement(nextButton).click();
        return this;
    }

    //Заполняем опции заказа
    public OrderPage setOrderOptions(String number, String rent, String courierComment) {
        By calendar = By.className("react-datepicker__day--00" + number);
        By rentPeriod = By.xpath(".//div[@class='Dropdown-option'][text()='" + rent + "']");
        webDriver.findElement(dateInput).click();
        webDriver.findElement(calendar).click();
        webDriver.findElement(rentPeriodInput).click();
        webDriver.findElement(rentPeriod).click();
        webDriver.findElement(isBlackColorCheckbox).click();
        webDriver.findElement(commentToCourier).sendKeys(courierComment);
        webDriver.findElement(orderButton).click();
        webDriver.findElement(positiveButton).click();//вынести в тест
        return this;
    }

    //Проверяем окно успеха
    public void checkSuccessWindow() {
        boolean isDisplayed = webDriver.findElement(successPopupHeader).isDisplayed();
        assertTrue(isDisplayed);
    }

    //Клик по лого самоката
    public void clickSamokatLogo() {
        webDriver.findElement(samokatLogo).click();
    }

    //Проверяем валидацию
    public void checkInputValidation() {
        boolean isFirstName = webDriver.findElement(firstNameError).isDisplayed();
        boolean isLastName = webDriver.findElement(lastNameError).isDisplayed();
        boolean isAddress = webDriver.findElement(addressError).isDisplayed();
        boolean isPhone = webDriver.findElement(phoneError).isDisplayed();

        assertTrue(isFirstName);
        assertTrue(isLastName);
        assertTrue(isAddress);
        assertTrue(isPhone);
    }
}
