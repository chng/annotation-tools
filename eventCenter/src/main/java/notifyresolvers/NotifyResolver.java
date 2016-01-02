package notifyresolvers;

import messagedo.Message;
import messagedo.Notify;

/**
 * Created by OurEDA on 2015/12/20.
 */
public class NotifyResolver implements MsgResolver {
    public boolean canResolve(Notify notify) {
        return notify.type == Notify.NotifyType.notify;
    }

    public Message resolve(Notify notify) {
        return new Message();
    }
}
