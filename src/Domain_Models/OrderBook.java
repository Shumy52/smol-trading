package Domain_Models;
import Events.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class OrderBook {
    private final Map<String, Order> orders = new HashMap<>();

    public void apply(Event e) {
        if (e instanceof OrderPlaced op) {
            orders.put(op.orderId, new Order(op.orderId, op.userId, op.isBuy, op.quantity, op.price));
        } else if (e instanceof OrderCancelled oc) {
            orders.remove(oc.orderId);
        } else if (e instanceof TradeExecuted te) {
            orders.remove(te.buyOrderId);
            orders.remove(te.sellOrderId);
        }
    }

    public Collection<Order> getActiveOrders() {
        return orders.values();
    }
}
