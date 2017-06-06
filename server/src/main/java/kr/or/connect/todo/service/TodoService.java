package kr.or.connect.todo.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.or.connect.todo.persistence.TodoDao;
import kr.or.connect.todo.persistence.TodoVO;

@Service
public class TodoService {

	TodoDao dao;

	@Autowired
	public TodoService(TodoDao dao) {
		this.dao = dao;
	}

	public TodoVO findById(Integer id) {
		return dao.selectById(id);
	}

	// SELETE(Count)
	public int countTodo() {
		return dao.countTodo();
	}

	// SELETE(All)
	public Collection<TodoVO> findAll() {
		return dao.selectAll();
	}

	// CREATE(Enter)
	public TodoVO create(TodoVO todo) {
		Integer id = dao.insert(todo);
		todo.setId(id);
		return todo;
	}

	// UPDATE(Check)
	public boolean update(TodoVO todo) {
		int affected = dao.update(todo);
		return affected == 1;
	}

	// DELET(Check)
	public boolean delete(Integer id) {
		int affected = dao.deleteById(id);
		return affected == 1;
	}
	
	// DELETE(Completed)
	public boolean deleteCompleted() {
		int affected = dao.deleteCompleted();
		return affected == 1;
	}

	// FILLTER(Todo)
	public Collection<TodoVO> filterTodo() {
		return dao.filterTodo();
	}

	// FILLTER(Did)
	public Collection<TodoVO> filterDid() {	
		return dao.filterDid();
	}

}