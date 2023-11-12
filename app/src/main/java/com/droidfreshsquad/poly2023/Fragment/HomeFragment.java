package com.droidfreshsquad.poly2023.Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.droidfreshsquad.poly2023.Adapter.ExploreAdapter;
import com.droidfreshsquad.poly2023.Domain.ExploreDomain;
import com.droidfreshsquad.poly2023.HoSo.Danh_Gia;
import com.droidfreshsquad.poly2023.R;
import com.droidfreshsquad.poly2023.ScreenMainActivity;
import com.droidfreshsquad.poly2023.datve.BookingActivity;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerViewExploreList;
    private RecyclerView.Adapter adapter;
    private LinearLayout lnlSearch;
    private ImageView danhgia;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public HomeFragment() {
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_screen_main, container, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewExploreList = view.findViewById(R.id.recyclerview2);
        recyclerViewExploreList.setLayoutManager(linearLayoutManager);
        ArrayList<ExploreDomain> explore = new ArrayList<>();
        explore.add(new ExploreDomain("imgexplore1", "Đà Nẵng", "Tham Quan Địa Điểm Du Lịch Đà Nẵng Nổi Tiếng"));
        explore.add(new ExploreDomain("imgexplore2", "Sài Gòn", "Tham Quan Địa Điểm Du Lịch Sài Gòn Nổi Tiếng"));
        explore.add(new ExploreDomain("imgexplore3", "Hà Nội", "Tham Quan Địa Điểm Du Lịch Hà Nội Nổi Tiếng"));
        adapter = new ExploreAdapter(explore);
        recyclerViewExploreList.setAdapter(adapter);

        lnlSearch = view.findViewById(R.id.lnlSearch);
        danhgia = (ImageView) view.findViewById(R.id.danhgia);

        danhgia.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Danh_Gia dialog = new Danh_Gia(HomeFragment.this);
                dialog.handleDialogEvents();
                dialog.show();
            }
        });


        lnlSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), BookingActivity.class));
            }
        });


        WebView webView = view.findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.tomorrow.io/weather/vi/VN/DN/Da_Nang/130195/");


        return view;

    }

}


