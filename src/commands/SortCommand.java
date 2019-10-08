package commands;

import classes.Creature;
import server.CollectionManager;

import java.util.Comparator;
import java.util.List;

public class SortCommand extends AbstractCommand {

    public SortCommand(CollectionManager manager) {
        super(manager);
        setDescription("Упорядочивает коллекцию по возрастанию по одному из заданных параметров: " +
                "-n по имени, -а по возрасту");
    }

    @Override
    public synchronized String execute(String arg) {
            List<Creature> collection = getManager().getCreatures();
            if (collection.size() != 0) {
                switch (arg) {
                    case "-n":
                        collection.sort(Comparator.comparing(Creature::getName));
                        getManager().save();
                        return "Коллекция упорядочена по имени.";
                    case "-a":
                        collection.sort(Comparator.comparing(Creature::getAge));
                        getManager().save();
                    default:
                        return "Неправильный параметр. Синтаксис 'sort -{n / m / d}'.";
                }
            } else return "Коллекция пуста. Нечего упорядочивать.";
    }
}