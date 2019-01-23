package com.romanenko.lew.testworkaxon.dI.models;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

import com.romanenko.lew.testworkaxon.base.RandomListUserContract;
import com.romanenko.lew.testworkaxon.base.RandomUserProfileContract;
import com.romanenko.lew.testworkaxon.dI.scopes.MainActivityScope;
import com.romanenko.lew.testworkaxon.screens.randomUserList.ListAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class UsersListModule {

    RandomListUserContract.ViewListRandomUsers view;
    RandomListUserContract.PresenterListRandomUsers presenter;
    ListAdapter.RecyclerViewClickListener listener;

    public UsersListModule(RandomListUserContract.ViewListRandomUsers view,
                           RandomListUserContract.PresenterListRandomUsers presenter,
                           ListAdapter.RecyclerViewClickListener listener) {
        this.view = view;
        this.presenter = presenter;
        this.listener = listener;
    }

    @MainActivityScope
    @Provides
    public RandomListUserContract.PresenterListRandomUsers providePresenter() {
        return presenter;
    }

    @MainActivityScope
    @Provides
    public RandomListUserContract.ViewListRandomUsers provideView() {
        return view;
    }

    @MainActivityScope
    @Provides
    ListAdapter provideListAdapter(Context context) {
        return new ListAdapter(context, listener);
    }

    @MainActivityScope
    @Provides
    LinearLayoutManager provideLinearLayoutManager(Context context) {
        return new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
    }
}
