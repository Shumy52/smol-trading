import Events.*;
import java.util.*;
import Domain_Models.*;

import java.util.List;

// For now, it only matches exact trades, so eye for eye.
// In the future we'll make partials, so one sell can mean more buy, lower prices are better etc.
public class TradeMatcher {
    List<TradeExecuted> match(Order newOrder, List<Order> existingOrders) {
        List<TradeExecuted> result = new ArrayList<>();

        for (Order other : existingOrders) {
            if (other.userId.equals(newOrder.userId)) continue;

            boolean oppositeType = newOrder.isBuy != other.isBuy;
            boolean priceMatch = newOrder.price == other.price;
            boolean qtyMatch = newOrder.quantity == other.quantity;

            if (oppositeType && priceMatch && qtyMatch) {
                String buyOrderId = newOrder.isBuy ? newOrder.orderId : other.orderId;
                String sellOrderId = newOrder.isBuy ? other.orderId : newOrder.orderId;

                // Active status is now managed by OrderBook through events
                // No need to set active flags on the orders directly

                result.add(new TradeExecuted(
                        UUID.randomUUID().toString(),
                        buyOrderId,
                        sellOrderId,
                        newOrder.quantity,
                        newOrder.price
                ));
                break;
            }
        }

        return result;
    }
}