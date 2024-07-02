package ex04;

import java.util.UUID;

public class TransactionsService {
    private UsersList usersList;

    public TransactionsService() {
        usersList = new UsersList();
    }

    public void addUser(User user) {
        usersList.addUser(user);
    }

    public void addUser(String incomingName, int incomingBalance) {
        usersList.addUser(new User(incomingName, incomingBalance));
    }

    public void performingTransferTransaction(int recipientId, int senderId, float incoming) throws UserNotFoundException, IllegalTransactionException {
        Transaction transactionRecipient = new Transaction(usersList.retrieveUserID(recipientId), usersList.retrieveUserID(senderId), Transaction.Category.CREDIT, -incoming);
        Transaction transactionSender = new Transaction(usersList.retrieveUserID(recipientId), usersList.retrieveUserID(senderId), Transaction.Category.DEBIT, incoming);

        UUID id = UUID.randomUUID();
        transactionRecipient.setIdentifier(id);
        transactionSender.setIdentifier(id);

        usersList.retrieveUserID(recipientId).getTransactionsList().add(transactionRecipient);
        usersList.retrieveUserID(senderId).getTransactionsList().add(transactionSender);
    }
    public float retrievingUserBalance(User user) {
        return user.getBalance();
    }

    public Transaction[] retrievingTransfers(User user) {
        return user.getTransactionsList().toArray();
    }

    public void removingTransaction(int userId, String id) {
        try {
            User user = usersList.retrieveUserID(userId);
            user.getTransactionsList().remove(id);
        } catch (UserNotFoundException e) {
            System.out.println("Пользователь не найден: " + e.getMessage());
        }
    }

    public Transaction[] checkTransaction(User user) {
        Transaction[] list = user.getTransactionsList().toArray();
        TransactionsLinkedList erroneousTransaction = new TransactionsLinkedList();
        for(int i = 0; i != user.getTransactionsList().getCount(); ++i) {
            TransactionsLinkedList anotherList;
            if(list[i].getRecipient() == user) {
                anotherList = list[i].getSender().getTransactionsList();
            } else {
                anotherList = list[i].getRecipient().getTransactionsList();
            }
            boolean result = false;
            for(int j = 0; j != anotherList.getCount(); ++j) {
                if(list[i] == anotherList.toArray()[j]) {
                    result = true;
                }
            }
            if(!result) {
                erroneousTransaction.add(list[i]);
            }
        }
        System.out.println(erroneousTransaction.toArray().length);
        return erroneousTransaction.toArray();
    }


}
