package com.example.icsproject.controller.usermanagement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.icsproject.databinding.FragmentSignUpViaEmailBinding;



public class SignUpViaEmailFragment extends Fragment {

    static final String TAG = SignUpViaEmailFragment.class.getSimpleName();
    private FragmentSignUpViaEmailBinding binding;
    private TextView signUpViaEmail;
    private EditText email;
    private EditText password;
    private TextView skip;
    private TextView login;
    private Button signUp;
    private Button facebook;
    private Button gmail;




    public SignUpViaEmailFragment() {
        // Required empty public constructor
    }

    public static SignUpViaEmailFragment newInstance() {
        SignUpViaEmailFragment fragment = new SignUpViaEmailFragment();
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
        binding = FragmentSignUpViaEmailBinding.inflate(inflater, container, false);
        initViews();
        initListener();
        return binding.getRoot();
    }
    private void initViews() {
        signUpViaEmail = binding.FSUVETitelTV;
        facebook = binding.FSUVEFacebookBU;
        signUp = binding.FSUVESingUpBU;
        gmail = binding.FSUVEGmailBU;
        email = binding.FSUVEEmailET;
        password = binding.FSUVEPasswordET;
        skip = binding.FSUVESkipTV;
        login  = binding.FSUVELogInTV;


    }

    private void initListener() {
        email.setOnClickListener(v -> { });
        password.setOnClickListener(v -> { });
        login.setOnClickListener(v -> { });
        signUp.setOnClickListener(v -> { });
        facebook.setOnClickListener(v -> { });
        gmail.setOnClickListener(v -> { });
        skip.setOnClickListener(v -> { });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;


    }
}