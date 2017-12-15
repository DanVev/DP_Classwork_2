package Commands;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vasily Danilin on 15.12.2017.
 */
public class CommandManager {
    private static CommandManager instance = null;
    private List<ACommand> list;
    private List<ICommand> initList;

    private CommandManager() {
        list = new ArrayList<>();
        List<ICommand> initList = new ArrayList<>();
    }

    public static CommandManager getInstance() {
        if (instance == null)
            instance = new CommandManager();
        return instance;
    }

    public void registry(ACommand command) {
        list.add(command);
    }

    public void undo() {
        if (list.size() > 8) {
            list.remove(list.size() - 1);
            //this.reset();
            for (ACommand c : list)
                c.doExecute();
        }
    }


    void addToInitState(ICommand command) {
        initList.add(command);
    }
}
