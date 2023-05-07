package com.istad.dataanalyticrestfulapi.service.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.istad.dataanalyticrestfulapi.mapper.TransactionMapper;
import com.istad.dataanalyticrestfulapi.model.Transaction;
import com.istad.dataanalyticrestfulapi.model.response.TransactionResponse;
import com.istad.dataanalyticrestfulapi.repository.TransactionRepository;
import com.istad.dataanalyticrestfulapi.service.Transaction_service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
@RequiredArgsConstructor
public class Transaction_serviceIMPL implements Transaction_service {
    private final TransactionRepository transactionRepository;
    private final TransactionMapper transactionMapper;
    @Override
    public PageInfo<Transaction> getAllTransaction(int page, int size,  int filter) {
        PageHelper.startPage(page, size);
        return new PageInfo<>(transactionRepository.getAllTransaction(filter));
    }

    @Override
    public int CreateTransaction(TransactionResponse transactionResponse) {
        return transactionRepository.createNewTransaction(transactionResponse);
    }
}
