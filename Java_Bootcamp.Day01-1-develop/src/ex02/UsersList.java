package ex02;

public class UsersList {
    private final UsersArrayList list;
    public UsersList() {
        list = new UsersArrayList();
    }

    public void AddUser(User user) {
        list.pushBack(user);
    }

    public User RetrieveUserID(int id) throws UserNotFoundException {
        return list.findUserID(id);
    }

    public User RetrieveUserIndex(int index) {
        return list.findUserIndex(index);
    }

    public int RetrieveCount() {
        return list.getIndex();
    }

}
