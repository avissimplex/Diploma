package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MonthExpiredPage {

    private final SelenideElement monthExpiredNotification = $(byText("Неверно указан срок действия карты"));

    public String getMonthExpiredNotificationAccept () {
        return monthExpiredNotification.getText();
    }
}
