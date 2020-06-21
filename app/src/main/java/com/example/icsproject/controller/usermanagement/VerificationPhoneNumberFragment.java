package com.example.icsproject.controller.usermanagement;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.icsproject.R;
import com.example.icsproject.databinding.FragmentVerificationCodeBinding;
import com.example.icsproject.databinding.FragmentVerificationPhoneNumberBinding;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.auth.api.phone.SmsRetrieverClient;
import com.google.android.gms.tasks.Task;

public class VerificationPhoneNumberFragment extends Fragment {

    static final String TAG =  VerificationPhoneNumberFragment.class.getSimpleName();
    private FragmentVerificationPhoneNumberBinding binding;
    private EditText mEditTextPhoneNumber;
    private Button mButtonSendCode;
    private Button mButtonSkip;



    public VerificationPhoneNumberFragment() {
        // Required empty public constructor
    }

    public static VerificationPhoneNumberFragment newInstance() {
        VerificationPhoneNumberFragment fragment = new VerificationPhoneNumberFragment();
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
        binding = FragmentVerificationPhoneNumberBinding.inflate(inflater, container, false);
        initViews();
        initListeners();
        return binding.getRoot();
    }



    private void initViews() {
        mEditTextPhoneNumber = binding.FVerificationPhoneNumberET;
        mButtonSendCode = binding.FVerificationPhoneNumberSendCodeBU;
        mButtonSkip = binding.FVerificationPhoneNumberSkipBU;

    }

    private void initListeners() {
        mEditTextPhoneNumber.setOnClickListener(v -> { });
        mButtonSendCode.setOnClickListener(v -> { });
        mButtonSkip.setOnClickListener(v -> { });
    }

    public void InsertSMSAutomatically (){
        SmsRetrieverClient client = SmsRetriever.getClient(getContext());
        Task<Void> task = client.startSmsRetriever();
    }
}