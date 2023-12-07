package com.wil.controller;

import com.wil.entity.Disk;
import com.wil.exception.NotFoundException;
import com.wil.service.DiskService;
import com.wil.util.AjaxResult;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by wil on 2018/5/23.
 */
@Controller
@RequestMapping("/disk")
public class DiskController {

    @Autowired
    private DiskService diskService;

    @GetMapping
    public String home(@RequestParam(name = "_", required = false, defaultValue = "0") Integer pId,
                       Model model) {
        if(pId != 0) {
            Disk disk = diskService.findById(pId);
            model.addAttribute("disk", disk);
        }

        List<Disk> diskList = diskService.findDiskListByPId(pId);
        model.addAttribute("diskList", diskList);
        return "teacher/disk";
    }

    /**
     * 新建文件夹
     * @param disk
     * @return
     */
    @PostMapping("/new")
    @ResponseBody
    public AjaxResult saveFolder(Disk disk) {
        diskService.saveNewFolder(disk);
        List<Disk> diskList = diskService.findDiskListByPId(disk.getpId());
        return AjaxResult.successWithData(diskList);
    }

    /**
     * 上传文件
     * @param pId
     * @param session
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult upload(Integer pId, HttpSession session,
                             @RequestParam("file") MultipartFile file) throws IOException {

        if(file.isEmpty()) {
            return AjaxResult.error("文件为空");
        }
        InputStream inputStream = file.getInputStream();
        long size = file.getSize();
        String fileName = file.getOriginalFilename();

        diskService.saveNewFile(inputStream, size, fileName, pId, (Integer) session.getAttribute("teacherId"));

        List<Disk> diskList = diskService.findDiskListByPId(pId);
        return AjaxResult.successWithData(diskList);

    }

    /**
     * 下载文件
     * @param id
     * @param fileName
     * @param response
     */
    @GetMapping("/download")
    public void download(@RequestParam("_") Integer id,
                         @RequestParam(required = false, defaultValue = "") String fileName,
                         HttpServletResponse response) {

        try {
            OutputStream outputStream = response.getOutputStream();
            //获得输入流，在controller中设置响应报文的信息
            InputStream inputStream = diskService.getDownloadInputStream(id);

            if(StringUtils.isNotEmpty(fileName)) {
                response.setContentType("application/octet-stream");
                fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");
                response.setHeader("Content-Disposition","attachment; filename="+fileName+"");
            }

            IOUtils.copy(inputStream, outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            throw new NotFoundException();
        }


    }


}
