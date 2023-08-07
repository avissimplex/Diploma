package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private final SelenideElement notificationAccept = $("[class='notification notification_visible notification_status_ok notification_has-closer notification_stick-to_right notification_theme_alfa-on-white']")
            .shouldBe(Condition.visible, Duration.ofSeconds(15));

    public String getNotificationAccept () {
        return notificationAccept.getText();
    }
}
