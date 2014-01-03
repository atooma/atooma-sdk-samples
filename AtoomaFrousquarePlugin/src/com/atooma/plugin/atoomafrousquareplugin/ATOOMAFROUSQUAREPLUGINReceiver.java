
package com.atooma.plugin.atoomafrousquareplugin;

import com.atooma.sdk.AtoomaRegistrationReceiver;

public class ATOOMAFROUSQUAREPLUGINReceiver extends AtoomaRegistrationReceiver {

    @Override
    public Class getRegisterServiceClass() {
        return ATOOMAFROUSQUAREPLUGINRegister.class;
    }

}
