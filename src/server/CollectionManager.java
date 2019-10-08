package server;

import classes.Creature;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.*;

public class CollectionManager {

    private Gson serializer ;
    List<Creature> collection;
    private Type collectionType;
    private File jsonCollection;
    private Date initDate;

    {
        collection = Collections.synchronizedList(new ArrayList<>());
        serializer = new Gson();
        collectionType = new TypeToken<LinkedList<Creature>>(){}.getType();
    }


    CollectionManager(String collectionPath){

        try {
            if (collectionPath == null) throw new FileNotFoundException();
        } catch (FileNotFoundException ex) {
            System.err.println("Путь к файлу должен быть задан переменной окружения 'path'.");
            System.exit(1);
        }
        jsonCollection = new File(collectionPath);
        try{

            String extension = jsonCollection.getAbsolutePath().substring(jsonCollection.getAbsolutePath().lastIndexOf(".") + 1);

            if (!jsonCollection.exists() | !extension.equals("json")) throw new FileNotFoundException();
            if (jsonCollection.length() == 0) throw new Exception("Файл пуст");
            if (!jsonCollection.canRead() || !jsonCollection.canWrite()) throw new SecurityException();

            try (BufferedReader collectionReader = new BufferedReader(new FileReader(jsonCollection))) {
                System.out.println("Загрузка коллекции " + jsonCollection.getAbsolutePath());
                String nextLine;
                StringBuilder result = new StringBuilder();
                while ((nextLine = collectionReader.readLine()) != null) result.append(nextLine);
                try {
                    collection= serializer.fromJson(result.toString(), collectionType);
                    System.out.println("Коллекция успешно загружена. Добавлено " + collection.size() + " элементов.");
                } catch (JsonSyntaxException ex) {
                    System.err.println("Ошибка синтаксиса JSON. " + ex.getMessage());
                    System.exit(1);
                }
            }

        }catch (FileNotFoundException e) {
            System.err.println("Файл по указанному пути не найден или он пуст.");
            System.exit(1);
        } catch (SecurityException e) {
            System.err.println("Файл защищён от чтения.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Что-то не так с файлом.");
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Файл пуст");
            System.exit(1);
        }
    }


    public void save() {
        try (BufferedWriter writerToFile = new BufferedWriter(new FileWriter(jsonCollection))) {
            writerToFile.write(serializer.toJson(collection));
        } catch (Exception ex) {
            System.out.println("Возникла непредвиденная ошибка. Коллекция не может быть сохранена.");
        }
    }

    public List<Creature> getCreatures() {
        return collection;
    }

    public File getJsonCollection() {
        return jsonCollection;
    }

    public Gson getSerializer() {
        return serializer;
    }

    public Type getCollectionType() {
        return collectionType;
    }

    @Override
    public String toString() {
        return "Тип коллекции: " + collection.getClass() +
                "\nТип элементов: " + Creature.class +
                "\nДата инициализации: " + initDate +
                "\nКоличество элементов: " + collection.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CollectionManager)) return false;
        CollectionManager that = (CollectionManager) o;
        return Objects.equals(collection, that.collection) &&
                Objects.equals(serializer, that.serializer) &&
                Objects.equals(collectionType, that.collectionType) &&
                Objects.equals(jsonCollection, that.jsonCollection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(collection, serializer, collectionType, jsonCollection);
    }
}
