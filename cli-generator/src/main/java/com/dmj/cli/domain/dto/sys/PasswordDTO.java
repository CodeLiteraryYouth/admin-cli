package com.dmj.cli.domain.dto.sys;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zd
 * @date 2021/9/13
 * @apiNote
 **/
@Data
public class PasswordDTO implements Serializable {

    private String password;

    private String newPassword;
}
