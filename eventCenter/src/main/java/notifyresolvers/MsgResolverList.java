package notifyresolvers;

import messagedo.Message;
import messagedo.Notify;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by OurEDA on 2015/12/18.
 */
@Component
public class MsgResolverList implements MsgResolver{

    //Ö°ÔðÁ´
    private List<MsgResolver> resolvers;
    public List<MsgResolver> getResolvers() {
        return resolvers;
    }
    public void setResolvers(List<MsgResolver> resolvers) {
        this.resolvers = resolvers;
    }

    public boolean canResolve(Notify notify) {
        return true;
    }

    public Message resolve(Notify notify) {
        for(MsgResolver resolver: resolvers) {
            if(resolver.canResolve(notify)) {
                System.out.println("NotifyType: "+notify.type);
                return resolver.resolve(notify);
            }
        }
        return null;
    }
}
