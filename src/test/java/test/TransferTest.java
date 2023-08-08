package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.*;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferTest {
    ChangePayPage changePayPage;
    FormPage formPage;
    VerificationPage verificationPage;
    DeniedPage deniedPage;

    ErrorUnfilledFormPage errorUnfilledFormPage;

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
    void shouldPurchaseSuccessfullyByCredit() {
        (new ChangePayPage()).changePurchaseByCredit();
        String title = (new FormPage()).getFormTitle();
        assertEquals("Кредит по данным карты",
                title);
        (new FormPage()).fillValidCard();
        String notification = (new VerificationPage()).getNotificationAccept();
        assertEquals("Успешно\n" +
                        "Операция одобрена Банком.",
                notification);
    }

    //Негативные тесты
    @Test
    @DisplayName("Should Reject Purchase by Declined Card")
    void shouldRejectPurchaseByDeclinedCard() {
        (new ChangePayPage()).changePurchaseByCard();
        String title = (new FormPage()).getFormTitle();
        assertEquals("Оплата по карте",
                title);
        (new FormPage()).fillDeclinedCard();
        String notification = (new DeniedPage()).getNotificationError();
        assertEquals("Ошибка! Банк отказал в проведении операции.",
                notification);
    }
    @Test
    @DisplayName("Should Reject Credit by Declined Card")
    void shouldRejectCreditByDeclinedCard() {
        (new ChangePayPage()).changePurchaseByCredit();
        String title = (new FormPage()).getFormTitle();
        assertEquals("Кредит по данным карты",
                title);
        (new FormPage()).fillDeclinedCard();
        String notification = (new DeniedPage()).getNotificationError();
        assertEquals("Ошибка! Банк отказал в проведении операции.",
                notification);
    }


    @Test
    @DisplayName("Should Reject Purchase by Invalid Card Number")
    void shouldRejectPurchaseByInvalidCardNumber() {
        (new ChangePayPage()).changePurchaseByCard();
        String title = (new FormPage()).getFormTitle();
        assertEquals("Оплата по карте",
                title);
        (new FormPage()).fillInvalidCardNumber();
        String notification = (new DeniedPage()).getNotificationError();
        assertEquals("Ошибка\n" +
                        "Ошибка! Банк отказал в проведении операции.",
                notification);
    }
    @Test
    @DisplayName("Should Reject by Invalid Card Number")
    void shouldRejectCreditByInvalidCardNumber() {
        (new ChangePayPage()).changePurchaseByCredit();
        String title = (new FormPage()).getFormTitle();
        assertEquals("Кредит по данным карты",
                title);
        (new FormPage()).fillInvalidCardNumber();
        String notification = (new DeniedPage()).getNotificationError();
        assertEquals("Ошибка\n" +
                        "Ошибка! Банк отказал в проведении операции.",
                notification);
    }

    @Test
    @DisplayName("Should Notificate Unfilled Form Buy")
    void ShouldNotificateUnfilledFormBuy() {
        (new ChangePayPage()).changePurchaseByCard();
        String title = (new FormPage()).getFormTitle();
        assertEquals("Оплата по карте",
                title);
        (new FormPage()).unfillForm();
        String notification = (new ErrorUnfilledFormPage()).getRedNotificationAccept();
        assertEquals("Неверный формат",
                notification);
    }

}