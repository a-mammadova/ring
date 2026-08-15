package com.b12cl.controller;

import com.b12cl.dto.CreateReminderRequest;
import com.b12cl.dto.UpdateReminderRequest;
import com.b12cl.model.Reminder;
import com.b12cl.service.ReminderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Reminder> createReminder(@Valid @RequestBody CreateReminderRequest request) {
        Reminder reminder = new Reminder(request.getTitle(), request.getLatitude(), request.getLongitude(),
                request.getRadius());

        Reminder createdReminder = reminderService.createReminder(reminder);

        return ResponseEntity.status(HttpStatus.CREATED).body(createdReminder);
    }

    @GetMapping("/{id}")
    public Reminder getReminderById(@PathVariable Long id) {
        return reminderService.getReminderById(id);
    }

    @PutMapping("/{id}")
    public Reminder updateReminder(@PathVariable Long id, @Valid @RequestBody UpdateReminderRequest request) {
        return reminderService.updateReminder(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReminder(@PathVariable Long id) {
        reminderService.deleteReminder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<Reminder> getAllReminders() {
        return reminderService.getAllReminders();
    }
}
