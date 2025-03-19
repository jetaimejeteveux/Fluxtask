/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.belajar.fluxtask.application.service;

import java.util.UUID;
import com.belajar.fluxtask.application.dto.TaskRequest;
import com.belajar.fluxtask.application.dto.TaskResponse;
import com.belajar.fluxtask.domain.model.Task;

/**
 *
 * @author firman
 */
public interface TaskService {
    TaskResponse createTask(TaskRequest taskRequest);
    TaskResponse getTaskById(UUID taskId);
    void processTask(Task task);
    void handleTaskFailure(Task task);
}
