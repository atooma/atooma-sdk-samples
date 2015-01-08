package com.atooma.plugin.sphero;

import android.content.Context;

import com.atooma.spherobot.SpheroBot;

public class SpheroSingleBot {
	
	private static SpheroBot instance;
	
	private SpheroSingleBot() {
		
	}
	
	public synchronized static SpheroBot getInstance(Context ctx) {
		if (instance == null)
			instance = new SpheroBot(ctx);
		return instance;
	}

}
