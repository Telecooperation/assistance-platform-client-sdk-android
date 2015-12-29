package de.tudarmstadt.informatik.tk.android.assistance.sdk.model.sensing.impl.contentobserver;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Reminders;
import android.support.annotation.Nullable;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import de.tudarmstadt.informatik.tk.android.assistance.sdk.db.DbCalendarEvent;
import de.tudarmstadt.informatik.tk.android.assistance.sdk.db.DbCalendarReminderEvent;
import de.tudarmstadt.informatik.tk.android.assistance.sdk.model.api.dto.DtoType;
import de.tudarmstadt.informatik.tk.android.assistance.sdk.model.sensing.AbstractContentObserverEvent;
import de.tudarmstadt.informatik.tk.android.assistance.sdk.util.DateUtils;
import de.tudarmstadt.informatik.tk.android.assistance.sdk.util.logger.Log;

/**
 * @author Unknown
 * @edited by Wladimir Schmidt (wlsc.dev@gmail.com)
 * @date 28.10.2015
 */
public class CalendarEvent extends AbstractContentObserverEvent {

    private static final String TAG = CalendarEvent.class.getSimpleName();

    private static CalendarEvent INSTANCE;

    private static final Uri URI_CALENDAR = android.provider.CalendarContract.Events.CONTENT_URI;
    private static final Uri URI_REMINDER = android.provider.CalendarContract.Reminders.CONTENT_URI;

    @Nullable
    private AsyncTask<Void, Void, Void> syncingTask;

    private static final String[] PROJECTION_EVENTS =
            {
                    Events._ID,
                    Events.ALL_DAY,
                    Events.AVAILABILITY,
                    Events.CALENDAR_ID,
                    Events.DESCRIPTION,
                    Events.DTSTART,
                    Events.DTEND,
                    Events.DURATION,
                    Events.EVENT_LOCATION,
                    Events.EVENT_TIMEZONE,
                    Events.EVENT_END_TIMEZONE,
                    Events.EXDATE,
                    Events.EXRULE,
                    Events.HAS_ALARM,
                    Events.LAST_DATE,
                    Events.ORIGINAL_ALL_DAY,
                    Events.ORIGINAL_ID,
                    Events.ORIGINAL_INSTANCE_TIME,
                    Events.RDATE,
                    Events.RRULE,
                    Events.STATUS,
                    Events.TITLE
            };

    private static final String[] PROJECTION_REMINDERS =
            {
                    Reminders._ID,
                    Reminders.METHOD,
                    Reminders.MINUTES};

    private CalendarEvent(Context context) {
        super(context);
    }

    /**
     * Gives singleton of this class
     *
     * @param context
     * @return
     */
    public static CalendarEvent getInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = new CalendarEvent(context);
        }

        return INSTANCE;
    }

    @Override
    public int getType() {
        return DtoType.CALENDAR;
    }

    @Override
    public void reset() {

    }

    protected void syncData() {

        Log.d(TAG, "Syncing data...");

        // Submit the query
        ContentResolver cr = context.getContentResolver();
        Cursor cur = cr.query(URI_CALENDAR, PROJECTION_EVENTS, "deleted=?", new String[]{"0"}, null);

        if (cur == null) {
            return;
        }

        Map<Long, DbCalendarEvent> allExistingEvents = new HashMap<>();

        try {

            List<DbCalendarEvent> events = daoProvider.getCalendarEventDao().getAll();

            for (DbCalendarEvent event : events) {
                allExistingEvents.put(event.getEventId(), event);
            }

            String created = DateUtils.dateToISO8601String(new Date(), Locale.getDefault());

            // Iterate over event
            while (cur.moveToNext() && isRunning()) {

                DbCalendarEvent event = new DbCalendarEvent();

                event.setEventId(getLongByColumnName(cur, Events._ID));
                event.setCalendarId(getLongByColumnName(cur, Events.CALENDAR_ID));
                event.setAllDay(getBoolByColumnName(cur, Events.ALL_DAY));
                event.setAvailability(getIntByColumnName(cur, Events.AVAILABILITY));
                event.setDescription(getStringByColumnName(cur, Events.DESCRIPTION));
                event.setTimestampStart(getLongByColumnName(cur, Events.DTSTART));
                event.setTimestampEnd(getLongByColumnName(cur, Events.DTEND));
                event.setDuration(getStringByColumnName(cur, Events.DURATION));
                event.setLocation(getStringByColumnName(cur, Events.EVENT_LOCATION));
                event.setTimezoneStart(getStringByColumnName(cur, Events.EVENT_TIMEZONE));
                event.setTimezoneEnd(getStringByColumnName(cur, Events.EVENT_END_TIMEZONE));
                event.setRecurrenceExceptionDate(getStringByColumnName(cur, Events.EXDATE));
                event.setRecurrenceExceptionRule(getStringByColumnName(cur, Events.EXRULE));
                event.setHasAlarm(getBoolByColumnName(cur, Events.HAS_ALARM));
                event.setLastDate(getLongByColumnName(cur, Events.LAST_DATE));
                event.setOriginalAllDay(getBoolByColumnName(cur, Events.ORIGINAL_ALL_DAY));
                event.setOriginalId(getStringByColumnName(cur, Events.ORIGINAL_ID));
                event.setOriginalInstanceTime(getLongByColumnName(cur, Events.ORIGINAL_INSTANCE_TIME));
                event.setRecurrenceDate(getStringByColumnName(cur, Events.RDATE));
                event.setRecurrenceRule(getStringByColumnName(cur, Events.RRULE));
                event.setStatus(getIntByColumnName(cur, Events.STATUS));
                event.setTitle(getStringByColumnName(cur, Events.TITLE));
                event.setIsNew(Boolean.TRUE);
                event.setIsDeleted(Boolean.FALSE);
                event.setIsUpdated(Boolean.FALSE);
                event.setCreated(created);

                try {
                    if (checkForChange(allExistingEvents, event)) {

                        Log.d(TAG, "Insert entry");
                        daoProvider.getCalendarEventDao().insert(event);
                        Log.d(TAG, "Finished");
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Cannot check calendar event for change", e);
                }

                syncReminders(event);
            }

        } catch (Exception e) {
            Log.e(TAG, "Cannot get all existing events!", e);
            return;
        } finally {
            cur.close();
        }

        for (DbCalendarEvent event : allExistingEvents.values()) {

            if (!isRunning()) {
                break;
            }

            syncReminders(event);
        }
    }

    protected void syncReminders(DbCalendarEvent event) {

        long eventId = event.getEventId();
        boolean hasAlarm = event.getHasAlarm();

        Map<Long, DbCalendarReminderEvent> mapExistingReminders = getExistingReminders(eventId);

        if (hasAlarm) {
            // optional selection
            final String selection = Reminders.EVENT_ID + " = ?";
            String[] selectionArgs = new String[]{String.valueOf(eventId)};

            // Submit the query
            ContentResolver cr = context.getContentResolver();

            Cursor cur = null;

            try {

                cur = cr.query(URI_REMINDER, PROJECTION_REMINDERS, selection, selectionArgs, null);

                if (cur == null) {
                    return;
                }

                String created = DateUtils.dateToISO8601String(new Date(), Locale.getDefault());

                // Iterate over event
                while (cur.moveToNext() && isRunning()) {

                    DbCalendarReminderEvent reminder = new DbCalendarReminderEvent();

                    reminder.setReminderId(getLongByColumnName(cur, Reminders._ID));
                    reminder.setEventId(eventId);
                    reminder.setMethod(getIntByColumnName(cur, Reminders.METHOD));
                    reminder.setMinutes(getIntByColumnName(cur, Reminders.MINUTES));
                    reminder.setIsNew(Boolean.TRUE);
                    reminder.setIsDeleted(Boolean.FALSE);
                    reminder.setIsUpdated(Boolean.FALSE);
                    reminder.setCreated(created);

                    try {
                        if (checkForReminderChange(mapExistingReminders, reminder)) {

                            Log.d(TAG, "Calendar reminder: Insert entry");
                            daoProvider.getCalendarReminderEventDao().insert(reminder);
                            Log.d(TAG, "Finished");
                        }
                    } catch (Exception e) {
                        Log.e(TAG, "Cannot check calendar reminder event for change", e);
                    }

                }
            } finally {
                if (cur != null) {
                    cur.close();
                }
            }

        }
    }

    private boolean checkForChange(Map<Long, DbCalendarEvent> map, DbCalendarEvent newItem) {

        Long eventId = newItem.getEventId();

        if (eventId == null) {
            return false;
        }

        DbCalendarEvent existingItem = map.remove(eventId);

        if (existingItem == null) {

            newItem.setIsNew(Boolean.TRUE);
            newItem.setIsUpdated(Boolean.FALSE);
            newItem.setIsDeleted(Boolean.FALSE);

            return true;

        } else {
            if (hasEventDifference(existingItem, newItem)) {

                newItem.setIsNew(Boolean.FALSE);
                newItem.setIsUpdated(Boolean.TRUE);
                newItem.setIsDeleted(Boolean.FALSE);
                newItem.setId(existingItem.getId());

                return true;
            }
        }

        return false;
    }

    private boolean checkForReminderChange(Map<Long, DbCalendarReminderEvent> map, DbCalendarReminderEvent newItem) {

        Long eventId = newItem.getEventId();

        if (eventId == null) {
            return false;
        }

        DbCalendarReminderEvent existingItem = map.remove(eventId);

        if (existingItem == null) {

            newItem.setIsNew(Boolean.TRUE);
            newItem.setIsUpdated(Boolean.FALSE);
            newItem.setIsDeleted(Boolean.FALSE);

            return true;

        } else {
            if (hasReminderDifference(existingItem, newItem)) {

                newItem.setIsNew(Boolean.FALSE);
                newItem.setIsUpdated(Boolean.TRUE);
                newItem.setIsDeleted(Boolean.FALSE);
                newItem.setId(existingItem.getId());

                return true;
            }
        }

        return false;
    }

    /**
     * Returns map of <reminder_id, reminder_object>
     *
     * @param eventId
     * @return
     */
    private Map<Long, DbCalendarReminderEvent> getExistingReminders(long eventId) {

        List<DbCalendarReminderEvent> list = daoProvider
                .getCalendarReminderEventDao()
                .getAllByEventId(eventId);

        Map<Long, DbCalendarReminderEvent> map = new HashMap<>();

        for (DbCalendarReminderEvent reminder : list) {
            map.put(reminder.getReminderId(), reminder);
        }

        return map;
    }

    private boolean hasEventDifference(DbCalendarEvent existingEvent, DbCalendarEvent newEvent) {

        if (checkForDifference(existingEvent.getAllDay(), newEvent.getAllDay()))
            return true;
        if (checkForDifference(existingEvent.getHasAlarm(), newEvent.getHasAlarm()))
            return true;
        if (checkForDifference(existingEvent.getOriginalAllDay(), newEvent.getOriginalAllDay()))
            return true;
        if (checkForDifference(existingEvent.getAvailability(), newEvent.getAvailability()))
            return true;
        if (checkForDifference(existingEvent.getCalendarId(), newEvent.getCalendarId()))
            return true;
        if (checkForDifference(existingEvent.getDescription(), newEvent.getDescription()))
            return true;
        if (checkForDifference(existingEvent.getDuration(), newEvent.getDuration()))
            return true;
        if (checkForDifference(existingEvent.getLastDate(), newEvent.getLastDate()))
            return true;
        if (checkForDifference(existingEvent.getLocation(), newEvent.getLocation()))
            return true;
        if (checkForDifference(existingEvent.getOriginalId(), newEvent.getOriginalId()))
            return true;
        if (checkForDifference(existingEvent.getOriginalInstanceTime(), newEvent.getOriginalInstanceTime()))
            return true;
        if (checkForDifference(existingEvent.getRecurrenceDate(), newEvent.getRecurrenceDate()))
            return true;
        if (checkForDifference(existingEvent.getRecurrenceExceptionDate(), newEvent.getRecurrenceExceptionDate()))
            return true;
        if (checkForDifference(existingEvent.getRecurrenceExceptionRule(), newEvent.getRecurrenceExceptionRule()))
            return true;
        if (checkForDifference(existingEvent.getRecurrenceRule(), newEvent.getRecurrenceRule()))
            return true;
        if (checkForDifference(existingEvent.getStatus(), newEvent.getStatus()))
            return true;
        if (checkForDifference(existingEvent.getTimestampStart(), newEvent.getTimestampStart()))
            return true;
        if (checkForDifference(existingEvent.getTimestampEnd(), newEvent.getTimestampEnd()))
            return true;
        if (checkForDifference(existingEvent.getTimezoneStart(), newEvent.getTimezoneStart()))
            return true;
        if (checkForDifference(existingEvent.getTimezoneEnd(), newEvent.getTimezoneEnd()))
            return true;
        if (checkForDifference(existingEvent.getTitle(), newEvent.getTitle()))
            return true;

        return false;
    }

    private boolean hasReminderDifference(DbCalendarReminderEvent existingReminder, DbCalendarReminderEvent newReminder) {

        if (checkForDifference(existingReminder.getMethod(), newReminder.getMethod()))
            return true;
        if (checkForDifference(existingReminder.getMinutes(), newReminder.getMinutes()))
            return true;

        return false;
    }

    @Override
    public void startSensor() {

        syncingTask = new AsyncTask<Void, Void, Void>() {

            @Nullable
            @Override
            protected Void doInBackground(Void... params) {

                syncData();
                context.getContentResolver().registerContentObserver(URI_CALENDAR, true, mObserver);
                context.getContentResolver().registerContentObserver(URI_REMINDER, true, mObserver);

                return null;
            }
        }.execute();

        setRunning(true);
    }

    @Override
    public void stopSensor() {

        syncingTask = null;
        setRunning(false);
    }

    @Override
    public void dumpData() {

    }
}
