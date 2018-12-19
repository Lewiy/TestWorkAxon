package com.romanenko.lew.testworkaxon.screens;

import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.romanenko.lew.testworkaxon.R;
import com.romanenko.lew.testworkaxon.base.IView;

public class BaseFragment extends Fragment implements IView {
    @Override
    public void showError(String error) {
        if(error != null)
        Toast.makeText(getContext(),
               error, Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(getContext(),
                    R.string.common_error, Toast.LENGTH_SHORT).show();
    }
}
