package com.atooma.plugin.transmission;

import com.atooma.sdk.RegisterService;
import com.atooma.sdk.plugin.Module;

public class TRANSMISSIONATOOMAMODULERegister extends RegisterService {
	@Override
	public Module getModuleInstance() {
		return new TRANSMISSIONATOOMAMODULE(this, TRANSMISSIONATOOMAMODULE.MODULE_ID, TRANSMISSIONATOOMAMODULE.MODULE_VERSION);
	}
}
