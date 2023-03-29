package java8.impl;

import java8.Animal;

public class Duck implements Animal {
    @Override
    public boolean canFly(int noWings) {
        System.out.println("Duck will always fly.");
        // ...
        return true;
    }
}
