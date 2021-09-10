package com.dmj.cli.domain.vo.sys;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zd
 * @date 2021/9/8
 * @apiNote
 **/
@Data
public class LoginForm implements Serializable {

    private String userName;

    private String password;

    private String uuid;

    private String code;
}
