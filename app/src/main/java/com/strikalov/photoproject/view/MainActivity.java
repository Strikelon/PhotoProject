package com.strikalov.photoproject.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.strikalov.photoproject.R;
import com.strikalov.photoproject.adapters.ImageRecyclerAdapter;
import com.strikalov.photoproject.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity {

    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenter();
        initRecycler();
    }

    private void initRecycler(){
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        ImageRecyclerAdapter adapter = new ImageRecyclerAdapter(mainPresenter.getRecyclerMainPresenter());
        recyclerView.setAdapter(adapter);
    }
}
