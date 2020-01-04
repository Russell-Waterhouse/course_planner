package com.example.degreeplanner.ui.welcome;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.degreeplanner.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewScheduleFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewScheduleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewScheduleFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private OnFragmentInteractionListener mListener;

    public NewScheduleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NewScheduleFragment.
     */
    static NewScheduleFragment newInstance() {
        NewScheduleFragment fragment = new NewScheduleFragment();
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
        View view = inflater.inflate(R.layout.fragment_new_schedule, container, false);
        initializeViews(view);
        // Inflate the layout for this fragment
        return view;
    }

    private void initializeViews(View parent){
        Spinner universities = parent.findViewById(R.id.university_spinner);
        universities.setOnItemSelectedListener(this);
        Spinner programs = parent.findViewById(R.id.program_spinner);
        programs.setOnItemSelectedListener(this);
//        TODO: finish with the program spinner
        Context context = getContext();
        if (context != null) {
            ArrayAdapter<CharSequence> universityAdapter = ArrayAdapter.createFromResource(context, R.array.universities, android.R.layout.simple_spinner_item);
            universityAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
            universities.setAdapter(universityAdapter);
        }
    }

    @Override
    public void onItemSelected(AdapterView parent, View view, int position, long id){
        Toast.makeText(getContext(), "Item selected", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView parent){
//        do Nothing
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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
    public interface OnFragmentInteractionListener {
        void createScheduleFromScratch();
        void createScheduleFromTemplate();
    }
}
