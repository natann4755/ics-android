package com.example.icsproject.controller.usermanagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.icsproject.R;
import com.example.icsproject.databinding.FragmentSignUpBinding;
import com.example.icsproject.databinding.FragmentUmChoiceModeBinding;


public class SignUpFragment extends Fragment  {
    private static final String TAG = SignUpFragment.class.getSimpleName();
    private FragmentSignUpBinding binding;
    private TextView signUp;
    private TextView skip;
    private TextView login;
    private Button facebook;
    private Button email;
    private Button gmail;


    public SignUpFragment() {

    }


    public static SignUpFragment newInstance() {

        SignUpFragment fragment = new SignUpFragment();
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

        binding = FragmentSignUpBinding.inflate(inflater, container, false);
        initViews();
        initListener();
        return binding.getRoot();
    }

    private void initViews() {
        signUp = binding.signUpSignUpTEXT;
        facebook = binding.signUpFacbookBUTTON;
        gmail = binding.signUpGmailBUTTON;
        email = binding.signUpEmailBUTTON;
        skip = binding.signUpSkipTEXT;
        login  = binding.signUpLogInTEXT;


    }

    private void initListener() {
        facebook.setOnClickListener(v -> { });
        gmail.setOnClickListener(v -> { });
        email.setOnClickListener(v -> { });
        skip.setOnClickListener(v -> { });
        login.setOnClickListener(v -> { });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;


    }
}