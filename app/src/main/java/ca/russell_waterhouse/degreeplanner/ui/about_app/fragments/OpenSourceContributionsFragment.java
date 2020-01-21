package ca.russell_waterhouse.degreeplanner.ui.about_app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.degreeplanner.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnOpenSourceFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OpenSourceContributionsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OpenSourceContributionsFragment extends Fragment {

    private OnOpenSourceFragmentInteractionListener mListener;

    public OpenSourceContributionsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment OpenSourceContributionsFragment.
     */
    public static OpenSourceContributionsFragment newInstance() {
        OpenSourceContributionsFragment fragment = new OpenSourceContributionsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_open_source_contributions, container, false);
        Button androidSdk = view.findViewById(R.id.android_sdk);
        androidSdk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null){
                    mListener.androidSdkButtonPressed();
                }
            }
        });
        Button lifeCycleButton = view.findViewById(R.id.live_data);
        lifeCycleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null){
                    mListener.liveDataLibraryButtonPressed();
                }
            }
        });
        Button roomButton = view.findViewById(R.id.room_persistence);
        roomButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.roomLibraryButtonPressed();
                }
            }
        });
        Button espressoButton = view.findViewById(R.id.espresso_library);
        espressoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.espressoLibraryButtonPressed();
                }
            }
        });
        Button materialButton = view.findViewById(R.id.material_library);
        materialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null){
                    mListener.materialLibraryButtonPressed();
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnOpenSourceFragmentInteractionListener) {
            mListener = (OnOpenSourceFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnOpenSourceFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnOpenSourceFragmentInteractionListener {
        void androidSdkButtonPressed();
        void roomLibraryButtonPressed();
        void liveDataLibraryButtonPressed();
        void espressoLibraryButtonPressed();
        void materialLibraryButtonPressed();
    }
}
