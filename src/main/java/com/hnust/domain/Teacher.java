package com.hnust.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 创建人：zhangRuWang
 * 创建时间：2020/10/24
 * 描述：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    private String token;
    private String teaId;
    private String teaNumber;
    private String name;
    private String password;
    private String phoNumber;
    private String eMail;
    private String colId;
}
