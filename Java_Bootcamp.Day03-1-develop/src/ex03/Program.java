package ex03;

public class Program {
    public static void main(String[] args) {
        int threadsCount = Integer.parseInt(args[0].substring(15));
        Store store = new Store();
        Thread producer = new Thread(new Producer(store));
        producer.start();

        for(int i = 1; i != threadsCount + 1; ++i) {
            Thread consumer = new Thread(new Consumer(store), "Thread " + i);
            consumer.start();
        }
    }
}
