package com.example.icsproject.controller.main.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.icsproject.R;
import com.example.icsproject.controller.usermanagement.SignUpFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChooseCommunityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChooseCommunityFragment extends Fragment {
    private static final String TAG = ChooseCommunityFragment.class.getSimpleName();





    public ChooseCommunityFragment() {
        // Required empty public constructor
    }



    public static ChooseCommunityFragment newInstance() {
        ChooseCommunityFragment fragment = new ChooseCommunityFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_choose_community, container, false);
    }
}