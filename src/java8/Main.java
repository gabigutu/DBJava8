package java8;

import java8.impl.Dog;
import java8.impl.Duck;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) {
        Animal dog = new Dog(); // Dog e clasa concreta care impl. Animal
        dog.canFly(0);
        Animal duck = new Duck();
        duck.canFly(2);

        Animal unknownAnimal = new Animal() {
            @Override
            public boolean canFly(int noWings) {
                System.out.println("Unknown Animal doesn't fly");
                return false;
            }
        }; // clasa anonima care implementeaza Animal
        unknownAnimal.canFly(-2);

        Animal otherAnimal = param1 -> {
            System.out.println("Other Animal does fly.");
            return true;
        }; // chestie concreta care impl interfata
        otherAnimal.canFly(5);

        Animal otherAnimal2 = x -> true;
        if (otherAnimal2.canFly(9)) {
            System.out.println("OtherAnimal2 can fly");
        } else {
            System.out.println("OtherAnimal2 cannot fly");
        }

        Animal otherAnimal3 = x -> (x % 2 == 0);
        if (otherAnimal3.canFly(6)) {
            System.out.println("OtherAnimal3 with 6 wings can fly");
        } else {
            System.out.println("OtherAnimal3 with 6 wings cannot fly");
        }

        if (otherAnimal3.canFly(7)) {
            System.out.println("OtherAnimal3 with 7 wings can fly");
        } else {
            System.out.println("OtherAnimal3 with 7 wings cannot fly");
        }

        MyConsumer<String> myStringConsumer = x -> {
            System.out.println("Consuming param: " + x);
        };
        myStringConsumer.accept("Sirul meu");
        myStringConsumer.accept("Sirul tau");

        Student student1 = new Student("Vasile");
        Student student2 = new Student("Gigel");
        MyConsumer<Student> myStudentConsumer = x -> {
            System.out.println("Consuming student: " + x);
        };
        myStudentConsumer.accept(student1);
        myStudentConsumer.accept(student2);

        Consumer<Student> studentConsumer = x -> {
            System.out.println("(java.util.function) Consuming student: " + x);
        };
        studentConsumer.accept(student1);
        studentConsumer.accept(student2);

        Student student3 = new Student("Student3");
        Consumer<Object> objectConsumer = x -> {
            System.out.println("(java.util.function) Object consumer consumes: " + x);
        };
        studentConsumer.andThen(objectConsumer).accept(student3);

        Consumer<String> stringConsumer = x -> {
            System.out.println("(java.util.function) Consuming string: " + x);
        };
        stringConsumer.andThen(objectConsumer).accept("sirrrrrrrr");

        Predicate<Integer> divisibleByTwo = x -> (x % 2 == 0);
        if (divisibleByTwo.test(6)) {
            System.out.println("6 is divisibleByTwo");
        } else {
            System.out.println("6 is not divisibleByTwo");
        }
        Predicate<Integer> divisibleByThree = x -> (x % 3 == 0);
        if (divisibleByTwo.and(divisibleByThree).test(6)) {
            System.out.println("6 is divisibleBy 2 and 3");
        }
        if (!divisibleByTwo.and(divisibleByThree).test(8)) {
            System.out.println("8 is not divisibleBy 2 and 3");
        }

        List<Integer> numbers = new ArrayList<>();
        numbers.add(9);
        numbers.add(17);
        numbers.add(14);
        long noElements = numbers.stream().count();
        System.out.println("No elements = " + noElements);

        BinaryOperator<Integer> binaryOperatorSum = (a, b) -> a + b;
        Optional<Integer> sum = numbers.stream().reduce(binaryOperatorSum);
        System.out.println("Sum = " + sum.get());

        Optional<Integer> mul = numbers.stream().reduce((a, b) -> a * b);
        System.out.println("Mul = " + mul.get());

        Optional<Integer> dif = numbers.stream().reduce((a, b) -> {
            System.out.println("BinaryOperator performs diff between " + a + " and " + b);
            return a - b;
        });
        System.out.println("Dif = " + dif.get());

        List<Student> students = new ArrayList<>();
        students.add(new Student("AnaMaria"));
        students.add(new Student("Daniel"));
        students.add(new Student("Florentina"));
        Function<Student, Integer> countLettersInStudentName = student -> student.getName().length();
        Object[] noLettersInName = students.stream().map(countLettersInStudentName).sorted().toArray();
        for (int i = 0; i < noLettersInName.length; i++) {
            System.out.println(noLettersInName[i] + ": " + ((Integer) noLettersInName[i]));
        }


    }
}
