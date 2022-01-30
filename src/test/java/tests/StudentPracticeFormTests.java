package tests;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class StudentPracticeFormTests {

    @BeforeAll
    static void setup() {
        Configuration.startMaximized = true;
    }

//    @Test
    @Owner("aippolitov")
    @Feature("Main menu")
    @Story("Menu Issue")
    @DisplayName("Registration Form Page test. Simple Selenide test")
    @Severity(SeverityLevel.BLOCKER)
    @Link(name = "BaseURL", url = "https://demoqa.com")
    void successfulFillTestWithVariables() {
        String firstName = "Анна",
                lastName = "Ипполитова",
                userEmail = "aippolitov@email.com",
                gender = "Female",
                userNumber = "9001231234",
                currentAddress = "Россия, лучший город Земли, ул. Ленина, д.1",
                state = "Rajasthan",
                city = "Jaipur",
                filePath = "ApacheJmeter.ico";
        String[] aHobbies = new String[]{"Sports", "Reading"};
        String[] aSubjects = new String[]{"Computer Science", "English"};


        open("https://demoqa.com/automation-practice-form");
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $(byText(gender)).click();
        $("#userNumber").setValue(userNumber);
        //  DateOfBirth_example
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").click();
        $("option[value='1995']").click();
        $(".react-datepicker__month-select").click();
        $("option[value='1']").click();
        $(".react-datepicker__day.react-datepicker__day--025").click();
        for (String sbjTemp : aSubjects)
            $("#subjectsInput").setValue(sbjTemp).sendKeys(Keys.ENTER);
        for (String hobby : aHobbies) {
            $("#hobbiesWrapper").$(byText(hobby)).click();
        }
        $("#uploadPicture").uploadFromClasspath(filePath);
        $("#currentAddress").setValue(currentAddress);
        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();
        $("#submit").click();

        $(".modal-content").shouldHave(text(firstName), text(lastName),
                text(userEmail),
                text(gender),
                text(userNumber),
                text("25 February,1995"),
                text(String.join(", ", aSubjects)),
                text(String.join(", ", aHobbies)),
                text(currentAddress),
                text(state + " " + city)
        );
    }
}