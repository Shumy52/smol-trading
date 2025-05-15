package Events;

public class OrderPlaced extends Event {
    private static final long serialVersionUID = 1L;
    public final String orderId;
    public final String userId;
    public final boolean isBuy; // true: buy, false: sell
    public final int quantity;
    public final double price;

    public OrderPlaced(String orderId, String userId, boolean isBuy, int quantity, double price) {
        this.orderId = orderId;
        this.userId = userId;
        this.isBuy = isBuy;
        this.quantity = quantity;
        this.price = price;
    }

}