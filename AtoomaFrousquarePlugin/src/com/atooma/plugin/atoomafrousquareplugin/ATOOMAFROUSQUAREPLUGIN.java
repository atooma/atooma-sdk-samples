package com.atooma.plugin.atoomafrousquareplugin;

import android.content.Context;

import com.atooma.plugin.Module;

public class ATOOMAFROUSQUAREPLUGIN extends Module {

	public static final String MODULE_ID = "FOURSQUARE";
	public static final int MODULE_VERSION = 1;

	public ATOOMAFROUSQUAREPLUGIN(Context context, String id, int version) {
		super(context, id, version);
	}

	@Override
	public void registerComponents() {
		registerTrigger(new TR_FriendsCheckin(getContext(), "FRIENDS-CHECKING", 1));
	}

	@Override
	public void defineUI() {
		setIcon(R.drawable.plugin_icon_normal, R.drawable.plugin_icon_pressed);
		setTitle(R.string.module_name);
	}

}