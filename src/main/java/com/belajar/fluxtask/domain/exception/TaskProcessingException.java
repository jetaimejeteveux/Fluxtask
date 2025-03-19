/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.belajar.fluxtask.domain.exception;

/**
 *
 * @author firman
 */
public class TaskProcessingException extends RuntimeException {
    public TaskProcessingException(String message) {
        super(message);
    }

    public TaskProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
