package com.wil.files;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wil on 2017/11/18.
 */
public interface FileStore {

    String saveFile(InputStream inputStream, String fileName) throws IOException;

    byte[] getFile(String fileName) throws IOException;

}
