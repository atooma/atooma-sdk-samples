
package com.atooma.plugin.transmission;

import com.atooma.sdk.AtoomaRegistrationReceiver;

public class TRANSMISSIONATOOMAMODULEReceiver extends AtoomaRegistrationReceiver {

    @Override
    public Class getRegisterServiceClass() {
        return TRANSMISSIONATOOMAMODULERegister.class;
    }

}
