package com.atooma.plugin.sphero;

import com.atooma.plugin.Module;
import com.atooma.sdk.AtoomaPluginService;

public class SPHERORegister extends AtoomaPluginService {

    @Override
    public Module getModuleInstance() {
        return new SPHERO(this, SPHERO.MODULE_ID, SPHERO.MODULE_VERSION);
    }

}
