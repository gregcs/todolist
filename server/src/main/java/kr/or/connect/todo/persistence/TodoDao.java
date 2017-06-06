package kr.or.connect.todo.persistence;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class TodoDao {
	
	private NamedParameterJdbcTemplate jdbc;
	private SimpleJdbcInsert insertAction;
	private RowMapper<TodoVO> rowMapper = BeanPropertyRowMapper.newInstance(TodoVO.class);
	
	public TodoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
		this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("TODO").usingGeneratedKeyColumns("id");
	}

	
	// SELETE(Count)
	public int countTodo() {
		Map<String, Object> params = Collections.emptyMap();
		return jdbc.queryForObject(TodoSqls.COUNT_TODO, params, Integer.class);
	}


	// SELETE(All)
	public List<TodoVO> selectAll() {
		Map<String, Object> params = Collections.emptyMap();
		return jdbc.query(TodoSqls.SELECT_ALL, params, rowMapper);
	}

	
	// SELETE(ById)
	public TodoVO selectById(Integer id) {
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		return jdbc.queryForObject(TodoSqls.SELECT_BY_ID, params, rowMapper);
	}

	
	// CREATE(Enter)
	public Integer insert(TodoVO todo) {
		SqlParameterSource params = new BeanPropertySqlParameterSource(todo);
		return insertAction.executeAndReturnKey(params).intValue();
	}

	
	// DELET(Check)
	public int deleteById(Integer id) {
		Map<String, ?> params = Collections.singletonMap("id", id);
		return jdbc.update(TodoSqls.DELETE_BY_ID, params);
	}

	
	// DELETE(Completed)
	public int deleteCompleted() {
		Map<String, Object> params = Collections.emptyMap();
		return jdbc.update(TodoSqls.DELETE_COMPLETED, params);
	}

	
	// UPDATE(Check)
	public int update(TodoVO todo) {
		int checked;
		System.out.println(todo.getCompleted());
		if (todo.getCompleted() == 0) {
			checked = 0;
		} else {
			checked = 1;
		}
		final String UPDATEQuery = "UPDATE TODO SET completed = " + checked + " WHERE id =" + todo.getId();
		SqlParameterSource params = new BeanPropertySqlParameterSource(todo);
		return jdbc.update(UPDATEQuery, params);
	}

	
	// FILLTER(Todo)
	public List<TodoVO> filterTodo() {
		Map<String, Object> params = Collections.emptyMap();
		return jdbc.query(TodoSqls.SELECT_TODO_FILTER, params, rowMapper);
	}

	
	// FILLTER(Did)
	public List<TodoVO> filterDid() {
		Map<String, Object> params = Collections.emptyMap();
		return jdbc.query(TodoSqls.SELECT_DID_FILTER, params, rowMapper);	
	}
}//end of class
