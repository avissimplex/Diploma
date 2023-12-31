package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.*;

public class ChangePayPage {

    private final SelenideElement PaymentButton = $("[class='button button_size_m button_theme_alfa-on-white']");
    private final SelenideElement creditButton = $$("[class='button button_view_extra button_size_m button_theme_alfa-on-white']").get(0);

    public FormPage changePaymentByCard() {
        PaymentButton.click();
        return new FormPage();
    }

    public FormPage changeCredit() {
        creditButton.click();
        return page(FormPage.class);
    }

}
