package classes;

import classes.Creature;
import com.google.gson.Gson;

public class JsonManager{

    private static Gson gson = new Gson();


    public static Creature readJson(String args){
        Creature creature = null;
        try {
            creature = gson.fromJson(args, Creature.class);
            if(!creature.nullCheck()){
                creature=null;
                throw new Exception();
            }
        }
        catch (Exception e){
            System.out.println("Некорректный аргумент");
        }
        return creature;
    }
    public static String writeJson(Creature creature){
        return gson.toJson(creature);
    }

}

