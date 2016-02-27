package com.riddlefox.greeting;

public class Greeter {
    public static void main (String[] args) {
        System.out.println(greet("world"));
    }

    public static String greet(String name) {
        return "Hello " + name;
    }
}
