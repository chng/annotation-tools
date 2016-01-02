package notifyresolvers;

import messagedo.Message;
import messagedo.Notify;

/**
 * Created by OurEDA on 2015/12/18.
 */
public interface MsgResolver {

    public boolean canResolve(Notify notify);

    public Message resolve(Notify notify);

}
