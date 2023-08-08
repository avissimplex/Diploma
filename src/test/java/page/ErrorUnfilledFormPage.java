package page;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ErrorUnfilledFormPage {
    private final SelenideElement redNotification = $(byText("Неверный формат"));

    public String getRedNotificationAccept () {
        return redNotification.getText();
    }
}
