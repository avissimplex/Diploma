import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PurchaseTest {

        @Test
        void ShouldPurchasePositive() {
            Configuration.holdBrowserOpen = true;
            open("http://localhost:8080");
            $("[class='button button_size_m button_theme_alfa-on-white']").click();
            $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
            $("[class='input-group__input-case'] input").setValue("11");
            $("[class='input-group__input-case'] [class='input input_type_text input_view_default input_size_m input_width_available input_has-label input_theme_alfa-on-white'] input").setValue("24");
            $("[class='input-group__input-case'] [class='input input_type_text input_view_default input_size_m input_width_available input_has-label input_theme_alfa-on-white'] input").setValue("Ivanov");
            $("[placeholder='999']").setValue("999");
            $$("[class='button button_view_extra button_size_m button_theme_alfa-on-white'] ").get(1).click();
            $( "[class='notification notification_visible notification_status_ok notification_has-closer notification_stick-to_right notification_theme_alfa-on-white']")
                    .shouldBe(Condition.visible, Duration.ofSeconds(15))
                    .shouldHave(exactText("Успешно\n" +
                            "Операция одобрена Банком."));
        }

    @Test
    void ShouldBuyPositive() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:8080");
        $$("[class='button button_view_extra button_size_m button_theme_alfa-on-white']").get(0).click();
        $("[placeholder='0000 0000 0000 0000']").setValue("4444 4444 4444 4441");
        $("[class='input-group__input-case'] input").setValue("11");
        $("[class='input-group__input-case'] [class='input input_type_text input_view_default input_size_m input_width_available input_has-label input_theme_alfa-on-white'] input").setValue("24");
        $("[class='input-group__input-case'] [class='input input_type_text input_view_default input_size_m input_width_available input_has-label input_theme_alfa-on-white'] input").setValue("Ivanov");
        $("[placeholder='999']").setValue("999");
        $$("[class='button button_view_extra button_size_m button_theme_alfa-on-white'] ").get(1).click();
        $( "[class='notification notification_visible notification_status_ok notification_has-closer notification_stick-to_right notification_theme_alfa-on-white']")
                .shouldBe(Condition.visible, Duration.ofSeconds(15))
                .shouldHave(exactText("Успешно\n" +
                        "Операция одобрена Банком."));
    }
}
