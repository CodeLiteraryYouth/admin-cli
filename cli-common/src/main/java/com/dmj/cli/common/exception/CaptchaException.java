package com.dmj.cli.common.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Administrator
 */
public class CaptchaException extends AuthenticationException {

    public CaptchaException(String msg) {
        super(msg);
    }
}
