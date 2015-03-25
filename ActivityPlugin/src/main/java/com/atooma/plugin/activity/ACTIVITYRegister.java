package com.atooma.plugin.activity;

import com.atooma.plugin.IModulePlugin;
import com.atooma.sdk.AtoomaPluginService;

public class ACTIVITYRegister extends AtoomaPluginService {

    @Override
    public IModulePlugin getModuleInstance() {
        return new ACTIVITY(this, ACTIVITY.MODULE_ID, ACTIVITY.MODULE_VERSION);
    }

}
