package com.example.final_project;

import android.widget.DatePicker;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.longClick;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsAnything.anything;

public class InvalidTimeInPastTest{

    private Administrator admin;

    @Rule
    public ActivityTestRule<MainActivity> activityRuleMain
            = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public ActivityTestRule<Profile> activityRuleProfile;



    @Test
    public void bookInPast() {

        onView(withId(R.id.SignInButton)).perform(click());
        onView(ViewMatchers.withId(R.id.passwordField2))
                .perform(typeText("PatientTest123"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.usernameField2))
                .perform(typeText("PatientTest123"), closeSoftKeyboard());
        onView(withId(R.id.signInButton)).perform(click());
        while (true) {
            try {
                onView(withId(R.id.bookButton)).perform(click());
                break;
            } catch (Exception e) {
                onView(withId(R.id.signInButton)).perform(click());
            }
        }

        onView(ViewMatchers.withId(R.id.nameField2))
                .perform(typeText("Clinic Test"), closeSoftKeyboard());
        onView(ViewMatchers.withId(R.id.addressField))
                .perform(typeText("157 Laurier East"), closeSoftKeyboard());

        onView(withId(R.id.searchBtn)).perform(click());
        try {
            onData(anything()).inAdapterView(withId(R.id.clinicList)).atPosition(0).perform(click());
        } catch (Exception e) {
            System.out.println("No clinic available");
        }
        //try to book an appt in the past, make sure it doesnt crash
        onView(withId(R.id.dateBtn)).perform(click());
        onView(withId(R.id.datePicker)).perform(PickerActions.setDate(2019, 1, 1));
        onView(withId(R.id.saveDate)).perform(click());
        onView(withId(R.id.timeBtn)).perform(click());
        onView(withId(R.id.timePicker)).perform(PickerActions.setTime(9, 30));
        onView(withId(R.id.saveTime)).perform(click());
        //onView(withId(R.id.bookBtn)).perform(click());

    }
}