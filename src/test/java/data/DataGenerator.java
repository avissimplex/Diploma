package data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateValidYear() {
        return LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("Y"));
    }
    public static String generateInvalidYear() {
        return LocalDate.now().minusYears(1).format(DateTimeFormatter.ofPattern("Y"));
    }
    public static String generateValidMonth() {
        return LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("M"));
    }

    public static String generateInvalidMonth() {
        return LocalDate.now().minusMonths(1).format(DateTimeFormatter.ofPattern("M"));
    }
}