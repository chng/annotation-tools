package eventcenter.handler;

import eventcenter.EventHandler;
import eventcenter.EventName;
import org.springframework.stereotype.Component;

/**
 * Created by OurEDA on 2015/12/20.
 */
@Component
public class MsgPushHandler extends EventHandler {
    public void afterPropertiesSet() throws Exception {
        eventCenter.register(EventName.msg_stored, this);
    }

    @Override
    public void handle(Object... param) {
        // push
        eventCenter.fire(EventName.end_and_log, param);
    }
}
