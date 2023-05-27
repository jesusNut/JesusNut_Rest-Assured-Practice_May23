package com.builderdesignpatternpojo;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StudentBuilderClass {

    private int rollno;
    private int age;
    private String firstName;
    private String lastName;
    private String email;

    public StudentBuilderClass setRollno(int rollno) {
        this.rollno = rollno;
        return this;
    }

    public StudentBuilderClass setAge(int age) {
        this.age = age;
        return this;
    }

    public StudentBuilderClass setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public StudentBuilderClass setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public StudentBuilderClass setEmail(String email) {
        this.email = email;
        return this;
    }

    public static StudentBuilderClass getBuilder() {

        return new StudentBuilderClass();
    }

    public StudentPojoClassForBuilder getStudent() {

        return new StudentPojoClassForBuilder(this.rollno, this.age, this.firstName, this.lastName, this.email);

    }

    public StudentBuilderClass and(){
        return this;
    }


}
