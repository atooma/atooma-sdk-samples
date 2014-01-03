package com.atooma.plugin.atoomafrousquareplugin;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.RemoteException;

import com.atooma.plugin.AlarmBasedTrigger;
import com.atooma.plugin.ParameterBundle;
import com.atooma.plugin.Schedule;

public class TR_FriendsCheckin extends AlarmBasedTrigger {

	private HashMap<String, String> sinceIds = new HashMap<String, String>();

	public TR_FriendsCheckin(Context context, String id, int version) {
		super(context, id, version);
	}

	@Override
	public Schedule getScheduleInfo() throws RemoteException {
		return new Schedule(true, true, false, System.currentTimeMillis(), (long) (10 * 1000));
	}

	@Override
	public void defineUI() {
		setTitle(R.string.tr_friends_checking);
		setIcon(R.drawable.plugin_icon_el_normal, R.drawable.plugin_icon_el_pressed);
	}

	@Override
	public void declareParameters() {
		addParameter(R.string.authentication_parameter, R.string.authentication_parameter, "ACCESS", "PLUGIN", true, "com.atooma.plugin.atoomafrousquareplugin.AccessFoursquare");
	}

	@Override
	public void onTimeout(String ruleId, ParameterBundle arg1) {
		String sinceId;
		synchronized (sinceIds) {
			sinceId = sinceIds.get(ruleId);
		}
		FoursquareApp mFsqApp = new FoursquareApp(getContext(), AccessFoursquare.CLIENT_ID, AccessFoursquare.CLIENT_SECRET);

		if (mFsqApp.hasAccessToken()) {
			JSONArray checkins = mFsqApp.getFriendsCheckin();
			JSONObject lastCheckin;
			try {
				lastCheckin = checkins.getJSONObject(0);
				if (sinceId == null)
					sinceId = lastCheckin.getString("id");
				if (lastCheckin.getString("id").equals(sinceId))
					return;
				else {
					synchronized (sinceIds) {
						sinceIds.put(ruleId, sinceId);
					}
					trigger(ruleId, new ParameterBundle());
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
}
