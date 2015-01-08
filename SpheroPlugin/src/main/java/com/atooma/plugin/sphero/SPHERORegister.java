package com.atooma.plugin.sphero;

import com.atooma.sdk.RegisterService;
import com.atooma.sdk.plugin.Module;

public class SPHERORegister extends RegisterService {

    @Override
    public Module getModuleInstance() {
        return new SPHERO(this, SPHERO.MODULE_ID, SPHERO.MODULE_VERSION);
    }

}
