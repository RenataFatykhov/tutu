package pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.WebDriverConditions.title;

public class GuidePage {
    private final ElementsCollection regionToggles = $$("summary[data-ti='accordion-summary']");
    private final ElementsCollection headings = $$("h3");

    @Step("Проверить заголовок путеводителя: {expected}")
    public GuidePage checkTitle(String expected) {
        webdriver().shouldHave(title(expected));
        return this;
    }

    @Step("Раскрыть все регионы: {label}")
    public GuidePage expandRegions(String label) {
        regionToggles.filterBy(text(label)).findBy(visible).click();
        return this;
    }

    @Step("Проверить доступность региона: {name}")
    public GuidePage checkRegion(String name) {
        headings.findBy(exactText(name)).shouldBe(visible);
        return this;
    }
}
