package com.istad.dataanalyticrestfulapi.repository.provider;

import com.istad.dataanalyticrestfulapi.model.Transaction;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.aop.AfterReturningAdvice;

import java.util.List;
import java.util.Map;

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

    public String insertTransaction(Transaction transaction) {
        return new SQL() {{
            INSERT_INTO("transaction_tb");
            VALUES("sender_account_id", "#{sender_account_id}");
            VALUES("receiver_account", "#{receiver_account}");
            VALUES("amount", "#{amount}");
            VALUES("remark", "#{remark}");
        }}.toString();
    }
    public String deleteTransaction(@Param("id") int id) {
        return new SQL() {{
            DELETE_FROM("transaction_tb");
            WHERE("id = #{id}");
        }}.toString();
    }
    public String updateTransaction(Transaction transaction) {
        return new SQL() {{
            UPDATE("transaction_tb");
            SET("sender_account_id = #{sender_account_id}");
            SET("receiver_account = #{receiver_account}");
            SET("amount = #{amount}");
            SET("remark = #{remark}");
            WHERE("id = #{id}");
        }}.toString();
    }
}
