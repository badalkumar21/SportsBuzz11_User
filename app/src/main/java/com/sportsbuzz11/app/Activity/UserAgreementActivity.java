package com.sportsbuzz11.app.Activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.sportsbuzz11.app.R;


public class UserAgreementActivity extends AppCompatActivity {

    TextView privacyPolicyText;
    TextView disclosureText;
    TextView termsAndConditionText;
    TextView agreeText;

    CheckBox checkBox;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_agreement);

        privacyPolicyText = findViewById(R.id.privacy_policy_text);
        disclosureText = findViewById(R.id.disclosure_text);
        termsAndConditionText = findViewById(R.id.terms_and_conditions_text);
        agreeText = findViewById(R.id.agree_text);

        checkBox = findViewById(R.id.agreement_check);

        privacyPolicyText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!isNetworkConnected()) {
                    Toast.makeText(UserAgreementActivity.this, "Please Connect to internet ", Toast.LENGTH_SHORT).show();
                } else {
                    intent = new Intent(getApplicationContext(), AgreementWebViewActivity.class);
                    intent.putExtra("key", "Privacy Policy");
                    startActivity(intent);
                }
            }
        });

        disclosureText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isNetworkConnected()) {
                    Toast.makeText(UserAgreementActivity.this, "Please Connect to internet ", Toast.LENGTH_SHORT).show();
                } else {
                    intent = new Intent(getApplicationContext(), AgreementWebViewActivity.class);
                    intent.putExtra("key", "Disclosure");
                    startActivity(intent);
                }
            }
        });

        termsAndConditionText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isNetworkConnected()) {
                    Toast.makeText(UserAgreementActivity.this, "Please Connect to internet ", Toast.LENGTH_SHORT).show();
                } else {
                    intent = new Intent(getApplicationContext(), AgreementWebViewActivity.class);
                    intent.putExtra("key", "Terms And Conditions");
                    startActivity(intent);
                }
            }
        });

        agreeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBox.isChecked()) {
                    intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(UserAgreementActivity.this, "Please click the box to make sure that you have gone through the Privacy Policy and Teams & Conditions.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
