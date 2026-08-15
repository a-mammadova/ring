package com.b12cl.service;

import com.b12cl.dto.UpdateReminderRequest;
import com.b12cl.model.Reminder;
import com.b12cl.exception.ReminderNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Service
public class ReminderService {
    private final List<Reminder> reminders = new ArrayList<>();

    private Long nextId = 1L;

    public Reminder createReminder(Reminder reminder) {
        reminder.setId(nextId);
        reminder.setActive(true);

        reminders.add(reminder);

        nextId++;
        return reminder;
    }

    public Reminder getReminderById(Long id) {
        for (Reminder reminder : reminders) {
            if (Objects.equals(reminder.getId(), id)) {
                return reminder;
            }
        }
        throw new ReminderNotFoundException(id);
    }

    public Reminder updateReminder(Long id, UpdateReminderRequest request) {
        for (Reminder reminder : reminders) {
            if (Objects.equals(reminder.getId(), id)) {
                reminder.setTitle(request.getTitle());
                reminder.setRadius(request.getRadius());
                reminder.setLatitude(request.getLatitude());
                reminder.setLongitude(request.getLongitude());

                return reminder;
            }
        }
        throw new ReminderNotFoundException(id);
    }

    public void deleteReminder(Long id) {

        Iterator<Reminder> iterator = reminders.iterator();

        while (iterator.hasNext()) {

            Reminder reminder = iterator.next();

            if (Objects.equals(reminder.getId(), id)) {
                iterator.remove();
                return;
            }
        }
        throw new ReminderNotFoundException(id);
    }

    public List<Reminder> getAllReminders() {
        return reminders;
    }
}
