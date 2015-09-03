/**
 * Rotte principali Apache Camel
 *
 * <p>Configurazione delle rotte Apache Camel</p>
 *
 * <p style="text-justify">
 * Strettamente collegato a CamelService</br>
 * </p>
 *
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 * @version 0.1-alpha
 * @see org.ar4k.Interfaccia
 */

package org.ar4k

import org.apache.camel.builder.RouteBuilder

class MasterCamelRoute extends RouteBuilder {
	def grailsApplication

	@Override 
	void configure() {
		def config = grailsApplication?.config
		from('activemq:topic:interfaccia.memoria').to('bean:interfacciaContestoService?method=testStampaMemoria')
		from('activemq:topic:consul.eventi').to('bean:interfacciaContestoService?method=eventiConsul')
		from('activemq:topic:activiti.eventi').to('bean:interfacciaContestoService?method=eventiActiviti')
		from('activemq:topic:jcloud.eventi').to('bean:interfacciaContestoService?method=eventiJCloud')
		//from('seda:memoria').to('bean:interfacciaContestoService?method=testStampaMemoria')
	}
}
