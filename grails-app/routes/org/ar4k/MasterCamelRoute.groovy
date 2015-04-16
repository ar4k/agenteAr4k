package org.ar4k

import org.apache.camel.builder.RouteBuilder

class MasterCamelRoute extends RouteBuilder {
	def grailsApplication

	@Override void configure() {
		def config = grailsApplication?.config
		// per prova Quartz
		from('seda:input').to('stream:out')
	}
}
