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
import com.coursemanager.entity.Grade;
import com.coursemanager.entity.GradeInfo;
import com.coursemanager.entity.GradeInfo2;
import com.coursemanager.entity.Searchtask;
import com.coursemanager.entity.Student;
import com.coursemanager.entity.Task;
import com.coursemanager.util.DataSourceUtils;

import sun.security.util.Length;

public class PracticeDao {

	
	
	public List<Course> findAllCourse() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select distinct c_name from course";		
	    return  runner.query(sql, new BeanListHandler<Course>(Course.class));
	}
	
	public List<Course> findAllClass() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select distinct c_class from course";		
	    return  runner.query(sql, new BeanListHandler<Course>(Course.class));
	}
	
	public List<Task> findStudentCourse(String sclass) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select distinct tk_name from task where tk_class=? ";		
	    return  runner.query(sql, new BeanListHandler<Task>(Task.class),sclass);
	}
	
	public List<Course> findTeacherCourse(String tid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select distinct c_name from course c join teacher t on c.t_id=t.t_id where t.t_id=?";		
	    return  runner.query(sql, new BeanListHandler<Course>(Course.class),tid);
	}

	public List<Course> findTeacherClass(String tid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select distinct c_class from course where t_id=?";		
	    return  runner.query(sql, new BeanListHandler<Course>(Course.class),tid);
	}

	public void addTask(Task task) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into task values(?,?,?,?,?,?,?,?,?)";
		runner.update(sql, task.getTk_id(),task.getC_id(),task.getTk_name(),task.getTk_class(),task.getTk_type(),
				task.getTk_proportion(),task.getTk_title(),task.getTk_value(),task.getTk_content());		
	}

	public List<Course> findCidByNameAndClass(String tname, String tclass) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select c_id from course where c_name=? and c_class=?";		
		 return  runner.query(sql, new BeanListHandler<Course>(Course.class),tname,tclass);
	}

	public List<Task> AdminfindTaskList(int currentPage, int pageSize, Searchtask searchtask) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from task t join course c on t.c_id=c.c_id where 1=1 ";	
		if(searchtask.getTk_name()!=null&&!searchtask.getTk_name().trim().equals("")){
			sql+="and t.tk_name = ? ";
			list.add(searchtask.getTk_name().trim());
		}
		if(searchtask.getTk_class()!=null&&!searchtask.getTk_class().trim().equals("")){
			sql+="and t.tk_class = ? ";
			list.add(searchtask.getTk_class().trim());
		}
		if(searchtask.getTk_type()!=null&&!searchtask.getTk_type().trim().equals("")){
			sql+="and t.tk_type = ? ";
			list.add(searchtask.getTk_type().trim());
		}
		sql+="order by t.tk_id limit ?,?";
		list.add((currentPage-1)*pageSize);
		list.add(pageSize);
		return  runner.query(sql, new BeanListHandler<Task>(Task.class),list.toArray());
	}

	public List<Task> TeacherfindTaskList(int currentPage, int pageSize,Searchtask searchtask, String tid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from task t join course c on t.c_id=c.c_id where c.t_id=? ";	
		list.add(tid);
		if(searchtask.getTk_name()!=null&&!searchtask.getTk_name().trim().equals("")){
			sql+="and t.tk_name = ? ";
			list.add(searchtask.getTk_name().trim());
		}
		if(searchtask.getTk_class()!=null&&!searchtask.getTk_class().trim().equals("")){
			sql+="and t.tk_class = ? ";
			list.add(searchtask.getTk_class().trim());
		}
		if(searchtask.getTk_type()!=null&&!searchtask.getTk_type().trim().equals("")){
			sql+="and t.tk_type = ? ";
			list.add(searchtask.getTk_type().trim());
		}
		sql+="order by t.tk_name limit ?,?";
		list.add((currentPage-1)*pageSize);
		list.add(pageSize);
		return  runner.query(sql, new BeanListHandler<Task>(Task.class),list.toArray());
	}

	public List<Task> StudentfindTaskList(int currentPage, int pageSize,Searchtask searchtask,String sclass) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from task where tk_class=? ";	
		list.add(sclass);
		if(searchtask.getTk_name()!=null&&!searchtask.getTk_name().trim().equals("")){
			sql+="and tk_name = ? ";
			list.add(searchtask.getTk_name().trim());
		}
		if(searchtask.getTk_type()!=null&&!searchtask.getTk_type().trim().equals("")){
			sql+="and tk_type = ? ";
			list.add(searchtask.getTk_type().trim());
		}
		sql+="order by tk_id limit ?,?";
		list.add((currentPage-1)*pageSize);
		list.add(pageSize);
		return  runner.query(sql, new BeanListHandler<Task>(Task.class),list.toArray());
	}

	public int getAdminTotalCount(Searchtask searchtask) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Object> list = new ArrayList<Object>();
		String sql = "select count(*) from task t join course c on t.c_id=c.c_id where 1=1 ";	
		if(searchtask.getTk_name()!=null&&!searchtask.getTk_name().trim().equals("")){
			sql+=" and t.tk_name = ? ";
			list.add(searchtask.getTk_name().trim());
		}
		if(searchtask.getTk_class()!=null&&!searchtask.getTk_class().trim().equals("")){
			sql+=" and t.tk_class = ? ";
			list.add(searchtask.getTk_class().trim());
		}
		if(searchtask.getTk_type()!=null&&!searchtask.getTk_type().trim().equals("")){
			sql+=" and t.tk_type = ? ";
			list.add(searchtask.getTk_type().trim());
		}
		Long query = (Long) runner.query(sql,new ScalarHandler(),list.toArray()); 
		return query.intValue();
	}
	
	public int getTeacherTotalCount(Searchtask searchtask, String tid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Object> list = new ArrayList<Object>();
		String sql = "select count(*) from task t join course c on t.c_id=c.c_id where 1=1 and c.t_id=? ";	
		list.add(tid);
		if(searchtask.getTk_name()!=null&&!searchtask.getTk_name().trim().equals("")){
			sql+="and t.tk_name = ? ";
			list.add(searchtask.getTk_name().trim());
		}
		if(searchtask.getTk_class()!=null&&!searchtask.getTk_class().trim().equals("")){
			sql+="and t.tk_class = ? ";
			list.add(searchtask.getTk_class().trim());
		}
		if(searchtask.getTk_type()!=null&&!searchtask.getTk_type().trim().equals("")){
			sql+="and t.tk_type = ? ";
			list.add(searchtask.getTk_type().trim());
		}
		Long query = (Long) runner.query(sql,new ScalarHandler(),list.toArray()); 
		return query.intValue();
	}
	
	public int getStudentTotalCount(Searchtask searchtask, String sclass) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Object> list = new ArrayList<Object>();
		String sql = "select count(*) from task where tk_class=? ";	
		list.add(sclass);
		if(searchtask.getTk_name()!=null&&!searchtask.getTk_name().trim().equals("")){
			sql+="and tk_name = ? ";
			list.add(searchtask.getTk_name().trim());
		}
		if(searchtask.getTk_type()!=null&&!searchtask.getTk_type().trim().equals("")){
			sql+="and tk_type = ? ";
			list.add(searchtask.getTk_type().trim());
		}
		Long query = (Long) runner.query(sql,new ScalarHandler(),list.toArray()); 
		return query.intValue();
	}

	public List<Object> FindClassByTname(String tname,String tid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select* from course c join teacher t on c.t_id=t.t_id where t.t_id=? and c.c_name=?";		      
		List<Object> query = runner.query(sql,new ColumnListHandler("c_class"),tid,tname);
		return query;
	}

	public void delTaskBytid(String taskid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from task where tk_id=?";
		runner.update(sql, taskid);
		
	}

	public void updateTask(Task task) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update task set tk_name=?,tk_class=?,tk_type=?,tk_time=?,tk_proportion=? where tk_id=?";
		runner.update(sql, task.getTk_name(),task.getTk_class(),task.getTk_type(),task.getTk_time(),task.getTk_proportion(),
				task.getTk_id());
		
	}

	public List<Task> findGradeCourse(String tid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select distinct tk_name from task t join course c on t.c_id=c.c_id where c.t_id=?";		
		return  runner.query(sql, new BeanListHandler<Task>(Task.class),tid);
	}
	
	public List<Task> FindGradeClass(String tkname, String tid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select distinct tk_class from task t join course c on t.c_id=c.c_id where c.t_id=? and t.tk_name=?";		      
		return  runner.query(sql, new BeanListHandler<Task>(Task.class),tid,tkname);
	}

	public List<Task> FindGradeClass(String tkname, String tkclass, String tid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select distinct tk_type from task t join course c on t.c_id=c.c_id where c.t_id=? and t.tk_name=? and t.tk_class=?";		      
		return  runner.query(sql, new BeanListHandler<Task>(Task.class),tid,tkname,tkclass);
	}
	
	public List<GradeInfo> findStudentByClass(String tkclass,String tkid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT s_no,s_name,s_class,g.t_id,g_1,g_2,g_3,g_4,g_5,g_id,s.s_id,tk_id,g_total,g_remark FROM student s "
				+ "LEFT JOIN (SELECT * FROM grade WHERE tk_id=?) g ON s.s_id=g.s_id WHERE s_class =? GROUP BY s_no";		
		return  runner.query(sql, new BeanListHandler<GradeInfo>(GradeInfo.class),tkid,tkclass);
		
	}

	public List<Task> findTkid(String tname, String tclass, String ttype) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from task where tk_name=? and tk_class=? and tk_type=?";
		return runner.query(sql, new BeanListHandler<Task>(Task.class), tname,tclass,ttype);
	}

	public void addGrade(Grade grade,String cid,String tkid,String tid, Integer avg) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into grade values(?,?,?,?,?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE g_1=?,g_2=?,g_3=?,g_4=?,g_5=?,g_total=?,g_remark=? ";
		runner.update(sql,grade.getG_id(),cid,tkid,grade.getS_id(),tid,grade.getG_1(),grade.getG_2(),grade.getG_3(),grade.getG_4(),grade.getG_5(),avg,grade.getG_remark(),
				grade.getG_1(),grade.getG_2(),grade.getG_3(),grade.getG_4(),grade.getG_5(),avg,grade.getG_remark());		
	}

	public List<GradeInfo> AdminfindGradeList(int currentPage, int pageSize, Searchtask searchTGrade) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Object> list = new ArrayList<Object>();
		String sql = "SELECT s_no,s_name,tk_class,tk_name,tk_type,g_1,g_2,g_3,g_4,g_5,g_total,g_remark,g_id "
				+ "FROM task t JOIN grade g ON t.tk_id = g.tk_id AND t.c_id = g.c_id "
				+ "JOIN student s ON g.s_id = s.s_id "
				+ "WHERE 1 = 1 ";
		if(searchTGrade.getTk_name()!=null&&!searchTGrade.getTk_name().trim().equals("")){
			sql+="and tk_name = ? ";
			list.add(searchTGrade.getTk_name().trim());
		}
		if(searchTGrade.getTk_class()!=null&&!searchTGrade.getTk_class().trim().equals("")){
			sql+="and tk_class = ? ";
			list.add(searchTGrade.getTk_class().trim());
		}
		if(searchTGrade.getTk_type()!=null&&!searchTGrade.getTk_type().trim().equals("")){
			sql+="and tk_type = ? ";
			list.add(searchTGrade.getTk_type().trim());
		}
		sql+="order by s_no limit ?,?";
		list.add((currentPage-1)*pageSize);
		list.add(pageSize);
		return  runner.query(sql, new BeanListHandler<GradeInfo>(GradeInfo.class),list.toArray());
	}
	
	
	
	public List<GradeInfo> StudentfindGradeList(int currentPage, int pageSize, Searchtask searchgrand, String sno) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Object> list = new ArrayList<Object>();
		String sql = "SELECT grade.g_id,student.s_name,student.s_no,student.s_class,task.tk_name,task.tk_type,grade.g_score "
				+ "FROM student, task, grade WHERE grade.s_id = student.s_id AND grade.tk_id = task.tk_id "
				+ "AND student.s_no=? ";
		list.add(sno);
		if(searchgrand.getTk_name()!=null&&!searchgrand.getTk_name().trim().equals("")){
			sql+="and task.tk_name = ? ";
			list.add(searchgrand.getTk_name().trim());
		}
		if(searchgrand.getTk_type()!=null&&!searchgrand.getTk_type().trim().equals("")){
			sql+="and task.tk_type = ? ";
			list.add(searchgrand.getTk_type().trim());
		}
		sql+="order by grade.g_id limit ?,?";
		list.add((currentPage-1)*pageSize);
		list.add(pageSize);
		return  runner.query(sql, new BeanListHandler<GradeInfo>(GradeInfo.class),list.toArray());
	}

	public int getAdminGradeTotalCount(Searchtask searchTGrade) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Object> list = new ArrayList<Object>();
		String sql = "SELECT COUNT(*) FROM task t "
				+ "JOIN grade g ON t.tk_id = g.tk_id AND t.c_id = g.c_id "
				+ "JOIN student s ON g.s_id = s.s_id "
				+ "WHERE 1 = 1 ";

		if(searchTGrade.getTk_name()!=null&&!searchTGrade.getTk_name().trim().equals("")){
			sql+="and tk_name = ? ";
			list.add(searchTGrade.getTk_name().trim());
		}
		if(searchTGrade.getTk_class()!=null&&!searchTGrade.getTk_class().trim().equals("")){
			sql+="and tk_class = ? ";
			list.add(searchTGrade.getTk_class().trim());
		}
		if(searchTGrade.getTk_type()!=null&&!searchTGrade.getTk_type().trim().equals("")){
			sql+="and tk_type = ? ";
			list.add(searchTGrade.getTk_type().trim());
		}
		Long query = (Long) runner.query(sql,new ScalarHandler(),list.toArray()); 
		return query.intValue();
	}

	
	
	
	public List<Task> findGrandTeacherCourse(String tid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select distinct tk_class from task t join course c on t.c_id=c.c_id where c.t_id=?";		
		return  runner.query(sql, new BeanListHandler<Task>(Task.class),tid);
	}

	public List<Course> findGradeStudentCourse(String sclass) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select c_name from course where c_class=?";		
		return  runner.query(sql, new BeanListHandler<Course>(Course.class),sclass);
	}

	public int findCourseStudent(String sclass) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from course where c_class=?";
		Long query = (Long) runner.query(sql,new ScalarHandler(),sclass); 
		return query.intValue();
	}

	public int findEvaluation(String sid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from teach_evaluation where s_id=?";
		Long query = (Long) runner.query(sql,new ScalarHandler(),sid); 
		return query.intValue();
	}

	public List<Task> findTaskId(String tid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT DISTINCT task.tk_id FROM teacher, task, course "
				+ "WHERE task.c_id = course.c_id AND course.t_id = ? GROUP BY task.tk_id";		
	    return  runner.query(sql, new BeanListHandler<Task>(Task.class),tid);
	}

	public List<GradeInfo2> findGradeTeacher(List<Task> taskid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Object> list = new ArrayList<Object>();
		int i = 1;
		String sql="SELECT s.s_name,s.s_no,";
		for (Task task : taskid) {			
			sql+="SUM(CASE g.tk_id WHEN ? THEN g.g_score ELSE 0 END) as t?,";
			
			list.add(task.getTk_id().trim());
			list.add(i);
			i++;
		}				
		sql+="SUM(g.g_score) as 'total' FROM student s LEFT OUTER JOIN grade g on s.s_id=g.s_id where ";
		for (Task task : taskid) {
		sql+="g.tk_id = ? or " ;	
		list.add(task.getTk_id().trim());
		}
		sql = sql.substring(0,sql.length()-3);
		sql+="GROUP BY s.s_name";
		return  runner.query(sql, new BeanListHandler<GradeInfo2>(GradeInfo2.class),list.toArray());
	}

	public List<Task> findTaskIdBysid(String sid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT DISTINCT tk_id FROM grade WHERE s_id = ?";
	    return  runner.query(sql, new BeanListHandler<Task>(Task.class),sid);
	}

	public List<GradeInfo> findGradeStudent(int currentPage, int pageSize, String sid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Object> list = new ArrayList<Object>();
		String sql = "SELECT tk_name,tk_class,tk_type,g_1,g_2,g_3,g_4,g_5,g_total,g_id "
				+ "FROM task t JOIN grade g ON t.tk_id = g.tk_id AND t.c_id = g.c_id "
				+ "WHERE g.s_id = ? ";
		sql+="order by g_id limit ?,?";
		list.add(sid);
		list.add((currentPage-1)*pageSize);
		list.add(pageSize);
		return  runner.query(sql, new BeanListHandler<GradeInfo>(GradeInfo.class),list.toArray());
	}

	public List<Task> findTaskIdAdmin() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT DISTINCT tk_id FROM grade ";
	    return  runner.query(sql, new BeanListHandler<Task>(Task.class));
	}

	public List<GradeInfo2> findGradeAdmin(List<Task> taskid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Object> list = new ArrayList<Object>();
		int i = 1;
		String sql="SELECT s.s_name,s.s_no,";
		for (Task task : taskid) {			
			sql+="SUM(CASE g.tk_id WHEN ? THEN g.g_score ELSE 0 END) as t?,";
			
			list.add(task.getTk_id().trim());
			list.add(i);
			i++;
		}				
		sql+="SUM(g.g_score) as 'total' FROM student s LEFT OUTER JOIN grade g on s.s_id=g.s_id where ";
		for (Task task : taskid) {
		sql+="g.tk_id = ? or " ;	
		list.add(task.getTk_id().trim());
		}
		sql+="1=1 GROUP BY s.s_name";
		return  runner.query(sql, new BeanListHandler<GradeInfo2>(GradeInfo2.class),list.toArray());
	}

	public void addTask2(Task task) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into task values(?,?,?,?,?,?,?,?,?)";
		runner.update(sql,task.getTk_id(),task.getC_id(),task.getTk_name(),task.getTk_class(),
				task.getTk_type(),task.getTk_time(),task.getTk_proportion(),null,null);
	}

	public void updataPwd_A(String pwd, String a_id) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update admin set a_password = ? where a_id = ?";
		runner.update(sql,pwd,a_id);
	}

	public void updataPwd_T(String pwd, String t_id) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update teacher set t_password = ? where t_id = ?";
		runner.update(sql,pwd,t_id);
		
	}

	public void updataPwd_S(String pwd, String s_id) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update student set s_password = ? where s_id = ?";
		runner.update(sql,pwd,s_id);
		
	}

	public int getStuGradeTotalCount(String sid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "SELECT COUNT(*) FROM task t JOIN grade g ON t.tk_id = g.tk_id AND t.c_id = g.c_id "
				+ "WHERE g.s_id = ?";
		Long query = (Long) runner.query(sql,new ScalarHandler(),sid); 
		return query.intValue();
	}

	public List<GradeInfo> findGradeTeacher(int currentPage, int pageSize, Searchtask searchTGrade, String tid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Object> list = new ArrayList<Object>();
		String sql = "SELECT s_no,s_name,tk_class,tk_name,tk_type,g_1,g_2,g_3,g_4,g_5,g_total,g_remark,g_id "
				+ "FROM task t JOIN grade g ON t.tk_id = g.tk_id AND t.c_id = g.c_id "
				+ "JOIN student s ON g.s_id = s.s_id "
				+ "WHERE g.t_id = ? ";
		list.add(tid);
		if(searchTGrade.getTk_name()!=null&&!searchTGrade.getTk_name().trim().equals("")){
			sql+="and tk_name = ? ";
			list.add(searchTGrade.getTk_name().trim());
		}
		if(searchTGrade.getTk_class()!=null&&!searchTGrade.getTk_class().trim().equals("")){
			sql+="and tk_class = ? ";
			list.add(searchTGrade.getTk_class().trim());
		}
		if(searchTGrade.getTk_type()!=null&&!searchTGrade.getTk_type().trim().equals("")){
			sql+="and tk_type = ? ";
			list.add(searchTGrade.getTk_type().trim());
		}
		sql+="order by s_no limit ?,?";
		list.add((currentPage-1)*pageSize);
		list.add(pageSize);
		return  runner.query(sql, new BeanListHandler<GradeInfo>(GradeInfo.class),list.toArray());
	}

	public int getTGradeTotalCount(Searchtask searchTGrade, String tid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Object> list = new ArrayList<Object>();
		String sql = "SELECT COUNT(*) FROM task t "
				+ "JOIN grade g ON t.tk_id = g.tk_id AND t.c_id = g.c_id "
				+ "JOIN student s ON g.s_id = s.s_id "
				+ "WHERE g.t_id =?";
		list.add(tid);
		if(searchTGrade.getTk_name()!=null&&!searchTGrade.getTk_name().trim().equals("")){
			sql+="and tk_name = ? ";
			list.add(searchTGrade.getTk_name().trim());
		}
		if(searchTGrade.getTk_class()!=null&&!searchTGrade.getTk_class().trim().equals("")){
			sql+="and tk_class = ? ";
			list.add(searchTGrade.getTk_class().trim());
		}
		if(searchTGrade.getTk_type()!=null&&!searchTGrade.getTk_type().trim().equals("")){
			sql+="and tk_type = ? ";
			list.add(searchTGrade.getTk_type().trim());
		}
		Long query = (Long) runner.query(sql,new ScalarHandler(),list.toArray()); 
		return query.intValue();
	}

	

	

	
	

	
	
}
