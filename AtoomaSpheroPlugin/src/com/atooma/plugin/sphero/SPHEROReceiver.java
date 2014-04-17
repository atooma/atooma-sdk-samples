package com.atooma.plugin.sphero;

import com.atooma.sdk.AtoomaRegistrationReceiver;

public class SPHEROReceiver extends AtoomaRegistrationReceiver {
	@Override
	public Class getRegisterServiceClass() {
		return SPHERORegister.class;
	}
}
