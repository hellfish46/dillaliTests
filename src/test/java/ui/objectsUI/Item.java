package ui.objectsUI;

public class Item {

    private String name;
    private String description;
    private int quantity;
    private String currency;
    private double price;
   // private long amount;

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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        if (quantity == 0){
            double amount = 1 * price;
            return amount;
        }
        double amount = quantity * price;
        return amount;
    }


}
