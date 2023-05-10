package com.istad.dataanalyticrestfulapi.repository;

import ch.qos.logback.core.model.ComponentModel;
import com.istad.dataanalyticrestfulapi.model.Transaction;
import com.istad.dataanalyticrestfulapi.model.User;
import com.istad.dataanalyticrestfulapi.model.UserTransaction;
import com.istad.dataanalyticrestfulapi.repository.provider.TransactionProvider;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Mapper
@Repository
public interface TransactionRepository {
    @Results(
        {
            @Result(column = "sender_account_id", property = "sender", one = @One(select = "getUserTransactionBYAccountId")),
            @Result(column = "receiver_account", property = "receiver", one = @One(select = "getUserTransactionBYAccountId"))
        }
    )
    @SelectProvider(type = TransactionProvider.class, method = "getAllTransaction")
    List<Transaction> getAllTransaction();


    @Select("select a.id, t.*, a.username, t.account_no, a.gender, a.address\n" +
            "from user_account_tb\n" +
            "inner join users_tb a on a.id = user_account_tb.user_id\n" +
            "inner join account_tb t on t.id = user_account_tb.account_id\n" +
            "where account_id= #{id}")
    @Results(value = {
            @Result(property = "user.userId", column = "id"),
            @Result(property = "user.username",column = "username"),
            @Result(property = "user.gender", column = "gender"),
            @Result(property = "user.address", column = "address")
    })
    UserTransaction getUserTransactionBYAccountId(int id);

    @InsertProvider(type = TransactionProvider.class, method = "insertTransaction")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertTransaction(Transaction transaction);

    @DeleteProvider(type = TransactionProvider.class, method = "deleteTransaction")
    int deleteTransaction(@Param("id") int id);

    @UpdateProvider(type = TransactionProvider.class, method = "updateTransaction")
    int updateTransaction(Transaction transaction);
}
