package com.handy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Dashboard extends Fragment implements View.OnClickListener {
    CardView cardViewsub1,cardViewsub2,cardViewsub3,cardViewsub4,cardViewsub5,cardViewsub6;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.activity_dashboard, container, false);
        cardViewsub1 = (CardView) myView.findViewById(R.id.cardsub1);
        cardViewsub2 = (CardView) myView.findViewById(R.id.cardsub2);
        cardViewsub3 = (CardView) myView.findViewById(R.id.cardsub3);
        cardViewsub4 = (CardView) myView.findViewById(R.id.cardsub4);
        cardViewsub5 = (CardView) myView.findViewById(R.id.cardsub5);
        cardViewsub6 = (CardView) myView.findViewById(R.id.cardsub6);
        cardViewsub1.setOnClickListener(this);
        cardViewsub2.setOnClickListener(this);
        cardViewsub3.setOnClickListener(this);
        cardViewsub4.setOnClickListener(this);
        cardViewsub5.setOnClickListener(this);
        cardViewsub6.setOnClickListener(this);
        return myView;
    }

    @Override
    public void onClick(View v) {
        Fragment fragment = null;
        switch (v.getId()){
            case R.id.cardsub1:fragment = new cardsub1(); break;
            case R.id.cardsub2:fragment = new cardsub2(); break;
            case R.id.cardsub3:fragment = new cardsub3(); break;
            case R.id.cardsub4:fragment = new cardsub4(); break;
            case R.id.cardsub5:fragment = new cardsub5(); break;
            case R.id.cardsub6:fragment = new cardsub6(); break;
            default:  break;
        }
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
        transaction.addToBackStack(null);

    }
}
