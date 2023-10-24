package com.droidfreshsquad.poly2023.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.droidfreshsquad.poly2023.Fragment.FragmentMain;
import com.droidfreshsquad.poly2023.Fragment.DatveFragment;
import com.droidfreshsquad.poly2023.Fragment.HomeFragment;
import com.droidfreshsquad.poly2023.Fragment.HosoFragment;
import com.droidfreshsquad.poly2023.Fragment.LienHeFragment;
import com.droidfreshsquad.poly2023.R;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class FragmentMain extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_main);
        bottomNavigationView = findViewById(R.id.BottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.menu_trangchu) {
                    ReplaceFragment(new HomeFragment());
                }
                if (item.getItemId() == R.id.menu_lienhe) {
                    ReplaceFragment(new LienHeFragment());
                }
                if (item.getItemId() == R.id.menu_datve) {
                    ReplaceFragment(new DatveFragment());
                }
                if (item.getItemId() == R.id.menu_hoso) {
                    ReplaceFragment(new HosoFragment());
                }
                return true;
            }
        });

        if (savedInstanceState == null) {
            // Nếu savedInstanceState là null, tức là Activity được tạo lần đầu
            // Thực hiện thay thế Fragment mặc định ở đây, ví dụ: homeFragment
            ReplaceFragment(new HomeFragment());
        }
    }

    private void ReplaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, fragment);
        fragmentTransaction.commit();
    }
}