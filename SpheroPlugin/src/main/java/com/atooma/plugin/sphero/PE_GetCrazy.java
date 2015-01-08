package com.atooma.plugin.sphero;

import android.content.Context;

import com.atooma.sdk.plugin.ParameterBundle;
import com.atooma.sdk.plugin.Performer;
import com.atooma.spherobot.SpheroBot;

import java.util.Random;

public class PE_GetCrazy extends Performer {

    public PE_GetCrazy(Context context, String id, int version) {
        super(context, id, version);
    }

    @Override
    public void defineUI() {
        setIcon(R.drawable.get_crazy_sphero);
        setTitle(R.string.pe_get_crazy_title);
    }

    @Override
    public void declareParameters() {
    }

    @Override
    public ParameterBundle onInvoke(String ruleId, ParameterBundle parameters) {
        SpheroBot sphero = SpheroSingleBot.getInstance(this.getContext());
        while (sphero.isRunning()) {
        }
        int iterations = random(2, 20);
        for (int i = 0; i < iterations; i++) {
            int action = random(1, 1000) % 2;
            if (action == 0) {
                float degree = random(0, 360);
                float speed = random(0, 100) / 100;
                long duration = random(500, 1000);
                sphero.drive(degree, speed, duration);
            } else {
                int r = random(0, 255);
                int g = random(0, 255);
                int b = random(0, 255);
                long duration = random(0, 500);
                sphero.setColor(r, g, b, duration);
            }
        }
        sphero.start();
        return parameters;
    }

    private int random(int start, int end) {
        Random r = new Random();
        return r.nextInt(end - start) + start;
    }
}
