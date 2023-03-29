package java8.impl;

import java8.Animal;

public class Dog implements Animal {
    @Override
    public boolean canFly(int noWings) {
        System.out.println("Dog can't fly whatever the no. of wings ");
        // ..
        return false;
    }
}
