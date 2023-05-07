package com.istad.dataanalyticrestfulapi.mapper;

import com.istad.dataanalyticrestfulapi.model.Transaction;
import com.istad.dataanalyticrestfulapi.model.response.TransactionResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    List<Transaction> saveTransactionToTransaction(List<TransactionResponse> response);
    List<TransactionResponse> transactionOriginal(List<Transaction> transaction);
}
