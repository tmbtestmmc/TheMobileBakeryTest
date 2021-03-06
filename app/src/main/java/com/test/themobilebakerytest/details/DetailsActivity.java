package com.test.themobilebakerytest.details;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.test.themobilebakerytest.R;
import com.test.themobilebakerytest.user.User;

public class DetailsActivity extends AppCompatActivity implements DetailsView, OnMapReadyCallback {

    DetailsPresenter presenter;
    User user;
    NestedScrollView nestedScrollView;
    ProgressBar progressBar;
    GoogleMap gMap;
    Double lat, lng;
    boolean mapReady;
    ImageView ivUserImageLarge;
    SupportMapFragment mapFragment;
    CollapsingToolbarLayout collapsingToolbarLayout;
    TextView tvDetails;
    Toolbar toolbar;
    FrameLayout flMap;
    TextView tvNotFound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ivUserImageLarge = (ImageView) findViewById(R.id.ivUserImageLarge);
        tvDetails = (TextView) findViewById(R.id.tvDescription);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        flMap = (FrameLayout) findViewById(R.id.flMap);
        tvNotFound = (TextView) findViewById(R.id.tvNotFound);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle(null);
        }

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        presenter = new DetailsPresenterImpl(this, new LoadDetailsInteractorImpl());

        String jsonUser = getIntent().getStringExtra("user");
        presenter.onCreate(jsonUser);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.gMap = googleMap;
        mapReady = true;
        setCoordinatesToMap();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        nestedScrollView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        nestedScrollView.setVisibility(View.VISIBLE);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.onDestroy();
        }
    }

    @Override
    public void onUserLoaded(User user) {
        this.user = user;
        if (user != null) {
            setUserDetailsToView();
            progressBar.setVisibility(View.GONE);
        } else {
            Toast.makeText(this, R.string.Error_loading_user, Toast.LENGTH_LONG).show();
            finish();
        }

    }

    @Override
    public void onCoordinatesLoaded(User user) {
        this.user = user;
        if (user != null) {
            if (user.getLocation().getLatitude() != null) {
                this.lat = user.getLocation().getLatitude();
                this.lng = user.getLocation().getLongitude();
            } else {
                flMap.setVisibility(View.GONE);
                tvNotFound.setVisibility(View.VISIBLE);
            }
            setCoordinatesToMap();
        }
    }

    private void setUserDetailsToView() {
        collapsingToolbarLayout.setTitle(user.getName().toString());
        tvDetails.setText(user.toString(this));
        Glide
                .with(this)
                .load(user.getPicture().getLarge())
                .error(R.drawable.icon_photo_not_loaded_256)
                .crossFade()
                .into(ivUserImageLarge);

    }

    private void setCoordinatesToMap() {
        boolean mapSetUp = gMap != null && mapReady && lat != null && lng != null && flMap.getVisibility() == View.VISIBLE;
        if (mapSetUp) {
            LatLng location = new LatLng(lat, lng);
            gMap.addMarker(new MarkerOptions().position(location));
            gMap.moveCamera(CameraUpdateFactory.newLatLng(location));
            gMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 15.0f));
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
