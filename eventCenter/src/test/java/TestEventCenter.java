import eventcenter.EventCenter;
import eventcenter.EventName;
import messagedo.Notify;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by OurEDA on 2015/12/18.
 */
public class TestEventCenter extends BaseTest {

    @Resource
    EventCenter eventCenter;

    @Test
    public void test() {
        Notify notify = new Notify();
        notify.type = Notify.NotifyType.amq;
        eventCenter.fire(EventName.msg_received, notify);
    }
}
