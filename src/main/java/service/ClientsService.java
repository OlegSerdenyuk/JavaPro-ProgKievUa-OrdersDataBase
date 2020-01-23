package service;

import dao.ClientsDao;
import entity.Clients;
import utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientsService extends Utils implements ClientsDao {
    private Connection connection = getConnection();

    @Override
    public void add(Clients clients) {
        if (!clientsAlreadyExist(clients)) {
            String sql1 = "Insert into Clients (nameSurname, phone, address) values (?, ?, ?)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql1);
                preparedStatement.setString(1, clients.getNameSurname());
                preparedStatement.setString(2, clients.getPhone());
                preparedStatement.setString(3, clients.getAddress());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Sorry, this client is already exists. Try again.");
        }
        clients.setId(getIdByPhone(clients));
    }

    public boolean clientsAlreadyExist(Clients clients) {
        boolean result = false;
        List<Clients> clientsList = new ArrayList<>();
        clientsList = getAll();
        for (Clients cl : clientsList) {
            if (cl.getPhone().equals(clients.getPhone()))
                result = true;
        }
        return result;
    }

    public int getIdByPhone(Clients clients) {
        int result = 0;
        String sql2 = "Select id_client From Clients Where phone = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setString(1, clients.getPhone());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  result;
    }

    public String getNameSurnameById(Clients clients) {
        String result = "";
        String sql3 = "Select nameSurname From Clients Where id_client = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql3);
            preparedStatement.setInt(1, clients.getId());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = resultSet.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  result;
    }

    @Override
    public List<Clients> getAll() {
        List<Clients> list = new ArrayList<>();
        String sql4 = "Select From Clients";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql4);
            while (resultSet.next()) {
                Clients clients = new Clients();
                clients.setId(resultSet.getInt("id_client"));
                clients.setNameSurname(resultSet.getString("nameSurname"));
                clients.setPhone(resultSet.getString("phone"));
                clients.setAddress(resultSet.getString("address"));
                list.add(clients);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void update(Clients newClients, Clients oldClients) {
        String sql5 = "Update Clients Set nameSurname = ?, phone = ?, address = ? Where phone = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql5);
            preparedStatement.setString(1, newClients.getNameSurname());
            preparedStatement.setString(2, newClients.getPhone());
            preparedStatement.setString(3, newClients.getAddress());
            preparedStatement.setString(4, oldClients.getPhone());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void delete(Clients clients) {
        String sql6 = "Delete From Clients Where phone = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql6);
            preparedStatement.setString(1, clients.getPhone());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
