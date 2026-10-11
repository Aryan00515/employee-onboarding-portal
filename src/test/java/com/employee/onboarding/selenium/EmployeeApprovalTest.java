
package com.employee.onboarding.selenium;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(BaseSeleniumTest.FailureScreenshotWatcher.class)
class EmployeeApprovalTest extends BaseSeleniumTest {

    @Test
    void employeeCanBeApproved() {

        String uniqueEmail =
                "approval" + System.currentTimeMillis() + "@example.com";

        driver.findElement(By.id("name")).sendKeys("Approval Test");
        driver.findElement(By.id("email")).sendKeys(uniqueEmail);
        driver.findElement(By.id("phone")).sendKeys("9876543210");
        driver.findElement(By.id("department"))
                .sendKeys("Computer Science");
        driver.findElement(By.id("designation"))
                .sendKeys("QA Engineer");
        driver.findElement(By.id("joiningDate"))
                .sendKeys(LocalDate.now().plusDays(7).toString());

        driver.findElement(
                By.cssSelector("#employeeForm button[type='submit']")
        ).click();

        By employeeRow = By.xpath(
                "//tr[td[contains(normalize-space(), '" + uniqueEmail + "')]]"
        );

        wait.until(ExpectedConditions.visibilityOfElementLocated(employeeRow));

        WebElement row = driver.findElement(employeeRow);

        assertTrue(
                row.getText().contains("PENDING"),
                "New employee should initially have PENDING status."
        );

        row.findElement(By.cssSelector(".approve-btn")).click();

        wait.until(driver -> {
            WebElement updatedRow = driver.findElement(employeeRow);
            return updatedRow.getText().contains("APPROVED");
        });

        String updatedRowText = driver.findElement(employeeRow).getText();

        assertTrue(
                updatedRowText.contains("APPROVED"),
                "Employee status should change to APPROVED."
        );
    }
}
