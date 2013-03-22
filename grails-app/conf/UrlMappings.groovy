class UrlMappings {
    static excludes = ['/admin/css/**', '/admin/img/**', '/admin/js/**']
	static mappings = {
		"/**"{
			controller = "Front"
			action = "route"
			constraints {
				// apply constraints here
			}
		}
		"500"(view:'/error')
	}
}
