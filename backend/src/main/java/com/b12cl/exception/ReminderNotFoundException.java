package com.b12cl.exception;

public class ReminderNotFoundException extends RuntimeException {
    public ReminderNotFoundException (Long id) {super("Reminder with id " + id + " was not found.");}
}