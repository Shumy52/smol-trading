import Domain_Models.*;
import Events.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TradingSystem {
    private final EventStore eventStore = new EventStore();
    private final Map<String, Account> accounts = new HashMap<>();
    private final OrderBook orderBook = new OrderBook();
    private final TradeMatcher matcher = new TradeMatcher();

    public void replay() {
        for (Event e : eventStore.getAllEvents()) {
            if (e instanceof FundsDebited fd) {
                getAccount(fd.userId).apply(e);
            } else if (e instanceof FundsCredited fc) {
                getAccount(fc.userId).apply(e);
            } else {
                orderBook.apply(e);
            }
        }
    }

    private Account getAccount(String userId) {
        return accounts.computeIfAbsent(userId, Account::new);
    }

    public void placeOrder(String orderId, String userId, boolean isBuy, int quantity, double price) {

        eventStore.append(new OrderPlaced(orderId, userId, isBuy, quantity, price));

        // Here should be the code for "trading" aka a buy and a sell match
        Order newOrder = new Order(orderId, userId, isBuy, quantity, price);
        List<TradeExecuted> matches = matcher.match(newOrder, orderBook.getActiveOrders());

        for (TradeExecuted trade : matches) {
            eventStore.append(trade);

            Order buyOrder = isBuy ? newOrder : orderBook.findOrderById(trade.buyOrderId);
            Order sellOrder = isBuy ? orderBook.findOrderById(trade.sellOrderId) : newOrder;

            double totalValue = trade.quantity * trade.price;
            eventStore.append(new FundsCredited(sellOrder.userId, totalValue));
            eventStore.append(new FundsDebited(buyOrder.userId, totalValue));
        }

        replay();
    }

    public double getBalance(String userId) {
        return getAccount(userId).getBalance();
    }

    public Collection<Order> getOrders() {
        return orderBook.getActiveOrders();
    }
}
