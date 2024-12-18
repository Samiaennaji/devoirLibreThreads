package org.example.dao;



import org.example.models.CustomerDao;
import org.example.models.CustomerModel;
import org.example.models.OrderModel;
import org.example.utils.DataConnection;

import java.sql.*;
import java.util.Date;

public class OrderDao {

    public static boolean addOrder(OrderModel order) {
        String query = "INSERT INTO Orders (id,date,amount, customer_id) VALUES (?,?,?,?)";
        try (Connection connection = DataConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1,order.getId());
            statement.setTimestamp(2, new Timestamp(order.getDate().getTime()));
            statement.setDouble(3,order.getAmount());
            statement.setInt(4,order.getCustomer().getId());
            return statement.executeUpdate() >0;

        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    public static OrderModel getOrderById(int id) {
        String query = "SELECT * FROM Orders WHERE id = ?";
        try(Connection connection=DataConnection.getConnection();
            PreparedStatement statement= connection.prepareStatement(query)){
            statement.setInt(1,id);
            ResultSet resultat= statement.executeQuery();
            if (resultat.next()){
                CustomerModel customer = CustomerDao.getCustomerById(resultat.getInt("customer_id"));
                return new OrderModel(
                        resultat.getInt("id"),
                        new Date(resultat.getTimestamp("date").getTime()),
                        resultat.getDouble("amount"),
                        customer
                );
            }
        }catch (SQLException e){
            e.printStackTrace();

        }
        return null;

    }
}
