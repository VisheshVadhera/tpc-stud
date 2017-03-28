package com.vishesh.tpc_stud.dashboard.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.vishesh.tpc_stud.R;
import com.vishesh.tpc_stud.core.views.BaseFragment;
import com.vishesh.tpc_stud.dashboard.adapters.RecruiterItemAdapter;
import com.vishesh.tpc_stud.dashboard.models.RecruiterModel;
import com.vishesh.tpc_stud.dashboard.presenters.RecruitersPresenter;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;


public class RecruitersFragment
        extends BaseFragment
        implements RecruitersPresenter.RecruitersView {

    @BindView(R.id.recycler_view_recruiters)
    RecyclerView recyclerViewRecruiters;

    @Inject
    RecruiterItemAdapter recruiterItemAdapter;
    @Inject
    RecruitersPresenter recruitersPresenter;

    public static RecruitersFragment newInstance() {
        return new RecruitersFragment();
    }

    public RecruitersFragment() {
        setRetainInstance(true);
    }

    @Override
    protected void injectDependencies() {
        getDependencyInjector()
                .inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recruitersPresenter.setView(this);
        recruitersPresenter.initialize();
    }

    @Override
    public void onResume() {
        super.onResume();
        recruitersPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        recruitersPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        recyclerViewRecruiters.setAdapter(null);
        recruitersPresenter.unsetView();
        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        recruitersPresenter.destroy();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recruiters;
    }

    @Override
    public void showJobOffers(List<RecruiterModel> recruiterModels) {
        recruiterItemAdapter.setData(recruiterModels);
        recyclerViewRecruiters.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewRecruiters.setAdapter(recruiterItemAdapter);
    }
}
