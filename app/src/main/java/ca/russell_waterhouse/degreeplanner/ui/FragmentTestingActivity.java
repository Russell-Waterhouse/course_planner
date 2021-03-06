package ca.russell_waterhouse.degreeplanner.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import ca.russell_waterhouse.degreeplanner.R;
import ca.russell_waterhouse.degreeplanner.ui.about_app.fragments.OpenSourceContributionsFragment;

public class FragmentTestingActivity extends AppCompatActivity
    implements OpenSourceContributionsFragment.OnOpenSourceFragmentInteractionListener {

    boolean androidSdkButtonPressed = false;
    boolean roomLibraryButtonPressed = false;
    boolean liveDataButtonPressed = false;
    boolean espressoButtonPressed = false;
    boolean materialButtonPressed = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_testing);
    }

    public void setFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().add(R.id.test_frame, fragment).commit();
    }

    @Override
    public void androidSdkButtonPressed() {
        androidSdkButtonPressed = true;
    }

    @Override
    public void roomLibraryButtonPressed() {
        roomLibraryButtonPressed = true;
    }

    @Override
    public void liveDataLibraryButtonPressed() {
        liveDataButtonPressed = true;
    }

    @Override
    public void espressoLibraryButtonPressed() {
        espressoButtonPressed = true;
    }

    @Override
    public void materialLibraryButtonPressed() {
        materialButtonPressed = true;
    }

    public boolean isAndroidSdkButtonPressed() {
        return androidSdkButtonPressed;
    }

    public boolean isRoomLibraryButtonPressed() {
        return roomLibraryButtonPressed;
    }

    public boolean isLiveDataButtonPressed() {
        return liveDataButtonPressed;
    }

    public boolean isEspressoButtonPressed() {
        return espressoButtonPressed;
    }

    public boolean isMaterialButtonPressed() {
        return materialButtonPressed;
    }
}
