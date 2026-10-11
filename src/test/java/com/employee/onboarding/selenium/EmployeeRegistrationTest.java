
package com.employee.onboarding.selenium;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(BaseSeleniumTest.FailureScreenshotWatcher.class)
class EmployeeRegistrationTest extends BaseSeleniumTest {

    @Test
    void employeeCanRegisterSuccessfully() {

        String uniqueEmail =
                "selenium" + System.currentTimeMillis() + "@example.com";

        driver.findElement(By.id("name")).sendKeys("Selenium Test");
        driver.findElement(By.id("email")).sendKeys(uniqueEmail);
        driver.findElement(By.id("phone")).sendKeys("9876543210");

        driver.findElement(By.id("department"))
                .sendKeys("Computer Science");

        driver.findElement(By.id("designation"))
                .sendKeys("Software Tester");

        driver.findElement(By.id("joiningDate"))
                .sendKeys(LocalDate.now().plusDays(7).toString());

        driver.findElement(
                By.cssSelector("#employeeForm button[type='submit']")
        ).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.id("message"),
                "Employee registered successfully!"
        ));

        String message = driver.findElement(By.id("message")).getText();

        assertTrue(
                message.contains("Employee registered successfully!"),
                "The registration success message should be displayed."
        );

        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.id("employeeTableBody"),
                uniqueEmail
        ));

        assertTrue(
                driver.findElement(By.id("employeeTableBody"))
                        .getText().contains(uniqueEmail),
                "The newly registered employee should appear in the table."
        );
    }
}
