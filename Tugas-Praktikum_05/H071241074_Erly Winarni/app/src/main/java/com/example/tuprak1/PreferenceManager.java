package com.example.tuprak1;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    private static final String PREF_NAME = "tuprak1_prefs";
    private static final String PREF_USERS = "tuprak1_user_creds";
    private static final String KEY_IS_LOGGED_IN = "isLoggedIn";
    private static final String KEY_CURRENT_USER = "currentUsername";
    private static final String KEY_IS_DARK_MODE = "isDarkMode";
    private SharedPreferences pref;
    private SharedPreferences userPrefs;
    private SharedPreferences.Editor editor;

    public PreferenceManager(Context context) {
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        userPrefs = context.getSharedPreferences(PREF_USERS, Context.MODE_PRIVATE);
        editor = pref.edit();
    }

    public void setLogin(boolean isLoggedIn, String username) {
        editor.putBoolean(KEY_IS_LOGGED_IN, isLoggedIn);
        editor.putString(KEY_CURRENT_USER, username);
        editor.apply();
    }

    public boolean isLoggedIn() {
        return pref.getBoolean(KEY_IS_LOGGED_IN, false);
    }

    public void saveUser(String username, String password) {
        SharedPreferences.Editor userEditor = userPrefs.edit();
        userEditor.putString(username, password);
        userEditor.apply();
    }

    public boolean checkUser(String username, String password) {
        String savedPassword = userPrefs.getString(username, null);
        return savedPassword != null && savedPassword.equals(password);
    }

    public String getUsername() {
        return pref.getString(KEY_CURRENT_USER, "");
    }

    public void setDarkMode(boolean isDarkMode) {
        editor.putBoolean(KEY_IS_DARK_MODE, isDarkMode);
        editor.apply();
    }

    public boolean isDarkMode() {
        return pref.getBoolean(KEY_IS_DARK_MODE, false);
    }

    public void logout() {
        editor.putBoolean(KEY_IS_LOGGED_IN, false);
        editor.putString(KEY_CURRENT_USER, "");
        editor.apply();
    }

    public void clear() {
        editor.clear();
        editor.apply();
        userPrefs.edit().clear().apply();
    }
}
