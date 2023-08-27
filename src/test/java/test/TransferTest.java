package test;

import com.codeborne.selenide.Configuration;
import data.SQLHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.ChangePayPage;
import page.FormPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TransferTest {

    @BeforeEach
    void setup() {
        String jarApp = System.getProperty("jarApp");
        open(jarApp, ChangePayPage.class);
        Configuration.holdBrowserOpen = true;
        SQLHelper.cleanDatabase();
        new ChangePayPage().changePaymentByCard();
    }

    //Позитивные тесты
    @Test
    @DisplayName("Should show Payment Title")
    void shouldShowPaymentTitle() {
        String title = new FormPage().getFormTitle();
        assertEquals("Оплата по карте",
                title);
    }

    @Test
    @DisplayName("Should payment successfully by approved card")
    void shouldPaymentSuccessfully() {
        new FormPage().fillValidCard();
        String notification = new FormPage().getNotificationAccept();
        assertEquals("Успешно\n" +
                        "Операция одобрена Банком.",
                notification);
        assertEquals("APPROVED", SQLHelper.getPaymentStatus());
        int amount = 4500000;
        assertEquals(amount, SQLHelper.getPaymentAmount());
    }

    //Негативные тесты
    @Test
    @DisplayName("Should Reject Payment by Declined Card")
    void shouldRejectPaymentByDeclinedCard() {
        new FormPage().fillDeclinedCard();
        String notification = new FormPage().getNotificationError();
        assertEquals("Ошибка! Банк отказал в проведении операции.",
                notification);
        assertEquals("DECLINED", SQLHelper.getPaymentStatus());
        int amount = 4500000;
        assertEquals(amount, SQLHelper.getPaymentAmount());
    }

    @Test
    @DisplayName("Should Reject Payment by Unknown Card Number")
    void shouldRejectPaymentByUnknownCardNumber() {
        new FormPage().fillUnknownCardNumber();
        assertEquals("DECLINED", SQLHelper.getPaymentStatus());
        int amount = 4500000;
        assertEquals(amount, SQLHelper.getPaymentAmount());
        String notification = new FormPage().getNotificationError();
        assertEquals("Ошибка\n" +
                        "Ошибка! Банк отказал в проведении операции.",
                notification);
    }

    @Test
    @DisplayName("Should Notificate Unfilled Payment Form ")
    void shouldNotificateUnfilledPaymentForm() {
        new FormPage().unfillForm();
        String notification = new FormPage().getRedNotificationAccept();
        assertEquals("Неверный формат",
                notification);
        assertNull(SQLHelper.getPaymentAmount(), "Payment amount should be null");
        assertNull(SQLHelper.getPaymentStatus(), "Payment status should be null");

    }

    @Test
    @DisplayName("Should Notificate Expired Year Payment")
    void shouldNotificateExpiredYearPayment() {
        new FormPage().fillExpiredYear();
        String notification = new FormPage().getDateExpiredNotification();
        assertEquals("Истёк срок действия карты",
                notification);
        assertNull(SQLHelper.getPaymentAmount(), "Payment amount should be null");
        assertNull(SQLHelper.getPaymentStatus(), "Payment status should be null");
    }

    @Test
    @DisplayName("Should Notificate Expired Month Payment")
    void shouldNotificateExpiredMonthPayment() {
        new FormPage().fillExpiredMonth();
        String notification = new FormPage().getMonthExpiredNotificationAccept();
        assertEquals("Истёк срок действия карты",
                notification);
        assertNull(SQLHelper.getPaymentAmount(), "Payment amount should be null");
        assertNull(SQLHelper.getPaymentStatus(), "Payment status should be null");
    }

    @Test
    @DisplayName("Should Decline Payment from Invalid Owner")
    void shouldDeclinePaymentInvalidOwner() {
        new FormPage().fillInvalidOwner();
        assertEquals(null, SQLHelper.getPaymentStatus());
        assertEquals(null, SQLHelper.getPaymentAmount());
        String notification = new FormPage().getNotificationOwnerInvalid();
        assertEquals("Неверный формат",
                notification);
    }

    @Test
    @DisplayName("Should Notificate Invalid Month Payment")
    void shouldNotificateInvalidMonthPayment() {
        new FormPage().fillInvalidMonth();
        String notification = new FormPage().getDateInvalidNotification();
        assertEquals("Неверно указан срок действия карты",
                notification);
        assertNull(SQLHelper.getPaymentAmount(), "Payment amount should be null");
        assertNull(SQLHelper.getPaymentStatus(), "Payment status should be null");
    }


    @Test
    @DisplayName("Should Notificate Invalid Year Payment")
    void shouldNotificateInvalidYearPayment() {
        new FormPage().fillInvalidYear();
        String notification = (new FormPage()).getDateInvalidNotification();
        assertEquals("Неверно указан срок действия карты",
                notification);
        assertNull(SQLHelper.getPaymentAmount(), "Payment amount should be null");
        assertNull(SQLHelper.getPaymentStatus(), "Payment status should be null");
    }


    @Test
    @DisplayName("Should Show Owner Payment Grey Suggest")
    void shouldShowOwnerGreySuggest() {
        new FormPage().getOwnerInputField();
    }


    @Test
    @DisplayName("Should Show CVC Payment Grey Suggest")
    void shouldShowCvcGreySuggest() {
        new FormPage().getCvcInputField();
    }
}