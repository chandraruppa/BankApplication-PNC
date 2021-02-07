package com.pnc.account.pncbank.util;

import org.springframework.util.CollectionUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AccountBalanceUtil {

    public static List<String> getBindingErrorList(BindingResult bindingResult){
        List<FieldError> fieldErrorList = bindingResult.getFieldErrors();
        List<String> errorList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(fieldErrorList)){
            for (FieldError fieldError : fieldErrorList){
                errorList.add( "@"+ fieldError.getField()+ ": "+ fieldError.getDefaultMessage());
            }
        }
        return errorList;
    }

    public static Date convertStartDate(String startDate){
        Date date = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            date = (Date) dateFormat.parse(startDate + "T00:00:01");
            return date;
        } catch(ParseException exception){
            exception.printStackTrace();
            return date;
        }
    }

    public static Date convertEndDate(String endDate){
        Date date = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
            date = (Date) dateFormat.parse(endDate + "T23:59:59");
            return date;
        } catch(ParseException exception){
            exception.printStackTrace();
            return date;
        }
    }
}
