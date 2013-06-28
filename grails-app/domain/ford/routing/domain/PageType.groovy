package ford.routing.domain

class PageType {

	String slug

	String description

	Boolean singleton

    String controller

    String action

	static constraints = {
		slug(unique:true)
	}
}
