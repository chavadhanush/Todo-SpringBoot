package com.ltp.springboottodoapplication.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Table(name = "todo_list")
public class Todo {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @NotBlank(message = "Description is required")
    private String description;

    @Getter
    @Setter
    private boolean complete;

    @Getter
    @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDate createdDate;

    @Getter
    @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDate modifiedDate;

    public Todo() {
    }

    public Todo(String description) {
        this.description = description;
        this.complete = false; // Assuming a new todo is not complete by default
        this.createdDate = LocalDate.now(); // Initialize with the current timestamp
        this.modifiedDate = LocalDate.now(); // Initialize with the current timestamp
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", complete=" + complete +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                '}';
    }
}
