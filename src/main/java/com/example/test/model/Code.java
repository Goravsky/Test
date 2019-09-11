package com.example.test.model;


import javax.persistence.*;

@Entity
@Table(name = "codes")
public class Code {

    @Id
    @GeneratedValue(generator = "increment")
    private long id;

    @Column(name = "code")
    private int code;

    public Code(){}

    public Code(int code) {
        this.code = code;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "Code: " + code;
    }
}