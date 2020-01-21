package ca.russell_waterhouse.degreeplanner.database;

import android.content.Context;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.room.Room;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DatabaseTest {

    private Database database;
    private CourseDAO dao;

    @Rule
    public InstantTaskExecutorRule rule = new InstantTaskExecutorRule();

    @Before
    public void setup(){
        Context context = getInstrumentation().getTargetContext().getApplicationContext();
        database = Room.inMemoryDatabaseBuilder(context, Database.class).build();
        dao = database.courseDAO();
    }

    @After
    public void tearDown() {
        database.clearAllTables();
        database.close();
    }
    @Test
    public void courseDAO() {
        assertNotNull(dao);
    }

    @Test
    public void getDataBase() {
        assertNotNull(database);
    }

    @Test
    public void insertionRetrievalTest() throws InterruptedException {
        CourseEntity testData = new CourseEntity("name",
                "pr1",
                "pr2",
                null,
                false,
                true,
                false,
                false,
                0.00,
                null,
                3,
                'a');
        dao.insert(testData);
        final LiveData<List<CourseEntity>> liveList = dao.getAllCourses();
        final CountDownLatch synchronizingLatch = new CountDownLatch(1);
        final CourseEntity[] retrievedData = {null};
        liveList.observeForever(new Observer<List<CourseEntity>>() {
            @Override
            public void onChanged(List<CourseEntity> courseEntities) {
                retrievedData[0] = courseEntities.get(0);
                synchronizingLatch.countDown();
                liveList.removeObserver(this);
            }
        });
        synchronizingLatch.await(1, TimeUnit.SECONDS);
        assertEquals(testData, retrievedData[0]);
    }
}