package messagedo;

/**
 * Created by OurEDA on 2015/12/18.
 */
public class Notify {
    public String name;

    public NotifyType type;
    public enum NotifyType {
        notify,
        stompq,
        amq;
    }
}
