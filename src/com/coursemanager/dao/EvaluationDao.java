package com.coursemanager.dao;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.coursemanager.entity.Course;
import com.coursemanager.entity.Evaluation;
import com.coursemanager.entity.Teacher;
import com.coursemanager.entity.User;
import com.coursemanager.entity.ViewEvaluationResultTeacher;
import com.coursemanager.entity.ViewStudent;
import com.coursemanager.util.DataSourceUtils;

public class EvaluationDao {

	public List<Course> findCourseByS_class(String s_class) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from course where c_class=?";
		List<Course> query = runner.query(sql, new BeanListHandler<Course>(Course.class), s_class);
		return query;
	}

	public void ProcessEvaluation(Evaluation evaluation, User user) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "insert into teach_evaluation values(null,?,?,?,?,?,?,?)";
		runner.update(sql, evaluation.getC_id(), user.getS_id(), evaluation.getTe_attitude_grade(),
				evaluation.getTe_method_grade(), evaluation.getTe_interact_grade(), evaluation.getTe_multimedia_grade(),
				evaluation.getTe_effect_grade());
	}

	public List<Course> findCourseByT_id(User user) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from course where t_id=?";
		return runner.query(sql, new BeanListHandler<Course>(Course.class), user.getT_id());

	}

	public List<ViewEvaluationResultTeacher> findEvaluationResultByC_id(int currentPage, int pageSize, String c_id)
			throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());

		String sql = "select te_id,te_attitude_grade,te_method_grade,te_interact_grade,te_multimedia_grade,te_effect_grade from teach_evaluation where c_id="
				+ c_id;

		sql = sql + " limit " + (currentPage - 1) * pageSize + " , " + pageSize;
		List<ViewEvaluationResultTeacher> query = runner.query(sql,
				new BeanListHandler<ViewEvaluationResultTeacher>(ViewEvaluationResultTeacher.class));
		return query;
	}

	public int findEvaluationResultByC_idTotal(String c_id) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from teach_evaluation where c_id=?";
		Long query = (Long) runner.query(sql, new ScalarHandler(), c_id);
		return query.intValue();
	}

	public List<Course> findCourseByS_i(User user) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from course where c_class=?";
		List<Course> query = runner.query(sql, new BeanListHandler<Course>(Course.class), user.getS_class());
		return query;
	}

	public List<ViewEvaluationResultTeacher> findEvaluationResultByS_id(int currentPage, int pageSize, User user,
			String c_id) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select te_id,te_attitude_grade,te_method_grade,te_interact_grade,te_multimedia_grade,te_effect_grade from teach_evaluation where s_id="
				+ user.getS_id() + " and c_id=" + c_id;
		sql = sql + " limit " + (currentPage - 1) * pageSize + " , " + pageSize;
		List<ViewEvaluationResultTeacher> query = runner.query(sql,
				new BeanListHandler<ViewEvaluationResultTeacher>(ViewEvaluationResultTeacher.class));
		return query;
	}

	public int findEvaluationResultByS_idTotal(User user, String c_id) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from teach_evaluation where s_id=? and c_id=?";
		Long query = (Long) runner.query(sql, new ScalarHandler(), user.getS_id(), c_id);
		return query.intValue();
	}

	public List<Course> findCourseName() throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select distinct c_name from course";
		return runner.query(sql, new BeanListHandler<Course>(Course.class));

	}

	public List<Teacher> findTeacherNameByC_name(String c_name) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select distinct t.t_id,t.t_name from teacher t,course c where c.t_id=t.t_id and c_name=?";
		return runner.query(sql, new BeanListHandler<Teacher>(Teacher.class), c_name);

	}

	public List<ViewEvaluationResultTeacher> findEvaluationResultByC_Name_T_id(int pageSize, int currentPage,
			String t_id, String c_name) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select * from teach_evaluation te join course c on te.c_id=c.c_id where c.c_name=? and c.t_id=? limit "
				+ (currentPage - 1) * pageSize + " , " + pageSize;
		return runner.query(sql, new BeanListHandler<ViewEvaluationResultTeacher>(ViewEvaluationResultTeacher.class),
				c_name, t_id);

	}

	public int findEvaluationResultByC_Name_T_idTotal(String c_name, String t_id) throws SQLException {
		QueryRunner runner = new QueryRunner(DataSourceUtils.getDataSource());
		String sql = "select count(*) from teach_evaluation te join course c on te.c_id=c.c_id where c.c_name=? and c.t_id=?";
		Long query = (Long) runner.query(sql, new ScalarHandler(),c_name, t_id);
		return query.intValue();
	}

}
