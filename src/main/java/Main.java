import dao.ClientsDao;
import dao.OrdersDao;
import dao.StuffsDao;
import entity.Clients;
import entity.Orders;
import entity.Stuffs;
import service.ClientsService;
import service.OrdersService;
import service.StuffsService;

import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OrdersDao ordersService = new OrdersService();
        ClientsDao clientsService = new ClientsService();
        StuffsDao stuffsService = new StuffsService();
        Menu menu = new Menu();
        int enter = 0;
        menu.showItem();

        while (true) {
            System.out.println("Choose a item: ");
            enter = scanner.nextInt();
            switch (enter) {
                case 1: {
                    clientsService.add(menu.addClients());
                    System.out.println("New client added.");
                    break;
                }
                case 2: {
                    stuffsService.add(menu.addStuffs());
                    System.out.println("New stuff added.");
                    break;
                }
                case 3: {
                    LinkedList<Object> objectLinkedList = (LinkedList<Object>)menu.createOrders();
                    ordersService.add((Orders) objectLinkedList.get(0), (Clients) objectLinkedList.get(1),
                            (Stuffs) objectLinkedList.get(2));
                    System.out.println("New order added.");
                    break;
                }
                case 4: {
                    menu.showAllClients();
                    break;
                }
                case 5: {
                    menu.showAllStuffs();
                    break;
                }
                case 6: {
                    menu.showAllOrders();
                    break;
                }
                case 7: {
                    System.exit(0);
                    break;
                }
            }
        }
    }
}
