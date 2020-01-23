package dao;

import entity.Clients;
import entity.Orders;
import entity.Stuffs;

import java.util.List;

public interface OrdersDao {
    void add(Orders orders, Clients clients, Stuffs stuffs);
    List<Orders> getAll();
    void update(Orders newOrders, Orders oldOrders);
    void delete(Orders orders);
}
