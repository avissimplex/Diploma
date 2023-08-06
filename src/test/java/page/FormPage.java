package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class FormPage {
    private final SelenideElement title = $("class=heading heading_size_m heading_theme_alfa-on-white");
    private final SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $("[class='input-group__input-case'] input");
    private final SelenideElement yearField = $("[class='input-group__input-case'] [class='input input_type_text input_view_default input_size_m input_width_available input_has-label input_theme_alfa-on-white'] input");
    private final SelenideElement ownerField = $("[class='input-group__input-case'] [class='input input_type_text input_view_default input_size_m input_width_available input_has-label input_theme_alfa-on-white'] input");
    private final SelenideElement cvcCvvField = $("[placeholder='999']");
    private final SelenideElement continueButton = $$("[class='button button_view_extra button_size_m button_theme_alfa-on-white'] ").get(1);
    private final SelenideElement notificationAccept = $("[class='notification notification_visible notification_status_ok notification_has-closer notification_stick-to_right notification_theme_alfa-on-white']");
    private final SelenideElement notificationError = $("[class='notification notification_visible notification_status_error notification_has-closer notification_stick-to_right notification_theme_alfa-on-white']");


    public void fillValidForm(DataHelper.AuthValidInfo info) {
        cardNumberField.setValue(info.getCardNumber());
        monthField.setValue(info.getMonth());
        yearField.setValue(info.getYear());
        ownerField.setValue(info.getOwner());
        cvcCvvField.setValue(info.getCvcCvv());
        continueButton.click();
           }
    public void fillInvalidForm(DataHelper.AuthInvalidInfo info) {
        cardNumberField.setValue(info.getCardNumber());
        monthField.setValue(info.getMonth());
        yearField.setValue(info.getYear());
        ownerField.setValue(info.getOwner());
        cvcCvvField.setValue(info.getCvcCvv());
        continueButton.click();
    }
}