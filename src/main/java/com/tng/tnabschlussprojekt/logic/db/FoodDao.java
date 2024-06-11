package com.tng.tnabschlussprojekt.logic.db;

import com.tng.tnabschlussprojekt.model.Food;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Food DAO class
 */
public class FoodDao implements Dao<Food> {

    //region Konstanten
    public static final String SQL_INSERT_FOOD = "INSERT INTO food (name, price, calories, stock, expiredDate, supplier) VALUES(?,?,?,?,?,?)";
    public static final String SQL_UPDATE_FOOD = "UPDATE food SET name = ?, price = ?, calories = ?, stock = ?, expiredDate = ?, supplier = ? " +
            "WHERE id = ?";
    public static final String SQL_DELETE_FOOD = "DELETE FROM food WHERE id = ?";
    public static final String SQL_ALL_QUERY = "SELECT * FROM food";
    // endregion

    //region Attribute
    //endregion

    //region Konstruktoren
    //endregion

    //region Methoden

    @Override
    public void create(Food food) {
        try (
                Connection connection = DBManager.getInstance().getConnection();
                PreparedStatement preStatement = connection.prepareStatement(
                        SQL_INSERT_FOOD,
                        Statement.RETURN_GENERATED_KEYS)
        )
        {
            preStatement.setString(1, food.getName());
            preStatement.setDouble(2, food.getPrice());
            preStatement.setInt(3, food.getCalories());
            preStatement.setInt(4, food.getStock());
            preStatement.setDate(5, Date.valueOf(food.getExpiredDate()));
            preStatement.setString(6, food.getSupplier());

            preStatement.executeUpdate();

            ResultSet genKey = preStatement.getGeneratedKeys();
            if(genKey.next()){
                int id = genKey.getInt(1);
                food.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Food food) {
        try (
                Connection connection = DBManager.getInstance().getConnection();
                PreparedStatement preStatement = connection.prepareStatement(SQL_UPDATE_FOOD)
        )
        {
            preStatement.setString(1, food.getName());
            preStatement.setDouble(2, food.getPrice());
            preStatement.setInt(3, food.getCalories());
            preStatement.setInt(4, food.getStock());
            preStatement.setDate(5, Date.valueOf(food.getExpiredDate()));
            preStatement.setString(6, food.getSupplier());
            preStatement.setInt(7, food.getId());
            preStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    @Override
    public void delete(Food food) {
        try (
                Connection connection = DBManager.getInstance().getConnection();
                PreparedStatement preStatement = connection.prepareStatement(SQL_DELETE_FOOD)
        )
        {
            preStatement.setInt(1, food.getId());
            preStatement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Food> readAll() {
        List<Food> foodList = new ArrayList<>();
        try (
                Connection connection = DBManager.getInstance().getConnection();
                PreparedStatement preStatement = connection.prepareStatement(SQL_ALL_QUERY)
        )
        {

            preStatement.execute();

            ResultSet resultSet = preStatement.getResultSet();
            while(resultSet.next()){
                Food tier = new Food(
                        resultSet.getString(2), // name
                        resultSet.getDouble(3), // price
                        resultSet.getInt(4), // calories
                        resultSet.getInt(5), // stock
                        resultSet.getDate(6).toLocalDate(), //date
                        resultSet.getString(7) // supplier
                        );

                tier.setId(resultSet.getInt(1));
                foodList.add(tier);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return foodList;
    }
    //endregion
}
