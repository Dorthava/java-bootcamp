package ex00;

public class Program {
    public static class Side implements Runnable {
        Thread thread;
        private final int count;

        public Side(String name, int count) {
           thread = new Thread(this, name);
           this.count = count;
           thread.start();
        }

        public static void printMessage(int count, Thread thread) {
            for(int i = 0; i != count; ++i) {
                System.out.println(thread.getName());
            }
        }

        @Override
        public void run() {
            printMessage(count, thread);
        }
    }

    static public void main(String[] args) {
        String numberString;
        int count = 0;
        if(args.length == 0) System.exit(-1);
        int index = args[0].lastIndexOf('=');
        if(index == -1) {
            System.exit(-1);
        }
        numberString = args[0].substring(index + 1);
        try {
            count = Integer.parseInt(numberString);
        } catch (NumberFormatException e) {
            System.err.println(e.getMessage());
            System.exit(-1);
        }


        new Side("Egg", count);
        new Side("Hen", count);

        Thread.currentThread().setName("Human");

        Side.printMessage(count, Thread.currentThread());
    }
}
