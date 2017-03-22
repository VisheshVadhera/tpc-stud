package com.vishesh.tpc_stud.gpa;

import android.os.Bundle;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.core.views.BaseActivity;

public class GpaActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpa);

        if (savedInstanceState == null) {
            addFragment(R.id.fragment_container, new GpaFragment());
        }
    }
}
