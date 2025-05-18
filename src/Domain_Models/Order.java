package Domain_Models;

public class Order {
    public final String orderId;
    public final String userId;
    public final boolean isBuy;
    public final int quantity;
    public final double price;

    public Order(String orderId, String userId, boolean isBuy, int quantity, double price) {
        this.orderId = orderId;
        this.userId = userId;
        this.isBuy = isBuy;
        this.quantity = quantity;
        this.price = price;
    }

   
}
