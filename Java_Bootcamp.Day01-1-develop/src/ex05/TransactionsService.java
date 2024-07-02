package ex05;

import java.util.UUID;

public class TransactionsService {
    private final UsersList usersList;

    public TransactionsService() {
        usersList = new UsersList();
    }

    public int addUser(User user) {
        usersList.addUser(user);
        return user.getIdentifier();
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

    public void retrievingUserBalance(int id) throws UserNotFoundException {
        System.out.println(usersList.retrieveUserID(id).getName() + " - " + usersList.retrieveUserID(id).getBalance());
    }

    public Transaction[] retrievingTransfers(User user) {
        return user.getTransactionsList().toArray();
    }
    public Transaction[] retrievingTransfers(int id) {
        return usersList.retrieveUserIndex(id).getTransactionsList().toArray();
    }
    public boolean removingTransaction(int userId, String id) {
        boolean resultRemoving = false;
        try {
            User user = usersList.retrieveUserID(userId);
            user.getTransactionsList().remove(id);
            resultRemoving = true;
        } catch (UserNotFoundException e) {
            System.out.println("Пользователь не найден: " + e.getMessage());
        }
        return resultRemoving;
    }

    public Transaction[] checkTransaction(int userId) throws UserNotFoundException{
        User user = usersList.retrieveUserID(userId);
        return checkTransaction(user);
    }

    public void checkAllTransaction() {
        for(int i = 0; i != usersList.retrieveCount(); ++i) {
            Transaction[] transactions = checkTransaction(usersList.retrieveUserIndex(i));
            for(int j = 0; j != transactions.length; ++j) {
                User user = usersList.retrieveUserIndex(i) == transactions[j].getSender() ? transactions[j].getRecipient() : transactions[j].getSender();
                System.out.println(usersList.retrieveUserIndex(i).getName() + "(id = " + usersList.retrieveUserIndex(i).getIdentifier() + ") has an unacknowledged transfer id = " + transactions[j].getIdentifier() + " from " + user.getName() + "(id = " + user.getIdentifier() + ") for " + -transactions[j].getTransferAmount());
            }
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
                if(list[i].getIdentifier().toString().equals(anotherList.toArray()[j].getIdentifier().toString())) {
                    result = true;
                }
            }
            if(!result) {
                erroneousTransaction.add(list[i]);
            }
        }
        return erroneousTransaction.toArray();
    }


}
