package routing.domain

class RegisteredCall {
	
	ModuleControl moduleControl
	
	String methodName
	
	static hasMany = [callParams:CallParam]

    static constraints = {
    }
}
