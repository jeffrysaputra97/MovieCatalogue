package com.ithb.jeffry.moviecatalogue.activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.ithb.jeffry.moviecatalogue.R;

import java.util.Locale;

public class SettingActivity extends AppCompatActivity {
    SharedPreferences mSettings;
    String appLocale = "";
    String settingLanguage = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        setActionBarTitle(getString(R.string.language_title));

        mSettings = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        settingLanguage = mSettings.getString("Locale", null);

        if (settingLanguage == null) {
            appLocale = Locale.getDefault().getLanguage().toLowerCase();
        } else {
            appLocale = settingLanguage;
        }

        switch (appLocale) {
            case "en":
                RadioButton radioButtonEn = findViewById(R.id.radioButton_en);
                radioButtonEn.setChecked(true);
                break;
            case "in":
                RadioButton radioButtonIn = findViewById(R.id.radioButton_in);
                radioButtonIn.setChecked(true);
                break;
        }

        RadioGroup radioGroupLanguage = findViewById(R.id.radioGroup_language);
        radioGroupLanguage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.radioButton_in:
                        setAppLocale("in");
                        break;
                    case R.id.radioButton_en:
                        setAppLocale("en");
                        break;
                }

                Toast.makeText(SettingActivity.this, getString(R.string.language_message), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onStop() {
        SharedPreferences.Editor editor = mSettings.edit();
        editor.putString("Locale", appLocale);
        editor.apply();
        super.onStop();
    }

    private void setActionBarTitle(String title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

    private void setAppLocale(String localeCode) {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.setLocale(new Locale(localeCode.toLowerCase()));
        res.updateConfiguration(conf, dm);
        appLocale = localeCode.toLowerCase();
    }
}
