package com.vishesh.placement.semesterGrades.views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;
import com.vishesh.placement.R;
import com.vishesh.placement.core.views.BaseFragment;
import com.vishesh.placement.semesterGrades.adapters.SemesterGradeItemAdapter;
import com.vishesh.placement.semesterGrades.models.SemesterGrade;
import com.vishesh.placement.semesterGrades.presenters.SemesterGradesPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class SemesterGradesFragment
        extends BaseFragment
        implements SemesterGradesPresenter.SemesterGradesView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recycler_view_semester_grades)
    RecyclerView recyclerViewSemesterGrades;

    @Inject
    SemesterGradeItemAdapter semesterGradeItemAdapter;
    @Inject
    SemesterGradesPresenter semesterGradesPresenter;

    public static Intent createIntent(Context context) {
        return new Intent(context, SemesterGradesActivity.class);
    }

    public SemesterGradesFragment() {
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        setupToolbar();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        semesterGradesPresenter.setView(this);
        if (savedInstanceState == null) {
            loadSemesterGrades();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        semesterGradesPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        semesterGradesPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recyclerViewSemesterGrades.setAdapter(null);
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        semesterGradesPresenter.destroy();
    }

    private void loadSemesterGrades() {
        semesterGradesPresenter.initialize();
    }

    private void setupToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        final ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();

        IconicsDrawable iconicsDrawable = new IconicsDrawable(getContext())
                .icon(GoogleMaterial.Icon.gmd_arrow_back)
                .sizeDp(16)
                .color(ContextCompat.getColor(getContext(), android.R.color.white));

        if (actionBar != null) {
            actionBar.setTitle(R.string.title_activity_semester_grades);
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(iconicsDrawable);
        }
    }

    @Override
    protected void injectDependencies() {
        getDependencyInjector().inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_semester_grades;
    }

    @Override
    public void showSemesterGrades(List<SemesterGrade> semesterGrades) {
        semesterGradeItemAdapter.setData(semesterGrades);
        recyclerViewSemesterGrades.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewSemesterGrades.setAdapter(semesterGradeItemAdapter);
    }
}
