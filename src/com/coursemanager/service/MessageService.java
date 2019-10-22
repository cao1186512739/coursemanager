package com.coursemanager.service;

import java.sql.SQLException;
import java.util.List;

import com.coursemanager.dao.MessageDao;
import com.coursemanager.dao.PracticeDao;
import com.coursemanager.entity.Course;
import com.coursemanager.entity.Message;
import com.coursemanager.entity.PageBean;
import com.coursemanager.entity.Searchmessage;
import com.coursemanager.entity.Task;
import com.coursemanager.entity.User;


public class MessageService {

	public void addMessage(Message message,String sscope,String tscope, String type) throws SQLException {
		if(sscope != null&&tscope.equals("")){
		MessageDao dao = new MessageDao();
		dao.addSMessage(message,type);
		}
		if(tscope != null && sscope.equals("")){
			MessageDao dao = new MessageDao();
			dao.addTMessage(message,type);
		}
	}

	public List<Object> FindScope(String mtype, String mscope) throws SQLException {
		List<Object> messageScpoe = null;
		if(mtype.equals("教师")&&mscope.equals("t_dept")){
			MessageDao dao = new MessageDao();
			return dao.FindScopetdept();
		}
		if(mtype.equals("教师")&&mscope.equals("t_office")){
			MessageDao dao = new MessageDao();
			return dao.FindScopetoffice();
		}
		if(mtype.equals("学生")&&mscope.equals("s_dept")){
			MessageDao dao = new MessageDao();
			return dao.FindScopesdept();
		}
		if(mtype.equals("学生")&&mscope.equals("s_class")){
			MessageDao dao = new MessageDao();
			return dao.FindScopesclass();
		}
		
		return messageScpoe;
	}

	public List<Message> MessagefindPageBean(int currentPage, int pageSize, Searchmessage searchmessage, User user) throws SQLException {
		MessageDao dao = new MessageDao();
		List<Message> messageList = null;
		if(user.getA_type()!=null&&user.getA_type().trim().equals("0")){
			messageList = dao.AdminfindMessageList(currentPage,pageSize,searchmessage);
		}
		if(user.getT_type()!=null&&user.getT_type().trim().equals("1")){
			String toffice = user.getT_dept();
			String tdept = user.getT_office();
			messageList = dao.TeacherfindMessageList(currentPage,pageSize,toffice,tdept);
		}
		if(user.getS_type()!=null&&user.getS_type().trim().equals("2")){
			String sclass = user.getS_class();
			String sdept = user.getS_dept();
			messageList = dao.StudentfindMessageList(currentPage,pageSize,sclass,sdept);
		}
		return messageList;
	}

	public int getMessageTotalCount(Searchmessage searchmessage, User user) throws SQLException {
		MessageDao dao = new MessageDao();
		int total = 0;
		if(user.getA_type()!=null&&user.getA_type().trim().equals("0")){
			total = dao.getAdminTotalCount(searchmessage);
		}
		if(user.getT_type()!=null&&user.getT_type().trim().equals("1")){
			String toffice = user.getT_dept();
			String tdept = user.getT_office();
			total = dao.getTeacherTotalCount(toffice,tdept);
		}
		if(user.getS_type()!=null&&user.getS_type().trim().equals("2")){
			String sclass = user.getS_class();
			String sdept = user.getS_dept();
			total = dao.getStudentTotalCount(sclass,sdept);
		}
		return total;
	}

	public List<Course> getTClass(String tid) throws SQLException {
		MessageDao dao = new MessageDao();
		return dao.getTClass(tid);
		
	}

	public void addTMessage(Message message, String type) throws SQLException {
		MessageDao dao = new MessageDao();
		dao.addTSMessage(message,type);
		
	}

	public void DelMessage(String mid) throws SQLException {
		MessageDao dao = new MessageDao();
		String[] ids =mid.split(",");
		for (String id : ids) {
		dao.DelMessage(id);
		}
		
	}

	public void updateMessage(Message message) throws SQLException {
		MessageDao dao = new MessageDao();
		dao.updateMessage(message);		
		
	}

	public List<Object> FindAllScope(String mscope) throws SQLException {
		List<Object> messageScpoe = null;
		MessageDao dao = new MessageDao();
		if(mscope.trim().equals("院系")){
			
			messageScpoe = dao.FindScopetdept();
		}
		if(mscope.trim().equals("教研室")){
			messageScpoe = dao.FindScopetoffice();	
		}
		if(mscope.trim().equals("班级")){
			messageScpoe = dao.FindScopesclass();
		}
		return messageScpoe;
	}

	public List<Message> TMessagefindPageBean(int currentPage, int pageSize, Searchmessage searchmessage, User user) throws SQLException {
		MessageDao dao = new MessageDao();
		List<Message> messageList = null;
		String tid = user.getT_id();
		messageList = dao.TMessagefindPageBean(currentPage,pageSize,searchmessage,tid);
		return messageList;
	}

	public int getTMessageTotalCount(Searchmessage searchmessage, User user) throws SQLException {
		MessageDao dao = new MessageDao();
		int total = 0;
		String tid = user.getT_id();		
		total = dao.getTTotalCount(searchmessage,tid);
		return total;
	}

	
}
