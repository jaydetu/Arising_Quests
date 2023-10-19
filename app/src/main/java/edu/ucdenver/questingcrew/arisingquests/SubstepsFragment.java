package edu.ucdenver.questingcrew.arisingquests;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import edu.ucdenver.questingcrew.arisingquests.databinding.ActivityMainBinding;

public class SubstepsFragment extends Fragment {
    public View onCreate(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_substeps, container, false);
    }
}
