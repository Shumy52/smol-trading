package Domain_Models;
import Events.*;

import java.util.*;

// Though process for this... This is a REBUILDER
// I was thinking about making this the one that matches trades together
// But that would break some principles... whatever
// Point is, this is not the one that should do that.

public class OrderBook {
    private final Map<String, Order> activeOrders = new HashMap<>();
    private final Map<String, Order> inactiveOrders = new HashMap<>();
    // Alive NPCs and dead NPCs, remember?

    public void apply(Event e) {
        if (e instanceof OrderPlaced op) {
            // New orders always go to active map, no point in adding dead orders
            activeOrders.put(op.orderId, new Order(op.orderId, op.userId, op.isBuy, op.quantity, op.price));
        } else if (e instanceof OrderCancelled oc) {
            // Move cancelled order from active to inactive
            Order order = activeOrders.remove(oc.orderId);
            if (order != null) {
                inactiveOrders.put(oc.orderId, order);
            }
        } else if (e instanceof TradeExecuted te) {
            // Move both executed orders from active to inactive
            Order buyOrder = activeOrders.remove(te.buyOrderId);
            Order sellOrder = activeOrders.remove(te.sellOrderId);
            
            if (buyOrder != null) {
                inactiveOrders.put(te.buyOrderId, buyOrder);
            }
            
            if (sellOrder != null) {
                inactiveOrders.put(te.sellOrderId, sellOrder);
            }
        }
    }

    public List<Order> getActiveOrders() {
        return new ArrayList<>(activeOrders.values());
    }

    public Order findOrderById(String orderId) {
        // Check active orders first, then inactive if not found
        Order order = activeOrders.get(orderId);
        if (order == null) {
            order = inactiveOrders.get(orderId);
        }
        return order;
    }
}