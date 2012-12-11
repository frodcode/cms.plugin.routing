package domain.routing

class RegisteredCall {
	
	ModuleControl moduleControl
	
	String methodName
	
	static hasMany = [callParams:CallParam]

    static constraints = {
    }
}
