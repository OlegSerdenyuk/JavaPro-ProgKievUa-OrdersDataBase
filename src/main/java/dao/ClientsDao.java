package dao;

import entity.Clients;
import java.util.List;

public interface ClientsDao {
    void add(Clients clients);
    List<Clients> getAll();
    void update(Clients newClients, Clients oldClients);
    void delete(Clients clients);
}
