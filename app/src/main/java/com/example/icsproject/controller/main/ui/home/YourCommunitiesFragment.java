package com.example.icsproject.controller.main.ui.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.icsproject.R;



public class YourCommunitiesFragment extends Fragment {



    public YourCommunitiesFragment() {

    }


    public static YourCommunitiesFragment newInstance() {
        YourCommunitiesFragment fragment = new YourCommunitiesFragment();
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

        return inflater.inflate(R.layout.fragment_your_communities, container, false);
    }
}