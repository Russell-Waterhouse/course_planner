package ca.russell_waterhouse.degreeplanner.ui.about_app.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import ca.russell_waterhouse.degreeplanner.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnDevelopmentFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AboutDevelopmentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AboutDevelopmentFragment extends Fragment {

    private OnDevelopmentFragmentInteractionListener mListener;

    public AboutDevelopmentFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AboutDevelopmentFragment.
     */
    public static AboutDevelopmentFragment newInstance() {
        AboutDevelopmentFragment fragment = new AboutDevelopmentFragment();
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
        View view = inflater.inflate(R.layout.fragment_about_development, container, false);
        Button seeCode = view.findViewById(R.id.see_code_button);
        seeCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.seeSourceButtonPushed();
            }
        });
        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnDevelopmentFragmentInteractionListener) {
            mListener = (OnDevelopmentFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnDevelopmentFragmentInteractionListener");
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
    public interface OnDevelopmentFragmentInteractionListener {
        void seeSourceButtonPushed();
    }
}
