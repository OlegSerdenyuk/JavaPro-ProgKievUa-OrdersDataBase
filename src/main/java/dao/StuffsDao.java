package dao;

import entity.Stuffs;
import java.util.List;

public interface StuffsDao {
    void add(Stuffs stuffs);
    List<Stuffs> getAll();
    void update(Stuffs newStuffs, Stuffs oldStuffs);
    void delete(Stuffs stuffs);
}
