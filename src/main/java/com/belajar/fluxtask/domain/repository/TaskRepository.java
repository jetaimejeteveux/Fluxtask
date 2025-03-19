/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.belajar.fluxtask.domain.repository;

import java.util.Optional;
import java.util.UUID;
import com.belajar.fluxtask.domain.model.Task;

/**
 *
 * @author firman
 */
public interface TaskRepository {
    Task save(Task task);
    Optional<Task> findById(UUID id);
    void update(Task task);
}
