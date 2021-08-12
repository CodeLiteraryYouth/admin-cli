package com.dmj.cli.security;

import org.springframework.security.core.AuthenticationException;

/**
 * @author Administrator
 */
public class CaptchaException extends AuthenticationException {

    public CaptchaException(String msg) {
        super(msg);
    }
}
