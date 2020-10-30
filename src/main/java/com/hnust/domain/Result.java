package com.hnust.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 创建人：zhangRuWang
 * 创建时间：2020/10/30
 * 描述：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T>{
    int code;
    String msg;
    T data;
}
