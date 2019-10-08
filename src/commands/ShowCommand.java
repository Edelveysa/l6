package commands;

import classes.Creature;
import server.CollectionManager;
import java.util.List;


public class ShowCommand extends AbstractCommand {

    public ShowCommand(CollectionManager manager) {
        super(manager);
        setDescription("Выводит все элементы коллекции.");
    }

    @Override
    public synchronized String execute() {
        List<Creature> collection = getManager().getCreatures();
        StringBuilder result = new StringBuilder();
        if (collection.size() != 0) {
            for (Creature s: collection) {
                result.append(getManager().getSerializer().toJson(s)).append("\n    ");
            }
            return result.toString();
        }
        else return "Коллекция пуста.";
    }
}