package example

class NewsModuleControl {
	
	public def loadNews(def page) {
		println page.id
		if (page.urlPart == '/') {
			return	 [news: ['super truper', 'Extra homepage new']]
		}
		if (page.urlPart == '/novinky') {
			return [news: ['News super 1', 'News super 2']]
		}
		
		return [news: []] 
	}

}
