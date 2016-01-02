package eventcenter;

import org.springframework.beans.factory.InitializingBean;

import javax.annotation.Resource;

/**
 * Created by OurEDA on 2015/12/18.
 */
public abstract class EventHandler implements InitializingBean {
    @Resource
    protected
    EventCenter eventCenter;

    public abstract void handle(Object ... param);
}
