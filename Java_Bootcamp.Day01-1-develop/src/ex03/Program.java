package ex03;

public class Program {
    static public void main(String[] args) {
        User user1 = new User("Ivan", 1000);
        User user2 = new User("Adel", 2000);

        Transaction transaction1 = new Transaction(user1, user2, Transaction.Category.CREDIT, 1000);
        Transaction transaction2 = new Transaction(user1, user2, Transaction.Category.DEBIT, -1000);

        user1.getTransactionsList().add(transaction1);
        user1.getTransactionsList().add(transaction2);

        Transaction[] array = user1.getTransactionsList().toArray();
        for(Transaction i : array) {
            System.out.println(i.getIdentifier().toString());
        }

        try {
            user1.getTransactionsList().remove("123");
        } catch (TransactionNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println("Мы хотим удалить ключ: " + transaction1.getIdentifier().toString());
            user1.getTransactionsList().remove(transaction1.getIdentifier().toString());
            array = user1.getTransactionsList().toArray();
            for(Transaction i : array) {
                System.out.println("Остался ключ: " + i.getIdentifier().toString());
            }
        } catch (TransactionNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
