package ex05;

public class UsersList {
    private final UsersArrayList list;
    public UsersList() {
        list = new UsersArrayList();
    }

    public void addUser(User user) {
        list.pushBack(user);
    }

    public User retrieveUserID(int id) throws UserNotFoundException {
        return list.findUserID(id);
    }

    public User retrieveUserIndex(int index) {
        return list.findUserIndex(index);
    }

    public int retrieveCount() {
        return list.getIndex();
    }

}

