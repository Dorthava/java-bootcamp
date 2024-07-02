package ex04;
public class Program {
    static public void main(String[] args) {
        TransactionsService service = new TransactionsService();

        User user1 = new User("Adel", 1000);
        User user2 = new User("John", 1000);

        System.out.println("Adel balance before: " + service.retrievingUserBalance(user1));
        System.out.println("John balance before: " + service.retrievingUserBalance(user2));

        service.addUser(user1);
        service.addUser(user2);

        try {
            service.performingTransferTransaction(user1.getIdentifier(), user2.getIdentifier(), 500);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Adel balance: " + service.retrievingUserBalance(user1));
        System.out.println("John balance: " + service.retrievingUserBalance(user2));

        Transaction[] transactions = service.retrievingTransfers(user1);

        System.out.println("Удаляем эту транзакцию у пользователя Adel:" + transactions[0].getIdentifier().toString());
        service.removingTransaction(user1.getIdentifier(), transactions[0].getIdentifier().toString());

        System.out.println("Сравниваем транзакции: ");
        Transaction[] errorTransactions = service.checkTransaction(user2);
        System.out.println("Мы нашли транзакцию, у которой нет пары(у пользователя John): " + errorTransactions[0].getIdentifier().toString());
    }
}

