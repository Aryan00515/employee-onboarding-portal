
# Week 9: Selenium Test Design and Local Execution

## 1. Objective

To automate critical user journeys in the Employee Onboarding Portal using Selenium WebDriver, JUnit 5, and Maven. The tests verify employee registration, form validation, employee listing, and approval.

## 2. Test Environment

- Operating system: macOS
- Java: 21
- Application: Spring Boot
- Frontend: HTML, CSS, JavaScript
- Database: H2 in-memory database
- Automation: Selenium WebDriver
- Test framework: JUnit 5
- Build and execution: Maven
- Browser: Google Chrome
- Application URL: http://localhost:8088
- Reverse proxy: Nginx

## 3. Test Cases

| ID | Test case | Test data | Expected result |
|---|---|---|---|
| TC-01 | Employee registration | Valid name, unique email, 10-digit phone, department, designation, future joining date | Success message appears and employee is listed |
| TC-02 | Form validation | Phone number: 123 | Error message: "Phone number must contain exactly 10 digits." |
| TC-03 | Employee listing | Register an employee with a unique email | Employee name and email appear in the employee table |
| TC-04 | Employee approval | Register a new employee and approve it | Status changes from PENDING to APPROVED |

## 4. Test Data

The tests generate unique email addresses using the current timestamp to reduce duplicate-email conflicts.

Example:
- Name: Selenium Test
- Email: Generated unique example.com email
- Phone: 9876543210
- Department: Computer Science
- Designation: Software Tester
- Joining date: Seven days after the test date

## 5. Execution Procedure

1. Start the Spring Boot application on port 8081.
2. Start Nginx on port 8088.
3. Verify that http://localhost:8088 is accessible.
4. Execute the automated tests using:

   `./mvnw test`

5. Review the Maven Surefire reports in `target/surefire-reports/`.

## 6. Results

Full-suite execution completed successfully.

- Total tests: 5
- Failures: 0
- Errors: 0
- Skipped: 0
- Build status: BUILD SUCCESS

The five tests include four Selenium tests and one existing Spring Boot application test.

## 7. Failure Screenshot Mechanism

The shared Selenium test base includes a JUnit TestWatcher that captures a browser screenshot when a Selenium test fails.

Screenshots are written to the `test-screenshots/` directory using the test method name and a timestamp.

The screenshot mechanism is intended to assist with debugging failed browser interactions. No failure screenshot was required during the successful test run.

## 8. Test Report Location

Maven Surefire generates test reports under:

- `target/surefire-reports/`
- XML reports: `TEST-*.xml`
- Text reports: `*.txt`

## 9. Conclusion

Four critical Employee Onboarding Portal user journeys were automated and passed individually. The complete Maven test suite also passed with five tests, zero failures, zero errors, and zero skipped tests.