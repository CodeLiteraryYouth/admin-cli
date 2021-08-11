package com.dmj.cli.domain.dto.wx;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zd
 */
@Data
public class WxEventDTO implements Serializable {

    private String ToUserName;

    private String FromUserName;

    private Long CreateTime;

    private String MsgType;

    private String Event;

    private String EventKey;

    private String Ticket;
}
