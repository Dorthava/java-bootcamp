package ex02;

import ex02.UserIdsGenerator;

public class User {
    private int identifier;
    private String name;
    private float balance;
    public User(String incomingName, int incomingBalance) {
        setIdentifier();
        setName(incomingName);
        setBalance(incomingBalance);
    }
    public int getIdentifier() {
        return identifier;
    }
    public String getName() {
        return name;
    }
    public float getBalance() {
        return balance;
    }
    public void setIdentifier() {
        identifier = UserIdsGenerator.getInstance().generateId();
    }

    public void setName(String incomingName) {
        name = incomingName;
    }

    public void setBalance(float incomingBalance) {
        try {
            if(incomingBalance < 0) throw new IllegalArgumentException("Недостаточно денег на счете.");
            balance = incomingBalance;
        } catch (IllegalArgumentException e) {
            System.out.println(e.toString());
            System.exit(-1);
        }
    }
}
