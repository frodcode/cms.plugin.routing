package routing.domain

class ModuleControl {
	
	String className
	
	String slug
	
	String description

    static constraints = {
		description(nullable:true)
		slug(unique: true)
    }
}
