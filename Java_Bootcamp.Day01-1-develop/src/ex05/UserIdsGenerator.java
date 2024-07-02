package ex05;

public class UserIdsGenerator {
    private final static UserIdsGenerator instance;
    private int id;
    static {
        instance = new UserIdsGenerator();
    }
    private UserIdsGenerator() {
        id = 1;
    }
    public static UserIdsGenerator getInstance() {
        return instance;
    }
    public int generateId() {
        return id++;
    }
}
