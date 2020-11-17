package com.hnust.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @program: demo
 * @author: 彭鑫淼
 * @description: 未知
 * @create: 2020-11-12 22:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerObj {
    private String content;
    private Boolean right;
}
