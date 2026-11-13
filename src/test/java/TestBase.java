import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import helpers.Attach;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

import static com.codeborne.selenide.Selenide.closeWebDriver;

public class TestBase {
    @BeforeAll
    static void setUp() {

        Configuration.browser = System.getProperty("browser", "chrome");
        Configuration.browserVersion = System.getProperty("browserVersion", "");
        Configuration.browserSize = System.getProperty("browserSize", "1920x1080");
        Configuration.baseUrl = System.getProperty("baseUrl", "https://www.tutu.ru").replaceAll("/+$", "");
        Configuration.pageLoadStrategy = "eager";
        Configuration.remote = System.getProperty("remote");
        Configuration.headless = Boolean.parseBoolean(System.getProperty("headless", "false"));

        DesiredCapabilities capabilities = new DesiredCapabilities();
        if (Configuration.remote != null && !Configuration.remote.isBlank()) {
            capabilities.setCapability("selenoid:options", Map.of(
                    "enableVNC", true, "enableVideo", true));
        }
        Configuration.browserCapabilities = capabilities;

    }

    @BeforeEach
    void setUpAllure() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterEach
    void tearDown() {
        if (!WebDriverRunner.hasWebDriverStarted()) return;
        try {
            Attach.screenshotAs("Last screenshot");
            Attach.pageSource();
            try {
                Attach.browserConsoleLogs();
            } catch (RuntimeException e) {
                Attach.attachAsText("Browser logs unavailable", e.getClass().getSimpleName());
            }
            if (Configuration.remote != null && !Configuration.remote.isBlank()) {
                Attach.addVideo();
            }
        } finally {
            closeWebDriver();
        }
    }
}
