package com.amabyte.vtucslabmanual.main;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;

import blink9.com.vtucslabmanual.R;

public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);


        // Add 'general' preferences.
        addPreferencesFromResource(R.xml.pref_general);


        // Add 'Theme' preferences, and a corresponding header.
        PreferenceCategory fakeHeader = new PreferenceCategory(this);
        fakeHeader.setTitle(R.string.pref_header_theme_settings);
        getPreferenceScreen().addPreference(fakeHeader);
        addPreferencesFromResource(R.xml.pref_theme);


        // Add 'About' preferences, and a corresponding header.
        fakeHeader = new PreferenceCategory(this);
        fakeHeader.setTitle(R.string.pref_header_about);
        getPreferenceScreen().addPreference(fakeHeader);
        addPreferencesFromResource(R.xml.pref_about);



        getPreferenceManager().findPreference("about_license").setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            public boolean onPreferenceClick(Preference preference) {
                Intent i = new Intent(getBaseContext(), DisplayActivity.class);
                i.putExtra("filename", "LICENSE");
                i.putExtra("path", "others");
                i.putExtra("type","options");
                startActivity(i);
                return true;
            }
        });

    }

}
