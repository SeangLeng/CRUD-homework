package com.istad.dataanalyticrestfulapi.service;

import com.github.pagehelper.PageInfo;
import com.istad.dataanalyticrestfulapi.model.Transaction;

public interface Transaction_service {
    PageInfo<Transaction> getAllTransaction(int page, int size);
    int createTransaction(Transaction transaction);
    int deleteTransaction(int id);
    int updateTransaction(Transaction transaction);
}