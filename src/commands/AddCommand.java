package commands;

import classes.Creature;
import com.google.gson.JsonSyntaxException;
import server.CollectionManager;
import classes.Creature;


public class AddCommand extends AbstractCommand {

    public AddCommand(CollectionManager manager) {
        super(manager);
        setDescription("Добавить новый элемент в коллекцию.");
    }

    @Override
    public synchronized String execute(String arg) {
            try {
                getManager().getCreatures().add(getManager().getSerializer().fromJson(arg, Creature.class));
                getManager().save();
                return "Элемент успешно добавлен.";
            } catch (JsonSyntaxException ex) {
                return "Синтаксическая ошибка JSON. Не удалось добавить элемент.";
            }
    }
}