package com.coursemanager.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.coursemanager.entity.Course;
import com.coursemanager.entity.Message;
import com.coursemanager.entity.Searchmessage;
import com.coursemanager.entity.Task;
import com.coursemanager.util.DataSourceUtils;

public class MessageDao {

	public void addSMessage(Message message, String type) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into message values(?,?,?,?,?,?)";
		runner.update(sql,message.getM_id(),message.getM_type(),message.getM_object(),message.getMs_scope(),message.getM_content(),type);
	}
	public void addTMessage(Message message, String type) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into message values(?,?,?,?,?,?)";
		runner.update(sql,message.getM_id(),message.getM_type(),message.getM_object(),message.getMt_scope(),message.getM_content(),type);
	}

	public List<Object> FindScopetdept() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select distinct t_dept from teacher";		      
		List<Object> query = runner.query(sql,new ColumnListHandler());
		return query;
	}

	public List<Object> FindScopetoffice() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select distinct t_office from teacher";		      
		List<Object> query = runner.query(sql,new ColumnListHandler());
		return query;
	}

	public List<Object> FindScopesdept() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select distinct s_dept from student";		      
		List<Object> query = runner.query(sql,new ColumnListHandler());
		return query;
	}

	public List<Object> FindScopesclass() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select distinct s_class from student";		      
		List<Object> query = runner.query(sql,new ColumnListHandler());
		return query;
	}
	public List<Message> AdminfindMessageList(int currentPage, int pageSize, Searchmessage searchmessage) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from message  where 1=1 ";	
		if(searchmessage.getM_scope()!=null&&!searchmessage.getM_scope().trim().equals("")){
			sql+="and m_scope = ? ";
			list.add(searchmessage.getM_scope().trim());
		}
		if(searchmessage.getM_object()!=null&&!searchmessage.getM_object().trim().equals("")){
			sql+="and m_object = ? ";
			list.add(searchmessage.getM_object().trim());
		}
		if(searchmessage.getM_type()!=null&&!searchmessage.getM_type().trim().equals("")){
			sql+="and m_type = ? ";
			list.add(searchmessage.getM_type().trim());
		}
		sql+="order by m_type limit ?,?";
		list.add((currentPage-1)*pageSize);
		list.add(pageSize);
		return  runner.query(sql, new BeanListHandler<Message>(Message.class),list.toArray());
	}
	public List<Message> TeacherfindMessageList(int currentPage, int pageSize,String toffice,String tdept) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from message where (m_scope=? or m_scope=?) and m_type='教师' ";
		sql+="order by m_id limit ?,?";
		list.add(toffice);
		list.add(tdept);
		list.add((currentPage-1)*pageSize);
		list.add(pageSize);
		return  runner.query(sql, new BeanListHandler<Message>(Message.class),list.toArray());
	}
	public List<Message> StudentfindMessageList(int currentPage, int pageSize,String sclass,String sdept) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from message where (m_scope=? or m_scope=?) and m_type='学生' ";	
		sql+="order by m_id limit ?,?";
		list.add(sclass);
		list.add(sdept);
		list.add((currentPage-1)*pageSize);
		list.add(pageSize);
		return  runner.query(sql, new BeanListHandler<Message>(Message.class),list.toArray());
	}
	
	public int getTeacherTotalCount(String toffice, String tdept) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Object> list = new ArrayList<Object>();
		String sql = "select count(*) from message where (m_scope=? or m_scope=?) and m_type='教师' ";
		list.add(toffice);
		list.add(tdept);
		Long query = (Long) runner.query(sql,new ScalarHandler(),list.toArray()); 
		return query.intValue();
	}
	public int getStudentTotalCount(String sclass, String sdept) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Object> list = new ArrayList<Object>();
		String sql = "select count(*) from message where (m_scope=? or m_scope=?) and m_type='学生' ";
		list.add(sclass);
		list.add(sdept);
		Long query = (Long) runner.query(sql,new ScalarHandler(),list.toArray()); 
		return query.intValue();
	}
	public List<Course> getTClass(String tid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select distinct c_class from course where t_id = ? ";		      
		List<Course> query = runner.query(sql,new BeanListHandler<Course>(Course.class),tid);
		return query;
	}
	public void addTSMessage(Message message, String type) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into message values(?,?,?,?,?,?)";
		runner.update(sql,message.getM_id(),message.getM_type(),message.getM_object(),message.getMs_scope(),message.getM_content(),type);
		
	}
	public void DelMessage(String id) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from message where m_id=?";
		runner.update(sql, id);
		
	}
	public void updateMessage(Message message) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update message set m_type = ?,m_object = ?,m_content = ? where m_id=?";
		runner.update(sql,message.getM_type(),message.getM_object(),message.getM_content(),message.getM_id());
		
	}
	public int getAdminTotalCount(Searchmessage searchmessage) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Object> list = new ArrayList<Object>();
		String sql = "select count(*) from message where 1 = 1  ";	
		if(searchmessage.getM_scope()!=null&&!searchmessage.getM_scope().trim().equals("")){
			sql+="and m_scope = ? ";
			list.add(searchmessage.getM_scope().trim());
		}
		if(searchmessage.getM_object()!=null&&!searchmessage.getM_object().trim().equals("")){
			sql+="and m_object = ? ";
			list.add(searchmessage.getM_object().trim());
		}
		if(searchmessage.getM_type()!=null&&!searchmessage.getM_type().trim().equals("")){
			sql+="and m_type = ? ";
			list.add(searchmessage.getM_type().trim());
		}
		Long query = (Long) runner.query(sql,new ScalarHandler(),list.toArray()); 
		return query.intValue();
	}
	public List<Message> TMessagefindPageBean(int currentPage, int pageSize, Searchmessage searchmessage, String tid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Object> list = new ArrayList<Object>();
		String sql = "SELECT * FROM message WHERE m_type='学生' AND m_flag=1 AND m_scope IN(SELECT c_class from course WHERE t_id =?) ";
		list.add(tid);
		if(searchmessage.getM_scope()!=null&&!searchmessage.getM_scope().trim().equals("")){
			sql+="and m_scope = ? ";
			list.add(searchmessage.getM_scope().trim());
		}
		
		sql+="order by m_type limit ?,?";
		list.add((currentPage-1)*pageSize);
		list.add(pageSize);
		return  runner.query(sql, new BeanListHandler<Message>(Message.class),list.toArray());
	}
	public int getTTotalCount(Searchmessage searchmessage, String tid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Object> list = new ArrayList<Object>();
		String sql = "SELECT count(*) FROM message WHERE m_type='学生' AND m_flag=1 AND m_scope IN(SELECT c_class from course WHERE t_id =?) ";
		list.add(tid);
		if(searchmessage.getM_scope()!=null&&!searchmessage.getM_scope().trim().equals("")){
			sql+="and m_scope = ? ";
			list.add(searchmessage.getM_scope().trim());
		}
		
		Long query = (Long) runner.query(sql,new ScalarHandler(),list.toArray()); 
		return query.intValue();
	}

	
	


}
