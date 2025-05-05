package Events;

import java.time.LocalDateTime;

public class FundsDebited implements Event {
    public final String userId;
    public final double amount;
    public final LocalDateTime timestamp;

    public FundsDebited(String userId, double amount) {
        this.userId = userId;
        this.amount = amount;
        this.timestamp = LocalDateTime.now();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}