package ex03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Producer implements Runnable{
    Store store;
    Producer(Store store) {
        this.store = store;
    }

    public void run() {
        try(BufferedReader br = new BufferedReader(new FileReader("ex03/files_urls.txt"))) {
            String line;
            while((line = br.readLine()) != null) {
                store.addFile(new Url(line.substring(2)));
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static class Url {
        private final String name;
        private final int index;

        public Url(String nameUrl) {
            name = nameUrl;
            index = IdsGenerator.getInstance().generateId();
        }

        public String getName() {
            return name;
        }

        public int getIndex() {
            return index;
        }
    }
}

