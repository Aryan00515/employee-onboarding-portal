
package com.employee.onboarding.selenium;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(BaseSeleniumTest.FailureScreenshotWatcher.class)
class FormValidationTest extends BaseSeleniumTest {

    @Test
    void invalidPhoneNumberShowsValidationMessage() {

        driver.findElement(By.id("name")).sendKeys("Validation Test");
        driver.findElement(By.id("email"))
                .sendKeys("validation" + System.currentTimeMillis() + "@example.com");
        driver.findElement(By.id("phone")).sendKeys("123");

        driver.findElement(By.id("department"))
                .sendKeys("Computer Science");

        driver.findElement(By.id("designation"))
                .sendKeys("Software Tester");

        driver.findElement(By.id("joiningDate"))
                .sendKeys(java.time.LocalDate.now().plusDays(7).toString());

        driver.findElement(
                By.cssSelector("#employeeForm button[type='submit']")
        ).click();

        String message = driver.findElement(By.id("message")).getText();

        assertEquals(
                "Phone number must contain exactly 10 digits.",
                message,
                "Invalid phone numbers should be rejected."
        );
    }
}
