package ru.netology.test;

import com.codeborne.selenide.Configuration;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;

import ru.netology.data.DataGenerator;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

class CardDeliveryTest {

    @BeforeAll
    static void setUpDriver() {
        WebDriverManager.chromedriver().setup();
        Configuration.headless = Boolean.getBoolean("headless");
        Configuration.browserSize = "1920x1080";
    }

    @BeforeEach
    void openSite() {
        open("http://localhost:9999/");
    }

    @Test
    void shouldSubmitIfAllFieldsValid() {
        String date = DataGenerator.plusDays(4);

        $("[data-test-id=city] input").setValue("Казань");

        var dateInput = $("[data-test-id=date] input");
        dateInput.doubleClick().sendKeys(Keys.DELETE);
        dateInput.setValue(date);

        $("[data-test-id=name] input").setValue("Иван-Петров Сергей");
        $("[data-test-id=phone] input").setValue("+79270000000");
        $("[data-test-id=agreement]").click();
        $$("button.button").findBy(text("Забронировать")).click();

        $("[data-test-id=notification]")
                .shouldBe(visible, Duration.ofSeconds(15))
                .shouldHave(text("Встреча успешно забронирована на " + date));
    }
}
