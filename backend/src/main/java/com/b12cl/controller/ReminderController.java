package com.b12cl.controller;

import com.b12cl.model.Reminder;
import com.b12cl.service.ReminderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reminders")
public class ReminderController {
    private final ReminderService reminderService;


    public ReminderController(ReminderService reminderService) {
        this.reminderService = reminderService;
    }

    @PostMapping
    public Reminder createReminder(@RequestBody Reminder reminder) {
        return reminderService.createReminder(reminder);
    }

    @GetMapping("/{id}")
    public Reminder getReminderById(@PathVariable Long id) {
        return reminderService.getReminderById(id);
    }

    @PutMapping("/{id}")
    public Reminder updateReminder(@PathVariable Long id, @RequestBody Reminder reminder) {
        return reminderService.updateReminder(id, reminder);
    }

    @DeleteMapping("/{id}")
    public boolean deleteReminder(@PathVariable Long id) {
        return reminderService.deleteReminder(id);
    }

    @GetMapping
    public List<Reminder> getAllReminders() {
        return reminderService.getAllReminders();
    }
}
