package com.dtt.simulations.controller;


import com.dtt.simulations.model.MoneyTransfer;
import com.dtt.simulations.responseentity.ApiResponse;
import com.dtt.simulations.service.Iface.MoneyTransferIface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class FinancialTransactionController {

    @Autowired
    MoneyTransferIface moneyTransferIface;

    @GetMapping("/api/get/all/money-transfer")
    public ApiResponse getAllMoneyTransfer(){

        return moneyTransferIface.getAllMoneyTransfer();

    }

    @GetMapping("/api/get/money-transfer/by/id/{id}")
    public ApiResponse getAllMoneyTransfer(@PathVariable int id){

        return moneyTransferIface.getMoneyTransferById(id);

    }

    @PostMapping("/api/save/money-transfer")
    public ApiResponse saveMoneyTransfer(@RequestBody MoneyTransfer moneyTransfer){

        return moneyTransferIface.saveMoneyTransfer(moneyTransfer);

    }
}
