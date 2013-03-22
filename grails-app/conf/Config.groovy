import routing.domain.Page

// configuration for plugin testing - will not be included in the plugin zip

grails.gorm.failOnError = true

log4j = {
	// Example of changing the log pattern for the default console
	// appender:
	//
	//appenders {
	//    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
	//}

	error  'org.codehaus.groovy.grails.web.servlet',  //  controllers
			'org.codehaus.groovy.grails.web.pages', //  GSP
			'org.codehaus.groovy.grails.web.sitemesh', //  layouts
			'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
			'org.codehaus.groovy.grails.web.mapping', // URL mapping
			'org.codehaus.groovy.grails.commons', // core / classloading
			'org.codehaus.groovy.grails.plugins', // plugins
			'org.codehaus.groovy.grails.orm.hibernate', // hibernate integration
			'org.springframework',
			'org.hibernate',
			'net.sf.ehcache.hibernate'

	warn   'org.mortbay.log'
}

grails.resources.modules = {
    admincore {
        resource url:'/admin/css/bootstrap.min.css'
        resource url:'/admin/css/bootstrap-responsive.min.css'
        resource url:'/admin/js/jquery.min.js'
    }

    adminlogin {
        dependsOn 'admincore'
        resource url:'/admin/css/unicorn.login.css'
        resource url:'/admin/css/bootstrap.min.css'
        resource url:'/admin/css/bootstrap-responsive.min.css'
        resource url:'/admin/js/unicorn.login.js'
    }
    admintypical {
        dependsOn 'admincore'
        resource url:'/admin/css/fullcalendar.css'
        resource url:'/admin/css/unicorn.grey.css'
        resource url:'/admin/js/excanvas.min.js'
        resource url:'/admin/js/jquery.ui.custom.js'
        resource url:'/admin/js/bootstrap.min.js'
        resource url:'/admin/js/jquery.flot.min.js'
        resource url:'/admin/js/jquery.flot.resize.min.js'
        resource url:'/admin/js/jquery.peity.min.js'
        resource url:'/admin/js/fullcalendar.min.js'
        resource url:'/admin/js/unicorn.js'
        resource url:'/admin/js/unicorn.dashboard.js'
    }
}

//grails.resources.mappers.bundle.enabled = false
//grails.resources.uri.prefix = 'staticresource'
//grails.resources.debug=true

grails.views.default.codec="none" // none, html, base64
grails.views.gsp.encoding="UTF-8"

// Added by the Spring Security Core plugin:
grails.plugins.springsecurity.userLookup.userDomainClassName = 'routing.domain.auth.AuthUser'
grails.plugins.springsecurity.userLookup.authorityJoinClassName = 'routing.domain.auth.AuthUserAuthRole'
grails.plugins.springsecurity.authority.className = 'routing.domain.auth.AuthRole'
