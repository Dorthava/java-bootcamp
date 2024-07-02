package ex05;

public class UsersArrayList {
    private User[] arrayOfUsers;
    private int index;
    public UsersArrayList() {
        arrayOfUsers = new User[10];
    }

    public User findUserIndex(int ind) {
        if(ind < 0 || ind >= index) return null;
        return arrayOfUsers[ind];
    }

    public User findUserID(int id) throws UserNotFoundException {
        int indexFind = -1;
        for(int i = 0; i != index; ++i) {
            if(arrayOfUsers[i].getIdentifier() == id) {
                indexFind = i;
                break;
            }
        }
        if(indexFind == -1) throw new UserNotFoundException("Пользователь не найден.");
        return arrayOfUsers[indexFind];
    }

    public int getIndex() {
        return index;
    }
    public void pushBack(User user) {
        if(index == arrayOfUsers.length) {
            resizeArray();
        }
        arrayOfUsers[index++] = user;
    }

    private void resizeArray() {
        int length = arrayOfUsers.length;
        User[] copyArray = new User[length];
        System.arraycopy(arrayOfUsers, 0, copyArray, 0, length);
        arrayOfUsers = new User[length * 2];
        System.arraycopy(copyArray, 0, arrayOfUsers, 0, length);
    }
}
