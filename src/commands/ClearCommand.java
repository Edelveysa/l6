package commands;

import server.CollectionManager;

public class ClearCommand extends AbstractCommand {

    public ClearCommand(CollectionManager manager) {
        super(manager);
        setDescription("Очистить коллекцию.");
    }

    @Override
    public synchronized String execute() {
        getManager().getCreatures().clear();
        getManager().save();
        return "Коллекция очищена.";
    }
}