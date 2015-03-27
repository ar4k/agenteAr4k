class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
		
		"/$qrcode" {
			controller = "App"
			action = "codeQR"
			constraints { qrcode(matches:/[0-9a-f]{32}/) }
		}

        "/"(view:"/index")
		"500"(view:'/error')
		"404"(view:'/error')
		"405"(view:'/error')
	}
}
