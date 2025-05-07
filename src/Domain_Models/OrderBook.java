package Domain_Models;
import Events.*;

import java.util.*;

// Though process for this... This is a REBUILDER
// I was thinking about making this the one that matches trades together
// But that would break some principles... whatever
// Point is, this is not the one that should do that.

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

    public List<Order> getActiveOrders() {return new ArrayList<Order>(orders.values());}

    public Order findOrderById(String buyOrderId) {
        return orders.get(buyOrderId);
    }
}
