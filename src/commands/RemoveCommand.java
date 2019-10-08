package commands;

import classes.Creature;
import com.google.gson.JsonSyntaxException;
import server.CollectionManager;
import java.util.List;


public class RemoveCommand extends AbstractCommand {

    public RemoveCommand(CollectionManager manager) {
        super(manager);
        setDescription("Удаляет элемент из коллекции по его значению.");
    }

    @Override
    public synchronized String execute(String arg) {
            List<Creature> collection = getManager().getCreatures();
            if (collection.size() != 0) {
                try {
                    if (collection.remove(getManager().getSerializer().fromJson(arg, Creature.class))) {
                        getManager().save();
                        return "Элемент успешно удален.";
                    } else return "Такого элемента нет в коллекции.";
                } catch (JsonSyntaxException ex) {
                    return "Синтаксическая ошибка JSON. Не удалось удалить элемент.";
                }
            }
            else return "Элемент не с чем сравнивать. Коллекция пуста.";
    }
}