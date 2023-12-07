package com.wil.controller;

import com.wil.entity.Admin;
import com.wil.entity.Announce;
import com.wil.service.AdminService;
import com.wil.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by wil on 2018/5/9.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/login")
    public String login() {
        return "admin/login";
    }

    @PostMapping("/login")
    public String login(RedirectAttributes redirectAttributes,
                        String adminID, String adminPassword,
                        HttpSession session) {

        try {
            Admin admin = adminService.login(adminID, adminPassword);
            session.setAttribute("admin", admin);
            session.setAttribute("roleId", admin.getRoleId());
            return "redirect:/admin/home/"+admin.getId();
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/admin/login";
        }

    }

    @GetMapping("/home/{id:\\d+}")
    public String home(Model model, @PathVariable Integer id) {
        Admin admin = adminService.findById(id);
        model.addAttribute("admin", admin);
        return "admin/home";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("admin");
        session.removeAttribute("roleId");
        return "redirect:/admin/login";
    }

    @GetMapping("/announce")
    public String announceList(Model model) {
        List<Announce> announceList = adminService.findAllAnnounce();
        model.addAttribute("announceList", announceList);
        return "admin/announceList";
    }

    @GetMapping("/announce/new")
    public String announceNew() {
        return "admin/newAnnounce";
    }

    @PostMapping("/announce/new")
    public String announceNew(Announce announce) {
        adminService.newAnnounce(announce);
        return "redirect:/admin/announce";
    }

    /**
     * 删除单个公告
     * @param id
     * @return
     */
    @GetMapping("/announce/del")
    @ResponseBody
    public AjaxResult delAnnounce(String id) {
        adminService.delAnnounceById(Integer.parseInt(id));
        return AjaxResult.success();
    }

    /**
     * 批量删除公告
     * @param ids
     * @return
     */
    @PostMapping("/announce/del")
    @ResponseBody
    public AjaxResult delAnnounces(String[] ids) {
        adminService.delAnnounces(ids);
        return AjaxResult.success();
    }


}
