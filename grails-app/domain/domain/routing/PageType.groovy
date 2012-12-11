package domain.routing

class PageType {
	
	String slug
	
	String description
	
	Boolean singleton
	
	String templateName
	
	static hasMany = [moduleControls: ModuleControl, registeredCalls: RegisteredCall]

    static constraints = {
		slug(unique:true)
		moduleControls(nullable:true)
		registeredCalls(nullable:true)
    }
	
}
