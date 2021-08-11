package com.dmj.cli.common.storage;

import com.dmj.cli.common.enums.FileTypeEnum;
import com.dmj.cli.common.storage.oss.OssUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 文件上传
 * @author zd
 */
@Component
@Slf4j
public class FileUpload {

    @Value("${file.type}")
    private String type;


    private static final Map<String, FileUploadHandler> FILE_UPLOAD_MAP=new HashMap<>();

    static {
        FILE_UPLOAD_MAP.put(FileTypeEnum.OSS.name(),new OssUtils());
    }

    public String uploadFile(MultipartFile file) throws Exception{
        String fileName=file.getOriginalFilename();
        String prefix=fileName.substring(fileName.lastIndexOf("."));
        //创建临时文件
        final File execFile=File.createTempFile(UUID.randomUUID().toString(),prefix);
        file.transferTo(execFile);
        String path=FILE_UPLOAD_MAP.get(type).upload(execFile);
        //执行完成后删除临时文件
        deleteFile(execFile);
        return path;
    }

    /**
     * 删除
     *
     * @param files
     */
    private void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }

}
