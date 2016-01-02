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
     * ���¼����Ĺ㲥һ��ʱ�䣬��ʹ�¼�����ִ�и��¼��Ĵ�����
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
     * ���Լ�ע��Ϊ�¼����ĵ�ĳ���¼��Ĵ�����
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
