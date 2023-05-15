package com.istad.dataanalyticrestfulapi.repository.provider;

import org.apache.ibatis.jdbc.SQL;

public class UserProvider {
    public static String getALlUsers(String filterName){
        return new SQL(){{
            SELECT("*");
            FROM("users_tb");
            if (!filterName.isEmpty()){
                WHERE("upper(username) like upper('%' || #{filterName} || '%')");
            }
        }}.toString();
    }
}
