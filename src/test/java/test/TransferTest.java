package test;

import data.SQLHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.*;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferTest {


    @BeforeEach
    void setup() {
        var changePayPage = open("http://localhost:8080/", ChangePayPage.class);
        SQLHelper.CleanDatabase();

    }

    //Позитивные тесты

    @Test
    @DisplayName("Should show Payment Title")
    void shouldShowPaymentTitle() {
        (new ChangePayPage()).changePaymentByCard();
        String title = (new FormPage()).getFormTitle();
        assertEquals("Оплата по карте",
                title);
    }

    @Test
    @DisplayName("Should show Credit Title")
    void shouldShowCreditTitle() {
        (new ChangePayPage()).changeCredit();
        String title = (new FormPage()).getFormTitle();
        assertEquals("Кредит по данным карты",
                title);
    }

    @Test
    @DisplayName("Should payment successfully by approved card")
    void shouldPaymentSuccessfully() throws SQLException {
        (new ChangePayPage()).changePaymentByCard();
        (new FormPage()).fillValidCard();
        String notification = (new VerificationPage()).getNotificationAccept();
        assertEquals("Успешно\n" +
                        "Операция одобрена Банком.",
                notification);
        assertEquals("APPROVED", SQLHelper.getPaymentStatus());
    }

    @Test
    @DisplayName("Should purchase successfully by credit")
    void shouldPurchaseSuccessfullyByCredit() {
        (new ChangePayPage()).changeCredit();
        (new FormPage()).fillValidCard();
        String notification = (new VerificationPage()).getNotificationAccept();
        assertEquals("Успешно\n" +
                        "Операция одобрена Банком.",
                notification);
    }

    //Негативные тесты
    @Test
    @DisplayName("Should Reject Payment by Declined Card")
    void shouldRejectPaymentByDeclinedCard() {
        (new ChangePayPage()).changePaymentByCard();
        (new FormPage()).fillDeclinedCard();
        String notification = (new DeniedPage()).getNotificationError();
        assertEquals("Ошибка! Банк отказал в проведении операции.",
                notification);
    }

    @Test
    @DisplayName("Should Reject Credit by Declined Card")
    void shouldRejectCreditByDeclinedCard() {
        (new ChangePayPage()).changeCredit();
        (new FormPage()).fillDeclinedCard();
        String notification = (new DeniedPage()).getNotificationError();
        assertEquals("Ошибка! Банк отказал в проведении операции.",
                notification);
    }


    @Test
    @DisplayName("Should Reject Payment by Unknown Card Number")
    void shouldRejectPaymentByUnknownCardNumber() {
        (new ChangePayPage()).changePaymentByCard();
        (new FormPage()).fillUnknownCardNumber();
        String notification = (new DeniedPage()).getNotificationError();
        assertEquals("Ошибка\n" +
                        "Ошибка! Банк отказал в проведении операции.",
                notification);
    }

    @Test
    @DisplayName("Should Reject Credit by Unknown Card Number")
    void shouldRejectCreditByUnknownCardNumber() {
        (new ChangePayPage()).changeCredit();
        (new FormPage()).fillUnknownCardNumber();
        String notification = (new DeniedPage()).getNotificationError();
        assertEquals("Ошибка\n" +
                        "Ошибка! Банк отказал в проведении операции.",
                notification);
    }

    @Test
    @DisplayName("Should Notificate Unfilled Payment Form ")
    void ShouldNotificateUnfilledPaymentForm() {
        (new ChangePayPage()).changePaymentByCard();
        (new FormPage()).unfillForm();
        String notification = (new ErrorUnfilledFormPage()).getRedNotificationAccept();
        assertEquals("Неверный формат",
                notification);
    }

    @Test
    @DisplayName("Should Notificate Unfilled Form Credit")
    void ShouldNotificateUnfilledFormCredit() {
        (new ChangePayPage()).changeCredit();
        (new FormPage()).unfillForm();
        String notification = (new ErrorUnfilledFormPage()).getRedNotificationAccept();
        assertEquals("Неверный формат",
                notification);
    }

    @Test
    @DisplayName("Should Notificate Expired Year Payment")
    void ShouldNotificateExpiredYearPayment() {
        (new ChangePayPage()).changePaymentByCard();
        (new FormPage()).fillExpiredYear();
        String notification = (new CardExpiredPage()).getDateExpiredNotificationAccept();
        assertEquals("Истёк срок действия карты",
                notification);
    }

    @Test
    @DisplayName("Should Notificate Expired Year Credit")
    void ShouldNotificateExpiredYearCredit() {
        (new ChangePayPage()).changeCredit();
        (new FormPage()).fillExpiredYear();
        String notification = (new CardExpiredPage()).getDateExpiredNotificationAccept();
        assertEquals("Истёк срок действия карты",
                notification);
    }

    @Test
    @DisplayName("Should Notificate Expired Month Payment")
    void ShouldNotificateExpiredMonthPayment() {
        (new ChangePayPage()).changePaymentByCard();
        (new FormPage()).fillExpiredMonth();
        String notification = (new MonthExpiredPage()).getMonthExpiredNotificationAccept();
        assertEquals("Неверно указан срок действия карты",
                notification);
    }

    @Test
    @DisplayName("Should Notificate Expired Month Credit")
    void ShouldNotificateExpiredMonthCredit() {
        (new ChangePayPage()).changeCredit();
        (new FormPage()).fillExpiredMonth();
        String notification = (new MonthExpiredPage()).getMonthExpiredNotificationAccept();
        assertEquals("Неверно указан срок действия карты",
                notification);
    }


}