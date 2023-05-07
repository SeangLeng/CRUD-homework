package com.istad.dataanalyticrestfulapi.repository.provider;

import org.apache.ibatis.jdbc.SQL;
import org.springframework.aop.AfterReturningAdvice;

public class TransactionProvider {
    public static String getAllTransaction(int filter){
        return new SQL(){{

            SELECT("*");
            FROM("transaction_tb");
            if (filter!=0){
                WHERE("sender_account_id = #{filter}");
            }
        }}.toString();
    }

    public static String createNewTransaction(){
        return new SQL(){{
            INSERT_INTO("transaction_tb");
            VALUES("sender_account_id", "#{T.sender_account_id}");
            VALUES("receiver_account", "#{T.receiver_account}");
            VALUES("amount", "#{T.amount}");
            VALUES("remark", "#{T.remark}");
            VALUES("transfer_at", "#{T.transfer_at}");

        }}.toString();
    }
}
