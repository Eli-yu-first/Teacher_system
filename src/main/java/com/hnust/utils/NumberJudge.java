package com.hnust.utils;

import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * @program: demo
 * @author: 彭鑫淼
 * @description: 未知
 * @create: 2020-11-12 19:35
 */
@Component
public class NumberJudge {
    public  boolean isInteger(String str) {
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }
}
