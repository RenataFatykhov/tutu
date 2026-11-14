package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.WebDriverConditions.urlContaining;

public class TrainsPage {
    private final SelenideElement heading = $("h1");
    private final SelenideElement timetable = $("[data-ti='train-cards-table']");

    @Step("Проверить расписание маршрута {from} — {to}")
    public TrainsPage checkRoute(String path, String from, String to) {
        webdriver().shouldHave(urlContaining(path));
        heading.shouldHave(text(from), text(to));
        timetable.shouldBe(visible).$$("tbody tr").shouldHave(sizeGreaterThan(0));
        return this;
    }
}
