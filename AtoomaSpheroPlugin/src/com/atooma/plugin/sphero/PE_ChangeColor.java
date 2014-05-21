package com.atooma.plugin.sphero;

import android.content.Context;

import com.atooma.plugin.ParameterBundle;
import com.atooma.plugin.Performer;
import com.atooma.spherobot.SpheroBot;

public class PE_ChangeColor extends Performer {

	public PE_ChangeColor(Context context, String id, int version) {
		super(context, id, version);
	}

	@Override
	public void defineUI() {
		setIcon(R.drawable.plugin_icon_el_normal);
		setTitle(R.string.pe_change_color_title);
	}

	@Override
	public void declareParameters() {
		addParameter(R.string.r_label, R.string.module_name, "SPHERO_NAME", "STRING", false);
		addParameter(R.string.r_label, R.string.module_name, "R", "NUMBER", true);
		addParameter(R.string.g_label, R.string.module_name, "G", "NUMBER", true);
		addParameter(R.string.b_label, R.string.module_name, "B", "NUMBER", true);
	}

	@Override
	public ParameterBundle onInvoke(String ruleId, ParameterBundle parameters) {
		SpheroBot sphero = new SpheroBot(this.getContext()); 
		int r = doubleToInt((Double)parameters.get("R"));
		int g = doubleToInt((Double)parameters.get("G"));
		int b = doubleToInt((Double)parameters.get("B"));
		sphero.setColor(r, g, b, 1000);
		sphero.start();
		return parameters;
	}
	
	private int doubleToInt(Double dblObj) {
		double dbl = dblObj;
		return (int)dbl;
	}
}
