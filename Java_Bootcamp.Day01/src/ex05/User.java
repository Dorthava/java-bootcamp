package ex05;

public class User {
    private int identifier;
    private String name;
    private float balance;
    private final TransactionsLinkedList list;

    public User(String incomingName, int incomingBalance) {
        setIdentifier();
        setName(incomingName);
        setBalance(incomingBalance);
        list = new TransactionsLinkedList();
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

    public TransactionsLinkedList getTransactionsList() throws TransactionNotFoundException {
        return list;
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
            System.out.println(e.getMessage());
            System.exit(-1);
        }
    }
}