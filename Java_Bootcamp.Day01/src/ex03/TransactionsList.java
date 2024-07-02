package ex03;

public interface TransactionsList {
    void add(Transaction transaction);
    void remove(String id) throws TransactionNotFoundException;
    Transaction[] toArray();
}