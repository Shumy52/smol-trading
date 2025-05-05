package Events;

import java.time.LocalDateTime;

public class OrderCancelled implements Event {
    public final String orderId;
    public final LocalDateTime timestamp;

    public OrderCancelled(String orderId) {
        this.orderId = orderId;
        this.timestamp = LocalDateTime.now();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}