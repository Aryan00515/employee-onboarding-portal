
package com.employee.onboarding.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.Optional;

public abstract class BaseSeleniumTest {

    protected WebDriver driver;
    protected WebDriverWait wait;

    private static final String APP_URL =
            System.getProperty("app.url", "http://localhost:8088");

    @BeforeEach
    void setUp() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(APP_URL);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void saveFailureScreenshot(String testName) {
        if (driver == null) {
            return;
        }

        try {
            Path screenshotDirectory = Path.of("test-screenshots");
            Files.createDirectories(screenshotDirectory);

            Path screenshotPath = screenshotDirectory.resolve(
                    testName + "-" + System.currentTimeMillis() + ".png"
            );

            byte[] screenshot =
                    ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            Files.write(screenshotPath, screenshot);

            System.out.println(
                    "Failure screenshot saved: " + screenshotPath.toAbsolutePath()
            );

        } catch (IOException | RuntimeException e) {
            System.err.println("Could not save failure screenshot: " + e.getMessage());
        }
    }

    public static class FailureScreenshotWatcher implements TestWatcher {

        @Override
        public void testFailed(
                ExtensionContext context,
                Throwable cause
        ) {
            context.getTestInstance()
                    .filter(BaseSeleniumTest.class::isInstance)
                    .map(BaseSeleniumTest.class::cast)
                    .ifPresent(test ->
                            test.saveFailureScreenshot(
                                    context.getRequiredTestMethod().getName()
                            )
                    );
        }
    }
}
