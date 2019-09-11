package com.example.test.controller;

import com.example.test.model.Code;
import com.example.test.dto.ContractDto;
import com.example.test.model.Status;
import com.example.test.model.Transaction;
import com.example.test.repository.CodeRepository;
import com.example.test.repository.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TransactionController {

    @Autowired
    private TransactionsRepository transactionsRepository;
    @Autowired
    private CodeRepository codeRepository;

    public TransactionController(){}
    public TransactionController(TransactionsRepository tr, CodeRepository cr) {
        this.transactionsRepository = tr;
        this.codeRepository = cr;
    }

    @PostMapping("/new")
    public void addContract(@RequestBody ContractDto incomeContract){
        int code = incomeContract.getCode();
        int contactNumber = incomeContract.getContactNumber();
        Timestamp time = new Timestamp(System.currentTimeMillis());
        Status status = Status.valueOf(incomeContract.getStatus());
        Code dbCode = codeRepository.findByCode(code);
        if(dbCode == null){
            transactionsRepository.saveAndFlush(new Transaction(new Code(code),status,time,contactNumber));
            System.out.println("Saved: " + code + "\n" + contactNumber + "\n" + status);
        }else{
            transactionsRepository.saveWithCode(dbCode.getId(), status.name(), time, contactNumber);
        }
    }

    @GetMapping("/allstatuses")
    public List<Transaction> getStatuses(){
        List<Transaction> allTransactions = this.transactionsRepository.findAll();
        return allTransactions;
    }

    @GetMapping("/statusbyid/{id}")
    public String getStatusById(@PathVariable long id){
        Transaction transaction = this.transactionsRepository.findById(id).get();
        return transaction.getStatus().toString();
    }

    @GetMapping("/statusbycode/{code}")
    public List<Status> getStatusByCode(@PathVariable int code){
        List<Transaction> transactions = this.transactionsRepository.findByCode(code);
        List<Status> statuses = new ArrayList<>();
        for(Transaction transaction : transactions){
            statuses.add(transaction.getStatus());
        }
        return statuses;
    }
}
