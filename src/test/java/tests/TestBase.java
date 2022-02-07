package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;
import tests.helpers.Attach;

public class TestBase {

    @BeforeAll
    @Step("Конфигурируем браузер и удаленный запуск")
    static void beforeAllMethod() {
        /*Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub"; */

        String login = System.getProperty("login");
        String password = System.getProperty("password");
        String url = System.getProperty("url");
        String browser = System.getProperty("browser");
        String browserVersion = System.getProperty("version");
        String browserSize = System.getProperty("browserSize");
        String remoteUrl = "https://" + login + ":" + password + "@" + url;

        Configuration.browser = browser;
        Configuration.browserVersion = browserVersion; // available versions: chrome - 90, 91 opera - 76, 77 firefox - 88, 89
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = browserSize;
        Configuration.remote = remoteUrl;
//        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
        Selenide.closeWebDriver();
    }
}
