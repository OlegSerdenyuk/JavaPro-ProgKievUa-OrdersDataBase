import entity.Clients;
import entity.Orders;
import entity.Stuffs;
import service.ClientsService;
import service.OrdersService;
import service.StuffsService;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public void showItem() {
        System.out.println("________MENU________");
        System.out.println(" 1 - Add clients");
        System.out.println(" 2 - Add stuffs");
        System.out.println(" 3 - Registration of orders");
        System.out.println(" 4 - Show all clients");
        System.out.println(" 5 - Show all stuffs");
        System.out.println(" 6 - Show all orders");
        System.out.println(" 7 - Exit\n");
    }

    public Clients addClients() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a name and surname (in formate - nameSurname): ");
        String nameSurname = scanner.nextLine();
        System.out.println("Enter a phone: ");
        String phone = scanner.nextLine();
        System.out.println("Enter a address: ");
        String address = scanner.nextLine();
        return new Clients(nameSurname, phone, address);
    }

    public Stuffs addStuffs() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter a name: ");
        String name = scanner.nextLine();
        System.out.println("Enter a description: ");
        String description = scanner.nextLine();
        System.out.println("Enter a price: ");
        int price = scanner.nextInt();
        return new Stuffs(name, description, price);
    }

    public List<Object> createOrders() {
        List<Object> objectList = new LinkedList<>();
        Scanner scanner = new Scanner(System.in);
        List<Clients> clientsList = new ArrayList<>();
        clientsList = new ClientsService().getAll();
        List<Stuffs> stuffsList = new ArrayList<>();
        stuffsList = new StuffsService().getAll();
        Clients clients = new Clients();
        Stuffs stuffs = new Stuffs();
        int id_clients;
        int id_stuffs;

        System.out.println("Choose id client for which you want to do order: ");
        for (Clients cl : clientsList) {
            System.out.println(cl.getId() + " : " + cl.getNameSurname());
        }
        id_clients = scanner.nextInt();
        for (Clients cl : clientsList) {
            if (id_clients == cl.getId()) {
                clients.setId(cl.getId());
                clients.setNameSurname(cl.getNameSurname());
                clients.setPhone(cl.getPhone());
                clients.setAddress(cl.getAddress());
            }
        }

        System.out.println("Choose id stuff for which you want to do order: ");
        for (Stuffs st : stuffsList) {
            System.out.println(st.getStuffsId() + " : " + st.getName());
        }
        id_stuffs = scanner.nextInt();
        for (Stuffs st : stuffsList) {
            if (id_stuffs == st.getStuffsId()) {
                stuffs.setStuffsId(st.getStuffsId());
                stuffs.setName(st.getName());
                stuffs.setDescription(st.getDescription());
                stuffs.setPrice(st.getPrice());
            }
        }

        Orders orders = new Orders(clients, stuffs);
        objectList.add(orders);
        objectList.add(clients);
        objectList.add(stuffs);
        return objectList;
    }

    public void showAllClients() {
        List<Clients> clientsList = new ArrayList<>();
        clientsList = new ClientsService().getAll();
        for (Clients cl : clientsList) {
            System.out.println(cl);
        }
    }

    public void showAllStuffs() {
        List<Stuffs> stuffsList = new ArrayList<>();
        stuffsList = new StuffsService().getAll();
        for (Stuffs st : stuffsList) {
            System.out.println(st);
        }
    }

    public void showAllOrders() {
        List<Orders> ordersList = new ArrayList<>();
        ordersList = new OrdersService().getAll();
        for (Orders or : ordersList) {
            System.out.println(or);
        }
    }
}
