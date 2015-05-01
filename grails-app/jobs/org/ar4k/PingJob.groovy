/**
 * Job Quartz principale
 *
 * <p>Configuraz i job Quartz</p>
 *
 * <p style="text-justify">
 * ...</br>
 * </p>
 *
 * @author Andrea Ambrosini (Rossonet s.c.a r.l)
 * @version 0.1-alpha
 * @see org.ar4k.Interfaccia
 */

package org.ar4k


class PingJob {
	//def timeout = 5000 // execute job once in 5 seconds
	
	def interfacciaContestoService
	
	static triggers = {
		simple name: 'ping', startDelay: 60000, repeatInterval: 300000
	}

	def execute() {
		interfacciaContestoService.battito()
	}
}
