
package com.atooma.plugin.atoomafoursquareplugin;

import com.atooma.plugin.Module;
import com.atooma.sdk.AtoomaPluginService;

public class ATOOMAFOURSQUAREPLUGINRegister extends AtoomaPluginService {
    @Override
    public Module getModuleInstance() {
        return new ATOOMAFOURSQUAREPLUGIN(this, ATOOMAFOURSQUAREPLUGIN.MODULE_ID, ATOOMAFOURSQUAREPLUGIN.MODULE_VERSION);
    }
}
