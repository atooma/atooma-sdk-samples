
package com.atooma.plugin.atoomafrousquareplugin;

import com.atooma.plugin.Module;
import com.atooma.sdk.RegisterService;

public class ATOOMAFROUSQUAREPLUGINRegister extends RegisterService {
    @Override
    public Module getModuleInstance() {
        return new ATOOMAFROUSQUAREPLUGIN(this, ATOOMAFROUSQUAREPLUGIN.MODULE_ID, ATOOMAFROUSQUAREPLUGIN.MODULE_VERSION);
    }
}
