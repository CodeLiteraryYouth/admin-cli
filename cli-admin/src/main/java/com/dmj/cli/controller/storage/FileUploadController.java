package com.dmj.cli.controller.storage;

import com.dmj.cli.common.constant.BaseResult;
import com.dmj.cli.storage.FileUpload;
import com.dmj.cli.util.str.StringUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zd
 */
@RestController
@RequestMapping("/file")
@Api(tags = "文件上传")
public class FileUploadController {

    @Autowired
    private FileUpload fileUpload;

    @PostMapping("/upload")
    public BaseResult<String> upload(@RequestParam("file") MultipartFile file) throws Exception{
        String path=fileUpload.uploadFile(file);
        if (StringUtils.isNotBlank(path)) {
            return BaseResult.success(path);
        }
        return BaseResult.fail(path);
    }
}
