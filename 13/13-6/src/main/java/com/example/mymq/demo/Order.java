package com.example.mymq.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author qindong
 * @date 2021/12/17 16:15
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Order {

    private Long id;
    private Long ts;
    private String symbol;
    private Double price;

}
