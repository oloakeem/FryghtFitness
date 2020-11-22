package come.example.myone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import come.example.myone.ui.gallery.StatusFragment;
import come.example.myone.ui.home.FavoritesFragment;
import come.example.myone.ui.send.ExerciseLibFragment;
import come.example.myone.ui.share.ThemeFragment;
import come.example.myone.ui.slideshow.SettingsFragment;
import come.example.myone.ui.tools.LogoutFragment;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private AppBarConfiguration mAppBarConfiguration;
    TextView textView;
    String userEmail;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        View header = navigationView.getHeaderView(0);
        textView = (TextView) header.findViewById(R.id.navUsername);
        AccountInfo();
        //I added this if statement to keep the selected fragment when rotating the device
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new NutritionGrid()).commit();

           }


    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        confirmDialog(getApplicationContext());
    }

    /* NOTE The Java Calendar class month numbering start at 0, not 1

   protected void onResume()
    {
        super.onResume();

        Calendar expirationDate = Calendar.getInstance();
        expirationDate.set(2020, 6, 13 ,23,59,00);  //hardcoded expiration date
        Calendar t = Calendar.getInstance();  //Calendar with current time/date
        if (t.compareTo(expirationDate) == 1)
            finish();
    }
 */
    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    switch (item.getItemId()) {
                        case R.id.nav_favorites:
                            selectedFragment = new NutritionGrid();
                            break;

                        case R.id.nav_gen:
                            selectedFragment = new WorkoutHome();
                            break;

                        case R.id.nav_search:
                            selectedFragment = new UsefulCalculator();

                            break;
                    }
                    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                    transaction.setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out);
                    transaction.replace(R.id.fragment_container, selectedFragment);
                    //   transaction.addToBackStack(tag);
                    transaction.commit();
            drawerLayout.closeDrawer(GravityCompat.START);
                    //   getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    //  selectedFragment).setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).commit();

                    return true;
                }
            };


    public void AccountInfo() {
        userEmail = getIntent().getExtras().getString("userEmail");
        if (userEmail.equals("guest")) {
            textView.setText("LoggedInAsGuest");
        } else {
            textView.setText(userEmail);
        }
    }

    private void confirmDialog(Context context) {

        AlertDialog alert = new AlertDialog.Builder(HomeActivity.this).create();
        alert.setTitle("Alert");
        alert.setMessage("Do you want to exit ?");
        alert.setIcon(R.drawable.ic_warning_black_24dp);
        alert.setCancelable(false);
        alert.setCanceledOnTouchOutside(false);

        alert.setButton(DialogInterface.BUTTON_POSITIVE, "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {


                        finish();
                        alert.dismiss();

                    }
                });

        alert.setButton(DialogInterface.BUTTON_NEGATIVE, "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        alert.dismiss();

                    }
                });

        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home__drawer, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()) {

            case R.id.nav_Status:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new StatusFragment()).commit();
                break;
            case R.id.nav_Favorites:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new FavoritesFragment()).commit();
                break;
            case R.id.nav_Exercise:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ExerciseLibFragment()).commit();
                break;
            case R.id.nav_Theme:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new ThemeFragment()).commit();
                break;
            case R.id.nav_Settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new SettingsFragment()).commit();
                break;
            case R.id.nav_Logout:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                        new LogoutFragment()).commit();
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
    return true;
    }
}

