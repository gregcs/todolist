package kr.or.connect.todo.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import kr.or.connect.todo.persistence.TodoVO;
import kr.or.connect.todo.service.TodoService;



@RestController
@RequestMapping("/api/todos")
public class TodoController {
	
	private final TodoService service;

	@Autowired
	public TodoController(TodoService service) {
		this.service = service;	
	}

	
	@GetMapping("/findall")
	Collection<TodoVO> readList() {
		return service.findAll();
	}
	
	
	@GetMapping("/count")
	int hello() {
		return service.countTodo();
	}
	
	
	@GetMapping("/active")
	Collection<TodoVO> filterTodo() {
		return service.filterTodo();
	}
	
	
	@GetMapping("/completed")
	Collection<TodoVO> filterDid() {
		return service.filterDid();	
	}
	
	
	@GetMapping("/{id}")
	TodoVO read(@PathVariable Integer id) {
		return service.findById(id);
	}

	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	TodoVO create(@RequestBody TodoVO todo) {
		return service.create(todo);		
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void delete(@PathVariable Integer id) {
		service.delete(id);
	}
	
	
	@DeleteMapping("/deleteCompleted")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteCompleted() {
		service.deleteCompleted();
	}
	
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void update(@PathVariable Integer id, @RequestBody TodoVO todo) {
		todo.setId(id);
		service.update(todo);	
	}
} //end of class