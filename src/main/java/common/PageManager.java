package common;

import pageObject.nopcommerce.PageActions.HomePageActions.CategoryPageActions;
import pageObject.nopcommerce.PageActions.HomePageActions.HomePageActions;
import pageObject.nopcommerce.PageActions.HomePageActions.RegisterPageActions;

public class PageManager {
    public static ThreadLocal<HomePageActions> homePage = new ThreadLocal<HomePageActions>();
    public static ThreadLocal<RegisterPageActions> registerPage = new ThreadLocal<RegisterPageActions>();;
    public static ThreadLocal<CategoryPageActions> categoryPage = new ThreadLocal<CategoryPageActions>();;

    public static HomePageActions getHomePage() {
        if (homePage.get() == null) {
            homePage.set(new HomePageActions());
        }
        return homePage.get();
    }

    public static RegisterPageActions getRegisterPage() {
        if (registerPage.get() == null) {
            registerPage.set(new RegisterPageActions());
        }
        return registerPage.get();
    }

    public static CategoryPageActions getCategoryPageActions() {
        if (categoryPage.get() == null) {
            categoryPage.set(new CategoryPageActions());
        }
        return categoryPage.get();
    }

}
