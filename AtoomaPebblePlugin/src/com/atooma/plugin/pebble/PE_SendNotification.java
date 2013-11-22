package com.atooma.plugin.pebble;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.content.Intent;

import com.atooma.plugin.ParameterBundle;
import com.atooma.plugin.Performer;

public class PE_SendNotification extends Performer {

	public PE_SendNotification(Context context, String id, int version) {
		super(context, id, version);
	}

	@Override
	public void defineUI() {
		setIcon(R.drawable.plugin_icon_normal, R.drawable.plugin_icon_pressed);
		setTitle(R.string.pe_send_notification_title);
	}

	@Override
	public void declareParameters() { 
		addParameter(R.string.title_label, R.string.module_name, "TITLE", "STRING", true);
		addParameter(R.string.body_label, R.string.module_name, "BODY", "STRING", true);
	}

	@Override
	public ParameterBundle onInvoke(String ruleId, ParameterBundle parameters) {
	    final Intent i = new Intent("com.getpebble.action.SEND_NOTIFICATION");
	    final Map data = new HashMap();
	    data.put("title", parameters.get("TITLE"));
	    data.put("body", parameters.get("BODY"));
	    final JSONObject jsonData = new JSONObject(data);
	    final String notificationData = new JSONArray().put(jsonData).toString();
	    i.putExtra("messageType", "PEBBLE_ALERT");
	    i.putExtra("sender", "PebbleAtoomaPlugin");
	    i.putExtra("notificationData", notificationData);
	    getContext().sendBroadcast(i);
		return parameters;
	}
}
