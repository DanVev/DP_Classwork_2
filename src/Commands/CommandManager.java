package Commands;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vasily Danilin on 15.12.2017.
 */
public class CommandManager {
    private static CommandManager instance = null;
    private List<ICommand> list;

    private CommandManager() {
        list = new ArrayList<>();
    }

    public static CommandManager getInstance() {
        if (instance == null)
            instance = new CommandManager();
        return instance;
    }

    public void registry(ICommand command) {
        list.add(command);
    }

    public void undo() {
        list.remove(list.size() - 1);
        for (ICommand c : list)
            c.execute();
    }
}
