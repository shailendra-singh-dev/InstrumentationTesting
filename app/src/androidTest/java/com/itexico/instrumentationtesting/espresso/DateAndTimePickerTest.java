package com.itexico.instrumentationtesting.espresso;

import android.support.test.espresso.contrib.PickerActions;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.itexico.instrumentationtesting.R;
import com.itexico.instrumentationtesting.espresso.activities.DateAndTimePicker;

import org.junit.Rule;
import org.junit.Test;

import java.util.Calendar;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.equalTo;

/**
 * Created by iTexico Developer on 8/26/2016.
 */
public class DateAndTimePickerTest {

    private static final String TAG = DateAndTimePickerTest.class.getSimpleName();
    @Rule
    public ActivityTestRule<DateAndTimePicker> mActivityRule = new ActivityTestRule<>(
            DateAndTimePicker.class);

    @Test
    public void openDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int monthOfYear = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        onView(withId(R.id.open_date_picker_dialog)).perform(click());
        onView(withClassName(equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(year, monthOfYear + 1, dayOfMonth));

        onView(withId(android.R.id.button1)).perform(click());
        Log.i(TAG, "year:" + year + ",monthOfYear + 1:" + (monthOfYear + 1) + ",dayOfMonth:" + (dayOfMonth));
        onView(withId(R.id.date_time_picker_update_status)).check(matches(withText(year + "/" + monthOfYear + "/" + dayOfMonth)));
    }

    @Test
    public void openTimePicker() {
        Calendar calendar = Calendar.getInstance();
        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        onView(withId(R.id.open_time_picker_dialog)).perform(click());
        onView(withClassName(equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(hourOfDay, minute));

        onView(withId(android.R.id.button1)).perform(click());
        Log.i(TAG, "hourOfDay:" + hourOfDay + ",minute:" + minute);
        onView(withId(R.id.date_time_picker_update_status)).check(matches(withText(hourOfDay + ":" + minute)));
    }

}
