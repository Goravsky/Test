package com.example.test.dto;

public class ContractDto {

    private int code;
    private int contactNumber;
    private String status;

    public ContractDto(int c, int cn, String s){
        this.code = c;
        this.contactNumber = cn;
        this.status = s;
    }

    public int getCode() {
        return code;
    }

    public int getContactNumber() {
        return contactNumber;
    }

    public String getStatus() {
        return status;
    }
}
