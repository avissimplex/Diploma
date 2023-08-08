package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class CardExpiredPage {
    private final SelenideElement dateExpiredNotification = $(byText("Истёк срок действия карты"));
    private final SelenideElement monthExpiredNotification = $(byText("Неверно указан срок действия карты"));
    public String getDateExpiredNotificationAccept () {
        return dateExpiredNotification.getText();
    }
    public String getMonthExpiredNotificationAccept () {
        return monthExpiredNotification.getText();
    }
}
