package java8.completable;

import java.util.concurrent.CompletableFuture;

public class LaterCf extends Thread {

    CompletableFuture cf;
    int delay;

    public LaterCf(CompletableFuture cf, int delay) {
        this.cf = cf;
        this.delay = delay;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(delay * 1000);
        } catch (InterruptedException exception) {
            exception.printStackTrace();
        }
        System.out.println("LaterCf finished and will say complete");
        cf.complete("It's done");
    }
}
