package com.example.degreeplanner;

import androidx.test.filters.MediumTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import com.example.degreeplanner.ui.main.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

@RunWith(AndroidJUnit4ClassRunner.class)
@MediumTest
public class MainActivityIntegratedTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void createNewCourseAndCheckItExists(){
        String courseName = "TEST100";
        String prerequisiteName = "TEST125";
        createCourse(courseName, prerequisiteName);
        checkCourseIsCorrect(courseName, prerequisiteName);
    }

    private void createCourse(String courseName, String prerequisiteName) {
        onView(withId(R.id.search_bar)).perform(closeSoftKeyboard());
        onView(withId(R.id.create_courses_button)).perform(click());
        onView(withId(R.id.course_id)).perform(replaceText(courseName));
        onView(withId(R.id.checkBoxFall)).perform(click());
        onView(withId(R.id.checkBoxSpring)).perform(click());
        onView(withId(R.id.checkBoxSummer)).perform(click());
        onView(withId(R.id.prereq_1)).perform(replaceText(prerequisiteName));
        onView(withId(R.id.done)).perform(click());
    }

    private void checkCourseIsCorrect(String courseName, String prerequisiteName){
        onView(allOf(withText(courseName), isDisplayed())).perform(click());
    }
}
