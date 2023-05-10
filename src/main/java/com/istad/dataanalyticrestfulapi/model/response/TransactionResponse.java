package com.istad.dataanalyticrestfulapi.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    int sender_account_id;
    int receiver_account;
    double amount;
    String remark;
    Date transfer_at;
}
