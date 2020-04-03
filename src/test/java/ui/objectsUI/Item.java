package ui.objectsUI;

public class Item {

    private String name;
    private String description;
    private int quantity;
    private String currency;
    private int price;
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getAmount() {
        if (quantity == 0){
            long amount = 1 * price;
            return amount;
        }
        long amount = quantity * price;
        return amount;
    }


}
