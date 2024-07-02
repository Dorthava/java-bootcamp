package ex01;

public class UserIdsGenerator {
    private final static UserIdsGenerator instance;
    private int id;
    static {
        instance = new UserIdsGenerator();
    }
    private UserIdsGenerator() {
        id = 0;
    }
    public static UserIdsGenerator getInstance() {
        return instance;
    }
    public int generateId() {
        return id++;
    }
}
