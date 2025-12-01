package com.oncohelper;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.oncohelper.ui.medications.AddMedicationFragment;
import com.oncohelper.ui.medications.MedicationListFragment;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // Устанавливаем слушатель выбора пунктов меню
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.nav_add) {
                loadFragment(new AddMedicationFragment(), true);
                return true;
            } else if (id == R.id.nav_list) {
                loadFragment(new MedicationListFragment(), true);
                return true;
            }
            return false;
        });

        // Устанавливаем слушатель для повторного нажатия
        bottomNavigationView.setOnItemReselectedListener(item -> {
            // Можно добавить дополнительную логику при повторном нажатии
            // Например, прокрутка списка в начало
        });

        // По умолчанию показываем список лекарств
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.nav_list);
        }
    }

    private void loadFragment(Fragment fragment, boolean addToBackStack) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        // Анимация перехода (опционально)
        ft.setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out,
                android.R.anim.fade_in,
                android.R.anim.fade_out
        );

        ft.replace(R.id.fragment_container, fragment);

        if (addToBackStack) {
            ft.addToBackStack(null);
        }

        ft.commit();
    }

    @Override
    public void onBackPressed() {
        // Если в back stack есть фрагменты
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            getSupportFragmentManager().popBackStack();

            // Обновляем выделение в bottom navigation
            Fragment currentFragment = getSupportFragmentManager()
                    .findFragmentById(R.id.fragment_container);

            if (currentFragment instanceof AddMedicationFragment) {
                bottomNavigationView.setSelectedItemId(R.id.nav_add);
            } else if (currentFragment instanceof MedicationListFragment) {
                bottomNavigationView.setSelectedItemId(R.id.nav_list);
            }
        } else {
            super.onBackPressed();
        }
    }
}