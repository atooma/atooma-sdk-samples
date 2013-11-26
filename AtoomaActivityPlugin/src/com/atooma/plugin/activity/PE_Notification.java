package com.atooma.plugin.activity;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.RemoteException;
import android.widget.Toast;

import com.atooma.plugin.ParameterBundle;
import com.atooma.plugin.Performer;

public class PE_Notification extends Performer {

	public PE_Notification(Context context, String id, int version) {
		super(context, id, version);
	}

	@Override
	public void defineUI() {
		setIcon(R.drawable.plugin_icon_normal, R.drawable.plugin_icon_pressed);
		setTitle(R.string.pe_name);
	}

	@Override
	public void declareParameters() {
		addParameter(R.string.parameter_name, R.string.parameter_name_ifnull, "STRINGS", "PLUGIN", true);
	}

	@Override
	public ParameterBundle onInvoke(String ruleId, ParameterBundle parameters) throws RemoteException {
		final String[] text = (String[]) parameters.get("STRINGS");
		Handler handler = new Handler(Looper.getMainLooper());
		handler.post(new Runnable() {
			public void run() {
				Toast.makeText(getContext(), text[0], Toast.LENGTH_LONG).show();
			}
		});
		return null;
	}

}
