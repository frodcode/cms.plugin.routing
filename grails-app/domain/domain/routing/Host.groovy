package domain.routing

class Host {
	
	String protocol
	
	String domain
	
	String port
	
	String domainUrlPart

    static constraints = {
		domainUrlPart(nullable:true)
    }
	
	public String getFullHostDomain()
	{
		String fullHostDomain = protocol + '://' + domain + ':' + port;
		if (domainUrlPart) {
			fullHostDomain += domainUrlPart
		}
		return fullHostDomain;
	}
}
