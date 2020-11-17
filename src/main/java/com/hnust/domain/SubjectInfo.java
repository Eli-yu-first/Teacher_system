package com.hnust.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: demo
 * @author: 彭鑫淼
 * @description: 未知
 * @create: 2020-11-13 21:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubjectInfo {
    private Integer total;
    private List<SubjectData> subject;
}
