package com.springcloud.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
@SuppressWarnings("serial")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain=true)
public class TbStudent implements Serializable {
    private Integer userId;

    private String userName;

    private Integer userSex;

    private Integer userAge;
    
    private String db_source;

    
}