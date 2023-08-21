package test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import data.SQLHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.ChangePayPage;
import page.FormPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransferTest {


    @BeforeEach
    void setup() throws SQLException {
        String jarApp = System.getProperty("jarApp");
        open(jarApp, ChangePayPage.class);
        Configuration.holdBrowserOpen = true;
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
    void shouldPaymentSuccessfully() {
        (new ChangePayPage()).changePaymentByCard();
        (new FormPage()).fillValidCard();
        String notification = (new FormPage()).getNotificationAccept();
        assertEquals("Успешно\n" +
                        "Операция одобрена Банком.",
                notification);
        assertEquals("APPROVED", SQLHelper.getPaymentStatus());
        int amount = 4500000;
        assertEquals(amount, SQLHelper.getPaymentAmount());

    }

    @Test
    @DisplayName("Should credit successfully by approved card")
    void shouldCreditSuccessfully() {
        (new ChangePayPage()).changeCredit();
        (new FormPage()).fillValidCard();
        String notification = (new FormPage()).getNotificationAccept();

        assertEquals("APPROVED", SQLHelper.getCreditStatus());
        int amount = 4500000;
        assertEquals(amount, SQLHelper.getCreditAmount());
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
        String notification = (new FormPage()).getNotificationError();
        assertEquals("Ошибка! Банк отказал в проведении операции.",
                notification);
        assertEquals("DECLINED", SQLHelper.getPaymentStatus());
        int amount = 4500000;
        assertEquals(amount, SQLHelper.getPaymentAmount());

    }

    @Test
    @DisplayName("Should Reject Credit by Declined Card")
    void shouldRejectCreditByDeclinedCard() {
        (new ChangePayPage()).changeCredit();
        (new FormPage()).fillDeclinedCard();
        assertEquals("DECLINED", SQLHelper.getCreditStatus());
        int amount = 4500000;
        assertEquals(amount, SQLHelper.getCreditAmount());
        String notification = (new FormPage()).getNotificationError();
        assertEquals("Ошибка! Банк отказал в проведении операции.",
                notification);
    }


    @Test
    @DisplayName("Should Reject Payment by Unknown Card Number")
    void shouldRejectPaymentByUnknownCardNumber() {
        (new ChangePayPage()).changePaymentByCard();
        (new FormPage()).fillUnknownCardNumber();
        assertEquals("DECLINED", SQLHelper.getPaymentStatus());
        int amount = 4500000;
        assertEquals(amount, SQLHelper.getPaymentAmount());
        String notification = (new FormPage()).getNotificationError();
        assertEquals("Ошибка\n" +
                        "Ошибка! Банк отказал в проведении операции.",
                notification);
    }

    @Test
    @DisplayName("Should Reject Credit by Unknown Card Number")
    void shouldRejectCreditByUnknownCardNumber() {
        (new ChangePayPage()).changeCredit();
        (new FormPage()).fillUnknownCardNumber();
        String notification = (new FormPage()).getNotificationError();
        assertEquals("Ошибка\n" +
                        "Ошибка! Банк отказал в проведении операции.",
                notification);
        assertEquals("DECLINED", SQLHelper.getCreditStatus());
        int amount = 4500000;
        assertEquals(amount, SQLHelper.getCreditAmount());

    }

    @Test
    @DisplayName("Should Notificate Unfilled Payment Form ")
    void ShouldNotificateUnfilledPaymentForm() {
        (new ChangePayPage()).changePaymentByCard();
        (new FormPage()).unfillForm();
        String notification = (new FormPage()).getRedNotificationAccept();
        assertEquals("Неверный формат",
                notification);
        assertEquals(null, SQLHelper.getPaymentStatus());
        assertEquals(null, SQLHelper.getPaymentAmount());
    }

    @Test
    @DisplayName("Should Notificate Unfilled Form Credit")
    void ShouldNotificateUnfilledFormCredit() {
        (new ChangePayPage()).changeCredit();
        (new FormPage()).unfillForm();
        String notification = (new FormPage()).getRedNotificationAccept();
        assertEquals("Неверный формат",
                notification);
        assertEquals(null, SQLHelper.getCreditStatus());
        assertEquals(null, SQLHelper.getCreditAmount());
    }

    @Test
    @DisplayName("Should Notificate Expired Year Payment")
    void ShouldNotificateExpiredYearPayment() {
        (new ChangePayPage()).changePaymentByCard();
        (new FormPage()).fillExpiredYear();
        String notification = (new FormPage()).getDateExpiredNotification();
        assertEquals("Истёк срок действия карты",
                notification);
        assertEquals(null, SQLHelper.getPaymentStatus());
        assertEquals(null, SQLHelper.getPaymentAmount());
    }

    @Test
    @DisplayName("Should Notificate Expired Year Credit")
    void ShouldNotificateExpiredYearCredit() {
        (new ChangePayPage()).changeCredit();
        (new FormPage()).fillExpiredYear();
        String notification = (new FormPage()).getDateExpiredNotification();
        assertEquals("Истёк срок действия карты",
                notification);
        assertEquals(null, SQLHelper.getCreditStatus());
        assertEquals(null, SQLHelper.getCreditAmount());
    }

    @Test
    @DisplayName("Should Notificate Expired Month Payment")
    void ShouldNotificateExpiredMonthPayment() {
        (new ChangePayPage()).changePaymentByCard();
        (new FormPage()).fillExpiredMonth();
        String notification = (new FormPage()).getMonthExpiredNotificationAccept();
        assertEquals("Истёк срок действия карты",
                notification);
        assertEquals(null, SQLHelper.getPaymentStatus());
        assertEquals(null, SQLHelper.getPaymentAmount());
    }

    @Test
    @DisplayName("Should Notificate Expired Month Credit")
    void ShouldNotificateExpiredMonthCredit() {
        (new ChangePayPage()).changeCredit();
        (new FormPage()).fillExpiredMonth();
        String notification = (new FormPage()).getMonthExpiredNotificationAccept();
        assertEquals("Истёк срок действия карты",
                notification);
        assertEquals(null, SQLHelper.getCreditStatus());
        assertEquals(null, SQLHelper.getCreditAmount());
    }

    @Test
    @DisplayName("Should Decline Payment from Invalid Owner")
    void ShouldDeclinePaymentInvalidOwner() {
        (new ChangePayPage()).changePaymentByCard();
        (new FormPage()).fillInvalidOwner();
        assertEquals(null, SQLHelper.getPaymentStatus());
        assertEquals(null, SQLHelper.getPaymentAmount());
        String notification = (new FormPage()).getNotificationOwnerInvalid();
        assertEquals("Неверный формат",
                notification);

    }

    @Test
    @DisplayName("Should Decline Credit from Invalid Owner")
    void ShouldDeclineCreditInvalidOwner() {
        (new ChangePayPage()).changeCredit();
        (new FormPage()).fillInvalidOwner();
        assertEquals(null, SQLHelper.getCreditStatus());
        assertEquals(null, SQLHelper.getCreditAmount());
        String notification = (new FormPage()).getNotificationOwnerInvalid();
        assertEquals("Неверный формат",
                notification);

    }

    @Test
    @DisplayName("Should Notificate Invalid Month Payment")
    void ShouldNotificateInvalidMonthPayment() {
        (new ChangePayPage()).changePaymentByCard();
        (new FormPage()).fillInvalidMonth();
        String notification = (new FormPage()).getDateInvalidNotification();
        assertEquals("Неверно указан срок действия карты",
                notification);
        assertEquals(null, SQLHelper.getPaymentStatus());
        assertEquals(null, SQLHelper.getPaymentAmount());
    }

    @Test
    @DisplayName("Should Notificate Invalid Month Credit")
    void ShouldNotificateInvalidMonthCredit() {
        (new ChangePayPage()).changeCredit();
        (new FormPage()).fillInvalidMonth();
        String notification = (new FormPage()).getDateInvalidNotification();
        assertEquals("Неверно указан срок действия карты",
                notification);
        assertEquals(null, SQLHelper.getCreditStatus());
        assertEquals(null, SQLHelper.getCreditAmount());
    }

    @Test
    @DisplayName("Should Notificate Invalid Year Payment")
    void ShouldNotificateInvalidYearPayment() {
        (new ChangePayPage()).changePaymentByCard();
        (new FormPage()).fillInvalidYear();
        String notification = (new FormPage()).getDateInvalidNotification();
        assertEquals("Неверно указан срок действия карты",
                notification);
        assertEquals(null, SQLHelper.getPaymentStatus());
        assertEquals(null, SQLHelper.getPaymentAmount());
    }

    @Test
    @DisplayName("Should Notificate Invalid Year Credit")
    void ShouldNotificateInvalidYearCredit() {
        (new ChangePayPage()).changeCredit();
        (new FormPage()).fillInvalidYear();
        String notification = (new FormPage()).getDateInvalidNotification();
        assertEquals("Неверно указан срок действия карты",
                notification);
        assertEquals(null, SQLHelper.getCreditStatus());
        assertEquals(null, SQLHelper.getCreditAmount());
    }

    @Test
    @DisplayName("Should Show Owner Grey Suggest")
    void ShouldShowOwnerGreySuggest() {
        (new ChangePayPage()).changeCredit();
        (new FormPage()).getOwnerInputField();

    }


    @Test
    @DisplayName("Should Show CVC Grey Suggest")
    void ShouldShowCvcGreySuggest() {
        (new ChangePayPage()).changeCredit();
        (new FormPage()).getCvcInputField();


    }
}