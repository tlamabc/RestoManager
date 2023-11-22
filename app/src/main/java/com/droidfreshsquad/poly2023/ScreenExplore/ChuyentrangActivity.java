package com.droidfreshsquad.poly2023.ScreenExplore;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.droidfreshsquad.poly2023.Fragment.DatveFragment;
import com.droidfreshsquad.poly2023.R;

public class ChuyentrangActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chuyentrang);

        if (getIntent() != null && getIntent().hasExtra("FRAGMENT_TO_LOAD")) {
            String fragmentToLoad = getIntent().getStringExtra("FRAGMENT_TO_LOAD");

            // Check the value of fragmentToLoad and load the corresponding fragment
            if ("DATVE_FRAGMENT".equals(fragmentToLoad)) {
                loadDatveFragment();
            }

            // Clear the extra to avoid reloading the fragment if the activity is recreated
            getIntent().removeExtra("FRAGMENT_TO_LOAD");
        } else {
            // Load the default fragment if no extra is provided
            loadDefaultFragment();
        }
    }

    private void loadDatveFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new DatveFragment())
                .commit();
    }

    private void loadDefaultFragment() {
        // Load your default fragment here
    }
    }
