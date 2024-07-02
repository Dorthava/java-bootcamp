package ex03;

import java.util.LinkedList;
import java.util.Queue;

public class Store {
    private final Queue<Producer.Url> queue;

    public Store() {
        this.queue = new LinkedList<>();
    }

    public void addFile(Producer.Url url) {
        synchronized (queue) {
            queue.add(url);
            queue.notify();
        }
    }

    public int takeFile() {
        synchronized (queue) {
            while (queue.isEmpty()) {
                try {
                    queue.wait();
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
            Producer.Url url = queue.poll();
            return url.getIndex();
        }
    }
}
