
package com.employee.onboarding.selenium;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(BaseSeleniumTest.FailureScreenshotWatcher.class)
class EmployeeListingTest extends BaseSeleniumTest {

    @Test
    void registeredEmployeeAppearsInTable() {

        String uniqueEmail =
                "listing" + System.currentTimeMillis() + "@example.com";

        driver.findElement(By.id("name")).sendKeys("Listing Test");
        driver.findElement(By.id("email")).sendKeys(uniqueEmail);
        driver.findElement(By.id("phone")).sendKeys("9876543210");
        driver.findElement(By.id("department"))
                .sendKeys("Computer Science");
        driver.findElement(By.id("designation"))
                .sendKeys("QA Tester");
        driver.findElement(By.id("joiningDate"))
                .sendKeys(java.time.LocalDate.now().plusDays(7).toString());

        driver.findElement(
                By.cssSelector("#employeeForm button[type='submit']")
        ).click();

        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                By.id("employeeTableBody"),
                uniqueEmail
        ));

        String tableText = driver.findElement(
                By.id("employeeTableBody")
        ).getText();

        assertTrue(
                tableText.contains(uniqueEmail),
                "The registered employee's email should appear in the table."
        );

        assertTrue(
                tableText.contains("Listing Test"),
                "The registered employee's name should appear in the table."
        );
    }
}