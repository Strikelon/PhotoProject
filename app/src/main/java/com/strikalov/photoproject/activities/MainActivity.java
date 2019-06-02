package com.strikalov.photoproject.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.strikalov.photoproject.R;
import com.strikalov.photoproject.adapters.ImageRecyclerAdapter;
import com.strikalov.photoproject.presenter.MainPresenter;
import com.strikalov.photoproject.view.MainView;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter
    public MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecycler();
    }

    private void initRecycler(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        ImageRecyclerAdapter adapter = new ImageRecyclerAdapter(mainPresenter.getRecyclerMainPresenter());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void startDetailActivity(int position) {
        Intent intent = DetailActivity.newIntent(this, position);
        startActivity(intent);
    }
}
