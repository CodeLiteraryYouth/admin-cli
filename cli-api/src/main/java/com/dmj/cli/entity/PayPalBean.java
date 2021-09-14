package com.dmj.cli.entity;

import com.dmj.cli.entity.pay.PayRequest;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;


@Component
@PropertySource("classpath:/paypal.properties")
@ConfigurationProperties(prefix = "paypal")
public class PayPalBean extends PayRequest {
    private String clientId;
    private String secret;
    private Boolean sandBox;
    private String domain;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public Boolean getSandBox() {
        return sandBox;
    }

    public void setSandBox(Boolean sandBox) {
        this.sandBox = sandBox;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    @Override
    public String toString() {
        return "PayPalBean{" +
                "clientId='" + clientId + '\'' +
                ", secret='" + secret + '\'' +
                ", sandBox=" + sandBox +
                ", domain='" + domain + '\'' +
                '}';
    }
}