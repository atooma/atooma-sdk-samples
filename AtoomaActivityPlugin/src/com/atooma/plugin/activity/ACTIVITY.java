package com.atooma.plugin.activity;

import android.content.Context;

import com.atooma.plugin.Module;

public class ACTIVITY extends Module {

	public static final String MODULE_ID = "ACTIVITY";
	public static final int MODULE_VERSION = 1;

	public ACTIVITY(Context context, String id, int version) {
		super(context, id, version);
	}

	@Override
	public void registerComponents() {
		registerTrigger(new TR_GetString(getContext(), "GET_STRINGS", 1));
		registerPerformer(new PE_Notification(getContext(), "NOTIFICATION", 1));
	}

	@Override
	public void defineUI() {
		setIcon(R.drawable.plugin_icon_normal, R.drawable.plugin_icon_pressed);
		setTitle(R.string.module_name);
	}

}
