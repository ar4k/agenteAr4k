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
	String etichetta = UUID.randomUUID()
	String direzione = 'L'
	HostRemoto hostRemoto
	Integer portaLocale = 22
	Integer portaTarget = 6631
	String hostLocale = '127.0.0.1'
	String hostTarget = ''
	String identificatore = ''
	Integer attiva = 0
	String stato = ''

	def salvataggio() {
		return [
			etichetta:etichetta,
			direzione:direzione,
			etichetta:hostRemoto.etichetta,
			portalocale:portaLocale,
			portatarget:portaTarget,
			hostlocale:hostLocale,
			hosttarget:hostTarget,
			identificatore:identificatore,
			attiva:attiva,
			stato:stato
		]
	}

	String crea() {
		if (direzione == 'L') {
			log.info("avvio tunnel: L:"+portaLocale+":"+hostTarget+":"+portaTarget)
			try {
				stato = sshService.addLTunnel(hostRemoto.collega(),portaLocale,hostTarget,portaTarget)
				attiva = 1
			} catch(e) {
				attiva = 0
				stato = e.toString()
				log.error(stato)
			}
			identificatore = 'L:'+portaLocale+":"+hostTarget+":"+portaTarget
		} else {
			log.info("avvio tunnel: R:"+portaTarget+":"+hostLocale+":"+portaLocale)
			try {
				sshService.addRTunnel(hostRemoto.collega(),portaTarget,hostLocale,portaLocale)
				attiva = 1
			} catch(e) {
				attiva = 0
				stato = e.toString()
				log.error(stato)
			}
			identificatore = 'R:'+portaTarget+":"+hostLocale+":"+portaLocale

		}
		return identificatore
	}

	String toString(){
		return "["+hostRemoto.etichetta+"] "+direzione+":"+hostLocale+":"+portaLocale+":"+hostTarget+":"+portaTarget
	}
}

