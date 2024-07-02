package ex05;

public class TransactionsLinkedList implements TransactionsList {
    private static class Node {
        private final Transaction transaction;
        private Node next;
        private Node previous;

        private Node(Transaction transaction, Node next, Node previous) {
            this.transaction = transaction;
            this.next = next;
            this.previous = previous;
        }

    }
    private Node current;
    private int count;
    public TransactionsLinkedList() {
        current = null;
        count = 0;
    }

    public int getCount() {
        return count;
    }

    public void add(Transaction transaction) {
        Node newNode;
        if(current != null) {
            newNode = new Node(transaction, null, current);
            current.next = newNode;
            newNode.previous = current;
            current = newNode;
        } else {
            current = new Node(transaction, null, null);
        }

        ++count;
    }

    public void remove(String id) throws TransactionNotFoundException {
        Node tmp = current;
        boolean result = true;
        for(int i = 0; i != count; ++i) {
            if(id.equals(tmp.transaction.getIdentifier().toString())) {
                if(tmp.next == null && tmp.previous == null) {
                    current = null;
                } else if(tmp.next != null && tmp.previous != null) {
                    tmp.previous.next = tmp.next;
                    tmp.next.previous = tmp.previous;
                } else if(tmp.next == null) {
                    tmp.previous.next = null;
                } else {
                    tmp.next.previous = null;
                }
                result = false;
                break;
            }
            tmp = tmp.previous;
        }
        if(result) throw new TransactionNotFoundException("Транзакции под индексом: " + id +  " найдено не было.");
        --count;
    }

    public Transaction[] toArray() {
     Transaction[] array = new Transaction[count];
     Node tmp = current;
     for(int i = 0; i != count; ++i) {
         array[i] = tmp.transaction;
         tmp = tmp.previous;
     }
     return array;
    }
}
