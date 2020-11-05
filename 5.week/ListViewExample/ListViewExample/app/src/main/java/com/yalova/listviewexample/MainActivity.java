package com.yalova.listviewexample;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ImageView myImageView;
    ObjectAnimator myAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] myList = new String[] {"İstanbul", "Ankara", "İzmir", "İstanbul", "Ankara",
                "İzmir", "İstanbul", "Ankara", "İzmir", "İstanbul",
                "Ankara", "İzmir", "İstanbul", "Ankara", "İzmir"};

        ListView myListView = findViewById(R.id.myListView);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, myList);
        myListView.setAdapter(myAdapter);

        myImageView = findViewById(R.id.myImageView);
        myAnimation = ObjectAnimator.ofFloat(myImageView, "translationX", 200f);
        myAnimation.setDuration(2000);

        myImageView.animate().alpha(0).setDuration(3000).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                myAnimation.start();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                myImageView.animate().alpha(1).setDuration(3000);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


    }
}