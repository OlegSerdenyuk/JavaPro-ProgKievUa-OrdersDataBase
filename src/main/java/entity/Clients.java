package entity;

public class Clients {
    private int clientsId;
    private String nameSurname;
    private String phone;
    private String address;

    public Clients() {
    }

    public Clients(String nameSurname, String phone, String address) {
        this.nameSurname = nameSurname;
        this.phone = phone;
        this.address = address;
    }

    public int getId() {
        return clientsId;
    }

    public void setId(int id) {
        this.clientsId = id;
    }

    public String getNameSurname() {
        return nameSurname;
    }

    public void setNameSurname(String nameSurname) {
        this.nameSurname = nameSurname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Clients{" +
                "clientsId=" + clientsId +
                ", nameSurname='" + nameSurname + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
