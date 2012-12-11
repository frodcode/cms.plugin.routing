package module

class ModuleRegister {

	private def registredModules = [:]
	
	public def register(def module)
	{
		if (!registredModules[module.class]) {
			registredModules[module.class] = module;
		}
	}
}
