package ex00;
class User {
    private int identifier;
    private String name;
    private float balance;
    public User(int incomingIdentifier, String incomingName, int incomingBalance) {
        setIdentifier(incomingIdentifier);
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
    public void setIdentifier(int incomingIdentifier) {
        identifier = incomingIdentifier;
    }

    public void setName(String incomingName) {
        name = incomingName;
    }

    public void setBalance(float incomingBalance) {
        try {
            if(incomingBalance < 0) throw new IllegalArgumentException("Недостаточно денег на счете.");
            balance = incomingBalance;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }

}