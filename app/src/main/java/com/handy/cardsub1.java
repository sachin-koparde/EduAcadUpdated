package com.handy;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class cardsub1 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_cardsub1, container, false);
    }

    public void Backpresses(){
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.activitycard1, new Dashboard()).commit();
    }

}
