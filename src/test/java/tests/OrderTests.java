package tests;

import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;

@Feature("Оформление заказа")
public class OrderTests extends BaseTests {

    @Test
    @Story("Покупка товара")
    @DisplayName("Полный цикл покупки рюкзака Sauce Labs Backpack")
    void testFullOrderFlow() {
        LoginPage loginPage = new LoginPage();
        InventoryPage inventoryPage = new InventoryPage();
        CartPage cartPage = new CartPage();
        CheckoutPage checkoutPage = new CheckoutPage();

        loginPage.openPage()
                .login("standard_user", "secret_sauce");

        inventoryPage.checkPageOpened()
                .addBackpackToCart()
                .checkCartBadgeCount(1);
        inventoryPage.openCart();

        cartPage.checkProductInCart("Sauce Labs Backpack")
                .proceedToCheckout();

        checkoutPage.fillCustomerInfo("Roman", "Zadoya", "123456")
                .finishOrder()
                .checkOrderSuccess();
    }
}
