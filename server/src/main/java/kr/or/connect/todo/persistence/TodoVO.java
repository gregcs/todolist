package kr.or.connect.todo.persistence;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class TodoVO {
	
	   private Integer id;
	   @NotNull
	   @Size(min = 1, max = 127)
	   private String todo;
	   private Integer completed;
	   private Timestamp date;
	   
	   //NoArgsConstructor
	   public TodoVO() {

	   }
	   
	   //AllArgsConstructor
	   public TodoVO(Integer id, String todo, Integer completed, Timestamp date) {
	      this.id = id;
	      this.todo = todo;
	      this.completed = completed;
	      this.date = date;
	   }

	   public int getId() {
	      return id;
	   }

	   public void setId(Integer id) {
	      this.id = id;
	   }

	   public String getTodo() {
	      return todo;
	   }

	   public void setTodo(String todo) {
	      this.todo = todo;
	   }

	   public Integer getCompleted() {
	      return completed;
	   }

	   public void setCompleted(Integer completed) {
	      this.completed = completed;
	   }

	   public Timestamp getDate() {
	      return date;
	   }

	   public void setDate(Timestamp date) {
	      this.date = date;
	   }

	   @Override
	   public String toString() {
	      return "Todo [id=" + id + ", todo=" + todo + ", completed=" + completed + ", date=" + date + "]";
	   }
}
