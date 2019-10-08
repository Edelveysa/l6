package classes;

import java.io.Serializable;
import java.time.ZonedDateTime;


public class Creature implements Comparable<Creature>, Serializable {

    private String name;
    private int age;
    private ZonedDateTime time;

    public Creature(String name, int age) {
        this.name = name;
        this.age = age;
        time = ZonedDateTime.now();
    }

    public void display() {
        System.out.println(getName() + " " + getAge());
    }

    public boolean nullCheck(){
        if (this.getName()==null||this.getAge()<1||this.getTime()==null) {
            return false;
        }
        return true;
    }

    public String getName() {
        return name;
    }

    public ZonedDateTime getTime(){return time;};

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTime(ZonedDateTime time){this.time = time;}

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int compareTo(Creature creature){
        return this.getName().compareTo(creature.getName());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + age;
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Creature other = (Creature) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (age != other.age)
            return false;
        return true;
    }

    @Override
    public String toString() {


        return "classes.Creature [Name=" + name + ", age=" + age + "time= " + time + "]";
    }
}
