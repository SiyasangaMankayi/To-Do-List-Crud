
package com.example.todolistcrud.repository;

import com.example.todolistcrud.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

}
