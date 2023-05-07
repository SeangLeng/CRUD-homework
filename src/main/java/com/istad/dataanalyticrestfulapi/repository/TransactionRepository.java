package com.istad.dataanalyticrestfulapi.repository;

import com.istad.dataanalyticrestfulapi.model.Transaction;
import com.istad.dataanalyticrestfulapi.model.response.TransactionResponse;
import com.istad.dataanalyticrestfulapi.repository.provider.TransactionProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Mapper
public interface TransactionRepository {
    @SelectProvider(type = TransactionProvider.class, method = "getAllTransaction")
    List<Transaction> getAllTransaction(int filter);

    @InsertProvider(type =  TransactionProvider.class, method = "createNewTransaction")
    int createNewTransaction(@Param("T") TransactionResponse transactionResponse);


}
