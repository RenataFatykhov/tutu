package pages.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.TrainsPage;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class TrainSearchForm {
    private final SelenideElement form = $("[data-ti='train-search-form']");
    private final ElementsCollection cities = form.$$("input[data-ti='input']");
    private final ElementsCollection suggestions = $$("[data-ti='suggest-item-name']");
    private final ElementsCollection swapButtons = form.$$("[data-ti='swapButton']");
    private final SelenideElement passengers = form.$("[data-ti='passengers_input']");
    private final SelenideElement addPassenger = $("button[aria-label='Добавить пассажира']");
    private final SelenideElement removePassenger = $("button[aria-label='Убрать пассажира']");

    @Step("Выбрать маршрут: {from} — {to}")
    public TrainSearchForm setRoute(String from, String to) {
        cities.get(0).shouldBe(visible).setValue(from);
        suggestions.findBy(exactText(from)).shouldBe(visible).click();
        cities.get(1).setValue(to);
        suggestions.findBy(exactText(to)).shouldBe(visible).click();
        return this;
    }

    @Step("Поменять города местами")
    public TrainSearchForm swapCities() {
        swapButtons.findBy(visible).click();
        return this;
    }

    @Step("Проверить выбранный маршрут: {from} — {to}")
    public TrainSearchForm checkRoute(String from, String to) {
        cities.get(0).shouldHave(value(from));
        cities.get(1).shouldHave(value(to));
        return this;
    }

    @Step("Добавить взрослого пассажира")
    public TrainSearchForm addAdult() {
        passengers.click();
        addPassenger.shouldBe(enabled).click();
        return this;
    }

    @Step("Убрать взрослого пассажира")
    public TrainSearchForm removeAdult() {
        removePassenger.shouldBe(enabled).click();
        return this;
    }

    @Step("Проверить число пассажиров: {expected}")
    public TrainSearchForm checkPassengers(String expected) {
        passengers.shouldHave(value(expected));
        return this;
    }

    @Step("Найти поезда без указания даты")
    public TrainsPage search() {
        form.$("[data-ti='submit-button']").click();
        // При включённом предложении отелей результаты открываются в новой вкладке.
        if (com.codeborne.selenide.WebDriverRunner.getWebDriver().getWindowHandles().size() > 1) {
            switchTo().window(1);
        }
        return new TrainsPage();
    }
}
