package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.*;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferTest {
//    ChangePayPage changePayPage;
//    FormPage formPage;
//    VerificationPage verificationPage;
//    DeniedPage deniedPage;
//
//    ErrorUnfilledFormPage errorUnfilledFormPage;
//
//    CardExpiredPage cardExpiredPage;

    @BeforeEach
    void setup() {
        var changePayPage = open("http://localhost:8080/", ChangePayPage.class);
    }

    //Позитивные тесты

    @Test
    @DisplayName("Should show Buy Title")
    void shouldShowBuyTitle() {
        (new ChangePayPage()).changePurchaseByCard();
        String title = (new FormPage()).getFormTitle();
        assertEquals("Оплата по карте",
                title);
    }

    @Test
    @DisplayName("Should show Credit Title")
    void shouldShowCreditTitle() {
        (new ChangePayPage()).changePurchaseByCredit();
        String title = (new FormPage()).getFormTitle();
        assertEquals("Кредит по данным карты",
                title);
    }

    @Test
    @DisplayName("Should purchase successfully by card")
    void shouldPurchaseSuccessfullyByCard() {
        (new ChangePayPage()).changePurchaseByCard();
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
        (new FormPage()).fillDeclinedCard();
        String notification = (new DeniedPage()).getNotificationError();
        assertEquals("Ошибка! Банк отказал в проведении операции.",
                notification);
    }

    @Test
    @DisplayName("Should Reject Credit by Declined Card")
    void shouldRejectCreditByDeclinedCard() {
        (new ChangePayPage()).changePurchaseByCredit();
        (new FormPage()).fillDeclinedCard();
        String notification = (new DeniedPage()).getNotificationError();
        assertEquals("Ошибка! Банк отказал в проведении операции.",
                notification);
    }


    @Test
    @DisplayName("Should Reject Purchase by Invalid Card Number")
    void shouldRejectPurchaseByInvalidCardNumber() {
        (new ChangePayPage()).changePurchaseByCard();
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
        (new FormPage()).unfillForm();
        String notification = (new ErrorUnfilledFormPage()).getRedNotificationAccept();
        assertEquals("Неверный формат",
                notification);
    }

    @Test
    @DisplayName("Should Notificate Unfilled Form Credit")
    void ShouldNotificateUnfilledFormCredit() {
        (new ChangePayPage()).changePurchaseByCredit();
        (new FormPage()).unfillForm();
        String notification = (new ErrorUnfilledFormPage()).getRedNotificationAccept();
        assertEquals("Неверный формат",
                notification);
    }

    @Test
    @DisplayName("Should Notificate Expired Year Buy")
    void ShouldNotificateExpiredYearBuy() {
        (new ChangePayPage()).changePurchaseByCard();
        (new FormPage()).fillExpiredYear();
        String notification = (new CardExpiredPage()).getDateExpiredNotificationAccept();
        assertEquals("Истёк срок действия карты",
                notification);
    }

    @Test
    @DisplayName("Should Notificate Expired Year Credit")
    void ShouldNotificateExpiredYearCredit() {
        (new ChangePayPage()).changePurchaseByCredit();
        (new FormPage()).fillExpiredYear();
        String notification = (new CardExpiredPage()).getDateExpiredNotificationAccept();
        assertEquals("Истёк срок действия карты",
                notification);
    }

    @Test
    @DisplayName("Should Notificate Expired Month Buy")
    void ShouldNotificateExpiredMonthBuy() {
        (new ChangePayPage()).changePurchaseByCard();
        (new FormPage()).fillExpiredMonth();
        String notification = (new MonthExpiredPage()).getMonthExpiredNotificationAccept();
        assertEquals("Неверно указан срок действия карты",
                notification);
    }

    @Test
    @DisplayName("Should Notificate Expired Month Credit")
    void ShouldNotificateExpiredMonthCredit() {
        (new ChangePayPage()).changePurchaseByCredit();
        (new FormPage()).fillExpiredMonth();
        String notification = (new MonthExpiredPage()).getMonthExpiredNotificationAccept();
        assertEquals("Неверно указан срок действия карты",
                notification);
    }


}