package com.elna.gallery.ui.activities;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;

import com.elna.gallery.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.action.ViewActions.click;

public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class, true, false);

    @Before
    public void setUp() throws Exception {
        rule.launchActivity(null);
    }

    @Test
    public void testLaunch() {
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView)).perform(RecyclerViewActions.scrollToPosition(12));
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView)).perform(RecyclerViewActions.scrollToPosition(30));
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView)).perform(RecyclerViewActions.scrollToPosition(02));
        Espresso.onView(ViewMatchers.withId(R.id.recyclerView))
                .perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));



    }
}

