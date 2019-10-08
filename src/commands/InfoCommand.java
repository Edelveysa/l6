package commands;

import server.CollectionManager;


public class InfoCommand extends AbstractCommand {

    public InfoCommand(CollectionManager manager) {
        super(manager);
        setDescription("Выводит информацию о коллекции.");
    }

    @Override
    public String execute(String arg) {
        return execute();
    }

    @Override
    public synchronized String execute() {
        return "Выполнил студент ВТ.\n" + getManager().toString();
    }
}