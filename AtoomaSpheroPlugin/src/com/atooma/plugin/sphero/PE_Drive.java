package com.atooma.plugin.sphero;

import android.content.Context;

import com.atooma.plugin.ParameterBundle;
import com.atooma.plugin.Performer;
import com.atooma.spherobot.SpheroBot;

public class PE_Drive extends Performer {

	public PE_Drive(Context context, String id, int version) {
		super(context, id, version);
	}
	
	@Override
	public void defineUI() {
		setIcon(R.drawable.drive_sphero);
		setTitle(R.string.pe_drive_title);
	}

	@Override
	public void declareParameters() {
		addParameter(R.string.degree_label, R.string.module_name, "DEGREE", "NUMBER", true);
		addParameter(R.string.speed_label, R.string.module_name, "SPEED", "NUMBER", true);
		addParameter(R.string.duration_label, R.string.module_name, "DURATION", "NUMBER", true);

	}

	@Override
	public ParameterBundle onInvoke(String ruleId, ParameterBundle parameters) {
		SpheroBot sphero = SpheroSingleBot.getInstance(this.getContext()); 
		if (sphero.isRunning()) {return null;}
		float degree = doubleToFloat((Double)parameters.get("DEGREE"));
		float speed = doubleToInt((Double)parameters.get("SPEED"));
		int duration = doubleToInt((Double)parameters.get("DURATION"));
		sphero.drive(degree, speed, duration);
		sphero.start();
		return parameters;
	}
	
	private int doubleToInt(Double dblObj) {
		double dbl = dblObj;
		return (int)dbl;
	}
	
	private float doubleToFloat(Double dblObj) {
		double dbl = dblObj;
		return (float)dbl;
	}
}
