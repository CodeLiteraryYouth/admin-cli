package com.dmj.cli.common.storage.oss;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * OSS配置类
 * @author zd
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "file.oss")
public class OssProperties implements InitializingBean {

    private String endpoint;

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName;

    public static String OSS_ENDPOINT;

    public static String OSS_ACCESS_KEY_ID;

    public static String OSS_ACCESS_KEY_SECRET;

    public static String OSS_BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        OSS_ENDPOINT=endpoint;
        OSS_ACCESS_KEY_ID=accessKeyId;
        OSS_ACCESS_KEY_SECRET=accessKeySecret;
        OSS_BUCKET_NAME=bucketName;
    }
}
