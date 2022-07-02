package com.example.androidfitness;

import android.accounts.AccountManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.androidfitness.databinding.ActivityMainBinding;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.common.AccountPicker;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.tasks.Task;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static final int GOOGLE_FIT_PERMISSION_REQUEST_CODE = 1;
    public static final int SOME_ACCOUNT_PICKER_REQUEST_CODE = 1;
    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private Intent chosenAccountIntent;

    private GoogleSignInAccount allowedGoogleSignedInAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.toolbar);
//
//        binding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        this.chosenAccountIntent =
                AccountPicker.newChooseAccountIntent(
                        new AccountPicker.AccountChooserOptions.Builder()
                                .setAllowableAccountsTypes(Arrays.asList("com.google"))
                                .build());
        startActivityForResult(this.chosenAccountIntent, SOME_ACCOUNT_PICKER_REQUEST_CODE);


//        GoogleSignInOptionsExtension fitnessOptions = FitnessOptions.builder()
//                .addDataType(DataType.TYPE_STEP_COUNT_DELTA, FitnessOptions.ACCESS_READ)
//                .build();
//
//        allowedGoogleSignedInAccount = GoogleSignIn.getAccountForExtension(this, fitnessOptions);
//
//        if (!GoogleSignIn.hasPermissions(allowedGoogleSignedInAccount, fitnessOptions)) {
//            GoogleSignIn.requestPermissions(
//                    this, // your activity
//                    GOOGLE_FIT_PERMISSION_REQUEST_CODE,
//                    allowedGoogleSignedInAccount,
//                    fitnessOptions);
//        } else {
//            // Permission is already granted
//            Toast.makeText(this, "Account permission already granted", Toast.LENGTH_SHORT).show();
//        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void stepsActivityButton(View view) {
        Intent stepsIntent = new Intent(this, StepsActivity.class);
        startActivity(stepsIntent);
    }

    public void caloriesActivity(View view) {
        Intent caloriesIntent = new Intent(this, CaloriesActivity.class);
        startActivity(caloriesIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == SOME_ACCOUNT_PICKER_REQUEST_CODE && resultCode == RESULT_OK) {
            String accountName = data.getStringExtra(AccountManager.KEY_ACCOUNT_NAME);
            Toast.makeText(this, "Last sign into " + accountName, Toast.LENGTH_SHORT).show();
        }
//        if (resultCode == Activity.RESULT_OK) {
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACTIVITY_RECOGNITION) != PackageManager.PERMISSION_GRANTED) {
//                // Permission is not granted
//                String[] permissions = {Manifest.permission.ACTIVITY_RECOGNITION};
//                ActivityCompat.requestPermissions(this, permissions, 1);
//            } else {
//                // Permission is already granted
//                Toast.makeText(this, "Permission already granted", Toast.LENGTH_SHORT).show();
//            }
//        }
    }
}