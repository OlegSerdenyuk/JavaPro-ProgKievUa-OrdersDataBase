package service;

import dao.StuffsDao;
import entity.Stuffs;
import utils.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StuffsService extends Utils implements StuffsDao {
    private Connection connection = getConnection();

    @Override
    public void add(Stuffs stuffs) {
        if (!stuffsAlreadyExist(stuffs)) {
            String sql1 = "Insert into Stuffs (name, description, price)";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql1);
                preparedStatement.setString(1, stuffs.getName());
                preparedStatement.setString(2, stuffs.getDescription());
                preparedStatement.setInt(3, stuffs.getPrice());
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Sorry, this stuff is already exists. Try again.");
        }
        stuffs.setStuffsId(getIdByName(stuffs));
    }


    public boolean stuffsAlreadyExist(Stuffs stuffs) {
        boolean result = false;
        List<Stuffs> stuffsList = new ArrayList<>();
        stuffsList = getAll();
        for (Stuffs st : stuffsList) {
            if (st.getName().equals(stuffs.getName()))
                result = true;
        }
        return result;
    }

    public int getIdByName(Stuffs stuffs) {
        int result = 0;
        String sql2 = "Select id_stuff From Stuffs Where name = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql2);
            preparedStatement.setString(1, stuffs.getName());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = resultSet.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return  result;

    }

    public String getNameById(Stuffs stuffs) {
        String result = "";
        String sql3 = "Select name From Stuffs Where id_stuff = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql3);
            preparedStatement.setInt(1, stuffs.getStuffsId());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            result = resultSet.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Stuffs> getAll() {
        List<Stuffs> list = new ArrayList<>();
        String sql4 = "Select From Stuffs";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql4);
            while (resultSet.next()) {
                Stuffs stuffs = new Stuffs();
                stuffs.setStuffsId(resultSet.getInt("id_stuff"));
                stuffs.setName(resultSet.getString("name"));
                stuffs.setDescription(resultSet.getString("description"));
                stuffs.setPrice(resultSet.getInt("price"));
                list.add(stuffs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void update(Stuffs newStuffs, Stuffs oldStuffs) {
        String sql5 = "Update Stuffs Set name = ?, description = ?, price = ? Where name = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql5);
            preparedStatement.setString(1, newStuffs.getName());
            preparedStatement.setString(2, newStuffs.getDescription());
            preparedStatement.setInt(3, newStuffs.getPrice());
            preparedStatement.setString(4, oldStuffs.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Stuffs stuffs) {
        String sql6 = "Delete From Stuffs Where name = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql6);
            preparedStatement.setString(1, stuffs.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

