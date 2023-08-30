package Jdbc;

import DBconfig.DataBaseConfig;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FoodDB {

  public DataBaseConfig dataBaseConfig;

    public FoodDB(DataBaseConfig dataBaseConfig) {
        this.dataBaseConfig = dataBaseConfig;
    }

    // Метод для добавления товара в таблицу FOOD
    public void insertFood(String foodName, String foodType, int foodExotic) {
        try (Connection connection = dataBaseConfig.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "INSERT INTO FOOD (FOOD_NAME, FOOD_TYPE, FOOD_EXOTIC) VALUES (?, ?, ?)")) {

            preparedStatement.setString(1, foodName);
            preparedStatement.setString(2, foodType);
            preparedStatement.setInt(3, foodExotic);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    // Метод для просмотра содержимого таблицы FOOD
    public void displayFoodTable() {
        try (Connection connection = dataBaseConfig.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM FOOD");
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                int foodId = resultSet.getInt("FOOD_ID");
                String foodName = resultSet.getString("FOOD_NAME");
                String foodType = resultSet.getString("FOOD_TYPE");
                double foodExotic = resultSet.getInt("FOOD_EXOTIC");
                System.out.println(" ID: " + foodId +
                        " NAME: " + foodName +
                        " TYPE: " + foodType +
                        " isEXOTIC: " + foodExotic);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Метод для удаления товара из таблицы FOOD
    public void deleteFood(int id) {
        try (Connection connection = dataBaseConfig.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM FOOD WHERE FOOD_ID = ?")) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
}
