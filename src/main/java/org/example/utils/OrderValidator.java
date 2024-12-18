package org.example.utils;

import org.example.models.OrderModel;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.List;

public class OrderValidator {

    private static final String OUTPUT_DIRECTORY = "data/Output ";
    private static final String ERROR_DIRECTORY = "data/Error ";


    public static boolean customerExists(int customerId){
        try(Connection connection = DataConnection.getConnection()){
            String query = "SELECT COUNT(*) FROM customer WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1,customerId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                return resultSet.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;  }


    public static boolean isOrderValid(OrderModel order){
        return order.getAmount()>0 && order.getCustomer().getId() >0 ;
    }

    public static void saveOrderToDatabase(OrderModel order){
        try(Connection conn = DataConnection.getConnection()){
            String query = "INSERT INTO orders (id, date , amount , customer_id) VALUES (?,?,?,?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1,order.getId());
            stmt.setTimestamp(2, new Timestamp(order.getDate().getTime()));
            stmt.setDouble(3, order.getAmount());
            stmt.setInt(4,order.getCustomer().getId());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}