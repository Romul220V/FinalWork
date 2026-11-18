package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.InventoryPage;
import pages.LoginPage;
import utils.ConfigReader;

@Feature("Авторизация")
public class LoginTests extends BaseTests {

    @Test
    @Story("Успешный вход")
    @DisplayName("Успешная авторизация стандартного пользователя")
    void testSuccessfulLogin() {
        LoginPage loginPage = new LoginPage();
        InventoryPage inventoryPage = new InventoryPage();

        loginPage.openPage()
                .login(ConfigReader.get("standard.user"), ConfigReader.get("user.password"));

        inventoryPage.checkPageOpened();
    }

    @Test
    @Story("Ошибки входа")
    @DisplayName("Авторизация заблокированного пользователя")
    void testLockedOutUserLogin() {
        LoginPage loginPage = new LoginPage();

        loginPage.openPage()
                .login(ConfigReader.get("standard.user"), ConfigReader.get("locked.user"));

        loginPage.checkErrorMessage("Epic sadface: Username and password do not match any user in this service");
    }

    @ParameterizedTest(name = "Проверка ошибки входа: логин={0}, пароль={1}")
    @Story("Валидация полей")
    @CsvSource({
            "'', secret_sauce, Epic sadface: Username is required",
            "standard_user, '', Epic sadface: Password is required",
            "standard_user, wrong_pass, Epic sadface: Username and password do not match any user in this service"
    })
    void testInvalidLoginParametrized(String username, String password, String expectedError) {
        LoginPage loginPage = new LoginPage();

        loginPage.openPage()
                .login(username, password);

        loginPage.checkErrorMessage(expectedError);
    }
}
