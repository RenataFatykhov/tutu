import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;

import static testdata.TestData.*;

public class TutuTests extends TestBase {
    private final MainPage mainPage = new MainPage();

    @Test
    @DisplayName("Главная страница: основные элементы доступны пользователю")
    void homePageSmokeTest() {
        mainPage.openPage(HOME_PATH)
                .checkPageOpen(HOME_PATH)
                .checkHeaderVisible()
                .checkHeaderLogo(HOME_URL)
                .checkSearchFormVisible()
                .checkLoginButton(LOGIN_BUTTON_TEXT)
                .checkMetaDescription(META_DESCRIPTION)
                .checkFooterVisible();
    }

    @Test
    @DisplayName("Пассажир выбирает города и меняет направление поездки")
    void swapRouteTest() {
        mainPage.openPage(HOME_PATH)
                .selectTrains()
                .setRoute(CITY_MOSCOW, CITY_SPB)
                .swapCities()
                .checkRoute(CITY_SPB, CITY_MOSCOW);
    }

    @Test
    @DisplayName("Двойной обмен городов восстанавливает исходный маршрут")
    void swapRouteTwiceTest() {
        mainPage.openPage(HOME_PATH)
                .selectTrains()
                .setRoute(CITY_MOSCOW, CITY_SPB)
                .swapCities()
                .swapCities()
                .checkRoute(CITY_MOSCOW, CITY_SPB);
    }

    @Test
    @DisplayName("Пассажир добавляет и удаляет попутчика")
    void passengersTest() {
        mainPage.openPage(HOME_PATH)
                .selectTrains()
                .addAdult()
                .checkPassengers(TWO_PASSENGERS)
                .removeAdult()
                .checkPassengers(ONE_PASSENGER);
    }

    @Test
    @DisplayName("Выбор маршрута и поиск открывают расписание поездов")
    void trainSearchTest() {
        mainPage.openPage(HOME_PATH)
                .selectTrains()
                .setRoute(CITY_MOSCOW, CITY_SPB)
                .search()
                .checkRoute(TRAINS_ROUTE_PATH, CITY_MOSCOW, CITY_SPB);
    }

    @Test
    @DisplayName("Пользователь находит инструкцию возврата через справочную")
    void returnTicketHelpTest() {
        mainPage.openPage(HOME_PATH)
                .goToHelp(HELP_PAGE_URL)
                .checkTitle(HELP_PAGE_TITLE)
                .selectCategory(HELP_CATEGORY_TRAINS)
                .openArticle(HELP_ARTICLE_RETURN_TICKET)
                .checkArticle(HELP_ARTICLE_RETURN_TICKET);
    }

    @Test
    @DisplayName("Пользователь раскрывает полный список регионов путеводителя")
    void guideRegionsTest() {
        mainPage.openPage(HOME_PATH)
                .goToGuide(GUIDE_PAGE_URL)
                .checkTitle(GUIDE_PAGE_TITLE)
                .expandRegions(EXPAND_REGIONS_LABEL)
                .checkRegion(REGION_TATARSTAN);
    }
}
