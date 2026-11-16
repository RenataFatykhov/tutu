package pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.webdriver;
import static com.codeborne.selenide.WebDriverConditions.title;

public class HelpPage {
    private final ElementsCollection categories = $$("h2 a");
    private final ElementsCollection links = $$("a");

    @Step("Проверить заголовок справочной: {expected}")
    public HelpPage checkTitle(String expected) {
        webdriver().shouldHave(title(expected));
        return this;
    }

    @Step("Выбрать раздел справочной: {name}")
    public HelpPage selectCategory(String name) {
        categories.findBy(exactText(name)).click();
        return new HelpPage();
    }

    @Step("Открыть статью: {name}")
    public HelpArticlePage openArticle(String name) {
        links.filterBy(exactText(name)).findBy(visible).click();
        return new HelpArticlePage();
    }

}
