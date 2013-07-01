package frod.routing.domain

import frod.routing.domain.auth.AuthRole

class Page {

    String urlPart

    String langPart

    UrlTypeEnum urlType

    /**
     * This property should not have any setter
     * Trigger changes value
     */
//    String fullUrlPart

    /**
     * This property should not have any setter
     * Trigger changes value
     */
    String url

    HttpMethodEnum httpMethod

    RequestTypeEnum requestType

    PageType pageType

    Page parent

    Domain domain

    static mappedBy = [parent: Page]

    static hasMany = [subPages: Page, authRoles: AuthRole]

    static constraints = {
        parent(nullable: true)
        domain(nullable: true)
        urlPart(blank: true)
        subPages(nullable: true)
        url(unique: ['httpMethod', 'requestType'])
        langPart(nullable: true)
    }

    public String getLinkFrom(Page page) {
        if (domain.getUrl() != page.domain.getUrl()) {
            return url
        }
        return getRelativeLink()
    }

    public String getRelativeLink() {
        return domain.domainUrlPart + getFullUrlPart()
    }

    public boolean isRoot() {
        if (parent) {
            return false;
        }
        return true
    }

    public Page getRoot() {
        def currentParent = this
        while (currentParent.parent) {
            currentParent = currentParent.parent
        }
        return currentParent;
    }

    private String getRootLangPart() {
        Page root = getRoot()
        return root.langPart ? root.langPart : ''
    }

    public boolean requirePageAuthRole() {
        if (this.authRoles) {
            return true;
        }
        return this.parent.requirePageAuthRole();
    }

    private void checkValues() {
        if (!parent && urlType != UrlTypeEnum.ROOT) {
            throw new ParentNotFoundException(sprintf('You must define parent if you want to use automatic property filling by parent. Caused byt urlPart "%s"', urlPart))
        }
        if (!urlPart.startsWith('/')) {
            throw new IllegalStateException('Url part must start with / char')
        }
        if (isRoot()) {
            if (!domain) {
                throw new IllegalStateException('Root must have its domain specified')
            }
        }
    }

	private void regenerateUrlFromParent() {
		this.url = ''
        url += domain.getUrl() + getRootLangPart() + getFullUrlPart()
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
		url += root.domain.getUrl() + getRootLangPart() + root.urlPart + urlPart
        for (Page child in subPages) {
            child.regenerateUrl();
        }
	}

	public void regenerateUrl() {
		if (urlType == UrlTypeEnum.FROM_PARENT || urlType == UrlTypeEnum.ROOT) {
			regenerateUrlFromParent()
		}
		if (urlType == UrlTypeEnum.FROM_ROOT) {
			regenerateUrlFromRoot()
		}
	}

    def beforeValidate() {
        println this.urlPart
        println 'before validate'
        println this.httpMethod

        if (!domain && parent) {
            domain = parent.domain
        }
        if (!urlType) {
            urlType = UrlTypeEnum.FROM_PARENT
        }
        if (!requestType) {
            requestType = RequestTypeEnum.REGULAR
        }
        if (!httpMethod) {
            httpMethod = HttpMethodEnum.GET
        }
        regenerateUrl()
    }

    def beforeInsert() {
		checkValues()
	}

	def beforeUpdate() {
		checkValues()
	}
}

