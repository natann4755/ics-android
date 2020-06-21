package com.example.icsproject.controller.main.ui.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.icsproject.R;
import com.example.icsproject.databinding.FragmentUmChoiceModeBinding;
import com.example.icsproject.databinding.FragmentVerificationCodeBinding;
import com.google.android.gms.auth.api.phone.SmsRetriever;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;



public class VerificationCodeFragment extends Fragment {
    private FragmentVerificationCodeBinding binding;
    private EditText mEditTextSmsCode;
    private Button mButtonContinue;


    public VerificationCodeFragment() {


    }


    public static VerificationCodeFragment newInstance() {
        VerificationCodeFragment fragment = new VerificationCodeFragment();
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
        binding = FragmentVerificationCodeBinding.inflate(inflater, container, false);
        initViews();
        initListeners();
        return binding.getRoot();
    }


    private void initViews() {
        mEditTextSmsCode = binding.verificationSmsCodeET;
        mButtonContinue = binding.verificationContinueBUTTON;
    }

    private void initListeners() {
        mEditTextSmsCode.setOnClickListener(v -> { });
        mButtonContinue.setOnClickListener(v -> { });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




    public class MySMSBroadcastReceiver extends BroadcastReceiver {


        @Override
        public void onReceive(Context context, Intent intent) {
            if (SmsRetriever.SMS_RETRIEVED_ACTION.equals(intent.getAction())) {
                Bundle extras = intent.getExtras();
                Status status = (Status) extras.get(SmsRetriever.EXTRA_STATUS);

                switch(status.getStatusCode()) {
                    case CommonStatusCodes.SUCCESS:
                        String message = (String) extras.get(SmsRetriever.EXTRA_SMS_MESSAGE);
                      // Insert into EditText
                        mEditTextSmsCode.setText(message);
                        break;
                    case CommonStatusCodes.TIMEOUT:
                       // Decide what to do
                        break;
                }
            }
        }



    }
}