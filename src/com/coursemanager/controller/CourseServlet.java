package com.coursemanager.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.coursemanager.entity.Course;
import com.coursemanager.entity.Searchcourse;
import com.coursemanager.entity.User;
import com.coursemanager.service.CourseService;
import com.coursemanager.util.BaseServlet;
import com.coursemanager.util.Json;
import com.google.gson.Gson;

public class CourseServlet extends BaseServlet {
//	public void FindDate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		int year;
//		HttpSession session = request.getSession();
//		Calendar cal=Calendar.getInstance();    
//		year=cal.get(Calendar.YEAR); 
//		session.setAttribute("year", year);
//		request.getRequestDispatcher("/InputCourseInformation.jsp").forward(request, response);
//	}
	
	public void ViewCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		CourseService service = new CourseService();
		List<Course> courseList = null;
		
		Map<String, String[]> searchinformation = request.getParameterMap();
		Searchcourse searchcourse  = new Searchcourse();
		try {
			BeanUtils.populate(searchcourse, searchinformation);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		int currentPage = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));
		HttpSession session=request.getSession();		
		User user = (User)session.getAttribute("user");
		int year = (int)session.getAttribute("year");
		Calendar cal=Calendar.getInstance();    
		int month = cal.get(Calendar.MONTH)+1;
		String date = "";
		if(month>7){
			date = (year-1)+"-"+year+"学年第二学期";
		}else{
			date = (year-1)+"-"+year+"学年第一学期";
		}
		
		try {
			courseList = service.CoursefindPageBean(currentPage,pageSize,searchcourse,user,date);
		} catch (SQLException e) { 
			e.printStackTrace();
		}
		int totalcount=0;
		try {
			totalcount = service.getUITotalCount(searchcourse,user);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Gson gson = new Gson();
//		String jsonList = gson.toJson(courseList);
		String json = "{\"total\":"+totalcount+",\"rows\":"+gson.toJson(courseList)+"}";
		response.getWriter().write(json);
//		request.setAttribute("pageBean", pageBean);

	}

	public void AddCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		//获取数据
		Map<String, String[]> courselist = request.getParameterMap();
		//封装数据
		Course course = new Course();
		Json j = new Json();
		Gson gson = new Gson();
		
		try {
			BeanUtils.populate(course, courselist);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		String t_id = user.getT_id();
		course.setT_id(t_id);
		CourseService service = new CourseService();
		int flag = 0;
		try {
			service.addCourse(course);
			flag = 1;	
			request.setAttribute("flag", flag);
			request.getRequestDispatcher("/InputCourseInformation.jsp").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("flag", flag);
			request.getRequestDispatcher("/InputCourseInformation.jsp").forward(request, response);			
		}	
	}

	
	public void DelCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String cid = request.getParameter("ids");
		CourseService service = new CourseService();
		Json j = new Json();
		Gson gson = new Gson();
		try {
			service.delCourseByCid(cid);			
			j.setSuccess(true);
			j.setMsg("删除成功�?");			
		} catch (SQLException e) {
			e.printStackTrace();
			j.setMsg("删除失败�?");
		}
		String json = gson.toJson(j);
		response.getWriter().write(json);
	}
	
	
	
	public void UpdateCourse(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		Map<String, String[]> courselist = request.getParameterMap();
		//封装数据
		Course course = new Course();
		Json j = new Json();
		Gson gson = new Gson();
		try {
			BeanUtils.populate(course, courselist);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		CourseService service = new CourseService();
		try {
			service.updateCourse(course);
			j.setSuccess(true);
			j.setMsg("修改数据成功�?");				
		} catch (SQLException e) {
			e.printStackTrace();
			j.setMsg("修改数据失败�?");
		}
		String json = gson.toJson(j);
		response.getWriter().write(json);
	}
	
	
	public void add_course(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		int flag = 0;
		// 得到上传文件的保存目录，将上传的文件存放于WEB-INF目录下，不允许外界直接访问，保证上传文件的安�?
		String savePath = this.getServletContext().getRealPath("/WEB-INF/Fileupload");
		File file = new File(savePath);
		// 判断上传文件的保存目录是否存�?
		if (!file.exists() && !file.isDirectory()) {
			System.out.println(savePath + "目录不存在，�?要创�?");
			// 创建目录
			file.mkdir();
		}

		// 消息提醒
		String message = "";
		if (ServletFileUpload.isMultipartContent(request)) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(1024 * 512);
			factory.setRepository(file);
			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			fileUpload.setFileSizeMax(100 * 1024 * 1024);// 设置�?大文件大�?
			try {
				@SuppressWarnings("unchecked")
				List<FileItem> items = fileUpload.parseRequest(request);// 获取�?有表�?
				for (FileItem item : items) {
					// 判断当前的表单控件是否是�?个普通控�?
					if (!item.isFormField()) {
						// 是一个文件控件时
						String excelFileName = new String(item.getName().getBytes(), "utf-8"); // 获取上传文件的名�?
						// 上传文件必须为excel类型,根据后缀判断(xls)
						String excelContentType = excelFileName.substring(excelFileName.lastIndexOf(".")); // 获取上传文件的类�?
						// System.out.println("上传文件�?:" + excelFileName);
						// System.out.println("文件大小:" + item.getSize());
						// System.out.println("\n---------------------------------------");
//						if (".xls".equals(excelContentType) || ".xlt".equals(excelContentType)
//								|| ".xlsx".equals(excelContentType) || ".xlsm".equals(excelContentType)) {
//							POIFSFileSystem fileSystem = new POIFSFileSystem(item.getInputStream());
//						HSSFWorkbook workbook = new HSSFWorkbook(fileSystem);
						Workbook workbook = null;												
						if (".xls".equals(excelContentType) || ".xlt".equals(excelContentType)){
							
							 workbook = new HSSFWorkbook(item.getInputStream());
						}
						else if(".xlsx".equals(excelContentType) || ".xlsm".equals(excelContentType)){
							//new FileInputStream(excelFileName)
							 workbook = new XSSFWorkbook(item.getInputStream());
						}else{
							//不是excel文件
							flag = 2;
							break;
						}						
							Sheet sheet = workbook.getSheetAt(0); // 得到工作页（标签�?
							for (Row row : sheet) {
								int rowNum = row.getRowNum();
								// System.out.println(rowNum);
								if (rowNum == 0) {
									String c_name= row.getCell(0).getStringCellValue();
									String c_class= row.getCell(1).getStringCellValue();
									String c_credit = row.getCell(2).getStringCellValue();
								    String c_year= row.getCell(3).getStringCellValue();
									if (!"课程名称".equals(c_name)) {
										//表头不对应
										flag = 3;										
										break;
									}
									if (!"专业班级".equals(c_class)) {
										flag = 3;										
									}
									if (!"学分".equals(c_credit)) {
										flag = 3;										
										break;
									}
									if (!"学期".equals(c_year)) {
										flag = 3;
										break;
									}

								}
								if (rowNum > 0) {									

									// 异常原因：Excel数据Cell有不同的类型�?
									// 当我们试图从�?个数字类型的Cell读取出一个字符串并写入数据库�?
									String c_name = getCellValue(row.getCell(0));
									String c_class = getCellValue(row.getCell(1));
									String c_credit = getCellValue(row.getCell(2));
									String c_year = getCellValue(row.getCell(3));								
									//封装
									Course course = new Course(); 
									course.setC_name(c_name);
									course.setC_class(c_class);
									course.setC_credit(c_credit);
									course.setC_year(c_year);									
									// 调到service�?
									HttpSession session=request.getSession();		
									User user = (User)session.getAttribute("user");
									String t_id = user.getT_id();
									CourseService service = new CourseService();
									service.add_course(course,t_id);
									// System.out.println("成功");
									flag = 1;
								}
							}
						} 
					}
				}
		catch (Exception e) {
				flag = 3;
				e.printStackTrace();
			}
		}

		request.setAttribute("flag", flag);
		request.getRequestDispatcher("/InputCourseInformation.jsp").forward(request, response);
	}

	private static String getCellValue(Cell cell) {
		String value = null;
		// �?单的查检列类�?
		// System.out.println("cell.getCellType():"+cell.getCellType());
		switch (cell.getCellType()) {

		case HSSFCell.CELL_TYPE_STRING:// 字符�?
			// System.out.println("HSSFCell.CELL_TYPE_STRING:"+HSSFCell.CELL_TYPE_STRING);
			value = cell.getRichStringCellValue().toString();
			break;
		case HSSFCell.CELL_TYPE_NUMERIC:// 数字
			// System.out.println("HSSFCell.CELL_TYPE_NUMERIC:"+HSSFCell.CELL_TYPE_NUMERIC);
			long dd = (long) cell.getNumericCellValue();
			value = dd + "";
			break;
		case HSSFCell.CELL_TYPE_BLANK:
			// System.out.println("HSSFCell.CELL_TYPE_BLANK:"+HSSFCell.CELL_TYPE_BLANK);
			value = "";
			break;
		case HSSFCell.CELL_TYPE_FORMULA:
			// System.out.println("HSSFCell.CELL_TYPE_FORMULA:"+HSSFCell.CELL_TYPE_FORMULA);
			value = String.valueOf(cell.getCellFormula());
			break;
		case HSSFCell.CELL_TYPE_BOOLEAN:// boolean型�??
			// System.out.println("HSSFCell.CELL_TYPE_BOOLEAN:"+HSSFCell.CELL_TYPE_BOOLEAN);
			value = String.valueOf(cell.getBooleanCellValue());
			break;
		case HSSFCell.CELL_TYPE_ERROR:
			// System.out.println("HSSFCell.CELL_TYPE_ERROR:"+HSSFCell.CELL_TYPE_ERROR);
			value = String.valueOf(cell.getErrorCellValue());
			break;
		default:
			// System.out.println("default");
			break;
		}
		return value;
	}


}
