package com.romanenko.lew.testworkaxon.screens;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.romanenko.lew.testworkaxon.R;
import com.romanenko.lew.testworkaxon.base.RandomUserProfileContract;
import com.romanenko.lew.testworkaxon.dI.components.DaggerActivityComponentUserProfile;
import com.romanenko.lew.testworkaxon.dI.models.UserProfileModule;
import com.romanenko.lew.testworkaxon.model.requestPOJO.Result;
import com.romanenko.lew.testworkaxon.presenters.UserDetailsPresenter;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfileFragment extends BaseFragment implements RandomUserProfileContract.ViewListRandomUsers {

    @BindView(R.id.profile_image)
    CircleImageView circleImageViewProfile;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.surname)
    TextView surname;
    @BindView(R.id.gender)
    TextView gender;
    @BindView(R.id.date_of_birth)
    TextView dateOfBirth;
    @BindView(R.id.cell_phone)
    TextView cellPhone;
    @BindView(R.id.email)
    TextView email;

    private final int CALL_REQUEST = 100;

    private static final String USER_ID = "user_id";

    @Inject
    public RandomUserProfileContract.PresenterListRandomUsers userProflePresenter;

    public static UserProfileFragment newInstance(Result result) {
        Bundle args = new Bundle();
        args.putString(USER_ID, result.getEmail().toString());
        UserProfileFragment fragment = new UserProfileFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        ButterKnife.bind(this, view);
        Bundle bundle = getArguments();

       // UserDetailsPresenter userDetailsPresenter = new UserDetailsPresenter(getContext());


       DaggerActivityComponentUserProfile.builder()
                .userProfileModule(new UserProfileModule(this,new UserDetailsPresenter(getContext())))
                .build().inject(this);


        userProflePresenter.loadRandomUserProfile(bundle.getString(USER_ID));
        userProflePresenter.attachView(this);
        userProflePresenter.viewIsReady();

        return view;
    }


    @Override
    public void loadRandomUserProfile(Result item) {

    }

    @Override
    public void loadImageProfile(String url) {
        Picasso.get().load(url).into(circleImageViewProfile);
    }

    @Override
    public void loadName(String name) {
        this.name.setText(name);
    }

    @Override
    public void loadSurname(String surName) {
        this.surname.setText(surName);
    }

    @Override
    public void loadGender(String gender) {
        this.gender.setText(gender);
    }

    @Override
    public void loadDateOfBirth(String dayOfBirth) {
        this.dateOfBirth.setText(dayOfBirth);
    }

    @Override
    public void loadCallPhone(String cellPhone) {
        this.cellPhone.setText(cellPhone);
    }

    @Override
    public void loadEmail(String email) {
        this.email.setText(email);
    }

    @OnClick(R.id.make_call_btn)
    public void OnClickCallBtn() {
        callPhoneNumber();
    }

    private void callPhoneNumber() {
        try
        {
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            {
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, CALL_REQUEST);

                    return;
                }
            }

            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + cellPhone.getText().toString()));
            startActivity(callIntent);
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults)
    {
        if(requestCode == CALL_REQUEST)
        {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                callPhoneNumber();
            }
            else
            {
                Toast.makeText(getContext(), getResources().getString(R.string.call_permission_denied_message), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
