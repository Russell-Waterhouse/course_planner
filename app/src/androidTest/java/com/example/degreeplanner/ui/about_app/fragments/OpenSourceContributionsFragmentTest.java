package com.example.degreeplanner.ui.about_app.fragments;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import com.example.degreeplanner.R;
import com.example.degreeplanner.ui.FragmentTestingActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;


@RunWith(AndroidJUnit4ClassRunner.class)
public class OpenSourceContributionsFragmentTest {


    @Rule
    public ActivityTestRule<FragmentTestingActivity> testRule = new ActivityTestRule<>(FragmentTestingActivity.class);

    @Before
    public void setup(){
        testRule.getActivity().setFragment(OpenSourceContributionsFragment.newInstance());
    }

    @Test
    public void assertViewVisible(){
        try {
            onView(withId(R.id.open_source_thanks)).check(matches(withText(R.string.thank_open_source)));
            onView(withId(R.id.prompt_see_libraries)).check(matches(withText(R.string.click_links_prompt)));
            onView(withId(R.id.android_sdk)).check(matches(withText(R.string.android_sdk)));
            onView(withId(R.id.room_persistence)).check(matches(withText(R.string.room_library)));
            onView(withId(R.id.live_data)).check(matches(withText(R.string.lifecycle_library)));
        }
        catch (NoMatchingViewException e){
            fail("One or more of the specified views were not visible to the user");
        }
        assertTrue(true);
    }

    @Test
    public void pushAndroidSDKButton(){
        assertFalse(testRule.getActivity().isAndroidSdkButtonPressed());
        onView(withId(R.id.android_sdk)).perform(click());
        assertTrue(testRule.getActivity().isAndroidSdkButtonPressed());
    }

    @Test
    public void pushRoomButton(){
        assertFalse(testRule.getActivity().isRoomLibraryButtonPressed());
        onView(withId(R.id.room_persistence)).perform(click());
        assertTrue(testRule.getActivity().isRoomLibraryButtonPressed());
    }

    @Test
    public void pushLifecycleButton(){
        assertFalse(testRule.getActivity().isLiveDataButtonPressed());
        onView(withId(R.id.live_data)).perform(click());
        assertTrue(testRule.getActivity().isLiveDataButtonPressed());
    }

}