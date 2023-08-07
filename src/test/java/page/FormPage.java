package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import data.DataGenerator;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class FormPage {
    private final SelenideElement title = $("[class='heading heading_size_m heading_theme_alfa-on-white']");
    private final SelenideElement cardNumberField = $("[placeholder='0000 0000 0000 0000']");
    private final SelenideElement monthField = $("[class='input-group__input-case'] input");
    private final SelenideElement yearField = $("[class='input-group__input-case'] [class='input input_type_text input_view_default input_size_m input_width_available input_has-label input_theme_alfa-on-white'] input");
    private final SelenideElement ownerField = $("[class='input-group__input-case'] [class='input input_type_text input_view_default input_size_m input_width_available input_has-label input_theme_alfa-on-white'] input");
    private final SelenideElement cvcCvvField = $("[placeholder='999']");
    private final SelenideElement continueButton = $$("[class='button button_view_extra button_size_m button_theme_alfa-on-white'] ").get(1);


   public VerificationPage fillValidCard () {
      cardNumberField.setValue(DataGenerator.generateValidCardNumber());
        monthField.setValue(DataGenerator.generateValidMonth());
        yearField.setValue(DataGenerator.generateValidYear());
        ownerField.setValue(DataGenerator.generateValidOwner());
        cvcCvvField.setValue(DataGenerator.generateValidCvcCvv());
       continueButton.click();
       return new VerificationPage();
           }
    public String getFormTitle () {
       return title.getText();
    }


}