package com.dmj.cli.common.storage.oss;

import cn.hutool.core.date.DateUtil;
import com.aliyun.oss.*;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import com.dmj.cli.common.constant.GlobalConstants;
import com.dmj.cli.common.storage.FileUploadHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Date;

/**
 * @author zd
 */
public class OssUtils extends FileUploadHandler {

    private static final Logger log= LoggerFactory.getLogger(OssUtils.class);

    @Override
    protected String upload(File file) {
        log.info("=========>OSS文件上传开始："+file.getName());

        String dateStr = DateUtil.formatDate(new Date());

        if(null == file){
            return null;
        }

        OSS ossClient = new OSSClientBuilder().build(OssProperties.OSS_ENDPOINT,OssProperties.OSS_ACCESS_KEY_ID,OssProperties.OSS_ACCESS_KEY_SECRET);
        try {
            //容器不存在，就创建
            if(! ossClient.doesBucketExist(OssProperties.OSS_BUCKET_NAME)){
                ossClient.createBucket(OssProperties.OSS_BUCKET_NAME);
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(OssProperties.OSS_BUCKET_NAME);
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }
            //创建文件路径
            String fileUrl = dateStr + "/" +file.getName();
            //上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(OssProperties.OSS_BUCKET_NAME, fileUrl, file));
            //设置权限 这里是公开读
            ossClient.setBucketAcl(OssProperties.OSS_BUCKET_NAME,CannedAccessControlList.PublicRead);
            if(null != result){
                log.info("==========>OSS文件上传成功,OSS地址："+fileUrl);
                StringBuilder sb=new StringBuilder();
                sb.append(GlobalConstants.HTTPS).append(OssProperties.OSS_BUCKET_NAME)
                        .append(".").append(OssProperties.OSS_ENDPOINT).append("/")
                        .append(fileUrl);
                return sb.toString();
            }
        }catch (OSSException oe){
            log.error(oe.getMessage());
        }catch (ClientException ce){
            log.error(ce.getMessage());
        }finally {
            //关闭
            ossClient.shutdown();
        }
        return null;
    }
}
