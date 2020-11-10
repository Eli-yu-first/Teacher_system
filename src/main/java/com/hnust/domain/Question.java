package com.hnust.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: demo
 * @author: 彭鑫淼
 * @description: 未知
 * @create: 2020-11-09 17:15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Question {
    private Integer id;
    private String question;
    private String answer;
    private Boolean checked;
}
