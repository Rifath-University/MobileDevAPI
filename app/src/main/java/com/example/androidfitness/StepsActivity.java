package com.example.androidfitness;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.fitness.Fitness;
import com.google.android.gms.fitness.FitnessOptions;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class StepsActivity extends AppCompatActivity {

    private GoogleSignInAccount allowedGoogleSignedInAccount;

    private static final int GOOGLE_FIT_PERMISSION_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        dailyStepCount();
//
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
//            dailyStepCount();
//        }
    }

    public void dailyStepCount() {
        Fitness.getHistoryClient(this, GoogleSignIn.getLastSignedInAccount(this))
                .readDailyTotal(DataType.TYPE_STEP_COUNT_DELTA)
                .addOnSuccessListener(dataSet -> {
                    Log.d("Status","Success");
                    long stepsTotal = dataSet.isEmpty()
                            ? 0
                            : dataSet.getDataPoints().get(0).getValue(Field.FIELD_STEPS).asInt();

                    TextView textView = findViewById(R.id.steps);
                    textView.setText(String.valueOf(stepsTotal));

                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("Status","Failure",e);
                    }
                })
                .addOnCompleteListener(new OnCompleteListener<DataSet>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSet> task) {
                        Log.d("Status","Complete");

                    }
                });
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == Activity.RESULT_OK) {
//            if (requestCode == GOOGLE_FIT_PERMISSION_REQUEST_CODE) {
//                dailyStepCount();
//            } else {
//                //return user to main activity screen because user said no
//            }
//        } else {
//            //return user to main activity screen because user said no
//        }
//    }

}