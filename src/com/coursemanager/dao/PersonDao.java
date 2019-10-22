package com.coursemanager.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.coursemanager.entity.Course;
import com.coursemanager.entity.SearchTeacher;
import com.coursemanager.entity.Searchstudent;
import com.coursemanager.entity.Student;
import com.coursemanager.entity.Teacher;
import com.coursemanager.entity.ViewStudent;
import com.coursemanager.entity.ViewStudent_update;
import com.coursemanager.entity.ViewTeacher_update;
import com.coursemanager.util.DataSourceUtils;

public class PersonDao {

	public void addTeacher(Teacher teacher) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into teacher values(?,?,?,?,?,?,?,?)";
		runner.update(sql, teacher.getT_id(),teacher.getT_no(),teacher.getT_name(),teacher.getT_password(),
				teacher.getT_titles(),teacher.getT_office(),teacher.getT_dept(),teacher.getT_type());	
	}

	public void addStudent(Student student) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into student values(?,?,?,?,?,?,?)";
		runner.update(sql, student.getS_id(),student.getS_no(),student.getS_name(),student.getS_password(),
				student.getS_class(),student.getS_dept(),student.getS_type());
	}

	public void student_upload(Student student) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into student values(null,?,?,?,?,?,?)";
		runner.update(sql, student.getS_no(), student.getS_name(), student.getS_password(), student.getS_class(),
				student.getS_dept(),student.getS_type());

	}

	public List<ViewStudent> findStudentData(int currentPage, int pageSize, Map<String, Object> map)
			throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		String sql = "select s_id,s_no,s_name,s_class,s_dept from student where 1=1 ";
		// 遍历map
		Set<Entry<String, Object>> set = map.entrySet();
		Iterator io = set.iterator();
		while (io.hasNext()) {
			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
			// 学号查询
			if ("s_no".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " and " + me.getKey() + " = " + me.getValue();
			}
			// 姓名查询
			if ("s_name".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " and " + me.getKey() + " like " + "'%" + me.getValue() + "%'";
			}
			// 班级查询
			if ("s_class".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " and " + me.getKey() + " = " + me.getValue();
			}

		}

		sql = sql + " limit " + (currentPage - 1) * pageSize + " , " + pageSize;
		List<ViewStudent> query = runner.query(sql, new BeanListHandler<ViewStudent>(ViewStudent.class));
		return query;
	}

	public int findAllStudentTotal() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from student";
		Long query = (Long) runner.query(sql, new ScalarHandler());
		return query.intValue();

	}

	public int findStudentTotal(Map<String, Object> map) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from student where 1=1";
		// 遍历map
		Set<Entry<String, Object>> set = map.entrySet();
		Iterator io = set.iterator();
		while (io.hasNext()) {
			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
			// 学号查询
			if ("s_no".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " and " + me.getKey() + " = " + me.getValue();
			}
			// 姓名查询
			if ("s_name".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " and " + me.getKey() + " like " + "'%" + me.getValue() + "%'";
			}
			// 班级查询
			if ("s_class".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " and " + me.getKey() + " = " + me.getValue();
			}

		}
		Long query = (Long) runner.query(sql, new ScalarHandler());
		return query.intValue();
	}

	public List<SearchTeacher> findTeacherData(int currentPage, int pageSize, Map<String, Object> map)
			throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		String sql = "select t_id,t_no,t_name,t_dept,t_office,t_titles from teacher where 1=1 ";
		Set<Entry<String, Object>> set = map.entrySet();
		Iterator io = set.iterator();
		while (io.hasNext()) {
			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
			// 工号查询
			if ("t_no".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " and " + me.getKey() + " = " + me.getValue();
			}
			// 姓名查询
			if ("t_name".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " and " + me.getKey() + " like " + "'%" + me.getValue() + "%'";
			}
			// 教研室查�?
			if ("t_office".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " and " + me.getKey() + " = " + me.getValue();
			}

		}

		sql = sql + " limit " + (currentPage - 1) * pageSize + " , " + pageSize;
		List<SearchTeacher> query = runner.query(sql, new BeanListHandler<SearchTeacher>(SearchTeacher.class));
		return query;
	}

	public int findAllTeacherTotal() throws SQLException {

		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from teacher";
		Long query = (Long) runner.query(sql, new ScalarHandler());
		return query.intValue();
	}

	public int findTeacherTotal(Map<String, Object> map) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from teacher where 1=1";

		// 遍历map
		Set<Entry<String, Object>> set = map.entrySet();
		Iterator io = set.iterator();
		while (io.hasNext()) {
			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
			// 学号查询
			if ("t_no".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " and " + me.getKey() + " = " + me.getValue();
			}
			// 姓名查询
			if ("t_name".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " and " + me.getKey() + " like " + "'%" + me.getValue() + "%'";
			}
			// 班级查询
			if ("t_office".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " and " + me.getKey() + " = " + me.getValue();
			}

		}
		Long query = (Long) runner.query(sql, new ScalarHandler());
		return query.intValue();
	}

	// 删除学生
	public void deleteStudentByS_id(String ids) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from student where s_id=?";
		runner.update(sql, ids);
	}

	public List<ViewStudent_update> findStudentData_update(int currentPage, int pageSize, Map<String, Object> map)
			throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		String sql = "select s_id,s_no,s_name,s_class,s_dept,s_password from student where 1=1 ";
		// 遍历map
		Set<Entry<String, Object>> set = map.entrySet();
		Iterator io = set.iterator();
		while (io.hasNext()) {
			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
			// 学号查询
			if ("s_no".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " and " + me.getKey() + " = " + me.getValue();
			}
			// 姓名查询
			if ("s_name".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " and " + me.getKey() + " like " + "'%" + me.getValue() + "%'";
			}
			// 班级查询
			if ("s_class".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " and " + me.getKey() + " = " + me.getValue();
			}

		}

		sql = sql + " limit " + (currentPage - 1) * pageSize + " , " + pageSize;
		List<ViewStudent_update> query = runner.query(sql,
				new BeanListHandler<ViewStudent_update>(ViewStudent_update.class));
		return query;
	}

	public void UpdateStudentInformation(Student student) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update student set s_no=?,s_name=?,s_password=?,s_dept=?,s_class=? where s_id=?";
		runner.update(sql, student.getS_no(), student.getS_name(), student.getS_password(), student.getS_dept(),
				student.getS_class(), student.getS_id());

	}

	public void delTeacherByT_id(String ids) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql ="delete from teacher where t_id=?";
		runner.update(sql, ids);
	}

	public List<ViewTeacher_update> findTeacherData_update(int currentPage, int pageSize, Map<String, Object> map)
			throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		String sql = "select t_id,t_no,t_name,t_dept,t_office,t_titles,t_password,t_titles from teacher where 1=1 ";
		Set<Entry<String, Object>> set = map.entrySet();
		Iterator io = set.iterator();
		while (io.hasNext()) {
			Map.Entry<String, Object> me = (Map.Entry<String, Object>) io.next();
			// 工号查询
			if ("t_no".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " and " + me.getKey() + " = " + me.getValue();
			}
			// 姓名查询
			if ("t_name".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " and " + me.getKey() + " like " + "'%" + me.getValue() + "%'";
			}
			// 教研室查�?
			if ("t_office".equals(me.getKey()) && !"".equals(me.getValue())) {
				sql += " and " + me.getKey() + " = " + me.getValue();
			}

		}

		sql = sql + " limit " + (currentPage - 1) * pageSize + " , " + pageSize;
		List<ViewTeacher_update> query = runner.query(sql,
				new BeanListHandler<ViewTeacher_update>(ViewTeacher_update.class));
		return query;
	}

	public void UpdateTeacherInformation(Teacher teacher) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update teacher set t_no=?,t_name=?,t_password=?,t_dept=?,t_office=?,t_titles=? where t_id=?";
		runner.update(sql, teacher.getT_no(), teacher.getT_name(), teacher.getT_password(), teacher.getT_dept(),
				teacher.getT_office(), teacher.getT_titles(), teacher.getT_id());

	}

	public void teacher_upload(Teacher teacher) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql="insert into teacher values(null,?,?,?,?,?,?,?)";
		runner.update(sql,teacher.getT_no(),teacher.getT_name(),teacher.getT_password(),teacher.getT_titles()
				,teacher.getT_office(),teacher.getT_dept(),teacher.getT_type());
		
	}
}
