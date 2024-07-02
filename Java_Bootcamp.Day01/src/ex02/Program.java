package ex02;

public class Program {
    static public void main(String[] args) {
        UsersList list = new UsersList();
        for(int i = 0; i != 20; ++i) {
            if(i < 10 || i > 15) {
                list.AddUser(new User("Ivan", i));
            } else {
                new User("Ivan", 1000);
            }
        }
        try {
            list.RetrieveUserID(30);
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }

        User user = list.RetrieveUserIndex(9);
        System.out.println("Индекс пользователя под индексом 9: " + user.getIdentifier());

        User user2 = list.RetrieveUserIndex(10);
        System.out.println("ID пользователя под индексом 9: " + user2.getIdentifier());

        System.out.println("Количество добавленных пользователей: " + list.RetrieveCount());
        try {
            System.out.println("Пользователь под ID 16 найден: " + list.RetrieveUserID(16).getIdentifier());
        } catch (UserNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
