
package com.atooma.plugin.sphero;

import android.content.Context;

import com.atooma.plugin.Module;

public class SPHERO extends Module {
    
    public static final String MODULE_ID = "SPHERO";
    public static final int MODULE_VERSION = 1;

    public SPHERO(Context context, String id, int version) {
        super(context, id, version);
    }
    
    @Override
    public void registerComponents() {
    	registerPerformer(new PE_ChangeColor(getContext(), "PE_CHANGE_COLOR", 1));
    }
    
    @Override
    public void defineUI() {
        setIcon(R.drawable.plugin_icon_normal, R.drawable.plugin_icon_pressed);
        setTitle(R.string.module_name);
    }
    
}