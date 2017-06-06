package kr.or.connect.todo.persistence;

public class TodoSqls {
	static final String SELECT_BY_ID = "SELECT id, TODO, COMPLETED, DATE FROM TODO where id = :id";
	static final String COUNT_TODO = "SELECT COUNT(*) FROM TODO WHERE completed=0";
	static final String SELECT_TODO_FILTER = "SELECT * FROM TODO  WHERE completed = 0 ORDER BY date";
	static final String SELECT_DID_FILTER = "SELECT * FROM TODO  WHERE completed = 1 ORDER BY date";
	static final String SELECT_ALL = "SELECT * FROM TODO ORDER BY date ";
	static final String DELETE_COMPLETED = "DELETE  FROM TODO WHERE completed=1";
	static final String DELETE_BY_ID = "DELETE FROM TODO WHERE id= :id";
}
