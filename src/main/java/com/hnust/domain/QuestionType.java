package com.hnust.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: 出系统项目
 * @author: 彭鑫淼
 * @description: 题目类型
 * @create: 2020-11-12 21:06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionType {
    private Integer id;
    private String name;
}
