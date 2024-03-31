package com.ltp.springboottodoapplication.repository;

import com.ltp.springboottodoapplication.entity.Todo;
import org.springframework.data.repository.CrudRepository;

public interface TodoRepository extends CrudRepository<Todo,Long> {
}
