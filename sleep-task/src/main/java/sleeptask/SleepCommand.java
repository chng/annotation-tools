package sleeptask;

/**
 * Created by OurEDA on 2015/12/16.
 */
public class SleepCommand implements Command {

    private Command commandWhenWakeUp = null;
    private ActiveObjectEngine engine = null;
    private long sleepTime = 0L;

    private long startTime = 0L;
    private boolean started = false;

    public SleepCommand(ActiveObjectEngine e, long sleepTime, Command wakeup) {
        this.engine = e;
        this.sleepTime = sleepTime;
        this.commandWhenWakeUp = wakeup;
    }

    public void execute(Object obj) {
        long currTime = System.currentTimeMillis();

        if(!started) {
            started = true;
            startTime = currTime;
            engine.addCommand(this);
            return;
        }

        if(currTime - startTime < sleepTime) {
            engine.addCommand(this);
        } else {
            engine.addCommand(commandWhenWakeUp);
            engine.addCommand(this);
            started = false;
        }
    }
}
