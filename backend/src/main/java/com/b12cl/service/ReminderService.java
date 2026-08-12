package com.b12cl.service;

import com.b12cl.model.Reminder;
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
        return null;
    }

    public Reminder updateReminder(Long id, Reminder updatedReminder) {
        for (Reminder reminder : reminders) {
            if (Objects.equals(reminder.getId(), id)) {
                reminder.setTitle(updatedReminder.getTitle());
                reminder.setRadius(updatedReminder.getRadius());
                reminder.setLatitude(updatedReminder.getLatitude());
                reminder.setLongitude(updatedReminder.getLongitude());

                return reminder;
            }
        }
        return null;
    }

    public boolean deleteReminder(Long id) {

        Iterator<Reminder> iterator = reminders.iterator();

        while (iterator.hasNext()) {

            Reminder reminder = iterator.next();

            if (Objects.equals(reminder.getId(), id)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    public List<Reminder> getAllReminders() {
        return reminders;
    }
}
