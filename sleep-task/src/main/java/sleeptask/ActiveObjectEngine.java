package sleeptask;

import java.util.LinkedList;
import com.google.common.collect.Lists;

/**
 * Created by OurEDA on 2015/12/16.
 */
public class ActiveObjectEngine {

    LinkedList<Command> commands = Lists.newLinkedList();

    public ActiveObjectEngine addCommand(Command command) {
        commands.add(command);
        return this;
    }

    public void run() {
        while(!commands.isEmpty()) {
            Command c = commands.getFirst();
            commands.removeFirst();
            c.execute(null);
        }
    }

}
