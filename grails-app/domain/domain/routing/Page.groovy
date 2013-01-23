package domain.routing

import routing.domain.auth.AuthRole

class Page {

	Host host

	String urlPart

	String langPart

	UrlTypeEnum urlType

	String url

	HttpMethodEnum httpMethod

	RequestTypeEnum requestType

	PageType pageType

	Page parent

	static mappedBy = [parent: Page]

	static hasMany = [subPages: Page, authRoles: AuthRole]

	static constraints = {
		parent(nullable : true)
		host(nullable: true)
		subPages(nullable:true)
		url(unique: ['httpMethod', 'requestType'])
		langPart (nullable: true)
	}

	public String getLinkTo(Page page) {
		def selfHostDomain = getFinalHost().getFullHostDomain()
		def linkHostDomain = page.getFinalHost().getFullHostDomain()
		if (selfHostDomain != linkHostDomain) {
			return url
		}
		return getRelativeLink()
	}

	public String getRelativeLink() {
		return getFinalHost().domainUrlPart + getFullUrlPart()
	}


	public Host getFinalHost() {
		if (!host) {
			if (!parent) {
				throw new IllegalStateException('At least parent has to have host defined');
			}
			return parent.getFinalHost();
		}
		return host;
	}

	public void setUrlPartFromText(def s) {
		def normalized = s.toLowerCase().replaceAll(/[^A-z0-9 ]/, "").replaceAll(/ +/, "-")
		this.setUrlPart('/' + normalized)
	}

	private void checkThereIsParent() {
		if (!parent) {
			throw new ParentNotFoundException('You must define parent if you want to use automatic property filling by parent')
		}
	}

	public boolean isRoot() {
		if (parent) {
			return false;
		}
		return true
	}

	public Page getRoot() {
		def currentParent = this
		while(currentParent.parent) {
			currentParent = currentParent.parent
		}
		return currentParent;
	}

	private String getRootLangPart() {
		Page root = getRoot()
		return root.langPart ? root.langPart : ''
	}

	private void regenerateUrlFromParent() {
		this.url = ''
		if (isRoot()) {
			url += getFinalHost().getFullHostDomain() + getRootLangPart() + getFullUrlPart()
		} else {
			checkThereIsParent()
			url += parent.getFinalHost().getFullHostDomain() + getRootLangPart() + getFullUrlPart()
		}

		for (Page child in subPages) {
			child.regenerateUrl();
		}
	}

	public String getFullUrlPart() {
		Page ancestor;
		if (isRoot()) {
			return urlPart;
		}
		if (urlType == UrlTypeEnum.FROM_ROOT) {
			ancestor = getRoot();
		} else {
			ancestor = parent;
		}
		if (ancestor.urlPart == '/') {
			return urlPart
		}
		return ancestor.urlPart + urlPart;
	}

	private void regenerateUrlFromRoot() {
		this.url = ''
		Page root = getRoot()
		url += root.getFinalHost().getFullHostDomain() + getRootLangPart() + root.urlPart + urlPart
	}

	public void regenerateUrl() {
		if (urlType == UrlTypeEnum.FROM_PARENT) {
			regenerateUrlFromParent()
		}
		if (urlType == UrlTypeEnum.FROM_ROOT) {
			regenerateUrlFromRoot()
		}
	}

	private void checkValues() {
		if (!urlPart.startsWith('/')) {
			throw new IllegalStateException('Url part must start with / char')
		}
		if (isRoot()) {
			if (!host) {
				throw new IllegalStateException('Root must have its host specified')
			}
		}
	}

	private void setDefaults() {
		if (!urlType) {
			urlType = UrlTypeEnum.FROM_PARENT
		}
		if (!httpMethod) {
			httpMethod = HttpMethodEnum.GET
		}
		if (!requestType) {
			requestType = RequestTypeEnum.REGULAR
		}
	}

	def beforeValidate() {
		setDefaults()
		println this.urlPart
		println 'before validate'
		println this.httpMethod
		regenerateUrl()
	}

	def beforeInsert() {
		checkValues()
	}

	def beforeUpdate() {
		checkValues()
	}
}

