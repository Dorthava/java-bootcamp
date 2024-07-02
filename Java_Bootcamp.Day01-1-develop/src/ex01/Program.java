package ex01;

public class Program {
    static public void main(String[] args) {
        User user1 = new User("Mike", 1000);
        User user2 = new User("John", 2000);
        System.out.println(user1.getIdentifier());
        System.out.println(user2.getIdentifier());
    }
}
