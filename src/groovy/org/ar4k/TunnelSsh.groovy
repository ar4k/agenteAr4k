package org.ar4k

import static groovyx.gpars.dataflow.Dataflow.task
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

import org.codehaus.groovy.grails.commons.GrailsApplication
import org.atmosphere.cpr.Broadcaster
import org.atmosphere.cpr.DefaultBroadcaster

import grails.converters.JSON
import grails.transaction.Transactional
import grails.util.Holders

import java.util.Formatter.DateTime

import com.jcraft.jsch.*

// Classi accessorie

// Tunnel SSH
class TunnelSsh {
	def sshService = Holders.applicationContext.getBean("sshService")
	String direzione = 'L'
	HostRemoto hostRemoto
	Integer portaLocale = 22
	Integer portaTarget = 6631
	String hostLocale = '127.0.0.1'
	String hostTarget = ''
	String identificatore = ''
	Integer attiva = 0

	String crea() {
		if (direzione == 'L') {
			println "avvio tunnel: L:"+portaLocale+":"+hostTarget+":"+portaTarget
			sshService.addLTunnel(hostRemoto.collega(),portaLocale,hostTarget,portaTarget)
			attiva = 1
			identificatore = 'L:'+portaLocale+":"+hostTarget+":"+portaTarget
		} else {
			println "avvio tunnel: R:"+portaTarget+":"+hostLocale+":"+portaLocale
			sshService.addRTunnel(hostRemoto.collega(),portaTarget,hostLocale,portaLocale)
			attiva = 1
			identificatore = 'R:'+portaTarget+":"+hostLocale+":"+portaLocale
		}
		hostRemoto.tunnel.add(identificatore)
		return identificatore
	}
}

