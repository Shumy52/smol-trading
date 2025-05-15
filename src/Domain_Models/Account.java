package Domain_Models;

import Events.*;

/**
 * This is the class concerned with the ca-ching
 * It holds the current balance, or used to reconstruct in it the balance
 */
package Domain_Models;

import Events.Event;
import Events.FundsCredited;
import Events.FundsDebited;

import java.io.Serializable;

public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private final String userId;
    private double balance = 0;
    
    public Account(String userId) {
        this.userId = userId;
    }
    
    public void apply(Event event) {
        if (event instanceof FundsCredited fc) {
            this.balance += fc.amount;
        } else if (event instanceof FundsDebited fd) {
            this.balance -= fd.amount;
        }
    }
    
    public double getBalance() {
        return balance;
    }
    
    public String getUserId() {
        return userId;
    }
}