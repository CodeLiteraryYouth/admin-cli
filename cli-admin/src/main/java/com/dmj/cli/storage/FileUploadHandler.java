package com.dmj.cli.storage;

import java.io.File;

/**
 * @author zd
 */
public abstract class FileUploadHandler {

    abstract protected String upload(File file);
}
