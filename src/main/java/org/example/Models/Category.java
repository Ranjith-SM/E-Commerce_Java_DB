package org.example.Models;

public class Category {
    private int id;
    private String CName;

//    public Category(int id, String category) {
//        this.id= id;
//        this.CName = category;
//
//    }



//    public Category(String s) {
//
//    }

    public Category(int i, String s) {
        this.id = i;
        this.CName = s;
    }

    public Category() {

    }



    public int getId() {
//        System.out.println(this.id);
        return id;
    }

    public void setId(int id) {
        this.id = id;

    }

    public String getCName() {
        return this.CName;
    }

    public void setCName(String CName) {
        this.CName = CName;
    }


}
