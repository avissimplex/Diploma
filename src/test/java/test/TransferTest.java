package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.ChangePayPage;
import page.FormPage;
import page.VerificationPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferTest {
    ChangePayPage changePayPage;
    FormPage formPage;

    @BeforeEach
    void setup() {
         var changePayPage = open("http://localhost:8080/", ChangePayPage.class);
    }
//Позитивные тесты
    @Test
    @DisplayName("Should purchase successfully by card")
    void shouldPurchaseSuccessfullyByCard() {
        (new ChangePayPage()).changePurchaseByCard();
        String title = (new FormPage()).getFormTitle();
        assertEquals("Оплата по карте",
                title);
        (new FormPage()).fillValidCard();
        String notification = (new VerificationPage()).getNotificationAccept();
        assertEquals("Успешно\n" +
                        "Операция одобрена Банком.",
                notification);
    }

    @Test
    @DisplayName("Should purchase successfully by credit")
}
