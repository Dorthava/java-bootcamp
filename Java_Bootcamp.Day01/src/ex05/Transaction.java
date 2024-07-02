package ex05;

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

    public Transaction(User incomingRecipient, User incomingSender, Category incomingCategory, float incomingAmount) throws IllegalArgumentException, IllegalTransactionException {
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

    public void setIdentifier(UUID id) { identifier = id; }

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
        if ((transferCategory == Category.DEBIT && incomingAmount < 0) || (transferCategory == Category.CREDIT && incomingAmount >= 0)) {
            throw new IllegalArgumentException("Ошибка при попытке настроить сумму платежа.");
        }
        transferAmount = incomingAmount;
    }

    private void setIdentifier() {
        identifier = UUID.randomUUID();
    }
    private void initTransaction() throws IllegalTransactionException {
        if((transferCategory == Category.CREDIT && sender.getBalance() < Math.abs(transferAmount)) || (transferCategory == Category.DEBIT && recipient.getBalance() < transferAmount)) {
            throw new IllegalTransactionException("Ошибка при попытке произвести транзакцию.");
        }
        if(transferCategory == Category.CREDIT) sender.setBalance(sender.getBalance() + transferAmount);
        else recipient.setBalance(recipient.getBalance() + transferAmount);
    }
}