
import org.junit.Test;
import sleeptask.ActiveObjectEngine;
import sleeptask.Command;
import sleeptask.SleepCommand;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by OurEDA on 2015/12/16.
 */
public class TestSleepCommand {

    AtomicLong count = new AtomicLong(0L);

    @Test
    public void test() {
        new Worker().run();
    }

    class Worker implements Runnable {
        public void run() {
            ActiveObjectEngine engine = new ActiveObjectEngine();
            System.out.println(count);
            engine.addCommand(new SleepCommand(engine, 1000, new Command() {
                public void execute(Object obj) {
                    System.out.println(count.incrementAndGet());
                }
            })).run();
        }
    }

}

