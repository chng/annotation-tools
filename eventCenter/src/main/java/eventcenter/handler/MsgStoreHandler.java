package eventcenter.handler;

import eventcenter.EventHandler;
import eventcenter.EventName;
import org.springframework.stereotype.Component;

/**
 * Created by OurEDA on 2015/12/20.
 */
@Component
public class MsgStoreHandler extends EventHandler {

    public void handle(Object... param) {
        // store
        eventCenter.fire(EventName.msg_stored, param);
    }

    public void afterPropertiesSet() throws Exception {
        eventCenter.register(EventName.msg_resolved, this);
    }
}
