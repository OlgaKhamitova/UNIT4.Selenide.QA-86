
import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.time.LocalDate;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {

    @Test
    public void testCardDelivery() {
        LocalDate currentDate = LocalDate.now();
        LocalDate date = currentDate.plusDays(3);

        open("http://localhost:9999/");
        $("[data-test-id=city] input").setValue("Нижний Новгород");
        $("[data-test-id=date] input").setValue(String.valueOf(date));
        $("[data-test-id=name] input").setValue("Хамитова Ольга");
        $("[data-test-id=phone] input").setValue("+79991111111");
        $("[data-test-id=agreement]").click();
        $(withText("Забронировать")).click();
        $("[data-test-id=notification]").shouldBe(visible, Duration.ofSeconds(15));
    }
}
