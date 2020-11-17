package com.hnust.store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataTest {
    private int num = 5;
    private String data = "这是一个题目，这是一个题目，这是一个题目，这是一个题目，这是一个题目，这是一个题目，这是一个题目，这是一个题目，这是一个题目"
            + "这是一个题目，这是一个题目，这是一个题目，这是一个题目，这是一个题目，这是一个题目，这是一个题目，这是一个题目，这是一个题目"
            + "这是一个题目，这是一个题目，这是一个题目，这是一个题目，这是一个题目，这是一个题目，这是一个题目，这是一个题目，这是一个题目";
    private String option = "A:1 B:2 C:3 D:4";
    private String kind;
}
