package com.mytaxi.android_demo;

import android.app.Activity;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.util.HumanReadables;
import android.support.test.espresso.util.TreeIterables;
import android.support.test.rule.ActivityTestRule;
import android.support.test.rule.GrantPermissionRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;
import android.support.test.runner.lifecycle.Stage;
import android.util.Log;
import android.view.View;

import com.mytaxi.android_demo.activities.AuthenticatedActivity;
import com.mytaxi.android_demo.activities.AuthenticationActivity;
import com.mytaxi.android_demo.activities.DriverProfileActivity;
import com.mytaxi.android_demo.activities.MainActivity;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.TimeoutException;

import androidx.test.espresso.intent.Intents;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

//import static android.support.test.InstrumentationRegistry.getInstrumentation;
//import static android.support.test.InstrumentationRegistry.getInstrumentation;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class AuthenticationTest {

    @Rule
    public GrantPermissionRule mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.ACCESS_FINE_LOCATION");

    /*@Rule
    public ActivityTestRule<AuthenticatedActivity>
            AuthenticatedActivity = new ActivityTestRule<>(AuthenticatedActivity.class, false, true);*/
    /*@Rule
    public ActivityTestRule<AuthenticationActivity>
            AuthenticationActivity = new ActivityTestRule<>(AuthenticationActivity.class, false, true);*/
    /*@Rule
    public ActivityTestRule<DriverProfileActivity>
            DriverProfileActivity = new ActivityTestRule<>(DriverProfileActivity.class, false, true);*/
    @Rule
    public ActivityTestRule<MainActivity>
            MainActivity = new ActivityTestRule<>(MainActivity.class, false, true);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.mytaxi.android_demo", appContext.getPackageName());
    }

    @Test
    public void Login() {
        Espresso.onView(withId(R.id.edt_username)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.edt_password)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.btn_login)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.edt_username)).perform(typeText("crazydog335"));
        Espresso.onView(withId(R.id.edt_password)).perform(typeText("venture"));
        Espresso.onView(withId(R.id.btn_login)).perform(click());
    }

    @Test
    public void FindDriver() {
        MainActivity.launchActivity(null);
        Espresso.onView(withId(R.id.textSearch)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.textSearch)).perform(typeText("sa"));
        onData(is("Sarah Scott")).perform(click());
    }

    @Test
    public void CallDriver() {
        //DriverProfileActivity.launchActivity(null);
        Espresso.onView(withId(R.id.fab)).check(matches(isDisplayed())).perform(click());
    }

    @Test
    public void Complete() {
        Espresso.onView(withId(R.id.edt_username)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.edt_password)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.btn_login)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.edt_username)).perform(typeText("crazydog335"));
        Espresso.onView(withId(R.id.edt_password)).perform(typeText("venture"));
        Espresso.onView(withId(R.id.btn_login)).perform(click());

        Espresso.onView(withId(R.id.textSearch)).check(matches(isDisplayed()));
        Espresso.onView(withId(R.id.textSearch)).perform(typeText("sa"));
        onData(is("Sarah Scott")).perform(click());

        Espresso.onView(withId(R.id.fab)).check(matches(isDisplayed())).perform(click());
    }
}

