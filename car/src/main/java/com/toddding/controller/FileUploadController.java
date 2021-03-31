package com.toddding.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import com.toddding.common.CodeMsg;
import com.toddding.common.Constant;
import com.toddding.common.Result;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * @Description:文件上传接口
 * @Author: hxc
 * @Date: 2021/3/10 16:09
 */
@RestController
@RequestMapping("file")
public class FileUploadController {

    @RequestMapping("uploadImage.do")
    public Result uploadImg(@RequestParam("image")MultipartFile image, HttpServletRequest request){
        //原图片名称
        String originFileName=image.getOriginalFilename();
        //获取图片的后缀
        String exName= FileUtil.extName(originFileName);
        //产生新图片名称
        String newFileName = DateUtil.format(new Date(), "yyyyMMddHHmmssSSS");
        newFileName=newFileName+"."+exName;
        //获取upload文件夹的物理路径
        String realPath = request.getServletContext().getRealPath(Constant.UPLOAD_FOLDER);
        //文件保存的物理路径
        String fileRealPath=realPath+ File.separator+newFileName;
        //文件的url路径 upload/xxx.jpg
        String url=Constant.UPLOAD_FOLDER+"/"+newFileName;
        try {
            image.transferTo(new File(fileRealPath));
            return new Result(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Result(CodeMsg.CAR_UPLOAD_IMG_ERROR);
    }

}
