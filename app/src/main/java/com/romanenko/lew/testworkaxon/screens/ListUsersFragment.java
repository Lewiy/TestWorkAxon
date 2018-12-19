package com.romanenko.lew.testworkaxon.screens;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.romanenko.lew.testworkaxon.R;
import com.romanenko.lew.testworkaxon.base.RandomListUserContract;
import com.romanenko.lew.testworkaxon.model.requestPOJO.Result;
import com.romanenko.lew.testworkaxon.presenters.RandomListUserPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListUsersFragment extends Fragment implements RandomListUserContract.ViewListRandomUsers {
    @BindView(R.id.lv_users)
    RecyclerView recyclerUsers;
    private ListAdapter listAdapter;
    private LinearLayoutManager linearLayoutManager;
    private boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
private RandomListUserPresenter randomListUserPresenter;
    public static ListUsersFragment newInstance() {
        ListUsersFragment fragment = new ListUsersFragment();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_users, container, false);
        ButterKnife.bind(this, view);



        randomListUserPresenter = new RandomListUserPresenter(getContext());
        randomListUserPresenter.attachView(this);
        randomListUserPresenter.viewIsReady();

        linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        listAdapter = new ListAdapter(getContext(), onClickItemList());
        recyclerUsers.setLayoutManager(linearLayoutManager);
        recyclerUsers.setAdapter(listAdapter);
        recyclerUsers.setItemAnimator(new DefaultItemAnimator());
        recyclerUsers.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));

        recyclerUsers.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                if(dy > 0) //check for scroll down
                {
                    visibleItemCount = linearLayoutManager.getChildCount();
                    totalItemCount = linearLayoutManager.getItemCount();
                    pastVisiblesItems = linearLayoutManager.findFirstVisibleItemPosition();

                    if (loading)
                    {
                        if ( (visibleItemCount + pastVisiblesItems) >= totalItemCount)
                        {
                            loading = false;
                            randomListUserPresenter.getRandomUserApi();

                        }
                    }
                }
            }
        });

        return view;
    }

    @Override
    public void loadListRandomUsers(List<Result> items) {
        listAdapter.setItems(items);
        loading = true;
    }

    @Override
    public void showError() {

    }

    private ListAdapter.RecyclerViewClickListener onClickItemList() {
        return new ListAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position, Result result) {
               // openUserFragmentDetail(Integer.parseInt(result.getId().getValue()));
                openUserFragmentDetail(position);
            }
        };
    }

    private void openUserFragmentDetail(int userId) {
         UserProfileFragment userProfileFragment = UserProfileFragment.newInstance(userId);
        ListUsersFragment listUsersFragment = ListUsersFragment.newInstance();
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.content_frame_main_activity, userProfileFragment,userProfileFragment.getClass().toString())
                .addToBackStack(userProfileFragment.getClass().toString())
                .commit();
    }



}
