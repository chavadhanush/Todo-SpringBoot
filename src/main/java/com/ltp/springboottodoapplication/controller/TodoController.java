package com.ltp.springboottodoapplication.controller;

import com.ltp.springboottodoapplication.entity.Todo;
import com.ltp.springboottodoapplication.repository.TodoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class TodoController {

    @Autowired
    private TodoRepository todoRepository;

    @GetMapping("/")
    public String showTodoList(Model model) {
        model.addAttribute("todoitems", todoRepository.findAll());
        return "form"; // Make sure you have a Thymeleaf template named "form"
    }

    @GetMapping("/edit/{id}")
    public String showUpdateTodoForm(@PathVariable("id") long id, Model model) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo item not found with id: " + id));
        model.addAttribute("todo", todo);
        return "update-todo-list"; // Make sure you have a Thymeleaf template named "update-todo-list"
    }

    @GetMapping("/addTodo")
    public String showAddTodoForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "add-todo-form"; // Make sure you have a Thymeleaf template for the add todo form
    }

    @PostMapping("/todo")
    public String createTodoItem(@Valid @ModelAttribute Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            // Handle validation errors
            return "add-todo-form"; // Return back to the add todo form with error messages
        }
        todo.setCreatedDate(LocalDate.now());
        todo.setModifiedDate(LocalDate.now());
        todoRepository.save(todo);
        return "redirect:/";
    }

    @PostMapping("/todo/{id}")
    public String updateTodo(@PathVariable("id") long id, @Valid @ModelAttribute Todo todo, BindingResult result) {
        if (result.hasErrors()) {
            // Handle validation errors
            return "update-todo-list"; // Return back to the update todo form with error messages
        }
        Todo existingTodo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo item not found with id: " + id));
        existingTodo.setDescription(todo.getDescription());
        existingTodo.setComplete(todo.isComplete());
        existingTodo.setModifiedDate(LocalDate.now());
        todoRepository.save(existingTodo);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteTodo(@PathVariable("id") long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Todo item not found with id: " + id));
        todoRepository.delete(todo);
        return "redirect:/";
    }

}
