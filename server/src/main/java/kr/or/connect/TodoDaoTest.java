package kr.or.connect;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import kr.or.connect.todo.AppConfig;
import kr.or.connect.todo.persistence.TodoDao;
import kr.or.connect.todo.persistence.TodoVO;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class TodoDaoTest {
	ConfigurableApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	DataSource dataSource = context.getBean(DataSource.class);
	TodoDao dao = new TodoDao(dataSource);
	TodoVO todoVO;
	
	@Test
	public void shouldCount() {
		int count = dao.countTodo();
		System.out.println(count);
	}
	
	@Test
	public void shouldSelect() {
		List<TodoVO> list = dao.selectAll();
		System.out.println(list);
	}	   
}