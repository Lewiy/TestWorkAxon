package com.romanenko.lew.testworkaxon.screens;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.romanenko.lew.testworkaxon.R;
import com.romanenko.lew.testworkaxon.base.RandomListUserContract;
import com.romanenko.lew.testworkaxon.model.requestPOJO.Result;
import com.romanenko.lew.testworkaxon.presenters.RandomListUserPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListUsersFragment extends BaseFragment implements RandomListUserContract.ViewListRandomUsers {
    @BindView(R.id.lv_users)
    RecyclerView recyclerUsers;

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    private ListAdapter mListAdapter;
    private LinearLayoutManager mLinearLayoutManager;
    private boolean loading = true;
    int mPastVisiblesItems, mVisibleItemCount, mTotalItemCount;
    private RandomListUserPresenter mRandomListUserPresenter;


    public static ListUsersFragment newInstance() {
        ListUsersFragment fragment = new ListUsersFragment();
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_users, container, false);
        ButterKnife.bind(this, view);


        mRandomListUserPresenter = new RandomListUserPresenter(getContext());
        mRandomListUserPresenter.attachView(this);
        mRandomListUserPresenter.viewIsReady();

        initializeUIElements();

        onClickRecycleItem();

        return view;
    }

    private void initializeUIElements() {
        mLinearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        mListAdapter = new ListAdapter(getContext(), onClickItemList());
        recyclerUsers.setLayoutManager(mLinearLayoutManager);
        recyclerUsers.setAdapter(mListAdapter);
        recyclerUsers.setItemAnimator(new DefaultItemAnimator());
        recyclerUsers.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
    }

    @Override
    public void loadListRandomUsers(List<Result> items) {
        progressBar.setVisibility(View.GONE);
        mListAdapter.setItems(items);
        loading = true;
    }


    private ListAdapter.RecyclerViewClickListener onClickItemList() {
        return new ListAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position, Result result) {
                openUserFragmentDetail(result);
            }
        };
    }

    private void onClickRecycleItem() {
        recyclerUsers.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    mVisibleItemCount = mLinearLayoutManager.getChildCount();
                    mTotalItemCount = mLinearLayoutManager.getItemCount();
                    mPastVisiblesItems = mLinearLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((mVisibleItemCount + mPastVisiblesItems) >= mTotalItemCount) {
                            progressBar.setVisibility(View.VISIBLE);
                            loading = false;
                            mRandomListUserPresenter.getRandomUsers(NUMBERLOADEDUSERS);
                        }
                    }
                }
            }
        });
    }

    private void openUserFragmentDetail(Result result) {
        UserProfileFragment userProfileFragment = UserProfileFragment.newInstance(result);
        ListUsersFragment listUsersFragment = ListUsersFragment.newInstance();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.content_frame_main_activity, userProfileFragment, userProfileFragment.getClass().toString())
                .addToBackStack(userProfileFragment.getClass().toString())
                .commit();
    }


}
