package com.wil.files;

import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by wil on 2017/11/20.
 */
@Component
public class QiNiuFileStoreImpl implements FileStore {

    @Value("${qiniu.ak}")
    private String accessKey;
    @Value("${qiniu.sk}")
    private String secretKey;
    @Value("${qiniu.bucketName}")
    private String bucketName;
    @Value("${qiniu.domain}")
    private String domain;


    @Override
    public String saveFile(InputStream inputStream, String fileName) throws IOException {

        Configuration configuration = new Configuration(Zone.zone1());
        UploadManager uploadManager = new UploadManager(configuration);

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucketName);

        byte[] bytes = IOUtils.toByteArray(inputStream);
        Response response = uploadManager.put(bytes, null, upToken);
        DefaultPutRet defaultPutRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
        return defaultPutRet.key;
    }

    @Override
    public byte[] getFile(String fileName) throws IOException {

        String finalName = String.format("%s/%s", domain, fileName);
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(finalName).openConnection();
        InputStream inputStream = httpURLConnection.getInputStream();
        return IOUtils.toByteArray(inputStream);
    }
}
