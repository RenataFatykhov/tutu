import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.TutuMainPage;

import static testdata.TestData.*;

public class TutuTests extends TestBase {

    TutuMainPage mainPage = new TutuMainPage();

    @Test
    @DisplayName("Открывается главная страница")
    public void openHomePageTest() {
        mainPage
                .openPage()
                .checkPageOpen("/");
    }

    @Test
    @DisplayName("На главной странице видна шапка сайта")
    public void headerVisibleTest() {
        mainPage
                .openPage()
                .checkHeaderVisible();

    }

    @Test
    @DisplayName("На главной странице есть meta description")
    public void metaDescriptionTest() {
        mainPage
                .openPage()
                .checkMetaDescription(META_DESCRIPTION);

    }

    @Test
    @DisplayName("На главной странице виден логотип")
    public void headerLogoTest() {

        mainPage
                .openPage()
                .checkHeaderLogo();

    }

    @Test
    @DisplayName("На главной странице видна форма поиска")
    public void searchFormVisibleTest() {
        mainPage
                .openPage()
                .checkSearchFormVisible();

    }

    @Test
    @DisplayName("На главной странице есть кнопка Войти")
    public void loginButtonVisibleTest() {
        mainPage
                .openPage()
                .checkLoginButton();

    }

    @Test
    @DisplayName("На главной странице виден футер")
    public void footerVisibleTest() {
        mainPage
                .openPage()
                .checkFooterVisible();

    }

    @Test
    @DisplayName("Открывается страница справочной")
    public void openHelpPageTest() {
        mainPage
                .openHelpPage()
                .checkPageOpen(HELP_PAGE_URL)
                .checkTitle(HELP_PAGE_TITLE);

    }

    @Test
    @DisplayName("Открывается страница путеводителя")
    public void openGuidePageTest() {
        mainPage
                .openGuidePage()
                .checkPageOpen(GUIDE_PAGE_URL)
                .checkTitle(GUIDE_PAGE_TITLE);

    }

    @Test
    @DisplayName("Открывается страница ЖД билетов")
    public void openTrainsPageTest() {
        mainPage
                .openTrainsPage()
                .checkPageOpen(TRAINS_PAGE_URL)
                .checkTitle(TRAINS_PAGE_TITLE);

    }
}
