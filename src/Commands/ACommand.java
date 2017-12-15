package Commands;

/**
 * Created by Vasily Danilin on 15.12.2017.
 */
abstract public class ACommand implements ICommand {
    @Override
    public final void execute() {
        CommandManager commandManager = CommandManager.getInstance();
        try {
            commandManager.registry((ACommand) this.clone());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        doExecute();
    }

    abstract void doExecute();
}
