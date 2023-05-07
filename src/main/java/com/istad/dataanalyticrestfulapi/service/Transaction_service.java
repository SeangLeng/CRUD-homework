package com.istad.dataanalyticrestfulapi.service;

import com.github.pagehelper.PageInfo;
import com.istad.dataanalyticrestfulapi.mapper.TransactionMapper;
import com.istad.dataanalyticrestfulapi.model.Transaction;
import com.istad.dataanalyticrestfulapi.model.response.TransactionResponse;

import java.util.List;

public interface Transaction_service {
    PageInfo<Transaction> getAllTransaction(int page, int size, int filter);
    int createTransaction(Transaction transaction);
    int deleteTransaction(int id);
    int updateTransaction(Transaction transaction);
}