package com.example.test.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(generator = "increment")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "code_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Code code;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "time")
    private Timestamp time;

    @Column(name = "contact_number")
    private int cNumber;

    public Transaction(){}

    public Transaction(Code code, Status status, Timestamp time, int cNumber) {
        this.code = code;
        this.status = status;
        this.time = time;
        this.cNumber = cNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getCNumber() {
        return cNumber;
    }

    public void setCNumber(int cNumber) {
        this.cNumber = cNumber;
    }

    @Override
    public String toString() {
        return "Transaction: " + "code-" + code.getCode() + " status-" + status + " contact_number-" + cNumber;
    }
}
