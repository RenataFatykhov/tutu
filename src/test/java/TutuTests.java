import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;

import static testdata.TestData.*;

public class TutuTests extends TestBase {
    private final MainPage mainPage = new MainPage();

    @Test
    @DisplayName("Главная страница: основные элементы доступны пользователю")
    void homePageSmokeTest() {
        mainPage.openPage().checkPageOpen("/")
                .checkHeaderVisible().checkHeaderLogo("https://www.tutu.ru/")
                .checkSearchFormVisible().checkLoginButton(LOGIN_BUTTON_TEXT)
                .checkMetaDescription(META_DESCRIPTION).checkFooterVisible();
    }

    @Test
    @DisplayName("Пассажир выбирает города и меняет направление поездки")
    void swapRouteTest() {
        mainPage.openPage().selectTrains()
                .setRoute("Москва", "Санкт-Петербург")
                .swapCities().checkRoute("Санкт-Петербург", "Москва");
    }

    @Test
    @DisplayName("Двойной обмен городов восстанавливает исходный маршрут")
    void swapRouteTwiceTest() {
        mainPage.openPage().selectTrains()
                .setRoute("Москва", "Санкт-Петербург")
                .swapCities().swapCities().checkRoute("Москва", "Санкт-Петербург");
    }

    @Test
    @DisplayName("Пассажир добавляет и удаляет попутчика")
    void passengersTest() {
        mainPage.openPage().selectTrains()
                .addAdult().checkPassengers("2 пассажира")
                .removeAdult().checkPassengers("1 пассажир");
    }

    @Test
    @DisplayName("Выбор маршрута и поиск открывают расписание поездов")
    void trainSearchTest() {
        mainPage.openPage().selectTrains()
                .setRoute("Москва", "Санкт-Петербург").search()
                .checkRoute("/poezda/Moskva/Sankt-Peterburg/", "Москва", "Санкт-Петербург");
    }

    @Test
    @DisplayName("Пользователь находит инструкцию возврата через справочную")
    void returnTicketHelpTest() {
        mainPage.openPage().goToHelp(HELP_PAGE_URL).checkTitle(HELP_PAGE_TITLE)
                .selectCategory("Поезда").openArticle("Как вернуть билет на поезд")
                .checkArticle("Как вернуть билет на поезд");
    }

    @Test
    @DisplayName("Пользователь раскрывает полный список регионов путеводителя")
    void guideRegionsTest() {
        mainPage.openPage().goToGuide(GUIDE_PAGE_URL)
                .checkTitle(GUIDE_PAGE_TITLE)
                .expandRegions("Все регионы России").checkRegion("Татарстан");
    }
}
