package data;

import lombok.Value;


public class DataHelper {
    private DataHelper() {
    }

    public static DataCard getCardValidInfo() {
        return new DataCard("4444 4444 4444 4441", DataGenerator.generateValidMonth(), DataGenerator.generateValidYear(), "Иванов", "999");
    }
    public static DataCard getCardInvalidYear() {
        return new DataCard("4444 4444 4444 4441", DataGenerator.generateValidMonth(), DataGenerator.generateInvalidYear(), "Иванов", "999");
    }
    public static DataCard getCardInvalidMonth() {
        return new DataCard("4444 4444 4444 4441", DataGenerator.generateInvalidMonth(), DataGenerator.generateValidYear(), "Иванов", "999");
    }
    @Value
    public static class DataCard {
        private String cardNumber;
        private String month;
        private String year;
        private String owner;
        private String cvcCvv;
    }

}