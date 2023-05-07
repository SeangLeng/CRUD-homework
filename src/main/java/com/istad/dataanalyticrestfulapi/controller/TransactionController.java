package com.istad.dataanalyticrestfulapi.controller;

import com.github.pagehelper.PageInfo;
import com.istad.dataanalyticrestfulapi.mapper.TransactionMapper;
import com.istad.dataanalyticrestfulapi.model.Transaction;
import com.istad.dataanalyticrestfulapi.model.User;
import com.istad.dataanalyticrestfulapi.model.response.AccountResponse;
import com.istad.dataanalyticrestfulapi.model.response.TransactionResponse;
import com.istad.dataanalyticrestfulapi.service.serviceImpl.Transaction_serviceIMPL;
import com.istad.dataanalyticrestfulapi.utils.Response;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.executor.ExecutionPlaceholder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {
    private final Transaction_serviceIMPL transactionService;
    private final TransactionMapper transactionMapper;
    @GetMapping("/getAllTransaction")
    Response<PageInfo<Transaction>> getAllTransaction(@RequestParam(defaultValue = "1") int page,
                                                      @RequestParam(defaultValue = "5") int size,
                                                      @RequestParam(defaultValue = "0", required = false)  int filter){
        try {
            PageInfo<Transaction> transaction_data = transactionService.getAllTransaction(page, size, filter);
            return Response.<PageInfo<Transaction>>ok().setPayload(transaction_data).setMessage("You got the data Successfully!");
        }catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            return Response.<PageInfo<Transaction>>exception().setMessage("Error happened! data not found!");
        }
    }

    @PostMapping("/create-new-transaction")
    Response<Transaction> createNewTransaction(@RequestBody TransactionResponse transactionResponse){
        try {
            int created = transactionService.CreateTransaction(transactionResponse);
            if (created > 0) {
                Transaction transaction = new Transaction()
                        .setSender_account_id(transactionResponse.getSender_account_id())
                        .setReceiver_account(transactionResponse.getReceiver_account())
                        .setAmount(transactionResponse.getAmount())
                        .setRemark(transactionResponse.getRemark());
                return Response.<Transaction>createSuccess().setPayload(transaction).setSuccess(true)
                        .setMessage("Transaction successfully created!");
            }else{
                return Response.<Transaction>badRequest().setMessage("Bad Request ! Failed to create transaction");
            }

        }catch (Exception e){
            return  Response.<Transaction>notFound().setSuccess(false).setMessage("Transaction not found!");
        }
    }
}
