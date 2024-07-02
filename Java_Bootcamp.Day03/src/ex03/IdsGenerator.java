package ex03;

public class IdsGenerator {
    private final static IdsGenerator instance;
    private int id;
    static {
        instance = new IdsGenerator();
    }
    private IdsGenerator() {
        id = 1;
    }
    public static IdsGenerator getInstance() {
        return instance;
    }
    public int generateId() {
        return id++;
    }

}
