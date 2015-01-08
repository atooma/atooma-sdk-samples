package com.atooma.plugin.atoomafoursquareplugin;

import android.content.Context;
import android.content.SharedPreferences;

import com.atooma.plugin.atoomafrousquareplugin.R;
import com.atooma.sdk.plugin.Module;

public class ATOOMAFOURSQUAREPLUGIN extends Module {

    public static final String MODULE_ID = "FOURSQUARE";
    public static final int MODULE_VERSION = 1;
    public FoursquareApp mFsqApp;

    public ATOOMAFOURSQUAREPLUGIN(Context context, String id, int version) {
        super(context, id, version);
    }

    @Override
    public void registerComponents() {
        registerTrigger(new TR_FriendsCheckin(getContext(), "FRIENDS-CHECKING", 1));
    }

    @Override
    public void defineUI() {
        setIcon(R.drawable.plugin_icon_normal);
        setTitle(R.string.module_name);
    }

    @Override
    public void clearCredentials() {
        SharedPreferences sp = getContext().getSharedPreferences("Prefs", Context.MODE_MULTI_PROCESS);
        sp.edit().clear().commit();
    }

    @Override
    public void defineAuth() {
        SharedPreferences sp = getContext().getSharedPreferences("Prefs", Context.MODE_MULTI_PROCESS);
        String authText = sp.getString("AutenticatedText", "");
        if (authText.length() > 0)
            setAuthenticated(true, authText);
        else
            setAuthenticated(false, "");
    }

}