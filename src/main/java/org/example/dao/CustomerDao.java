package org.example.models;

import org.example.models.CustomerModel;
import org.example.utils.DataConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDao {


    public static CustomerModel getCustomerById(int id) {
        String query = "SELECT * FROM Customer WHERE id = ?";
        try (Connection connection = DataConnection.getConnection();
             PreparedStatement statement= connection.prepareStatement(query)){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new CustomerModel(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone")
                );
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static boolean addCustomer(CustomerModel customer) {
        String query = "INSERT INTO Customer(id,name,email,phone) VALUES(?,?,?,?)";
        try(Connection connection= DataConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(query)){
            statement.setInt(1,customer.getId());
            statement.setString(2,customer.getName());
            statement.setString(3,customer.getEmail());
            statement.setString(4,customer.getPhone());
            return statement.executeUpdate() >0;
        }catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
}
