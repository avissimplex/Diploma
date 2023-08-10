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
    private final SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $("[class='input-group__input-case'] input");
    private final SelenideElement yearField = $("[class='input-group__input-case'] [class='input input_type_text input_view_default input_size_m input_width_available input_has-label input_theme_alfa-on-white'] input");
    private final SelenideElement ownerField = $("[class='input-group__input-case'] [class='input input_type_text input_view_default input_size_m input_width_available input_has-label input_theme_alfa-on-white'] input");
    private final SelenideElement cvcCvvField = $("[placeholder='999']");
    private final SelenideElement ownerInputField = $$("[class=input__control]").get(3);
    private final SelenideElement CvcInputField = $$("[class=input__control]").get(4);
    private final SelenideElement continueButton = $(byText("Продолжить"));
    private final SelenideElement notificationOwnerInvalid = $(byText("Неверный формат"));
    private final SelenideElement notificationAccept = $("[class='notification notification_visible notification_status_ok notification_has-closer notification_stick-to_right notification_theme_alfa-on-white']");
    private final SelenideElement notificationError = $("[class='notification notification_visible notification_status_error notification_has-closer notification_stick-to_right notification_theme_alfa-on-white']");
    private final SelenideElement dateExpiredNotification = $(byText("Истёк срок действия карты"));
    private final SelenideElement dateInvalidNotification = $(byText("Неверно указан срок действия карты"));
    private final SelenideElement redNotification = $(byText("Неверный формат"));
    private final SelenideElement placeholderOwner = $("[placeholder='Ivanov Ivan']");
    private final SelenideElement placeholderCvc = $("[placeholder='999']");


    public void getOwnerInputField() {
        ownerInputField.click();
        placeholderOwner.shouldBe(Condition.visible);
    }

    public String getMonthExpiredNotificationAccept() {
        return dateExpiredNotification.getText();
    }


    public void getCvcInputField() {
        CvcInputField.click();
        placeholderCvc.shouldBe(Condition.visible);

    }


    public String getDateInvalidNotification() {
        return dateInvalidNotification.getText();
    }

    public FormPage fillValidCard() {
        cardNumberField.setValue(DataGenerator.generateValidCardNumber());
        monthField.setValue(DataGenerator.generateValidMonth());
        yearField.setValue(DataGenerator.generateValidYear());
        ownerField.setValue(DataGenerator.generateValidOwner());
        cvcCvvField.setValue(DataGenerator.generateValidCvcCvv());
        continueButton.click();
        continueButton.shouldBe(Condition.visible, Duration.ofSeconds(10));
        return new FormPage();
    }

    public FormPage fillDeclinedCard() {
        cardNumberField.setValue(DataGenerator.generateDeclinedCardNumber());
        monthField.setValue(DataGenerator.generateValidMonth());
        yearField.setValue(DataGenerator.generateValidYear());
        ownerField.setValue(DataGenerator.generateValidOwner());
        cvcCvvField.setValue(DataGenerator.generateValidCvcCvv());
        continueButton.click();
        continueButton.shouldBe(Condition.visible, Duration.ofSeconds(10));
        return new FormPage();
    }

    public FormPage fillUnknownCardNumber() {
        cardNumberField.setValue(DataGenerator.generateUnknownCardNumber());
        monthField.setValue(DataGenerator.generateValidMonth());
        yearField.setValue(DataGenerator.generateValidYear());
        ownerField.setValue(DataGenerator.generateValidOwner());
        cvcCvvField.setValue(DataGenerator.generateValidCvcCvv());
        continueButton.click();
        continueButton.shouldBe(Condition.visible, Duration.ofSeconds(10));
        return new FormPage();
    }

    public FormPage unfillForm() {
        continueButton.click();
        continueButton.shouldBe(Condition.visible, Duration.ofSeconds(10));
        return new FormPage();
    }

    public FormPage fillExpiredYear() {
        cardNumberField.setValue(DataGenerator.generateValidCardNumber());
        monthField.setValue(DataGenerator.generateValidMonth());
        yearField.setValue(DataGenerator.generateExpiredYear());
        ownerField.setValue(DataGenerator.generateValidOwner());
        cvcCvvField.setValue(DataGenerator.generateValidCvcCvv());
        continueButton.click();
        continueButton.shouldBe(Condition.visible, Duration.ofSeconds(10));
        return new FormPage();
    }

    public FormPage fillExpiredMonth() {
        cardNumberField.setValue(DataGenerator.generateValidCardNumber());
        monthField.setValue(DataGenerator.generateExpiredMonth());
        yearField.setValue(DataGenerator.generateValidYear());
        ownerField.setValue(DataGenerator.generateValidOwner());
        cvcCvvField.setValue(DataGenerator.generateValidCvcCvv());
        continueButton.click();
        continueButton.shouldBe(Condition.visible, Duration.ofSeconds(10));
        return new FormPage();
    }

    public FormPage fillInvalidMonth() {
        cardNumberField.setValue(DataGenerator.generateValidCardNumber());
        monthField.setValue(DataGenerator.generateInvalidMonth());
        yearField.setValue(DataGenerator.generateValidYear());
        ownerField.setValue(DataGenerator.generateValidOwner());
        cvcCvvField.setValue(DataGenerator.generateValidCvcCvv());
        continueButton.click();
        continueButton.shouldBe(Condition.visible, Duration.ofSeconds(10));
        return new FormPage();
    }

    public FormPage fillInvalidYear() {
        cardNumberField.setValue(DataGenerator.generateValidCardNumber());
        monthField.setValue(DataGenerator.generateValidMonth());
        yearField.setValue(DataGenerator.generateInvalidYear());
        ownerField.setValue(DataGenerator.generateValidOwner());
        cvcCvvField.setValue(DataGenerator.generateValidCvcCvv());
        continueButton.click();
        continueButton.shouldBe(Condition.visible, Duration.ofSeconds(10));
        return new FormPage();
    }

    public FormPage fillInvalidOwner() {
        cardNumberField.setValue(DataGenerator.generateValidCardNumber());
        monthField.setValue(DataGenerator.generateValidMonth());
        yearField.setValue(DataGenerator.generateValidYear());
        ownerField.setValue(DataGenerator.generateInvalidOwner());
        cvcCvvField.setValue(DataGenerator.generateValidCvcCvv());
        continueButton.click();
        continueButton.shouldBe(Condition.visible, Duration.ofSeconds(10));
        return new FormPage();
    }

    public String getFormTitle() {
        return title.getText();
    }

    public String getNotificationAccept() {
        notificationAccept.shouldBe(Condition.visible, Duration.ofSeconds(10));
        return notificationAccept.getText();
    }

    public String getNotificationOwnerInvalid() {
        return notificationOwnerInvalid.getText();
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
}


