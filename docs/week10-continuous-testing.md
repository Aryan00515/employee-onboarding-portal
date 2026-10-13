# Week 10: Continuous Testing in Jenkins

## 1. Objective

To integrate automated Selenium tests into the Jenkins CI/CD pipeline, publish test reports, and prevent packaging and deployment when tests fail.

## 2. Tools and Technologies

* Jenkins LTS
* Java 21
* Maven
* Selenium WebDriver
* JUnit 5
* Spring Boot
* Git and GitHub

## 3. Pipeline Implementation

The Jenkins pipeline consists of the following stages:

1. Checkout source code from GitHub.
2. Build the application.
3. Run automated tests using `./mvnw test`.
4. Publish JUnit test reports.
5. Package the application after successful testing.
6. Deploy the application after all previous stages succeed.

## 4. Deliberately Introduced Defect

An incorrect expected phone-validation message was temporarily introduced in `FormValidationTest.java`.

Jenkins Build #6 detected the incorrect assertion and reported one test failure. This demonstrated that the pipeline detects test failures and prevents subsequent stages from proceeding.

## 5. Defect Correction

The expected validation message was restored to:

`Phone number must contain exactly 10 digits.`

The Selenium approval test also encountered a `StaleElementReferenceException` when the employee table refreshed. The test was updated to re-locate the employee row during polling and handle stale-element exceptions.

## 6. Testing and Verification

* Local Maven tests passed after correcting the issues.
* Jenkins Build #8 exposed the stale-element issue.
* The stale-element correction was committed and pushed as commit `87299cd`.
* A subsequent Jenkins build completed successfully.

## 7. Conclusion

Continuous testing was integrated into the Employee Onboarding Portal's Jenkins pipeline. The pipeline demonstrated failure detection, test-report publication, and prevention of subsequent stages when tests failed. After correcting the test issues, the local tests and Jenkins pipeline completed successfully.
