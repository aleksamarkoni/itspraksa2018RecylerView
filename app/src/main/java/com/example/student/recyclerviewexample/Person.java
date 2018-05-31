package com.example.student.recyclerviewexample;

import com.google.gson.annotations.SerializedName;

public class Person {
    @SerializedName("avatar")
    public String url;
    @SerializedName("first_name")
    public String name;
    @SerializedName("last_name")
    public String prezime;

    @SerializedName("id")
    public Integer id;

    public Person(String url, String name, String prezime) {
        this.url = url;
        this.name = name;
        this.prezime = prezime;
    }
}
