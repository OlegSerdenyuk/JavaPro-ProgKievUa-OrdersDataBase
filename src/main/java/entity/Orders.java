package entity;

import service.ClientsService;
import service.StuffsService;

public class Orders {
    private int ordersId;
    private Clients clients = new Clients();
    private Stuffs stuffs = new Stuffs();

    public Orders() {
    }

    public Orders(Clients clients, Stuffs stuff) {
        this.clients = clients;
        this.stuffs = stuff;
    }

    public int getOrdersId() {
        return ordersId;
    }

    public void setOrdersId(int ordersId) {
        this.ordersId = ordersId;
    }

    public int getClientsId() {
        return clients.getId();
    }

    public void setClientsId(int id) {
        clients.setId(id);
    }

    public int getStuffsId() {
        return this.stuffs.getStuffsId();
    }

    public void setStuffsId(String id) {
        this.stuffs.setStuffsId(Integer.parseInt(id));
    }

    @Override
    public String toString() {
        return "Orders{" +
                "ordersId=" + ordersId +
                ", clientsName=" + new ClientsService().getNameSurnameById(clients) +
                ", stuffsName=" + new StuffsService().getNameById(stuffs) +
                '}';
    }
}
