package com.example.icsproject.controller.main.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.icsproject.R;
import com.example.icsproject.databinding.FragmentVerificationCodeBinding;
import com.example.icsproject.databinding.FragmentVerificationSuccessfulBinding;


public class VerificationSuccessfulFragment extends Fragment {
    private FragmentVerificationSuccessfulBinding binding;
    private Button mButtonContinue;


    public VerificationSuccessfulFragment() {

    }



    public static VerificationSuccessfulFragment newInstance() {
        VerificationSuccessfulFragment fragment = new VerificationSuccessfulFragment();
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

        binding = FragmentVerificationSuccessfulBinding.inflate(inflater, container, false);
        initViews();
        initListener();
        return binding.getRoot();
    }

    private void initViews() {
        mButtonContinue = binding.verificationContinueBUTTON;
    }

    private void initListener() {
        mButtonContinue.setOnClickListener(v -> { });
    }
}