package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.title;

public class HelpArticlePage {
    private final SelenideElement heading = $("h1");

    @Step("Проверить открытую статью: {expected}")
    public HelpArticlePage checkArticle(String expected) {
        heading.shouldHave(text(expected));
        webdriver().shouldHave(title(expected));
        return this;
    }
}
