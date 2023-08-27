package test;

import com.codeborne.selenide.Configuration;
import data.SQLHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.ChangePayPage;
import page.FormPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class CreditTest {
    @BeforeEach
    void setup() {
        String jarApp = System.getProperty("jarApp");
        open(jarApp, ChangePayPage.class);
        Configuration.holdBrowserOpen = true;
        SQLHelper.cleanDatabase();
        new ChangePayPage().changeCredit();
    }

    //Позитивные тесты
    @Test
    @DisplayName("Should show Credit Title")
    void shouldShowCreditTitle() {
        String title = new FormPage().getFormTitle();
        assertEquals("Кредит по данным карты",
                title);
    }

    @Test
    @DisplayName("Should credit successfully by approved card amount 4500000")
    @Disabled
    void shouldCreditSuccessfully4500000() {
        new FormPage().fillValidCard();
        String notification = new FormPage().getNotificationAccept();
        assertEquals("APPROVED", SQLHelper.getCreditStatus());
        assertEquals("Успешно\n" +
                        "Операция одобрена Банком.",
                notification);
        int amount = 4500000;
        assertEquals(amount, SQLHelper.getCreditAmount());

    }

    @Test
    @DisplayName("Should credit successfully by approved card unknown amount")
    void shouldCreditSuccessfully() {
        new FormPage().fillValidCard();
        String notification = new FormPage().getNotificationAccept();
        assertEquals("APPROVED", SQLHelper.getCreditStatus());
        assertEquals("Успешно\n" +
                        "Операция одобрена Банком.",
                notification);
    }

    //Негативные тесты
    @Test
    @DisplayName("Should Reject Credit by Declined Card")
    void shouldRejectCreditByDeclinedCard() {
        new FormPage().fillDeclinedCard();
        assertEquals("DECLINED", SQLHelper.getCreditStatus());
        String notification = new FormPage().getNotificationError();
        assertEquals("Ошибка! Банк отказал в проведении операции.",
                notification);
    }

    @Test
    @DisplayName("Should Reject Credit by Unknown Card Number")
    void shouldRejectCreditByUnknownCardNumber() {
        new FormPage().fillUnknownCardNumber();
        String notification = new FormPage().getNotificationError();
        assertEquals("Ошибка\n" +
                        "Ошибка! Банк отказал в проведении операции.",
                notification);
        assertEquals("DECLINED", SQLHelper.getCreditStatus());
    }

    @Test
    @DisplayName("Should Notificate Unfilled Form Credit")
    void shouldNotificateUnfilledFormCredit() {
        new FormPage().unfillForm();
        String notification = new FormPage().getRedNotificationAccept();
        assertEquals("Неверный формат",
                notification);
        assertNull(SQLHelper.getCreditStatus(), "Credit status should be null");
    }

    @Test
    @DisplayName("Should Notificate Expired Year Credit")
    void shouldNotificateExpiredYearCredit() {
        new FormPage().fillExpiredYear();
        String notification = new FormPage().getDateExpiredNotification();
        assertEquals("Истёк срок действия карты",
                notification);
        assertNull(SQLHelper.getCreditStatus(), "Credit status should be null");
    }

    @Test
    @DisplayName("Should Notificate Expired Month Credit")
    void shouldNotificateExpiredMonthCredit() {
        new FormPage().fillExpiredMonth();
        String notification = new FormPage().getMonthExpiredNotificationAccept();
        assertEquals("Истёк срок действия карты",
                notification);
        assertNull(SQLHelper.getCreditStatus(), "Credit status should be null");
    }

    @Test
    @DisplayName("Should Decline Credit from Invalid Owner")
    void shouldDeclineCreditInvalidOwner() {
        new FormPage().fillInvalidOwner();
        assertEquals(null, SQLHelper.getCreditStatus());
        String notification = new FormPage().getNotificationOwnerInvalid();
        assertEquals("Неверный формат",
                notification);
    }

    @Test
    @DisplayName("Should Notificate Invalid Month Credit")
    void shouldNotificateInvalidMonthCredit() {
        new FormPage().fillInvalidMonth();
        String notification = new FormPage().getDateInvalidNotification();
        assertEquals("Неверно указан срок действия карты",
                notification);
        assertNull(SQLHelper.getCreditStatus(), "Credit status should be null");
    }

    @Test
    @DisplayName("Should Notificate Invalid Year Credit")
    void shouldNotificateInvalidYearCredit() {
        new FormPage().fillInvalidYear();
        String notification = new FormPage().getDateInvalidNotification();
        assertEquals("Неверно указан срок действия карты",
                notification);
        assertNull(SQLHelper.getCreditStatus(), "Credit status should be null");
    }

    @Test
    @DisplayName("Should Show Owner Credit Grey Suggest")
    void shouldShowOwnerGreySuggest() {
        new FormPage().getOwnerInputField();
    }

    @Test
    @DisplayName("Should Show CVC Credit Grey Suggest")
    void shouldShowCvcGreySuggest() {
        new FormPage().getCvcInputField();
    }
}
