import java.util.concurrent.CyclicBarrier;

public class Main {

    private static final int NUMBER_OF_FRIENDS = 10;

    public static void main(String[] args) {
        CyclicBarrier cyclicBarrier = new CyclicBarrier(NUMBER_OF_FRIENDS, new Etapas());

        for (int i = 0; i < NUMBER_OF_FRIENDS; i++) {
            new Thread(new Ciclista("Cyclist " + i, cyclicBarrier), "Cyclist " + i).start();
        }
    }

}
