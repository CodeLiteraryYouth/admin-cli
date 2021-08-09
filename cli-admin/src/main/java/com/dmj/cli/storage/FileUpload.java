package com.dmj.cli.storage;

import com.dmj.cli.common.enums.FileTypeEnum;
import com.dmj.cli.storage.oss.OssUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传
 * @author zd
 */
@Component
@Slf4j
public class FileUpload {

    @Value("${file.type}")
    private String type;

    private static final Map<String,FileUploadHandler> FILE_UPLOAD_MAP=new HashMap<>();

    static {
        FILE_UPLOAD_MAP.put(FileTypeEnum.OSS.name(),new OssUtils());
    }

    public String uploadFile(File file) {
        return FILE_UPLOAD_MAP.get(type).upload(file);
    }

}
