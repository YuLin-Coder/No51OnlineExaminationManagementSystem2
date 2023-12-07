package com.wil.files;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.UUID;

/**
 * Created by wil on 2017/11/18.
 */
@Component
public class LocalFileStoreImpl implements FileStore {

    @Value("${upload.path}")
    private String uploadPath;


    @Override
    public String saveFile(InputStream inputStream, String fileName) throws IOException {

        String saveName = "";
        if(fileName.lastIndexOf(".") != -1) {
            saveName = UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
        }

        OutputStream outputStream = new FileOutputStream(new File(uploadPath, saveName));
        IOUtils.copy(inputStream, outputStream);
        outputStream.flush();
        outputStream.close();
        inputStream.close();

        return saveName;

    }

    @Override
    public byte[] getFile(String fileName) throws IOException {

        InputStream inputStream = new FileInputStream(new File(uploadPath, fileName));
        return IOUtils.toByteArray(inputStream);
    }
}
