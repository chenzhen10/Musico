package by.epam.kimbar;


import by.epam.kimbar.entity.Tag;
import by.epam.kimbar.util.Converter;
import by.epam.kimbar.util.file.NewsWriter;

import java.io.IOException;

public class Main {
    static {
        System.out.println(1);
    }

    public static void main(String[] args) {

    }


}

class Factory {
    public static Vehicle create(Class c) {
        if (c.getSimpleName().equals("Tank")) {
            return new Tank();
        } else if (c.getSimpleName().equals("Car")) {
            return new Car();
        } else return null;
    }
}

interface Vehicle {
}


class Tank extends Factory implements Vehicle {

    public Tank() {
        System.out.println("Tank");
    }


}

class Car extends Factory implements Vehicle {

    public Car() {
        System.out.println("Car");
    }


}


