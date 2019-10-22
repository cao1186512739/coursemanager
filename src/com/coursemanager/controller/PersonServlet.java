package com.coursemanager.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.coursemanager.entity.Course;
import com.coursemanager.entity.SearchTeacher;
import com.coursemanager.entity.Student;
import com.coursemanager.entity.Teacher;
import com.coursemanager.entity.ViewStudent;
import com.coursemanager.entity.ViewStudent_update;
import com.coursemanager.entity.ViewTeacher_update;
import com.coursemanager.service.CourseService;
import com.coursemanager.service.PersonService;
import com.coursemanager.util.Json;
import com.google.gson.Gson;

public class PersonServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String method = request.getParameter("method");
		if ("AddPersonInformation".equals(method)) {
			AddPersonInformation(request, response);
		} else if ("student_upload".equals(method)) {
			student_upload(request, response);
		} else if ("ViewStudent".equals(method)) {
			try {
				ViewStudent(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if ("ViewTeacher".equals(method)) {
			try {
				ViewTeacher(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if ("DeleteStudentInformation".equals(method)) {
			DeleteStudentInformation(request, response);
		} else if ("UpdateStudentInformation".equals(method)) {
			UpdateStudentInformation(request, response);
		} else if ("ViewStudent_update".equals(method)) {
			try {
				ViewStudent_update(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if ("DeleteTeacher".equals(method)) {
			DeleteTeacher(request, response);
		} else if ("UpdateTeacherInformation".equals(method)) {
			UpdateTeacherInformation(request, response);
		} else if ("ViewTeacher_update".equals(method)) {
			try {
				ViewTeacher_update(request, response);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if ("teacher_upload".equals(method)) {
			teacher_upload(request, response);
		}

	}

	// 批量导入老师
	private void teacher_upload(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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
						Workbook workbook = null;												
						if (".xls".equals(excelContentType) || ".xlt".equals(excelContentType)){							
							 workbook = new HSSFWorkbook(item.getInputStream());
						}else if(".xlsx".equals(excelContentType) || ".xlsm".equals(excelContentType)){
							 workbook = new XSSFWorkbook(item.getInputStream());
						}else{
							flag = 2;
							break;
						}
							
							Sheet sheet = workbook.getSheetAt(0); // 得到工作页（标签�?
							for (Row row : sheet) {
								int rowNum = row.getRowNum();
								// System.out.println(rowNum);
								if (rowNum == 0) {
									String t_no = row.getCell(0).getStringCellValue();
									String t_name = row.getCell(1).getStringCellValue();
									String t_password = row.getCell(2).getStringCellValue();
									String t_titles = row.getCell(3).getStringCellValue();
									String t_office = row.getCell(4).getStringCellValue();
									String t_dept = row.getCell(5).getStringCellValue();

									if (!"工号".equals(t_no)) {
										flag = 3;
										message = "excel格式不对!";
										break;
									}
									if (!"姓名".equals(t_name)) {
										flag = 3;
										message = "excel格式不对!";
										break;
									}
									if (!"密码".equals(t_password)) {
										flag = 3;
										message = "excel格式不对!";
										break;
									}
									if (!"职位".equals(t_titles)) {
										flag = 3;
										message = "excel格式不对!";
										break;
									}
									if (!"教研室".equals(t_office)) {
										flag = 3;
										message = "excel格式不对!";
										break;
									}
									if (!"院系".equals(t_dept)) {
										flag = 3;
										message = "excel格式不对!";
										break;
									}

								}
								if (rowNum > 0) {
									

									// 异常原因：Excel数据Cell有不同的类型�?
									// 当我们试图从�?个数字类型的Cell读取出一个字符串并写入数据库�?

									String t_no = getCellValue(row.getCell(0));
									String t_name = getCellValue(row.getCell(1));
									String t_password = getCellValue(row.getCell(2));
									String t_titles = getCellValue(row.getCell(3));
									String t_office = getCellValue(row.getCell(4));
									String t_dept = getCellValue(row.getCell(5));

									//封装
									Teacher teacher = new Teacher();
									teacher.setT_no(t_no);
									teacher.setT_name(t_name);
									teacher.setT_password(t_password);
									teacher.setT_titles(t_titles);
									teacher.setT_office(t_office);
									teacher.setT_dept(t_dept);
									teacher.setT_type("1");
									

									// 调到service�?
									PersonService service = new PersonService();
									service.teacher_upload(teacher);

									// System.out.println("成功");
									flag = 1;

								}
							}
						} 
					}

			} catch (Exception e) {			
				e.printStackTrace();
				flag = 3;
			}
		}

		request.setAttribute("flag", flag);
		request.getRequestDispatcher("/InputPersonInformation.jsp").forward(request, response);

	}

	private void ViewTeacher_update(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int currentPage = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));

		// 搜索
		String t_no = request.getParameter("t_no") == null ? "" : request.getParameter("t_no");
		String t_name = request.getParameter("t_name") == null ? "" : request.getParameter("t_name");
		String t_office = request.getParameter("t_office") == null ? "" : request.getParameter("t_office");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("t_no", t_no);
		map.put("t_name", t_name);
		map.put("t_office", t_office);

		PersonService service = new PersonService();
		List<ViewTeacher_update> teacherList = service.findTeacherData_update(currentPage, pageSize, map);

		int total = 0;
		if ("t_no".equals("") && "t_name".equals("") || "t_office".equals("")) {
			total = service.findAllTeacherTotal();
		} else {
			total = service.findTeacherTotal(map);
		}

		Gson gson = new Gson();
		String json = "{\"total\":" + total + ", \"rows\":" + gson.toJson(teacherList) + "}";
		// System.out.println(json);
		response.getWriter().write(json);
		// request.getRequestDispatcher("/ViewStudent.jsp").forward(request,response);

	}

	private void UpdateTeacherInformation(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, String[]> teacherlist = request.getParameterMap();
		// 封装数据
		Teacher teacher = new Teacher();
		Json j = new Json();
		Gson gson = new Gson();
		try {
			BeanUtils.populate(teacher, teacherlist);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		PersonService service = new PersonService();
		try {
			service.UpdateTeacherInformation(teacher);
			j.setSuccess(true);
			j.setMsg("修改数据成功�?");
		} catch (SQLException e) {
			e.printStackTrace();
			j.setMsg("修改数据失败�?");
		}
		String json = gson.toJson(j);
		response.getWriter().write(json);

	}

	private void DeleteTeacher(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String t_id = request.getParameter("ids");
		PersonService service = new PersonService();
		Json j = new Json();
		Gson gson = new Gson();
		try {
			service.delTeacherByT_id(t_id);
			j.setSuccess(true);
			j.setMsg("删除成功�?");
		} catch (SQLException e) {
			e.printStackTrace();
			j.setMsg("删除失败�?");
		}
		String json = gson.toJson(j);
		response.getWriter().write(json);

	}

	// 更新学生信息
	private void UpdateStudentInformation(HttpServletRequest request, HttpServletResponse response) throws IOException {

		Map<String, String[]> studentlist = request.getParameterMap();
		// 封装数据
		Student student = new Student();
		Json j = new Json();
		Gson gson = new Gson();
		try {
			BeanUtils.populate(student, studentlist);
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		PersonService service = new PersonService();
		try {
			service.UpdateStudentInformation(student);
			j.setSuccess(true);
			j.setMsg("修改数据成功");
		} catch (SQLException e) {
			e.printStackTrace();
			j.setMsg("修改数据失败");
		}
		String json = gson.toJson(j);
		response.getWriter().write(json);

	}

	private void ViewStudent_update(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int currentPage = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));

		// 搜索
		String s_no = request.getParameter("s_no") == null ? "" : request.getParameter("s_no");
		String s_name = request.getParameter("s_name") == null ? "" : request.getParameter("s_name");
		String s_class = request.getParameter("s_class") == null ? "" : request.getParameter("s_class");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("s_no", s_no);
		map.put("s_name", s_name);
		map.put("s_class", s_class);

		PersonService service = new PersonService();
		List<ViewStudent_update> studentList = service.findStudentData_update(currentPage, pageSize, map);

		int total = 0;
		if ("s_no".equals("") && "s_name".equals("") || "s_class".equals("")) {
			total = service.findAllStudentTotal();
		} else {
			total = service.findStudentTotal(map);
		}

		Gson gson = new Gson();
		String json = "{\"total\":" + total + ", \"rows\":" + gson.toJson(studentList) + "}";
		// System.out.println(json);
		response.getWriter().write(json);
		// request.getRequestDispatcher("/ViewStudent.jsp").forward(request,response);

	}

	// 删除学生信息
	private void DeleteStudentInformation(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String s_id = request.getParameter("ids");
		PersonService service = new PersonService();
		Json j = new Json();
		Gson gson = new Gson();
		try {
			service.delStudentByS_id(s_id);
			j.setSuccess(true);
			j.setMsg("删除成功");
		} catch (SQLException e) {
			e.printStackTrace();
			j.setMsg("删除失败");
		}
		String json = gson.toJson(j);
		response.getWriter().write(json);

	}

	// 人员信息查询 --------------查询老师的信�?
	private void ViewTeacher(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {

		int currentPage = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));

		// 搜索
		String t_no = request.getParameter("t_no") == null ? "" : request.getParameter("t_no");
		String t_name = request.getParameter("t_name") == null ? "" : request.getParameter("t_name");
		String t_office = request.getParameter("t_office") == null ? "" : request.getParameter("t_office");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("t_no", t_no);
		map.put("t_name", t_name);
		map.put("t_office", t_office);

		PersonService service = new PersonService();
		List<SearchTeacher> teacherList = service.findTeacherData(currentPage, pageSize, map);

		int total = 0;
		if ("t_no".equals("") && "t_name".equals("") || "t_office".equals("")) {
			total = service.findAllTeacherTotal();
		} else {
			total = service.findTeacherTotal(map);
		}

		Gson gson = new Gson();
		String json = "{\"total\":" + total + ", \"rows\":" + gson.toJson(teacherList) + "}";
		// System.out.println(json);
		response.getWriter().write(json);
		// request.getRequestDispatcher("/ViewStudent.jsp").forward(request,response);

	}

	// 人员信息查询 --------------查询学生的信�?
	private void ViewStudent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int currentPage = Integer.parseInt(request.getParameter("page"));
		int pageSize = Integer.parseInt(request.getParameter("rows"));

		// 搜索
		String s_no = request.getParameter("s_no") == null ? "" : request.getParameter("s_no");
		String s_name = request.getParameter("s_name") == null ? "" : request.getParameter("s_name");
		String s_class = request.getParameter("s_class") == null ? "" : request.getParameter("s_class");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("s_no", s_no);
		map.put("s_name", s_name);
		map.put("s_class", s_class);

		PersonService service = new PersonService();
		List<ViewStudent> studentList = service.findStudentData(currentPage, pageSize, map);

		int total = 0;
		if ("s_no".equals("") && "s_name".equals("") || "s_class".equals("")) {
			total = service.findAllStudentTotal();
		} else {
			total = service.findStudentTotal(map);
		}

		Gson gson = new Gson();
		String json = "{\"total\":" + total + ", \"rows\":" + gson.toJson(studentList) + "}";
		// System.out.println(json);
		response.getWriter().write(json);
		// request.getRequestDispatcher("/ViewStudent.jsp").forward(request,response);

	}

	// 批量导入学生
	private void student_upload(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
						Workbook workbook = null;												
						if (".xls".equals(excelContentType) || ".xlt".equals(excelContentType)){							
							 workbook = new HSSFWorkbook(item.getInputStream());
						}else if(".xlsx".equals(excelContentType) || ".xlsm".equals(excelContentType)){
							 workbook = new XSSFWorkbook(item.getInputStream());
						}else{
							flag = 2;
							break;
						}
							
							Sheet sheet = workbook.getSheetAt(0); // 得到工作页（标签�?
							for (Row row : sheet) {
								int rowNum = row.getRowNum();
								// System.out.println(rowNum);
								if (rowNum == 0) {
									String s_no = row.getCell(0).getStringCellValue();
									String s_name = row.getCell(1).getStringCellValue();
									String s_password = row.getCell(2).getStringCellValue();
									String s_class = row.getCell(3).getStringCellValue();
									String s_dept = row.getCell(4).getStringCellValue();

									if (!"学号".equals(s_no)) {
										flag = 3;
										message = "excel格式不对!";
										break;
									}
									if (!"姓名".equals(s_name)) {
										flag = 3;
										message = "excel格式不对!";
										break;
									}
									if (!"密码".equals(s_password)) {
										flag = 3;
										message = "excel格式不对!";
										break;
									}
									if (!"班级".equals(s_class)) {
										flag = 3;
										message = "excel格式不对!";
										break;
									}
									if (!"院系".equals(s_dept)) {
										flag = 3;
										message = "excel格式不对!";
										break;
									}

								}
								if (rowNum > 0) {
									/*
									 * String s_no =
									 * row.getCell(0).getStringCellValue();
									 * String s_name =
									 * row.getCell(1).getStringCellValue();
									 * String s_password =
									 * row.getCell(2).getStringCellValue();
									 * String s_class =
									 * row.getCell(3).getStringCellValue();
									 * String s_dept =
									 * row.getCell(4).getStringCellValue();
									 */

									// 异常原因：Excel数据Cell有不同的类型�?
									// 当我们试图从�?个数字类型的Cell读取出一个字符串并写入数据库�?

									String s_no = getCellValue(row.getCell(0));
									String s_name = getCellValue(row.getCell(1));
									String s_password = getCellValue(row.getCell(2));
									String s_class = getCellValue(row.getCell(3));
									String s_dept = getCellValue(row.getCell(4));

									Student student = new Student();
									student.setS_no(s_no);
									student.setS_name(s_name);
									student.setS_password(s_password);
									student.setS_class(s_class);
									student.setS_dept(s_dept);
									student.setS_type("2");

									// 调到service�?
									PersonService service = new PersonService();
									service.student_upload(student);

									// System.out.println("成功");
									flag = 1;

								}
							}
						}
					}
			} catch (Exception e) {
				flag = 3;
				e.printStackTrace();
			}
		}

		request.setAttribute("flag", flag);
		request.getRequestDispatcher("/InputPersonInformation.jsp").forward(request, response);
	}

	// 增加人员
	private void AddPersonInformation(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String type = request.getParameter("link_type");
		int flag = 0;
		if (type.equals("1")) {
			Map<String, String[]> personlist = request.getParameterMap();
			Teacher teacher = new Teacher();
			try {
				BeanUtils.populate(teacher, personlist);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			teacher.setT_type(type);
			PersonService service = new PersonService();
			try {
				service.addTeacher(teacher);
				flag = 1;
			} catch (SQLException e) {
				flag = 0;
				e.printStackTrace();
			}

			// 跳转
			request.setAttribute("flag", flag);
			request.getRequestDispatcher("/InputPersonInformation.jsp").forward(request, response);
		}
		if (type.equals("2")) {
			Map<String, String[]> personlist = request.getParameterMap();
			Student student = new Student();
			try {
				BeanUtils.populate(student, personlist);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			student.setS_type(type);
			PersonService service = new PersonService();
			try {
				service.addStudent(student);
				flag = 1;
			} catch (SQLException e) {
				flag = 0;
				e.printStackTrace();
			}
			// 跳转
			request.setAttribute("flag", flag);
			request.getRequestDispatcher("/InputPersonInformation.jsp").forward(request, response);
		}
		// response.sendRedirect(request.getContextPath()+"/ViewTeacherServlet");

	}

	// 传入cell的�?�，进行cell值类型的判断，并返回String类型
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

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}