
package com.atooma.plugin.atoomafoursquareplugin;

import com.atooma.plugin.Module;
import com.atooma.sdk.RegisterService;

public class ATOOMAFOURSQUAREPLUGINRegister extends RegisterService {
    @Override
    public Module getModuleInstance() {
        return new ATOOMAFOURSQUAREPLUGIN(this, ATOOMAFOURSQUAREPLUGIN.MODULE_ID, ATOOMAFOURSQUAREPLUGIN.MODULE_VERSION);
    }
}
