import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
@Slf4j
public class ProductPage {

    public WebDriver driver;

    By AddBtn = By.xpath("//*[text()='Добавить']");
    By nameInput = By.xpath("//*[@id='name']");
    By goodType = By.xpath("//*[@id='type']");
    By checkBoxExotic = By.xpath("//*[@id='exotic']");
    By saveBtn = By.xpath("//*[@id='save']");

    public ProductPage(WebDriver driver) {
        this.driver = driver;
    }


    //Нажимаем кнопку «Добавить»
    public ProductPage pressAddButton()  {
        log.info("Нажимаем кнопку 'Добавить'");

        WebElement addButton = driver.findElement(AddBtn);
        addButton.click();

        return this;
    }

    // Вводим наименование товара
    // Проверяем, что введенное наименование отображается в поле «Наименование»
    public ProductPage enterGoodName(String name) {

        WebElement inputName = driver.findElement(nameInput);
        inputName.sendKeys(name);

        String inputText = inputName.getAttribute("value");
        log.info("Введено наименование товара: {}", inputText);

        Assertions.assertEquals(name, inputName.getAttribute("value"),
                "Наименование не отображается в поле");

        return this;
    }

    // Выбираем тип товара
    // Проверяем, что выбранный тип отображается в поле «Тип»
    public ProductPage selectGoodType(String type) {


        WebElement typeInput = driver.findElement(goodType);
        typeInput.click();
        WebElement typeOption = driver.findElement(By.xpath("//*[@value='" + type + "']"));
        typeOption.click();

        String typeChoice = typeOption.getAttribute("value");
        log.info("Выбран тип товара: {}", typeChoice);

        Assertions.assertEquals(type, typeInput.getAttribute("value"),
                "Выбраный тип не отображается в поле");

        return this;
    }

    // Активируем чекбокс «Экзотический»
    // Проверяем, что чекбокс активен
    public ProductPage activeExoticCheckBox() {

        log.info("Активинуем чекбокс экзотический");

        WebElement checkBox = driver.findElement(checkBoxExotic);
        checkBox.click();
        Assertions.assertTrue(checkBox.isSelected(), "Чекбокс не активен");

        return this;
    }

    // Кликаем по кнопке сохранить
    // Проверяем, что окно для ввода данных закрылось
    public ProductPage pressSaveBtn() {

        log.info("Кликаем по кнопке 'Сохранить'");

        WebElement saveButton = driver.findElement(saveBtn);
        saveButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        boolean isInvisible = wait.until(ExpectedConditions
                .invisibilityOfElementLocated(By.xpath("//*[@class=\"modal-content\"]")));
        Assertions.assertTrue(isInvisible,"Окно ввода данных не закрылось");

        return this;
    }

    // Проверяем отображение добаленной позиции в таблице
    public ProductPage checkGoodAdd(String goodName, String goodType, String Exotic) {

        String Name = goodName;
        String Type = goodType;
        String isExotic = Exotic;

        WebElement tableRowUF = driver.findElement(By.xpath(
                "//tr[contains(., '" + Name + "') " +
                                "and contains(., '" + Type + "') " +
                                    "and contains(., '" + isExotic + "')]"));

        if (tableRowUF != null) {
            log.info("Товар успешно добавлен в таблицу по значениям: {}, {}, {}", Name, Type, isExotic);
        } else {
            log.error("Товар не был добавлен в таблицу по значениям: {}, {}, {}", Name, Type, isExotic);
        }

        Assertions.assertNotNull(tableRowUF);

        return this;
   }

}

