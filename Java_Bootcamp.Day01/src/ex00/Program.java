package ex00;
class Program {
    static public void main(String[] args) {
        User user1 = new User(1, "John", 1000);
        User user2 = new User(2,"Mike", 2000);

        System.out.println("John balance: " + user1.getBalance());
        System.out.println("Mike balance: " + user2.getBalance());

        Transaction transaction1 = new Transaction(user2, user1, Transaction.Category.CREDIT, 1000);
        System.out.println("John balance: " + user1.getBalance());
        System.out.println("Mike balance: " + user2.getBalance());

        Transaction transaction2 = new Transaction(user2, user1, Transaction.Category.DEBIT, -1500);
        System.out.println("John balance: " + user1.getBalance());
        System.out.println("Mike balance: " + user2.getBalance());
        Transaction transaction3 = new Transaction(user2, user1, Transaction.Category.DEBIT, -1500);
        System.out.println("John balance: " + user1.getBalance());
        System.out.println("Mike balance: " + user2.getBalance());

        try {
            Transaction transaction4 = new Transaction(user2, user1, Transaction.Category.DEBIT, -1500);
            System.out.println("Этот текст не будет выведен.");
            System.out.println("Этот текст не будет выведен.");
        }  catch (IllegalArgumentException e) {
            System.out.println(e.toString());
        }


    }
}
