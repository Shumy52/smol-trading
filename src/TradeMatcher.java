import Events.*;
import java.util.*;
import Domain_Models.*;

import java.util.List;

// For now it only matches exact trades, so eye for eye.
// In the future we'll make partials, so one sell can mean more buy, lower prices are better etc.
public class TradeMatcher {
    List<TradeExecuted> match(Order newOrder, List<Order> existingOrders) {
        List<TradeExecuted> result = new ArrayList<>();

        for (Order other : existingOrders) {
            if (other.active || other.userId.equals(newOrder.userId)) continue;

            boolean oppositeType = newOrder.isBuy != other.isBuy;
            boolean priceMatch = newOrder.price == other.price;
            boolean qtyMatch = newOrder.quantity == other.quantity;

            if (oppositeType && priceMatch && qtyMatch) {
                result.add(new TradeExecuted(
                        UUID.randomUUID().toString(),
                        newOrder.orderId,
                        other.orderId,
                        newOrder.quantity,
                        newOrder.price
                ));
                break;
            }
        }

        return result;
    }

}
