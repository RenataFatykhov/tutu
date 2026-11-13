package pages;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.regex.Pattern;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverConditions.url;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static testdata.TestData.*;

public class TutuMainPage {
    private final ElementsCollection headers = $$("[data-ti='header']");
    private final ElementsCollection headerLogos = $$("[data-ti='header-logo']");
    private final SelenideElement searchForm = $("[data-ti='search-form']");
    private final ElementsCollection loginButtons = $$("[data-ti='login-button']");
    private final SelenideElement footer = $("[data-ti='footer']");

    @Step("Открыть страницу")
    public TutuMainPage openPage() {
        open("/");
        return this;
    }

    @Step("Открыть страницу справочной")
    public TutuMainPage openHelpPage() {
        open(HELP_PAGE_URL);
        return this;
    }

    @Step("Открыть страницу путеводителя")
    public TutuMainPage openGuidePage() {
        open(GUIDE_PAGE_URL);
        return this;
    }

    @Step("Открыть страницу ЖД билетов")
    public TutuMainPage openTrainsPage() {
        open(TRAINS_PAGE_URL);
        return this;
    }

    @Step("Проверка видимости шапки сайта")
    public TutuMainPage checkHeaderVisible() {
        headers.findBy(visible).shouldBe(visible);
        return this;
    }

    @Step("Проверка заголовка страницы")
    public TutuMainPage checkTitle(String expectedText) {
        new WebDriverWait(getWebDriver(), Duration.ofMillis(Configuration.timeout))
                .until(ExpectedConditions.titleContains(expectedText));
        return this;
    }

    @Step("Проверка meta description")
    public TutuMainPage checkMetaDescription(String expectedText) {
        $("meta[name='description']")
                .shouldHave(attributeMatching("content", "(?s).*" + Pattern.quote(expectedText) + ".*"));
        return this;
    }

    @Step("Проверка логотипа в шапке")
    public TutuMainPage checkHeaderLogo() {
        headerLogos.findBy(visible)
                .shouldHave(attribute("href", "https://www.tutu.ru/"));
        return this;
    }

    @Step("Проверка формы поиска")
    public TutuMainPage checkSearchFormVisible() {
        searchForm.shouldBe(visible);
        return this;
    }

    @Step("Проверка кнопки входа")
    public TutuMainPage checkLoginButton() {
        loginButtons.findBy(visible).shouldHave(text(LOGIN_BUTTON_TEXT));
        return this;
    }

    @Step("Проверка видимости футера")
    public TutuMainPage checkFooterVisible() {
        footer.scrollTo().shouldBe(visible);
        return this;
    }

    @Step("Проверка текущего URL")
    public TutuMainPage checkPageOpen(String expectedPath) {
        String expectedUrl = Configuration.baseUrl.replaceAll("/+$", "") + expectedPath;
        webdriver().shouldHave(url(expectedUrl));
        return this;
    }

}
