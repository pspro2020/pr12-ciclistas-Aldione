import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Objects;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class Ciclista implements Runnable{

    private final String name;
    private final CyclicBarrier cyclicBarrier;

    public Ciclista(String name, CyclicBarrier cyclicBarrier) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(cyclicBarrier);
        this.name = name;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        try {
            goToGasStation();
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while is going to the gas station.\n", name);
            return;
        }
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while waiting for friends in the gas station.\n", name);
            return;
        } catch (BrokenBarrierException e) {
            System.out.printf("%s doesn't wait any more for friends in the gas station because someone isn't coming.\n", name);
        }
        try {
            firstStage();
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while going to the first stage.\n", name);
            return;
        }
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while waiting for friends to finish their first stage.\n", name);
            return;
        } catch (BrokenBarrierException e) {
            System.out.printf("%s doesn't wait any more for friends to finish their first stage because someone isn't going to finish it.\n", name);
        }
        try {
            secondStage();
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while going to the second stage.\n", name);
            return;
        }
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while waiting for friends to finish their second stage.\n", name);
            return;
        } catch (BrokenBarrierException e) {
            System.out.printf("%s doesn't wait any more for friends to finish their second stage because someone isn't going to finish it.\n", name);
        }
        try {
            goHome();
        } catch (InterruptedException e) {
            System.out.printf("%s has been interrupted while going back home\n", name);
        }
    }

    private void goHome() throws InterruptedException {
        System.out.printf("%s - %s is going home.\n", DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(LocalTime.now()) ,Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(3)+1);
        System.out.printf("%s - %s arrieve home.\n",DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(LocalTime.now()) ,Thread.currentThread().getName());
    }

    private void secondStage() throws InterruptedException {
        System.out.printf("%s - %s start the second stage (go back to gas station).\n",DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(LocalTime.now()) ,Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10)+5);
        System.out.printf("%s - %s finished the second stage (arrieve gas station).\n",DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(LocalTime.now()) ,Thread.currentThread().getName());
    }

    private void firstStage() throws InterruptedException {
        System.out.printf("%s - %s start the first stage.\n",DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(LocalTime.now()) ,Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(10)+5);
        System.out.printf("%s - %s finished the first stage.\n",DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(LocalTime.now()) ,Thread.currentThread().getName());
    }

    private void goToGasStation() throws InterruptedException {
        System.out.printf("%s - %s is going to the Gas Station.\n",DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(LocalTime.now()), Thread.currentThread().getName());
        TimeUnit.SECONDS.sleep(ThreadLocalRandom.current().nextInt(3)+1);
        System.out.printf("%s - %s is waiting for the rest of his friends.\n",DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(LocalTime.now()), Thread.currentThread().getName());
    }
}
