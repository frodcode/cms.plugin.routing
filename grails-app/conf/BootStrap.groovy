import domain.routing.Host
import domain.routing.HttpMethodEnum;
import domain.routing.ModuleControl
import domain.routing.Page
import domain.routing.PageType;
import domain.routing.RegisteredCall
import domain.routing.RequestTypeEnum;
import domain.routing.UrlTypeEnum;


class BootStrap {
	def init = {
		
		Host defaultHost = new Host(protocol : 'http', domain: 'localhost', port: '8080', domainUrlPart: '/Routing')
		defaultHost.save(failOnError:true)
		
		def moduleControls = [
			ModuleControl articleMC = new ModuleControl(
				className: example.ArticleModuleControl.class.getName(),
				slug: 'article'
				),
			ModuleControl newsMC = new ModuleControl(
				className: example.NewsModuleControl.class.getName(),
				slug: 'news'
				),
			]
		moduleControls*.save();
		def pageTypes = [
			PageType homepagePageType = new PageType(
				slug : 'homepage',
				description: 'Domovská stránka',
				singleton: true,
				templateName: '/front/homepage',
				moduleControls: [articleMC],
				registeredCalls : [
					new RegisteredCall(
						moduleControl: newsMC,
						methodName: 'loadNews'
						),
					]
				),
			PageType pluginListPageType = new PageType(
				slug : 'pluginlist',
				description: 'Seznam pluginů',
				singleton: true,
				templateName: '/front/pluginlist',
				moduleControls: [articleMC],
				),
			PageType pluginDetailPageType = new PageType(
				slug : 'plugindetail',
				description: 'Detail pluginu',
				singleton: false,
				templateName: '/front/plugindetail',
				moduleControls: [articleMC],
				),
			PageType articlePageType = new PageType(
				slug : 'article',
				description: 'Článek',
				singleton: false,
				templateName: '/front/article',
				moduleControls: [articleMC],
				),
			PageType newsPageType = new PageType(
				slug : 'news',
				description: 'Novinky',
				singleton: true,
				templateName: '/front/news',
				moduleControls: [articleMC],
				registeredCalls : [
					new RegisteredCall(
						moduleControl: newsMC,
						methodName: 'loadNews'
						),
					]
				),
			]
		pageTypes*.save();
		def pages = [
			Page homepage = new Page(
				host: defaultHost,
				urlPart: '/',
				urlType: UrlTypeEnum.FROM_PARENT,
				requestType: RequestTypeEnum.REGULAR,
				httpMethod: HttpMethodEnum.GET,
				pageType: homepagePageType
				),
			
			Page plugins = new Page(
				parent: homepage,
				urlPart: '/pluginy',
				pageType: pluginListPageType
				),
			Page contentPlugin = new Page(
				parent: plugins,
				urlPart: '/content',
				pageType: pluginDetailPageType
				),
			Page routing = new Page(
				parent: plugins,
				urlPart: '/routing',
				pageType: pluginDetailPageType
				),
			Page howToBuildApp = new Page(
				parent: homepage,
				urlPart: '/jak-napsat-aplikaci',
				pageType: articlePageType
				),
			Page aboutProject = new Page(
				parent: homepage,
				urlPart: '/o-projektu',
				pageType: articlePageType
				),
			Page newsGet = new Page(
				parent: homepage,
				urlPart: '/novinky',
				pageType: newsPageType
				),
			Page newsPost = new Page(
				httpMethod: HttpMethodEnum.POST,
				parent: homepage,
				urlPart: '/novinky',
				pageType: newsPageType
				),
		]
		pages*.save();
	}
}
