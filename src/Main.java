import Domain_Models.Order;
import java.util.UUID;

// Claude 3.7 Sonnet wrote most of this. I just rounded the sharp edges.
public class Main {
    public static void main(String[] args) {
        // Create the trading system
        TradingSystem tradingSystem = new TradingSystem();
        
        // Define user IDs
        String alice = "alice123";
        String bob = "bob456";
        String charlie = "charlie789";
        
        System.out.println("===== TRADING SYSTEM DEMONSTRATION =====\n");
        
        // Check initial balances
        System.out.println("Initial balances:");
        System.out.println("Alice: $" + tradingSystem.getBalance(alice));
        System.out.println("Bob: $" + tradingSystem.getBalance(bob));
        System.out.println("Charlie: $" + tradingSystem.getBalance(charlie));
        System.out.println();
        
        // Place some orders that won't immediately match
        String aliceBuyOrder = UUID.randomUUID().toString();
        System.out.println("Alice places a buy order for 10 shares at $50 each");
        tradingSystem.placeOrder(aliceBuyOrder, alice, true, 10, 50);
        
        String bobBuyOrder = UUID.randomUUID().toString();
        System.out.println("Bob places a buy order for 5 shares at $45 each");
        tradingSystem.placeOrder(bobBuyOrder, bob, true, 5, 45);
        
        // Display active orders
        System.out.println("\nActive orders after initial placements:");
        for (Order order : tradingSystem.getOrders()) {
            String type = order.isBuy ? "BUY" : "SELL";
            System.out.println(order.userId + ": " + type + " " + order.quantity + 
                              " shares at $" + order.price + " (Order ID: " + order.orderId + ")");
        }
        System.out.println();
        
        // Place a matching sell order
        String charlieSellOrder = UUID.randomUUID().toString();
        System.out.println("Charlie places a sell order for 10 shares at $50 each (should match with Alice's order)");
        tradingSystem.placeOrder(charlieSellOrder, charlie, false, 10, 50);
        
        // Check balances after the trade
        System.out.println("\nBalances after trade execution:");
        System.out.println("Alice: $" + tradingSystem.getBalance(alice));
        System.out.println("Bob: $" + tradingSystem.getBalance(bob));  
        System.out.println("Charlie: $" + tradingSystem.getBalance(charlie));
        
        // Display remaining active orders
        System.out.println("\nRemaining active orders:");
        if (tradingSystem.getOrders().isEmpty()) {
            System.out.println("No active orders");
        } else {
            for (Order order : tradingSystem.getOrders()) {
                String type = order.isBuy ? "BUY" : "SELL";
                System.out.println(order.userId + ": " + type + " " + order.quantity + 
                                  " shares at $" + order.price + " (Order ID: " + order.orderId + ")");
            }
        }
        
        // Place another set of orders to demonstrate more complex matching
        System.out.println("\n===== SECOND ROUND OF ORDERS =====");
        
        String aliceSellOrder = UUID.randomUUID().toString();
        System.out.println("\nAlice places a sell order for 5 shares at $45 each (should match with Bob's order)");
        tradingSystem.placeOrder(aliceSellOrder, alice, false, 5, 45);
        
        // Check final balances
        System.out.println("\nFinal balances:");
        System.out.println("Alice: $" + tradingSystem.getBalance(alice));
        System.out.println("Bob: $" + tradingSystem.getBalance(bob));
        System.out.println("Charlie: $" + tradingSystem.getBalance(charlie));

        System.out.println("\nRemaining active orders:");
        if (tradingSystem.getOrders().isEmpty()) {
            System.out.println("No active orders");
        } else {
            for (Order order : tradingSystem.getOrders()) {
                String type = order.isBuy ? "BUY" : "SELL";
                System.out.println(order.userId + ": " + type + " " + order.quantity +
                        " shares at $" + order.price + " (Order ID: " + order.orderId + ")");
            }
        }
        
        System.out.println("\n===== DEMONSTRATION COMPLETE =====");
    }
}