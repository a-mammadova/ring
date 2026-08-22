package com.b12cl.service;

import com.b12cl.dto.UpdateReminderRequest;
import com.b12cl.model.Reminder;
import com.b12cl.exception.ReminderNotFoundException;
import com.b12cl.repository.ReminderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReminderService {

    public ReminderRepository reminderRepository;

    public ReminderService(ReminderRepository reminderRepository) {
        this.reminderRepository = reminderRepository;
    }
    public Reminder createReminder(Reminder reminder) {
        reminder.setActive(true);

        return reminderRepository.save(reminder);
    }

    public Reminder getReminderById(Long id) {
        return reminderRepository.findById(id).orElseThrow(() -> new ReminderNotFoundException(id));
    }

    public Reminder updateReminder(Long id, UpdateReminderRequest request) {
        Reminder reminder = reminderRepository.findById(id).orElseThrow(() -> new ReminderNotFoundException(id));

        reminder.setTitle(request.getTitle());
        reminder.setRadius(request.getRadius());
        reminder.setLatitude(request.getLatitude());
        reminder.setLongitude(request.getLongitude());

        return reminderRepository.save(reminder);

    }

    public void deleteReminder(Long id) {

        Reminder reminder = reminderRepository.findById(id).orElseThrow(() -> new ReminderNotFoundException(id));

        reminderRepository.delete(reminder);
    }

    public List<Reminder> getAllReminders() {
        return reminderRepository.findAll();
    }
}
