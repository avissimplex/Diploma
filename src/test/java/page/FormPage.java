package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class FormPage {
    private final SelenideElement title = $("[class='heading heading_size_m heading_theme_alfa-on-white']");
    private final SelenideElement cardNumberField = $$("[class=input__control]").get(0);
    private final SelenideElement monthField = $("[class='input-group__input-case'] input");
    private final SelenideElement yearField = $("[class='input-group__input-case'] [class='input input_type_text input_view_default input_size_m input_width_available input_has-label input_theme_alfa-on-white'] input");
    private final SelenideElement ownerInputField = $("[class='input-group__input-case'] [class='input input_type_text input_view_default input_size_m input_width_available input_has-label input_theme_alfa-on-white'] input"); //$$("[class=input__control]").get(3);
    private final SelenideElement CvcInputField = $$("[class=input__control]").get(4);
    private final SelenideElement continueButton = $(byText("Продолжить"));
    private final SelenideElement notificationAccept = $("[class='notification notification_visible notification_status_ok notification_has-closer notification_stick-to_right notification_theme_alfa-on-white']");
    private final SelenideElement notificationError = $("[class='notification notification_visible notification_status_error notification_has-closer notification_stick-to_right notification_theme_alfa-on-white']");
    private final SelenideElement dateExpiredNotification = $(byText("Истёк срок действия карты"));
    private final SelenideElement dateInvalidNotification = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement redNotification = $(byText("Неверный формат"));

    private final SelenideElement placeholderOwner = $("[placeholder='Ivanov Ivan']");
    private final SelenideElement placeholdercvcCvvField = $("[placeholder='999']");


    public FormPage fillForm(String cardNumber, String month, String year, String owner, String cvc) {
        cardNumberField.setValue(cardNumber);
        monthField.setValue(month);
        yearField.setValue(year);
        ownerInputField.setValue(owner);
        placeholdercvcCvvField.setValue(cvc);
        continueButton.click();
        continueButton.shouldBe(Condition.visible, Duration.ofSeconds(10));
        return new FormPage();
    }

    public FormPage fillValidCard() {
        return new FormPage().fillForm(
                DataGenerator.generateValidCardNumber(),
                DataGenerator.generateValidMonth(),
                DataGenerator.generateValidYear(),
                DataGenerator.generateValidOwner(),
                DataGenerator.generateValidCvcCvv()
        );
    }

    public FormPage fillDeclinedCard() {
        return new FormPage().fillForm(
                DataGenerator.generateDeclinedCardNumber(),
                DataGenerator.generateValidMonth(),
                DataGenerator.generateValidYear(),
                DataGenerator.generateValidOwner(),
                DataGenerator.generateValidCvcCvv()
        );
    }

    public FormPage fillUnknownCardNumber() {
        return new FormPage().fillForm(
                DataGenerator.generateUnknownCardNumber(),
                DataGenerator.generateValidMonth(),
                DataGenerator.generateValidYear(),
                DataGenerator.generateValidOwner(),
                DataGenerator.generateValidCvcCvv()
        );
    }

    public FormPage unfillForm() {
        return new FormPage().fillForm(
                "",
                "",
                "",
                "",
                ""
        );
    }

    public FormPage fillExpiredYear() {
        return new FormPage().fillForm(
                DataGenerator.generateValidCardNumber(),
                DataGenerator.generateValidMonth(),
                DataGenerator.generateExpiredYear(),
                DataGenerator.generateValidOwner(),
                DataGenerator.generateValidCvcCvv()
        );
    }

    public FormPage fillExpiredMonth() {
        return new FormPage().fillForm(
                DataGenerator.generateValidCardNumber(),
                DataGenerator.generateExpiredMonth(),
                DataGenerator.generateValidYear(),
                DataGenerator.generateValidOwner(),
                DataGenerator.generateValidCvcCvv()
        );
    }

    public FormPage fillInvalidMonth() {
        return new FormPage().fillForm(
                DataGenerator.generateValidCardNumber(),
                DataGenerator.generateInvalidMonth(),
                DataGenerator.generateValidYear(),
                DataGenerator.generateValidOwner(),
                DataGenerator.generateValidCvcCvv()
        );
    }

    public FormPage fillInvalidYear() {
        return new FormPage().fillForm(
                DataGenerator.generateValidCardNumber(),
                DataGenerator.generateValidMonth(),
                DataGenerator.generateInvalidYear(),
                DataGenerator.generateValidOwner(),
                DataGenerator.generateValidCvcCvv()
        );
    }

    public FormPage fillInvalidOwner() {
        return new FormPage().fillForm(
                DataGenerator.generateValidCardNumber(),
                DataGenerator.generateValidMonth(),
                DataGenerator.generateValidYear(),
                DataGenerator.generateInvalidOwner(),
                DataGenerator.generateValidCvcCvv()
        );
    }

    public String getFormTitle() {
        return title.getText();
    }

    public String getNotificationAccept() {
        notificationAccept.shouldBe(Condition.visible, Duration.ofSeconds(10));
        return notificationAccept.getText();
    }

    public String getNotificationOwnerInvalid() {
        return redNotification.getText();
    }

    public String getNotificationError() {
        notificationError.shouldBe(Condition.visible, Duration.ofSeconds(15));
        return notificationError.getText();
    }

    public String getDateExpiredNotification() {
        return dateExpiredNotification.getText();
    }

    public String getRedNotificationAccept() {
        return redNotification.getText();
    }

    public void getOwnerInputField() {
        ownerInputField.click();
        placeholderOwner.shouldBe(Condition.visible);
    }

    public String getMonthExpiredNotificationAccept() {
        return dateExpiredNotification.getText();
    }

    public void getCvcInputField() {
        CvcInputField.click();
        placeholdercvcCvvField.shouldBe(Condition.visible);

    }

    public String getDateInvalidNotification() {
        return dateInvalidNotification.getText();
    }

}


