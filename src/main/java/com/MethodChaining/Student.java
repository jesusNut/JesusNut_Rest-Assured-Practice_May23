package com.MethodChaining;

public class Student {

    private String firstName;
    private  String lastname;
    private  String fullname;
    private  String fatherName;
    private  int rollno;
    private  float salary;

    public Student(String name, String lastname, String fatherName, int rollno, float salary) {
        this.firstName = name;
        this.lastname = lastname;
        this.fatherName = fatherName;
        this.rollno = rollno;
        this.salary = salary;
    }

    //method-1


    public Student modifyName() {

        this.fullname = "Mr. " + this.firstName + " " + this.lastname;
        return this;

    }

    //method-2


    public Student modifyFathersName(){

        this.fatherName = "Mr. "+this.fatherName;
        return this;

    }

    public void printBasicInfo(){


        System.out.println("Student enrolled : "+this.fullname);
        System.out.println("Students father info : "+this.fatherName);
    }


}
