
import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    @Test
    public void testCardDelivery() {
        LocalDate currentDate = LocalDate.now();
        LocalDate date = currentDate.plusDays(10);
        String dateFormatted = date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));

        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Нижний Новгород");
        $("[data-test-id=date] input").sendKeys(Keys.chord(Keys.CONTROL, "a"));
        $("[data-test-id=date] input").sendKeys(Keys.BACK_SPACE);
        $("[data-test-id=date] input").setValue(dateFormatted);
        $("[data-test-id=name] input").setValue("Хамитова Ольга");
        $("[data-test-id=phone] input").setValue("+79991111111");
        $("[data-test-id=agreement]").click();
        $(withText("Забронировать")).click();
        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__title").shouldHave(Condition.exactText("Успешно!"));
        $("[data-test-id=notification] .notification__content").shouldHave(Condition.exactText("Встреча успешно забронирована на " + dateFormatted));
    }
}
