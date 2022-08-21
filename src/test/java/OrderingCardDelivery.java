import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

class OrderingCardDelivery {

    public String generateDate(int days){
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    @Test
    void test() {
        Configuration.holdBrowserOpen = true;

        String planningDate = generateDate(3);
        open("http://localhost:9999/");
        $("[data-test-id = 'city'] input").setValue("Ростов-на-Дону");
        $("[data-test-id='date'] input").setValue(planningDate);
        $("[data-test-id ='name'] input").setValue("Киркорова Алла-Виктория");
        $("[data-test-id='phone'] input").setValue("+79001234567");
        $("[data-test-id='agreement']").click();
        $(".button__text").click();
        $("div .notification__content").should(visible, Duration.ofSeconds(15));
        $("div .notification__content").shouldHave(exactText("Встреча успешно забронирована на " + planningDate));
    }
}
