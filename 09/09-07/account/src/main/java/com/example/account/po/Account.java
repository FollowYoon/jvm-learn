package com.example.account.po;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qindong
 * @date 2021/11/18 11:40
 */
@Data
public class Account implements Serializable {

    private Integer id;

    private String name;

    private Integer cnyBalance;

    private Integer usBalance;

}
