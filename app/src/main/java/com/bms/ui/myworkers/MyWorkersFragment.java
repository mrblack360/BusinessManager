package com.bms.ui.myworkers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bms.R;

public class MyWorkersFragment extends Fragment {

    public View onCreateView (@NonNull LayoutInflater inflater,
                              ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate (R.layout.fragment_myworkers, container, false);
        return root;
    }
}