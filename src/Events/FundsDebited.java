package Events;

public class FundsDebited extends Event{
    private static final long serialVersionUID = 1L;
    public final String userId;
    public final double amount;

    public FundsDebited(String userId, double amount) {
        this.userId = userId;
        this.amount = amount;
    }

}