import Domain_Models.*;
import Events.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

class TradingSystem {
    private final EventStore eventStore = new EventStore();
    private final Map<String, Account> accounts = new HashMap<>();
    private final OrderBook orderBook = new OrderBook();

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
        double totalCost = quantity * price;
        if (isBuy) {
            eventStore.append(new FundsDebited(userId, totalCost));
        }

        eventStore.append(new OrderPlaced(orderId, userId, isBuy, quantity, price));
        replay();
    }

    public double getBalance(String userId) {
        return getAccount(userId).getBalance();
    }

    public Collection<Order> getOrders() {
        return orderBook.getActiveOrders();
    }
}
