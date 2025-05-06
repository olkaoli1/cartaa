package ru.netology.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DataGenerator {
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    public static String plusDays(int days) {
        return LocalDate.now().plusDays(days).format(FMT);
    }
}
