package eventcenter.handler;

import eventcenter.EventHandler;
import eventcenter.EventName;
import org.springframework.stereotype.Component;

/**
 * Created by OurEDA on 2015/12/18.
 */
@Component
public class ExceptionHandler extends EventHandler {

    public void handle(Object... param) {
        Exception e = (Exception) param[0];
        // log
        eventCenter.fire(EventName.end_and_log, param);
    }

    public void afterPropertiesSet() throws Exception {
        eventCenter.register(EventName.exception_occured, this);
    }
}
