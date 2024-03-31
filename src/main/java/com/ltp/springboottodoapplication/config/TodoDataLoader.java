package com.ltp.springboottodoapplication.config;

import com.ltp.springboottodoapplication.entity.Todo;
import com.ltp.springboottodoapplication.repository.TodoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



@Component
public class TodoDataLoader implements CommandLineRunner{
    private final Logger logger = LoggerFactory.getLogger(TodoDataLoader.class);

    @Autowired
    TodoRepository todoRepository;

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        if (todoRepository.count() == 0) {
            Todo todo1 = new Todo("Get the Milk");
            Todo todo2 = new Todo("water the plants");

            todoRepository.save(todo1);
            todoRepository.save(todo2);
        }
        logger.info("No of Todo items :{}",todoRepository.count());
    }
}
