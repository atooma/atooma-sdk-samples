package com.atooma.plugin.pebble;

import android.content.Context;

import com.atooma.sdk.plugin.Module;

public class PEBBLE extends Module {

    public static final String MODULE_ID = "PEBBLE";
    public static final int MODULE_VERSION = 1;

    public PEBBLE(Context context, String id, int version) {
        super(context, id, version);
    }

    @Override
    public void registerComponents() {
        registerPerformer(new PE_SendNotification(getContext(), "PE_SEND_NOTIFICATION", 1));
    }

    @Override
    public void defineUI() {
        setIcon(R.drawable.plugin_icon_normal);
        setTitle(R.string.module_name);
    }

    @Override
    public void clearCredentials() {
    }

    @Override
    public void defineAuth() {
    }
}