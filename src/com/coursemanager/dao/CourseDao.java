package com.coursemanager.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.coursemanager.entity.Course;
import com.coursemanager.entity.Searchcourse;
import com.coursemanager.entity.User;
import com.coursemanager.util.DataSourceUtils;

public class CourseDao {

	public void addCourse(Course course) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into course values(?,?,?,?,?,?)";
		runner.update(sql, course.getC_id(),course.getT_id(),course.getC_name(),course.getC_class(),course.getC_year(),
				course.getC_credit());
	}

	public List<Course> findProductListForPageBean(int index, int currentCount) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		//limit 索引  条数
		//SELECT * FROM 表名�? LIMIT M,N ，Limit限制的是从结果集的M位置处取出N条输�?,其余抛弃.全表扫描,
		//速度会很�? �? 有的数据库结果集返回不稳定，适用于数据量较少的情�?(元组�?/千级)，可优化
		String sql = "select * from course limit ?,?";
		return runner.query(sql, new BeanListHandler<Course>(Course.class), index,currentCount);
	}

	public void delCourseByCid(String cid) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "delete from course where c_id=?";
		runner.update(sql, cid);
		
	}


	public void updateCourse(Course course) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "update course set c_name=?,c_class=?,c_year=?,c_credit=? where c_id=?";
		runner.update(sql, course.getC_name(),course.getC_class(),course.getC_year(),course.getC_credit(),course.getC_id());
		
	}

	public List<Course> UIfindCourseListForPageBean(int currentPage, int pageSize,Searchcourse searchcourse,User user) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from course where 1=1 ";
		if(searchcourse.getT_name()!=null&&!searchcourse.getT_name().trim().equals("")){
			sql+=" and t_name like ? ";
			list.add("%"+searchcourse.getT_name().trim()+"%");
		}
		if(searchcourse.getT_no()!=null&&!searchcourse.getT_no().trim().equals("")){
			sql+=" and c_type like ? ";
			list.add("%"+searchcourse.getT_no().trim()+"%");
		}
		if(searchcourse.getC_class()!=null&&!searchcourse.getC_class().trim().equals("")){
			sql+=" and c_class like ? ";
			list.add("%"+searchcourse.getC_class().trim()+"%");
		}
		sql+=" limit ?,?";
		list.add((currentPage-1)*pageSize);
		list.add(pageSize);
		return  runner.query(sql, new BeanListHandler<Course>(Course.class),list.toArray());
		//return runner.query(sql, new BeanListHandler<Course>(Course.class), (currentPage-1)*pageSize,pageSize);
	}

	

	public List<Course> AdminfindCourseList(int currentPage, int pageSize, Searchcourse searchcourse,String date) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from course c join teacher t on c.t_id=t.t_id where c.c_year=? ";
		if(searchcourse.getC_year()!=null&&!searchcourse.getC_year().trim().equals("")){
			list.add(searchcourse.getC_year().trim());
		}else{
			list.add(date);
		}
		if(searchcourse.getT_name()!=null&&!searchcourse.getT_name().trim().equals("")){
			sql+=" and t.t_name like ? ";
			list.add("%"+searchcourse.getT_name().trim()+"%");
		}
		if(searchcourse.getT_no()!=null&&!searchcourse.getT_no().trim().equals("")){
			sql+=" and t.t_no like ? ";
			list.add("%"+searchcourse.getT_no().trim()+"%");
		}
		if(searchcourse.getC_class()!=null&&!searchcourse.getC_class().trim().equals("")){
			sql+=" and c.c_class like ? ";
			list.add("%"+searchcourse.getC_class().trim()+"%");
		}
		
		sql+="order by c_id limit ?,?";
		list.add((currentPage-1)*pageSize);
		list.add(pageSize);
		return  runner.query(sql, new BeanListHandler<Course>(Course.class),list.toArray());
	}

	public List<Course> TeacherfindCourseList(int currentPage, int pageSize,Searchcourse searchcourse, String tid,String date) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Object> list = new ArrayList<Object>();
		String sql = "select * from course c join teacher t on c.t_id=t.t_id where t.t_id=? and c.c_year=? ";
		list.add(tid);
		if(searchcourse.getC_year()!=null&&!searchcourse.getC_year().trim().equals("")){
			list.add(searchcourse.getC_year().trim());
		}else{
			list.add(date);
		}
		sql+="order by c_id limit ?,?";
		list.add((currentPage-1)*pageSize);
		list.add(pageSize);
	    return  runner.query(sql, new BeanListHandler<Course>(Course.class),list.toArray());
	}

	public List<Course> StudentfindCourseList(int currentPage, int pageSize,Searchcourse searchcourse, String sclass,String date) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Object> list = new ArrayList<Object>();
		list.add(sclass);
		String sql = "select * from course where c_class=? and c_year=? ";	
		if(searchcourse.getC_year()!=null&&!searchcourse.getC_year().trim().equals("")){
			list.add(searchcourse.getC_year().trim());
		}else{
			list.add(date);
		}
		sql+="order by c_id limit ?,?";
		list.add((currentPage-1)*pageSize);
		list.add(pageSize);
	    return  runner.query(sql, new BeanListHandler<Course>(Course.class),list.toArray());
	}

	public int getAdminTotalCount(Searchcourse searchcourse) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		List<Object> list = new ArrayList<Object>();
		String sql = "select count(*) from course c join teacher t on c.t_id=t.t_id where 1=1 ";	
		if(searchcourse.getT_name()!=null&&!searchcourse.getT_name().trim().equals("")){
			sql+=" and t.t_name like ? ";
			list.add("%"+searchcourse.getT_name().trim()+"%");
		}
		if(searchcourse.getT_no()!=null&&!searchcourse.getT_no().trim().equals("")){
			sql+=" and t.t_no like ? ";
			list.add("%"+searchcourse.getT_no().trim()+"%");
		}
		if(searchcourse.getC_class()!=null&&!searchcourse.getC_class().trim().equals("")){
			sql+=" and c.c_class like ? ";
			list.add("%"+searchcourse.getC_class().trim()+"%");
		}
		Long query = (Long) runner.query(sql,new ScalarHandler(),list.toArray()); 
		return query.intValue();
	}

	public int getTeacherTotalCount(String tno) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from course c join teacher t on c.t_id=t.t_id where t_no=?";
		Long query = (Long) runner.query(sql,new ScalarHandler(),tno); 
		return query.intValue();
	}

	public int getStudentTotalCount(String sclass) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from course where c_class=?";
		Long query = (Long) runner.query(sql,new ScalarHandler(),sclass); 
		return query.intValue();
	}

	public void add_course(Course course, String t_id) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into course values(?,?,?,?,?,?)";
		runner.update(sql, course.getC_id(),t_id,course.getC_name(),course.getC_class(),course.getC_year(),
				course.getC_credit());
		
	}

}
