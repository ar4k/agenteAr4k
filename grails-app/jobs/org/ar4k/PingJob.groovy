package org.ar4k


class PingJob {
	//def timeout = 5000 // execute job once in 5 seconds
	
	def accoppiatoreService
	
	static triggers = {
		simple name: 'ping', startDelay: 60000, repeatInterval: 60000
	}

	def execute() {
		accoppiatoreService.freeMemory()
	}
}
