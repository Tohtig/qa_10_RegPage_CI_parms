package tests;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import com.github.javafaker.Faker;
import tests.pages.StudentsRegistrationFormPage;

import java.util.Locale;

public class StudentsRegistrationFormTests extends TestBase{
    StudentsRegistrationFormPage studentsRegistrationFormPage;
    Faker faker;

    @Test
    @Owner("aippolitov")
    @Feature("Main menu")
    @Story("Menu Issue")
    @DisplayName("Registration Form Page test. Page Object pattern")
    @Severity(SeverityLevel.BLOCKER)
    @Link(name = "BaseURL", url = "https://demoqa.com/automation-practice-form")
    void successfulFillFormTest() {
        studentsRegistrationFormPage = new StudentsRegistrationFormPage();
        studentsRegistrationFormPage.openPage();
        studentsRegistrationFormPage.fillForm();
        studentsRegistrationFormPage.checkData();
    }

    @Test
    @Owner("aippolitov")
    @Feature("Main menu")
    @Story("Menu Issue")
    @DisplayName("Registration Form Page test. Use com.github.javafaker.Faker")
    @Severity(SeverityLevel.BLOCKER)
    @Link(name = "BaseURL", url = "https://demoqa.com/automation-practice-form")
    void successfulFillFormTestWithFacker() {
        faker = new Faker(new Locale("en"));
        studentsRegistrationFormPage = new StudentsRegistrationFormPage(faker);
        studentsRegistrationFormPage.openPage();
        studentsRegistrationFormPage.fillForm();
        studentsRegistrationFormPage.checkData();
    }
}
