package page;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Selenide.*;

public class ChangePayPage {

    private final SelenideElement buyButton = $("[class='button button_size_m button_theme_alfa-on-white']");
    private final SelenideElement creditButton = $$("[class='button button_view_extra button_size_m button_theme_alfa-on-white']").get(0);

    public FormPage changePurchaseByCard() {
        buyButton.click();
        return new FormPage();
//        return page(FormPage.class);
    }

    public FormPage changePurchaseByCredit() {
       creditButton.click();
        return page(FormPage.class);
    }

}
