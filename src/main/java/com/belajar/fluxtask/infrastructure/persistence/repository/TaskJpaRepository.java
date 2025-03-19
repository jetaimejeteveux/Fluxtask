/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package com.belajar.fluxtask.infrastructure.persistence.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.belajar.fluxtask.infrastructure.persistence.entity.TaskEntity;

/**
 *
 * @author firman
 */
public interface TaskJpaRepository extends JpaRepository<TaskEntity, UUID> {

}
