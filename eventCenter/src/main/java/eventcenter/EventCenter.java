package eventcenter;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * Created by OurEDA on 2015/12/18.
 */
@Component
public class EventCenter {

    private Map<EventName, List<EventHandler>> regTable = Maps.newHashMap();

    /**
     * 向事件中心广播一个时间，驱使事件中心执行该事件的处理器
     * @param eventName
     * @param param
     */
    public void fire(EventName eventName, Object ... param) {
        System.out.println(eventName.getName());
        List<EventHandler> handlerList = regTable.get(eventName);
        if(CollectionUtils.isEmpty(handlerList)) {
            // log
            return;
        }
        for(EventHandler handler: handlerList) {
            try {
                handler.handle(param);
            } catch (Exception e) {
                fire(EventName.exception_occured, e);
            }
        }
    }

    /**
     * 将自己注册为事件中心的某个事件的处理器
     * @param eventName
     * @param handler
     */
    public void register(EventName eventName, EventHandler handler) {

        List<EventHandler> handlerList = regTable.get(eventName);
        if(null == handlerList) {
            handlerList = Lists.newLinkedList();
        }

        handlerList.add(handler);
        regTable.put(eventName, handlerList);
    }
}
