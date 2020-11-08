import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

public class Etapas implements Runnable{


    @Override
    public void run() {
        System.out.printf("%s - A new stage begins, commanded by %s.\n", DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM).format(LocalTime.now()), Thread.currentThread().getName());
    }

}
