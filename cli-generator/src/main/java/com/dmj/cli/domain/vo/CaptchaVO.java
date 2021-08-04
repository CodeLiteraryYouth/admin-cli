package com.dmj.cli.domain.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zd
 */
@Data
public class CaptchaVO implements Serializable {

    private String token;

    private String code;
}
