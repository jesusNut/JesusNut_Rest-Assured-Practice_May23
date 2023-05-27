package com.MethodChaining;

public class Runner {

    public static void main(String[] args) {

        Student stud = new Student("Abhishek", "Bhardwaj", "Rakesh Kumar", 101, 5000.00f);

        stud.modifyName().modifyFathersName().printBasicInfo();
    }
}
