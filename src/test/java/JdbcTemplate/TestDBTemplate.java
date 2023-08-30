package JdbcTemplate;

import DBconfig.DataBaseConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;

public class TestDBTemplate {
    @Test
    @DisplayName("Проверка работы БД при добавлении нового товара")
    public void addCheckDel() throws IOException {
        DataBaseConfig dataBaseConfig = new DataBaseConfig();
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataBaseConfig.getDataSource());
        FoodDBTemplate foodDB = new FoodDBTemplate(jdbcTemplate);

            // Шаг 1: Добавление товара в таблицу FOOD
            foodDB.insertFood("Огурец", "VEGETABLE", 0);
            System.out.println("Шаг 1: Запрос выполнен");

            // Шаг 2: Просмотр содержимого таблицы FOOD
            foodDB.displayFoodTable();
            System.out.println("Шаг 2: Запрос вернул содержимое таблицы FOOD");

            // Постусловие: Удаление добавленного товара
            foodDB.deleteFood(5);
            System.out.println("Постусловие: Товар удален");

    }
}
