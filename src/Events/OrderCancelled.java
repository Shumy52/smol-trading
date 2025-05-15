package Events;

public class OrderCancelled extends Event {
    private static final long serialVersionUID = 1L;
    public final String orderId;

    public OrderCancelled(String orderId) {
        this.orderId = orderId;
    }

}