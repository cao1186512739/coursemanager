package com.coursemanager.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.coursemanager.entity.User;

public class DownLoadServlet extends HttpServlet {

 //文件下载   
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession();
		
		String readpath = (String) session.getAttribute("path");
//		String readpath = "/WEB-INF/"+newpath;
        //得到要下载的文件�?
        String fileName = request.getParameter("filename");  //23239283-92489-阿凡�?.avi
        fileName = new String(fileName.getBytes("UTF-8"));
        //上传的文件都是保存在/WEB-INF/upload目录下的子目录当�?
        String fileSaveRootPath=this.getServletContext().getRealPath(readpath);
        //通过文件名找出文件的�?在目�?
        String path = findFileSavePathByFileName(fileName,fileSaveRootPath);
        //得到要下载的文件
        File file = new File(path + "\\" + fileName);
        //如果文件不存�?
        if(!file.exists()){
            request.setAttribute("message", "您要下载的资源已被删除！�?");
            request.getRequestDispatcher("/message.jsp").forward(request, response);
            return;
        }
        //处理文件�?
        String realname = fileName.substring(fileName.indexOf("_")+1);
        //设置响应头，控制浏览器下载该文件
        response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(realname, "UTF-8"));
        //读取要下载的文件，保存到文件输入�?
        FileInputStream in = new FileInputStream(path + "\\" + fileName);
        //创建输出�?
        OutputStream out = response.getOutputStream();
        //创建缓冲�?
        byte buffer[] = new byte[1024];
        int len = 0;
        //循环将输入流中的内容读取到缓冲区当中
        while((len=in.read(buffer))>0){
            //输出缓冲区的内容到浏览器，实现文件下�?
            out.write(buffer, 0, len);
        }
        //关闭文件输入�?
        in.close();
        //关闭输出�?
        out.close();
    }
    
    /**
    * @Method: findFileSavePathByFileName
    * @Description: 通过文件名和存储上传文件根目录找出要下载的文件的�?在路�?
    * @Anthor:
    * @param filename 要下载的文件�?
    * @param saveRootPath 上传文件保存的根目录，也就是/WEB-INF/upload目录
    * @return 要下载的文件的存储目�?
    */ 
    public String findFileSavePathByFileName(String filename,String saveRootPath){
        int hashcode = filename.hashCode();
        int dir1 = hashcode&0xf;  //0--15
        int dir2 = (hashcode&0xf0)>>4;  //0-15
        String dir = saveRootPath + "\\" + dir1 + "\\" + dir2;  //upload\2\3  upload\3\5
        File file = new File(dir);
        if(!file.exists()){
            //创建目录
            file.mkdirs();
        }
        return dir;
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}