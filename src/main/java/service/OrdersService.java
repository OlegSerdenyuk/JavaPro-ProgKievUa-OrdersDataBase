package service;

import dao.OrdersDao;
import entity.Clients;
import entity.Orders;
import entity.Stuffs;
import utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrdersService extends Utils implements OrdersDao {
    private Connection connection = getConnection();

    @Override
    public void add(Orders orders, Clients clients, Stuffs stuffs) {
        if (!ordersAlreadyExist(orders)) {
            String sql1 = "Insert into Orders (id_orders, id_clients, id_stuffs) value (?, ?, ?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql1);
                preparedStatement.setInt(1, orders.getOrdersId());
                preparedStatement.setInt(2, orders.getClientsId());
                preparedStatement.setInt(3, orders.getStuffsId());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Sorry, this order is already exists. Try again.");
        }
    }

    public boolean ordersAlreadyExist(Orders orders) {
        boolean result = false;
        List<Orders> ordersList = new ArrayList<>();
        ordersList = getAll();
        for (Orders or : ordersList) {
            if (or.getOrdersId() == orders.getOrdersId() && or.getClientsId() == orders.getClientsId()
                    && or.getStuffsId() == orders.getStuffsId());
            result = true;
        }
        return result;
    }

    @Override
    public List<Orders> getAll() {
        List<Orders> ordersList = new ArrayList<>();
        String sql2 = "Select From Orders";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql2);
            while (resultSet.next()) {
                Orders orders = new Orders();
                orders.setOrdersId(resultSet.getInt("id_orders"));
                orders.setClientsId(resultSet.getInt("id_clients"));
                orders.setStuffsId(resultSet.getString("id_stuffs"));
                ordersList.add(orders);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ordersList;
    }

    @Override
    public void update(Orders newOrders, Orders oldOrders) {
        String sql3 = "Update Orders Set id_orders = ?, id_clients = ?, id_stuffs = ? Where id_orders = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql3);
            preparedStatement.setInt(1, newOrders.getOrdersId());
            preparedStatement.setInt(2, newOrders.getClientsId());
            preparedStatement.setInt(3, newOrders.getStuffsId());
            preparedStatement.setInt(4, oldOrders.getOrdersId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Orders orders) {
        String sql4 = "Delete From Orders Where id_orders = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql4);
            preparedStatement.setInt(1, orders.getOrdersId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
