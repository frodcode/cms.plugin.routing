package routing.domain

class PageType {

	String slug

	String description

	Boolean singleton

	String templateName

    PageType parent

    static mappedBy = [parent: PageType]

	static hasMany = [subPages: PageType, moduleControls: ModuleControl, registeredCalls: RegisteredCall]

	static constraints = {
		slug(unique:true)
		moduleControls(nullable:true)
		registeredCalls(nullable:true)
        parent(nullable : true)
	}

    public boolean isRoot()
    {
        if (parent) {
            return false;
        }
        return true;
    }

    public PageType getRoot()
    {
        if (isRoot()) {
            return this
        }
        return parent.getRoot();
    }


}
