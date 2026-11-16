package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.components.TrainSearchForm;

import java.util.regex.Pattern;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;

public class MainPage {
    private final ElementsCollection headers = $$("[data-ti='header']");
    private final ElementsCollection headerLogos = $$("[data-ti='header-logo']");
    private final SelenideElement searchForm = $("[data-ti='search-form']");
    private final ElementsCollection loginButtons = $$("[data-ti='login-button']");
    private final SelenideElement metaDescription = $("meta[name='description']");
    private final ElementsCollection links = $$("a");
    private final SelenideElement trainsTab = $("[data-ti='tab-unified-train']");
    private final SelenideElement footer = $("[data-ti='footer']");

    @Step("Открыть страницу")
    public MainPage openPage(String path) {
        open(path);
        return this;
    }

    @Step("Перейти в справочную: {path}")
    public HelpPage goToHelp(String path) {
        SelenideElement link = links.filterBy(attribute("href", Configuration.baseUrl + path)).findBy(visible);
        boolean newWindow = "_blank".equals(link.getAttribute("target"));
        link.click();
        if (newWindow) switchTo().window(1);
        return new HelpPage();
    }

    @Step("Перейти в путеводитель: {path}")
    public GuidePage goToGuide(String path) {
        SelenideElement link = links.filterBy(attribute("href", Configuration.baseUrl + path)).findBy(visible);
        boolean newWindow = "_blank".equals(link.getAttribute("target"));
        link.click();
        if (newWindow) switchTo().window(1);
        return new GuidePage();
    }

    @Step("Выбрать поиск поездов")
    public TrainSearchForm selectTrains() {
        trainsTab.shouldBe(visible).click();
        return new TrainSearchForm();
    }

    @Step("Проверка видимости шапки сайта")
    public MainPage checkHeaderVisible() {
        headers.findBy(visible).shouldBe(visible);
        return this;
    }

    @Step("Проверка meta description")
    public MainPage checkMetaDescription(String expectedText) {
        metaDescription
                .shouldHave(attributeMatching("content", "(?s).*" + Pattern.quote(expectedText) + ".*"));
        return this;
    }

    @Step("Проверка логотипа в шапке")
    public MainPage checkHeaderLogo(String expectedUrl) {
        headerLogos.findBy(visible)
                .shouldHave(attribute("href", expectedUrl));
        return this;
    }

    @Step("Проверка формы поиска")
    public MainPage checkSearchFormVisible() {
        searchForm.shouldBe(visible);
        return this;
    }

    @Step("Проверка кнопки входа")
    public MainPage checkLoginButton(String expectedText) {
        loginButtons.findBy(visible).shouldHave(text(expectedText));
        return this;
    }

    @Step("Проверка видимости футера")
    public MainPage checkFooterVisible() {
        footer.scrollTo().shouldBe(visible);
        return this;
    }

    @Step("Проверка текущего URL")
    public MainPage checkPageOpen(String expectedPath) {
        String expectedUrl = Configuration.baseUrl.replaceAll("/+$", "") + expectedPath;
        webdriver().shouldHave(url(expectedUrl));
        return this;
    }

}
