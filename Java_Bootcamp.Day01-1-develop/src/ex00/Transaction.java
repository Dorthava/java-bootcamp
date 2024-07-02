package ex00;

import java.util.UUID;
public class Transaction {
    public enum Category {
        DEBIT, CREDIT
    }
    private UUID identifier;
    private User recipient;
    private User sender;
    private Category transferCategory;
    private float transferAmount;

    public Transaction(User incomingRecipient, User incomingSender, Category incomingCategory, int incomingAmount) throws IllegalArgumentException {
        setIdentifier();
        setRecipient(incomingRecipient);
        setSender(incomingSender);
        setTransferCategory(incomingCategory);
        setTransferAmount(incomingAmount);
        initTransaction();
    }

    public UUID getIdentifier() {
        return identifier;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    public Category getTransferCategory() {
        return transferCategory;
    }

    public float getTransferAmount() {
        return transferAmount;
    }

    public void setIdentifier() {
        identifier = UUID.randomUUID();
    }

    public void setRecipient(User incomingRecipient) {
        recipient = incomingRecipient;
    }

    public void setSender(User incomingSender) {
        sender = incomingSender;
    }

    public void setTransferCategory(Category incomingCategory) {
        transferCategory = incomingCategory;
    }

    public void setTransferAmount(float incomingAmount) throws IllegalArgumentException {
        if ((transferCategory == Category.DEBIT && incomingAmount >= 0) || (transferCategory == Category.CREDIT && incomingAmount < 0)) {
            throw new IllegalArgumentException("Ошибка при попытке настроить сумму платежа.");
        }
        transferAmount = incomingAmount;
    }

    private void initTransaction() throws  IllegalArgumentException {
        if((transferCategory == Category.CREDIT && recipient.getBalance() < Math.abs(transferAmount)) || (transferCategory == Category.DEBIT && sender.getBalance() < transferAmount)) {
            throw new IllegalArgumentException("Ошибка при попытке произвести транзакцию.");
        }
        sender.setBalance(sender.getBalance() - transferAmount);
        recipient.setBalance(recipient.getBalance() + transferAmount);
    }
}
