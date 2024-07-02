package ex03;

class Consumer implements Runnable {
    Store store;
    Consumer(Store store){
        this.store = store;
    }

    @Override
    public void run() {
        while(true) {
            int result = store.takeFile();
            System.out.println(Thread.currentThread().getName() + " start download file number " + result);
            System.out.println(Thread.currentThread().getName() + " finish download file number " + result);
        }
    }
}