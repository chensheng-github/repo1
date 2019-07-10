package cn.itcast.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/user")
public class UserController {
   @RequestMapping("/testFileUpload")
    public String testFileUpload(HttpServletRequest request) throws FileUploadException {
       System.out.println("testFileUpload方法执行了");
       //上传位置
       String path = request.getSession().getServletContext().getRealPath("/upload/");
       File file=new File(path);
       if(!file.exists()){
           file.mkdirs();
       }
       DiskFileItemFactory factory=new DiskFileItemFactory();
       ServletFileUpload upload=new ServletFileUpload(factory);
       List<FileItem> items = upload.parseRequest(request);
       for (FileItem item : items) {
           if(item.isFormField()){
               //是表单项，不做处理
           }else {
               //获取文件的名称
               String fileName = item.getName();
               String uuid = UUID.randomUUID().toString().replace("-", "");
               fileName=fileName+"_"+uuid;
               try {
                   item.write(new File(path,fileName));
                   //删除临时文件
                   item.delete();
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
       }

       return "success";
    }


    @RequestMapping("/testFileUpload2")
    public String testFileUpload2(HttpServletRequest request, MultipartFile upload) throws FileUploadException {
        System.out.println("testFileUpload2方法执行了");
        //上传位置
        String path = request.getSession().getServletContext().getRealPath("/upload/");
        File file=new File(path);
        if(!file.exists()){
            file.mkdirs();
        }

                //获取文件的名称
        String fileName= upload.getName();
        String uuid = UUID.randomUUID().toString().replace("-", "");
                fileName=fileName+"_"+uuid;
                try {
                    upload.transferTo(new File(path,fileName));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "success";
    }
}
