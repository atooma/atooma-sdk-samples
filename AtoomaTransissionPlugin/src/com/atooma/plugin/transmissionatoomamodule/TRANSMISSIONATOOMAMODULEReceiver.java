
package com.atooma.plugin.transmissionatoomamodule;

import com.atooma.sdk.AtoomaRegistrationReceiver;

public class TRANSMISSIONATOOMAMODULEReceiver extends AtoomaRegistrationReceiver {

    @Override
    public Class getRegisterServiceClass() {
        return TRANSMISSIONATOOMAMODULERegister.class;
    }

}
