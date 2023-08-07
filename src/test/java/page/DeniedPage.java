package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class DeniedPage {
    private final SelenideElement notificationError = $("[class='notification notification_visible notification_status_error notification_has-closer notification_stick-to_right notification_theme_alfa-on-white']")
            .shouldBe(Condition.visible, Duration.ofSeconds(15));

    public String getNotificationError () {
        return notificationError.getText();
    }
}
