package com.istad.dataanalyticrestfulapi.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.istad.dataanalyticrestfulapi.mapper.TransactionMapper;
import com.istad.dataanalyticrestfulapi.model.Transaction;
import com.istad.dataanalyticrestfulapi.model.response.TransactionResponse;
import com.istad.dataanalyticrestfulapi.repository.TransactionRepository;
import com.istad.dataanalyticrestfulapi.service.Transaction_service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.istad.dataanalyticrestfulapi.repository.provider.TransactionProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class Transaction_serviceIMPL implements Transaction_service {
    private final TransactionRepository transactionRepository;
    private final TransactionRepository transactionMapper;
    @Override
    public PageInfo<Transaction> getAllTransaction(int page, int size,  int filter) {
        PageHelper.startPage(page, size);
        return new PageInfo<>(transactionRepository.getAllTransaction(filter));
    }

    @Override
    public int createTransaction(Transaction transaction) {
        return transactionMapper.insertTransaction(transaction);
    }
    @Override
    public int deleteTransaction(int id) {
        return transactionMapper.deleteTransaction(id);
    }
    @Override
    public int updateTransaction(Transaction transaction) {
        return transactionMapper.updateTransaction(transaction);
    }
}
