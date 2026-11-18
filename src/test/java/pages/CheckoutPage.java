package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CheckoutPage extends BasePage {

    private SelenideElement firstNameInput = $("#first-name");
    private SelenideElement lastNameInput = $("#last-name");
    private SelenideElement postalCodeInput = $("#postal-code");
    private SelenideElement continueButton = $("#continue");


    private SelenideElement finishButton = $("#finish");
    private SelenideElement completeHeader = $(".complete-header");

    @Step("Заполнить данные покупателя: {firstName} {lastName}, индекс {postalCode}")
    public CheckoutPage fillCustomerInfo(String firstName, String lastName, String postalCode) {
        firstNameInput.setValue(firstName);
        lastNameInput.setValue(lastName);
        postalCodeInput.setValue(postalCode);
        continueButton.click();
        return this;
    }

    @Step("Подтвердить оформление заказа")
    public CheckoutPage finishOrder() {
        finishButton.click();
        return this;
    }

    @Step("Проверить сообщение об успешном заказе")
    public void checkOrderSuccess() {
        completeHeader.shouldBe(visible).shouldHave(text("Thank you for your order!"));
    }
}