package eventcenter.handler;

import eventcenter.*;
import messagedo.Message;
import messagedo.Notify;
import notifyresolvers.MsgResolverList;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by OurEDA on 2015/12/18.
 */
@Component
public class MsgResolveHandler extends EventHandler implements InitializingBean {

    @Resource
    private MsgResolverList resolverList;

    @Override
    public void handle(Object... param) {

        /**
         * Resolver
         */
        Notify notify = (Notify) param[0];
        Message message = resolverList.resolve(notify);
        eventCenter.fire(EventName.msg_resolved, message);
    }

    public void afterPropertiesSet() throws Exception {
        eventCenter.register(EventName.msg_received, this);
    }
}
