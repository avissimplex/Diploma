package data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataGenerator {
    private DataGenerator() {
    }
    public static String generateValidCardNumber() {
        return "4444 4444 4444 4441";
    }

    public static String generateDeclinedCardNumber() {
        return "4444 4444 4444 4442";
    }

    public static String generateInvalidCardNumber() {
        return "4444 3444 4444 4442";
    }

    public static String generateValidOwner() {
        return "Ivanov";
    }

    public static String generateInvalidOwner() {
        return "12345";
    }

    public static String generateValidCvcCvv() {
        return "678";
    }

    public static String generateInvalidCvcCvv() {
        return "99";
    }
    public static String generateValidYear() {
        return LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("uu"));
    }
    public static String generateInvalidYear() {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("u"));
    }
    public static String generateValidMonth() {
        return LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("MM"));
    }

    public static String generateInvalidMonth() {
        return LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("M"));
    }
}