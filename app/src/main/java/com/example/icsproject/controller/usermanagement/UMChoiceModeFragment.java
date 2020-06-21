package com.example.icsproject.controller.usermanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.icsproject.databinding.FragmentUmChoiceModeBinding;
import com.example.icsproject.user_management.UMFacebook;
import com.example.icsproject.user_management.UMGlobalManagement;
import com.example.icsproject.user_management.UMGoogle;
import com.example.icsproject.user_management.UMMailAndPassword;

import java.util.Arrays;

import static com.example.icsproject.user_management.UMGoogle.RC_SIGN_IN;


public class UMChoiceModeFragment extends Fragment {

    static final String TAG = UMChoiceModeFragment.class.getSimpleName();
    private FragmentUmChoiceModeBinding binding;

    static UMChoiceModeFragment newInstance() {
        return new UMChoiceModeFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentUmChoiceModeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("Lead Report");
        }
        initListener();
    }

    private void initListener() {
        binding.UMCMGoogle.setOnClickListener(v -> {
            Intent signInIntent = new UMGoogle(getContext()).getGoogleSignInClient().getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        });

        binding.UMCMMailPassword.setOnClickListener(v -> {
            new UMMailAndPassword("nathanb@ravtech.co.il", "nathan").signUp(getActivity());
        });

        binding.UMCMFacebook.setOnClickListener(v -> {
            UMFacebook.getInstance().getLoginManager(getActivity()).logIn(getActivity(), Arrays.asList("email", "public_profile"));
        });

        binding.UMCMAnonymous.setOnClickListener(v -> {
            UMGlobalManagement.signInAnonymously(getActivity());
        });

    }
}
