import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
@Feature("Тестируем добавление товара")
@Slf4j
public class TestAddGoods extends BaseTest {
    ProductPage productPage = new ProductPage(driver);


    @Test
    @Description("Добавляем обычный фрукт")
    void addUsualFruit() {

        log.info("начинаем выполнять тест 1");

        productPage
                .pressAddButton()
                .enterGoodName("Груша")
                .selectGoodType("FRUIT")
                .pressSaveBtn()
                .checkGoodAdd("Груша", "Фрукт", "false");

        log.info("Заканчиваем выполнять тест 1");


    }
    @Test
    @Description("Добавляем экзотический овощ")
    void addExoticVegetable() {

        log.info("начинаем выполнять тест 2");

        productPage
                .pressAddButton()
                .enterGoodName("Батат")
                .selectGoodType("VEGETABLE")
                .activeExoticCheckBox()
                .pressSaveBtn()
                .checkGoodAdd("Батат", "Овощ", "true");

        log.info("Заканчиваем выполнять тест 2");


    }


}
