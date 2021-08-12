package com.dmj.cli.domain.query.sys;

import com.dmj.cli.domain.query.BaseQuery;
import lombok.Data;

import java.io.Serializable;

/**
 * @author zd
 */
@Data
public class OauthClientQuery extends BaseQuery implements Serializable {

    private static final long serialVersionUID=1L;

    private String clientName;
}
