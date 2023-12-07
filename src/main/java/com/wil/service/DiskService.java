package com.wil.service;

import com.wil.entity.Disk;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by wil on 2018/5/23.
 */
public interface DiskService {


    Disk findById(Integer pId);

    List<Disk> findDiskListByPId(Integer pId);

    /**
     * 新建文件夹
     * @param disk
     */
    void saveNewFolder(Disk disk);

    /**
     * 保存上传的文件
     * @param inputStream
     * @param size
     * @param fileName
     * @param pId
     * @param accountId
     */
    void saveNewFile(InputStream inputStream, long size, String fileName, Integer pId, Integer accountId);

    InputStream getDownloadInputStream(Integer id) throws IOException;
}
