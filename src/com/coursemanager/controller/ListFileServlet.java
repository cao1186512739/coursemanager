package com.coursemanager.controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.coursemanager.entity.Course;
import com.coursemanager.entity.FileList;
import com.coursemanager.entity.Teacher;
import com.coursemanager.entity.User;
import com.coursemanager.service.FileService;
import com.coursemanager.util.BaseServlet;

/**
 * @author 刁龙
 *
 */
public class ListFileServlet extends BaseServlet {

	//学生获取下载的文件列表
    public void ListTeacherFile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获取上传文件的目�?
    	String cname = request.getParameter("cname");
    	//得到文件上传类型
      	String type = request.getParameter("type");
      	
    	HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String sclass = user.getS_class();
		FileService service = new FileService();
		List<Teacher> teacherId = null;
		List<Course> courseId = null;
		try {
			teacherId = service.FindTeacherId(cname,sclass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			courseId = service.FindCourseId(cname,sclass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String tno = teacherId.get(0).getT_no();
		String cid = courseId.get(0).getC_id();
		String path = "/WEB-INF/"+"/"+type+"/"+tno+cid;
					
        String uploadFilePath = this.getServletContext().getRealPath(path);
        //存储要下载的文件�?
//        Map<String,String> fileNameMap = new HashMap<String,String>();
        List<Map<String, Object>> fileNameMap = new ArrayList<Map<String, Object>>();
        //递归遍历filepath目录下的�?有文件和目录，将文件的文件名存储到map集合�?
        listfile(new File(uploadFilePath),fileNameMap);//File既可以代表一个文件也可以代表�?个目�?
        //将Map集合发�?�到listfile.jsp页面进行显示       
        session.setAttribute("path", path);
        request.setAttribute("fileNameMap", fileNameMap);
        request.getRequestDispatcher("/listfile.jsp").forward(request, response);      
    }
    
    public void listfile(File file,List<Map<String, Object>> list){
    	if(file.exists()){
    		if(!file.isFile()){
                //列出该目录下的所有文件和目录
                File files[] = file.listFiles();                     
                //遍历files[]数组
                for(File f : files){
                    //递归
                    listfile(f,list);          
                }
            }else{
                /**
                 * 处理文件名，上传后的文件是以uuid_文件名的形式去重新命名的，去除文件名的uuid_部分
                    file.getName().indexOf("_")�?索字符串中第�?次出�?"_"字符的位置，如果文件名类似于�?9349249849-88343-8344_阿_凡_�?.avi
                    那么file.getName().substring(file.getName().indexOf("_")+1)处理之后就可以得到阿_凡_�?.avi部分
                 */
                String realName = file.getName().substring(file.getName().indexOf("_")+1);
                //file.getName()得到的是文件的原始名称，这个名称是唯�?的，因此可以作为key，realName是处理过后的名称，有可能会重�?
                Long length = file.length();
                
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("name",file.getName());
                map.put("realName", realName); 
                map.put("length",FormetFileSize(file.length()));
                list.add(map);
            }
    	}else{
    		System.out.println("空文件");
    		return ;
    	}
    	}
        //如果file代表的不是一个文件，而是�?个目�?
        
    
    
    public String FormetFileSize(long fileS) {//转换文件大小
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    //学生课程
    public void FindStudentCourse(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");	
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String sclass = user.getS_class();
		FileService service = new FileService();
		List<Course> ScourseList = null;
		try {
			ScourseList = service.FindStudentCourse(sclass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		session.setAttribute("studentcourseList", ScourseList);		
		request.getRequestDispatcher("/filehome.jsp").forward(request, response);
	}	
  
  //老师获取上传文件的目录  
    public void FindTeacherFile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	String cname = null;
    	String tclass = null;
    	String type = null;
    	FileList filelist = new FileList();
    	
    	
	    	 cname = request.getParameter("cname");
			 tclass = request.getParameter("tclass");
			 type = request.getParameter("type");
		
		
    	
    	
    	filelist.setC_name(cname);
		filelist.setC_class(tclass);
		filelist.setC_type(type);
		HttpSession session2 = request.getSession();
		HttpSession session3 = request.getSession();
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String tid = user.getT_id();
		
		FileService service = new FileService();
		List<Course> courseId = null;
		int flag = 0;
		try {
			courseId = service.FindCid(cname,tid,tclass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String cid = courseId.get(0).getC_id();
		String tno = user.getT_no();
		
		
		String path = "/WEB-INF/"+"/"+type+"/"+tno+cid;
		
        String uploadFilePath = this.getServletContext().getRealPath(path);
        
        //存储要下载的文件�?
//        Map<String,String> fileNameMap = new HashMap<String,String>();
        List<Map<String, Object>> fileNameMap = new ArrayList<Map<String, Object>>();
        //递归遍历filepath目录下的�?有文件和目录，将文件的文件名存储到map集合�?
        listfile(new File(uploadFilePath),fileNameMap);//File既可以代表一个文件也可以代表�?个目�?
        //将Map集合发�?�到listfile.jsp页面进行显示       
        session3.setAttribute("path", path);
        session2.setAttribute("filelist", filelist);
        request.setAttribute("fileNameMap", fileNameMap);
        request.getRequestDispatcher("/filehome2.jsp").forward(request, response);     
	}	
   
    //删除文件
    public void del(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	HttpSession session = request.getSession();
    	HttpSession session2 = request.getSession();
		String readpath = (String) session.getAttribute("path");
		FileList filellist = (FileList) session2.getAttribute("filelist");
		String cname = filellist.getC_name();
		String cclass = filellist.getC_class();
		String type = filellist.getC_type();
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
        file.delete();
        int flag = 4;
        System.out.println("删除文件成功");
        request.setAttribute("flag", flag);
        request.getRequestDispatcher("/ListFileServlet?method=FindTeacherFile&cname="+cname+"&tclass="+cclass+"&type="+type).forward(request, response);     
       
        
    }
       
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
}