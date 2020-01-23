package entity;

public class Stuffs {
    private int stuffsId;
    private String name;
    private String description;
    private int price;

    public Stuffs() {
    }

    public Stuffs(String name, String description, int price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getStuffsId() {
        return stuffsId;
    }

    public void setStuffsId(int stuffsId) {
        this.stuffsId = stuffsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Stuffs{" +
                "stuffsId=" + stuffsId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
