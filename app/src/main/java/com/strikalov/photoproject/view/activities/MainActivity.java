package com.strikalov.photoproject.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.strikalov.photoproject.R;
import com.strikalov.photoproject.view.adapters.ImageRecyclerAdapter;
import com.strikalov.photoproject.presenter.MainPresenter;
import com.strikalov.photoproject.view.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends MvpAppCompatActivity implements MainView {

    @InjectPresenter
    public MainPresenter mainPresenter;

    private ImageRecyclerAdapter adapter;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initRecycler();
    }

    private void initRecycler(){
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        adapter = new ImageRecyclerAdapter(mainPresenter.getRecyclerMainPresenter());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateRecyclerView() {
        if(adapter != null){
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void startDetailActivity(String photoUrl) {
        Intent intent = DetailActivity.newIntent(this, photoUrl);
        startActivity(intent);
    }
}
