package com.wil.service;

import com.wil.entity.Admin;
import com.wil.entity.Announce;

import java.util.List;

/**
 * Created by wil on 2018/5/9.
 */
public interface AdminService {
    /**
     * 管理员登录
     * @param number
     * @param password
     * @return
     */
    Admin login(String number, String password);

    Admin findById(Integer id);

    /**
     * 查找所有的公告
     * @return
     */
    List<Announce> findAllAnnounce();

    /**
     * 根据公告id删除公告
     * @param id
     */
    void delAnnounceById(Integer id);

    /**
     * 批量删除公告
     * @param ids
     */
    void delAnnounces(String[] ids);

    void newAnnounce(Announce announce);
}
