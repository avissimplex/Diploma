package test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import data.DataGenerator;
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
    void setup() {
        String jarApp = System.getProperty("jarApp");
        open(jarApp, ChangePayPage.class);
        Configuration.holdBrowserOpen = true;
        SQLHelper.CleanDatabase();
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
    void ShouldNotificateUnfilledPaymentForm() {
        new FormPage().unfillForm();
        String notification = new FormPage().getRedNotificationAccept();
        assertEquals("Неверный формат",
                notification);
        assertEquals(null, SQLHelper.getPaymentStatus());
        assertEquals(null, SQLHelper.getPaymentAmount());
    }

    @Test
    @DisplayName("Should Notificate Expired Year Payment")
    void ShouldNotificateExpiredYearPayment() {
        new FormPage().fillExpiredYear();
        String notification = new FormPage().getDateExpiredNotification();
        assertEquals("Истёк срок действия карты",
                notification);
        assertEquals(null, SQLHelper.getPaymentStatus());
        assertEquals(null, SQLHelper.getPaymentAmount());
    }

    @Test
    @DisplayName("Should Notificate Expired Month Payment")
    void ShouldNotificateExpiredMonthPayment() {
        new FormPage().fillExpiredMonth();
        String notification = new FormPage().getMonthExpiredNotificationAccept();
        assertEquals("Истёк срок действия карты",
                notification);
        assertEquals(null, SQLHelper.getPaymentStatus());
        assertEquals(null, SQLHelper.getPaymentAmount());
    }

    @Test
    @DisplayName("Should Decline Payment from Invalid Owner")
    void ShouldDeclinePaymentInvalidOwner() {
        new FormPage().fillInvalidOwner();
        assertEquals(null, SQLHelper.getPaymentStatus());
        assertEquals(null, SQLHelper.getPaymentAmount());
        String notification = new FormPage().getNotificationOwnerInvalid();
        assertEquals("Неверный формат",
                notification);
    }

    @Test
    @DisplayName("Should Notificate Invalid Month Payment")
    void ShouldNotificateInvalidMonthPayment() {
        new FormPage().fillInvalidMonth();
        String notification = new FormPage().getDateInvalidNotification();
        assertEquals("Неверно указан срок действия карты",
                notification);
        assertEquals(null, SQLHelper.getPaymentStatus());
        assertEquals(null, SQLHelper.getPaymentAmount());
    }


    @Test
    @DisplayName("Should Notificate Invalid Year Payment")
    void ShouldNotificateInvalidYearPayment() {
        new FormPage().fillInvalidYear();
        String notification = (new FormPage()).getDateInvalidNotification();
        assertEquals("Неверно указан срок действия карты",
                notification);
        assertEquals(null, SQLHelper.getPaymentStatus());
        assertEquals(null, SQLHelper.getPaymentAmount());
    }


    @Test
    @DisplayName("Should Show Owner Payment Grey Suggest")
    void ShouldShowOwnerGreySuggest() {
       new FormPage().getOwnerInputField();
    }


    @Test
    @DisplayName("Should Show CVC Payment Grey Suggest")
    void ShouldShowCvcGreySuggest() {
       new FormPage().getCvcInputField();
    }
}