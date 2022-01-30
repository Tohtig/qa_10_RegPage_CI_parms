package tests.pages;

import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import com.github.javafaker.Faker;

public class StudentsRegistrationFormPage {
    private final String firstName,
            lastName,
            userEmail,
            gender,
            userNumber,
            monthOfBirth,
            yearOfBirth,
            dayOfBirth,
            dayOfWeekOfBirth,
            currentAddress,
            state,
            city,
            filePath;
    private final String[] aHobbies, aSubjects;

    public StudentsRegistrationFormPage() {
        this.firstName = "Анна";
        this.lastName = "Ипполитова";
        this.userEmail = "aippolitov@email.com";
        this.gender = "Female";
        this.userNumber = "9001231234";
        this.monthOfBirth = "May";
        this.yearOfBirth = "1988";
        this.dayOfBirth = "10";
        this.dayOfWeekOfBirth = "Tuesday";
        this.currentAddress = "Россия, лучший город Земли, ул. Ленина, д.1";
        this.state = "Rajasthan";
        this.city = "Jaipur";
        this.filePath = "ApacheJmeter.ico";
        this.aHobbies = new String[]{"Sports", "Reading"};
        this.aSubjects = new String[]{"Computer Science", "English"};
    }

    public StudentsRegistrationFormPage(Faker faker) {
        this.firstName = faker.name().firstName();
        this.lastName = faker.name().lastName();
        this.userEmail = faker.internet().emailAddress();
        this.gender = faker.demographic().sex();
        this.userNumber = faker.number().digits(10);
        this.monthOfBirth = "May";
        this.yearOfBirth = "1988";
        this.dayOfBirth = "10";
        this.dayOfWeekOfBirth = "Tuesday";
        this.currentAddress = faker.address().fullAddress();
        this.state = "Rajasthan";
        this.city = "Jaipur";
        this.filePath = "ApacheJmeter.ico";
        this.aHobbies = new String[]{"Sports", "Reading"};
        this.aSubjects = new String[]{"Computer Science", "English"};
    }

    public void openPage() {
        open("https://demoqa.com/automation-practice-form");
    }

    public void fillForm() {
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $(byText(gender)).click();
        $("#userNumber").setValue(userNumber);
        //  DateOfBirth_example
        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(String.format("[aria-label='Choose %s, %s %sth, %s']",
                dayOfWeekOfBirth, monthOfBirth, dayOfBirth, yearOfBirth)).click();
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
    }

    public void checkData() {
        $(".modal-content").shouldHave(text(firstName), text(lastName),
                text(userEmail),
                text(gender),
                text(userNumber),
                text(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth),
                text(String.join(", ", aSubjects)),
                text(String.join(", ", aHobbies)),
                text(currentAddress),
                text(state + " " + city)
        );
    }
}
