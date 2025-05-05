package Events;

import java.time.LocalDateTime;

public class TradeExecuted implements Event {
    public final String buyOrderId;
    public final String sellOrderId;
    public final int quantity;
    public final double price;
    public final LocalDateTime timestamp;

    public TradeExecuted(String buyOrderId, String sellOrderId, int quantity, double price) {
        this.buyOrderId = buyOrderId;
        this.sellOrderId = sellOrderId;
        this.quantity = quantity;
        this.price = price;
        this.timestamp = LocalDateTime.now();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}