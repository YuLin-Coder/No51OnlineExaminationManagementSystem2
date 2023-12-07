package com.wil.service.impl;

import com.wil.entity.Disk;
import com.wil.entity.DiskExample;
import com.wil.exception.ServiceException;
import com.wil.files.FileStore;
import com.wil.mapper.DiskMapper;
import com.wil.service.DiskService;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * Created by wil on 2018/5/23.
 */
@Service
public class DiskServiceImpl implements DiskService {

    @Autowired
    private DiskMapper diskMapper;

    @Autowired
    @Qualifier("qiNiuFileStoreImpl")
    private FileStore fileStore;


    @Override
    public Disk findById(Integer id) {
        return diskMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Disk> findDiskListByPId(Integer pId) {
        DiskExample diskExample = new DiskExample();
        diskExample.createCriteria().andPIdEqualTo(pId);
        return diskMapper.selectByExample(diskExample);
    }

    @Override
    public void saveNewFolder(Disk disk) {
        disk.setType(Disk.TYPE_DIR);
        disk.setCreateTime(new Date());
        diskMapper.insertSelective(disk);
    }

    /**
     * 保存上传的文件
     * @param inputStream
     * @param size
     * @param fileName
     * @param pId
     * @param teacherId
     */
    @Override
    @Transactional
    public void saveNewFile(InputStream inputStream, long size, String fileName, Integer pId, Integer teacherId) {
        Disk disk = new Disk();
        disk.setCreateTime(new Date());
        disk.setType(Disk.TYPE_FILE);
        disk.setTeacherId(teacherId);
        disk.setFileSize(FileUtils.byteCountToDisplaySize(size));
        disk.setByteSize(Double.longBitsToDouble(size));
        disk.setpId(pId);
        disk.setName(fileName);
        disk.setDownloadCount(0);

        try {
            String saveName = fileStore.saveFile(inputStream, fileName);
            disk.setSaveName(saveName);
            diskMapper.insertSelective(disk);
        } catch (IOException ex) {
            throw new ServiceException("文件上传异常", ex);
        }
    }

    @Override
    @Transactional
    public InputStream getDownloadInputStream(Integer id) throws IOException {

        Disk disk = diskMapper.selectByPrimaryKey(id);
        if(disk == null || disk.getType().equals(Disk.TYPE_DIR)) {
            throw new ServiceException("文件不存在或已被删除");
        }

        disk.setDownloadCount(disk.getDownloadCount()+1);
        diskMapper.updateByPrimaryKeySelective(disk);

        byte[] bytes = fileStore.getFile(disk.getSaveName());
        return new ByteArrayInputStream(bytes);
    }

}
