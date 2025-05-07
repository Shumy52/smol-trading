package Domain_Models;

import Events.*;

/**
 * This is the class concerned with the ca-ching
 * It holds the current balance, or used to reconstruct in it the balance
 */
public class Account {
    private final String userId;
    private double balance = 0;

    public Account(String userId) {
        this.userId = userId;
    }

    public void apply(Event event){
        if(event instanceof FundsDebited fd && fd.userId.equals(userId)){
            balance -= fd.amount; // Yes, balances can be also negative.
        }
        else if(event instanceof FundsCredited fc && fc.userId.equals(userId)){
            balance += fc.amount;
        }
    }

    public double getBalance(){
        return balance;
    }
}
