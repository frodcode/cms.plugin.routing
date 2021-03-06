import domain.routing.*;

class RoutingGrailsPlugin {
    // the plugin version
    def version = "0.1"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.1 > *"
    // the other plugins this plugin depends on
    def dependsOn = [:]
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
        "grails-app/views/error.gsp"
    ]

    // TODO Fill in these fields
    def title = "Routing Plugin" // Headline display name of the plugin
    def author = "Your name"
    def authorEmail = ""
    def description = '''\
Brief summary/description of the plugin.
'''
	

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/routing"

    // Extra (optional) plugin metadata

    // License: one of 'APACHE', 'GPL2', 'GPL3'
//    def license = "APACHE"

    // Details of company behind the plugin (if there is one)
//    def organization = [ name: "My Company", url: "http://www.my-company.com/" ]

    // Any additional developers beyond the author specified above.
//    def developers = [ [ name: "Joe Bloggs", email: "joe@bloggs.net" ]]

    // Location of the plugin's issue tracker.
//    def issueManagement = [ system: "JIRA", url: "http://jira.grails.org/browse/GPMYPLUGIN" ]

    // Online location of the plugin's browseable source code.
//    def scm = [ url: "http://svn.codehaus.org/grails-plugins/" ]

    def doWithWebDescriptor = { xml ->
        // TODO Implement additions to web.xml (optional), this event occurs before
    }

    def doWithSpring = {
        routingService(routing.RoutingService)	
		callExecutor(module.CallExecutor) 
		'routing.control.RoutingModuleControl'(routing.control.RoutingModuleControl) {
			routingService = ref('routingService');
		}
		'routing.auth.AuthModuleControl'(routing.auth.AuthModuleControl) {
			
		}
		'example.NewsModuleControl'(example.NewsModuleControl)
		'example.ArticleModuleControl'(example.ArticleModuleControl)
    }

    def doWithDynamicMethods = { ctx ->
        // TODO Implement registering dynamic methods to classes (optional)
    }

    def doWithApplicationContext = { applicationContext ->
        // TODO Implement post initialization spring config (optional)
    }

    def onChange = { event ->
        // TODO Implement code that is executed when any artefact that this plugin is
        // watching is modified and reloaded. The event contains: event.source,
        // event.application, event.manager, event.ctx, and event.plugin.
    }

    def onConfigChange = { event ->
        // TODO Implement code that is executed when the project configuration changes.
        // The event is the same as for 'onChange'.
    }

    def onShutdown = { event ->
        // TODO Implement code that is executed when the application shuts down (optional)
    }
	
	public static def loadFixtures(def ctx, defaultHost) {
		def atuhModuleControl = ctx.authModuleControl
		def moduleControls = [
			ModuleControl routingMc = new ModuleControl(
			className: routing.control.RoutingModuleControl.class.getName(),
			slug: 'routing'
			),
		]
		moduleControls*.save();

		def pageTypes = [
			homepagePageType : new PageType(
			slug : 'homepage',
			description: 'Homepage',
			singleton: true,
			templateName: '/routing/front/homepage',
			moduleControls: moduleControls,
			registeredCalls : []),
		
			adminHomepagePageType : new PageType(
			slug : 'admin_homepage',
			description: 'Admin article page',
			singleton: true,
			templateName: '/routing/admin/homepage',
			moduleControls: moduleControls,
			registeredCalls : []),
		
			loginPageType : new PageType(
			slug : atuhModuleControl.loginSlug,
			description: 'Admin login page',
			singleton: true,
			templateName: '/routing/admin/login',
			moduleControls: moduleControls,
			registeredCalls : []),
		
			doLoginPageType : new PageType(
			slug : atuhModuleControl.doLoginSlug,
			description: 'Admin confirm login',
			singleton: true,
			templateName: '/routing/admin/do-login',
			moduleControls: moduleControls,
			registeredCalls : []),
		]
		pageTypes*.value*.save();
		def pages = [
			homepage : new Page(
			host: defaultHost,
			urlPart: '/',
			urlType: UrlTypeEnum.FROM_PARENT,
			requestType: RequestTypeEnum.REGULAR,
			httpMethod: HttpMethodEnum.GET,
			pageType: pageTypes.homepagePageType
			),

			adminHomepage : new Page(
			host: defaultHost,
			urlPart: '/admin',
			urlType: UrlTypeEnum.FROM_PARENT,
			requestType: RequestTypeEnum.REGULAR,
			httpMethod: HttpMethodEnum.GET,
			pageType: pageTypes.adminHomepagePageType
			),			
		]
		pages['adminLogin'] = new Page(
			parent: pages.adminHomepage,
			urlPart: '/login',
			pageType: pageTypes.loginPageType
			);
		pages['adminDoLogin'] = new Page(
			parent: pages.adminHomepage,
			urlPart: '/do-login',
			pageType: pageTypes.doLoginPageType
			);
		pages*.value*.save();
		return [pages: pages, pageTypes: pageTypes, moduleControls: [routingMc:routingMc]]
	}
	
}
