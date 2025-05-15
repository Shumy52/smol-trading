package Events;

public class TradeExecuted extends Event{
    private static final long serialVersionUID = 1L;
    public final String tradeId;
    public final String buyOrderId;
    public final String sellOrderId;
    public final int quantity;
    public final double price;

    public TradeExecuted(String tradeId, String buyOrderId, String sellOrderId, int quantity, double price) {
        this.tradeId = tradeId;
        this.buyOrderId = buyOrderId;
        this.sellOrderId = sellOrderId;
        this.quantity = quantity;
        this.price = price;
    }

}