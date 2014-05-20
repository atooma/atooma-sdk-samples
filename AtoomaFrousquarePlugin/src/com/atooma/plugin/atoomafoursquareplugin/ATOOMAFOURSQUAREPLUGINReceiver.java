package com.atooma.plugin.atoomafoursquareplugin;

import com.atooma.sdk.AtoomaRegistrationReceiver;

public class ATOOMAFOURSQUAREPLUGINReceiver extends AtoomaRegistrationReceiver {

	@Override
	public Class getRegisterServiceClass() {
		return ATOOMAFOURSQUAREPLUGINRegister.class;
	}

}
