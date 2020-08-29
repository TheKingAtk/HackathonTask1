package com.theking.movieui;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectionUI.detailsIn{
    FrameLayout container;
    ActionBar actionBar;
    ArrayList<movie> movies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar=getSupportActionBar();
        movies=new ArrayList<>();
        movie temp=new movie();
        ArrayList<Integer> images=new ArrayList<>();
        images.add(R.drawable.br1);
        images.add(R.drawable.br2);
        images.add(R.drawable.br3);
        images.add(R.drawable.br49);
        temp.setImages(images);
        temp.setDescription("Young Blade Runner K's discovery of a long-buried secret leads him to " +
                "track down former Blade Runner Rick Deckard, who's been missing for thirty years.");
        temp.setGenre("Action, Drama, Mystery");
        temp.setImgResource(R.drawable.br49);
        temp.setName("Blade Runner 2049");
        temp.setRating((float)8.0);
        movies.add(temp);
        temp=new movie();
        images=new ArrayList<>();
        images.add(R.drawable.pp);
        images.add(R.drawable.pp1);
        images.add(R.drawable.pp2);
        images.add(R.drawable.pp3);
        temp.setImages(images);
        temp.setDescription("When a pill that gives its users unpredictable superpowers for five minutes hits the streets of New Orleans, a teenage " +
                "dealer and a local cop must team with an ex-soldier to take down the group responsible for its creation.");
        temp.setGenre("Action, Crime, Sci-Fi");
        temp.setImgResource(R.drawable.pp);
        temp.setName("Project Power");
        temp.setRating((float)6.0);
        movies.add(temp);
       // movies.add(temp);
        SelectionUI selectionUI=new SelectionUI(getApplicationContext(),movies,this);
        container=findViewById(R.id.cont);
        container.addView(selectionUI);
    }

    @Override
    public void onBackPressed() {
        container.removeAllViews();
        container.addView(new SelectionUI(getApplicationContext(),movies,this));
    }

    @Override
    public void movie(int movie) {
       container.removeAllViews();
       container.addView(new Details(getApplicationContext(),movies.get(movie)));
    }
}
