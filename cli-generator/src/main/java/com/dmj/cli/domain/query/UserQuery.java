package com.dmj.cli.domain.query;

import lombok.Data;

import java.io.Serializable;

/**
 * @author zd
 */
@Data
public class UserQuery extends BaseQuery implements Serializable {

    private static final long serialVersionUID=1L;

    private String userName;
}
