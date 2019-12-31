package com.example.degreeplanner;

import androidx.test.filters.MediumTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;

import com.example.degreeplanner.ui.main.MainActivity;
import com.example.degreeplanner.ui.main.MainViewModel;

import org.junit.Test;
import org.mockito.*;

import org.junit.Rule;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static junit.framework.TestCase.assertTrue;

@RunWith(AndroidJUnit4ClassRunner.class)
@MediumTest
public class MainActivityInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    MainViewModel mainViewModel;

    @InjectMocks MainActivity mainActivity;

    @Test
    public void testCompile(){
        assertTrue(true);
    }
}
