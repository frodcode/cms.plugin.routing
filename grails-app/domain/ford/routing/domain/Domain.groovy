package ford.routing.domain

class Domain {
	
	String protocol
	
	String host
	
	String port
	
	String domainUrlPart

    static transients = ['url', 'fullDomain']

    static constraints = {
		domainUrlPart(nullable:true)
    }
	
	public String getUrl()
	{
		String url = protocol + '://' + host + ':' + port;
		if (domainUrlPart) {
			url += domainUrlPart
		}
		return url;
	}

    public String getFullDomain() {
        return protocol + '://' + host + ':' + port + "/"
    }
}
