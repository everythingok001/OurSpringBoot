package w.p.j.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import w.p.j.config.ProjectEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;


@Controller
@RequestMapping("/")
public class MainController extends BaseController {

    @Autowired
    ProjectEntity projectEntity;

    @RequestMapping("/index")
    public String index(){
        return "index";
    }


    @RequestMapping("/userInfo")
    public String userInfo(){
        return "user";
    }


    @RequestMapping("/uploadFile")
    @ResponseBody
    public String uploadFile(@RequestParam(value = "uploadfile", required = false) MultipartFile file, HttpServletRequest request){
        //取临时存放文件的目录
        String tempPath = request.getSession().getServletContext().getRealPath(projectEntity.getTempUploadFileDirectory());
        //如果临时目录不存在，则创建
        File tempPathDirectory = new File(tempPath);
        if(!tempPathDirectory.exists()) {
            tempPathDirectory.mkdirs();
        }
        //上传的文件换为别名
        String fileName = String.valueOf(System.currentTimeMillis());
        //获取文件的后缀
        String suffix = file.getOriginalFilename().substring
                (file.getOriginalFilename().lastIndexOf("."));
        //组成新的文件
        File newFile = new File(tempPath+ File.separator + fileName + suffix);
        try {
            //将文件拷贝至临时目录
            file.transferTo(newFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return fileName + suffix;
    }
}