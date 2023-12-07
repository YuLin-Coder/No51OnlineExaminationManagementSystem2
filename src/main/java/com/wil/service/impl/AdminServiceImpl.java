package com.wil.service.impl;

import com.wil.entity.Admin;
import com.wil.entity.Announce;
import com.wil.entity.AnnounceExample;
import com.wil.exception.ServiceException;
import com.wil.mapper.AdminMapper;
import com.wil.mapper.AnnounceMapper;
import com.wil.service.AdminService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by wil on 2018/5/9.
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private AnnounceMapper announceMapper;
    @Value("${jdbc.salt}")
    private String salt;

    /**
     * 管理员登录
     * @param number
     * @param password
     * @return
     */
    @Override
    public Admin login(String number, String password) throws ServiceException {

        Admin admin = adminMapper.findByNumber(number);
        if(admin == null) {
            throw new ServiceException("管理员帐号不存在");
        }
        String codedPass = DigestUtils.md5Hex(salt + password);
        if(!codedPass.equals(admin.getPassword())) {
            throw new ServiceException("密码错误");
        }
        return admin;
    }

    @Override
    public Admin findById(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    /**
     * 查找所有的公告
     * @return
     */
    @Override
    public List<Announce> findAllAnnounce() {
        return announceMapper.selectByExample(new AnnounceExample());
    }

    /**
     * 根据公告id删除公告
     * @param id
     */
    @Override
    public void delAnnounceById(Integer id) {
        announceMapper.deleteByPrimaryKey(id);
    }

    /**
     * 批量删除公告
     * @param ids
     */
    @Override
    public void delAnnounces(String[] ids) {
        for(int i = 0; i < ids.length; i++) {
            announceMapper.deleteByPrimaryKey(Integer.parseInt(ids[i]));
        }
    }

    @Override
    public void newAnnounce(Announce announce) {
        announce.setCreateTime(new Date());
        announceMapper.insert(announce);
    }
}
