package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import utils.ConfigReader;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {
    private SelenideElement usernameInput = $("#user-name");
    private SelenideElement passwordInput = $("#password");
    private SelenideElement loginButton = $("#login-button");
    private SelenideElement errorMessage = $("[data-test='error']");

    @Step("Открыть страницу авторизации")
    public LoginPage openPage() {
        open(ConfigReader.get("base.url"));
        return this;
    }

    @Step("Авторизоваться пользователем {username}")
    public void login(String username, String password) {
        usernameInput.setValue(username);
        passwordInput.setValue(password);
        loginButton.click();
    }

    @Step("Проверить, что отображается ошибка: {expectedText}")
    public void checkErrorMessage(String expectedText) {
        errorMessage.shouldBe(visible).shouldHave(text(expectedText));
    }
}