package com.tiler;

import com.opencsv.bean.CsvBindByPosition;

public class Employee {
    @CsvBindByPosition(position = 0)
    public long id;
    @CsvBindByPosition(position = 1)
    public String firstName;
    @CsvBindByPosition(position = 2)
    public String lastName;
    @CsvBindByPosition(position = 3)
    public String country;
    @CsvBindByPosition(position = 4)
    public int age;

    public Employee() {
        // Пустой конструктор
    }

    public Employee(long id, String firstName, String lastName, String country, int age) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCountry() {
        return country;
    }

    public int getAge() {
        return age;
    }
}