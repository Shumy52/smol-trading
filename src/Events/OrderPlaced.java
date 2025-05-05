package Events;

import java.time.LocalDateTime;

public class OrderPlaced implements Event {
    public final String orderId;
    public final String userId;
    public final boolean isBuy; // true: buy, false: sell
    public final int quantity;
    public final double price;
    public final LocalDateTime timestamp;

    public OrderPlaced(String orderId, String userId, boolean isBuy, int quantity, double price) {
        this.orderId = orderId;
        this.userId = userId;
        this.isBuy = isBuy;
        this.quantity = quantity;
        this.price = price;
        this.timestamp = LocalDateTime.now();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}