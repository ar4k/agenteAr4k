package org.ar4k


import java.io.IOException;
import java.io.InputStream;

import grails.util.Holders

import org.apache.commons.io.IOUtils

import grails.converters.JSON

import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

import org.atmosphere.cpr.AtmosphereResourceEventListenerAdapter
import org.atmosphere.cpr.Broadcaster
import org.atmosphere.cpr.DefaultBroadcaster
import org.atmosphere.cpr.Meteor

import com.jcraft.jsch.*

class DefaultMeteorHandler extends HttpServlet {
	def atmosphereMeteor = Holders.applicationContext.getBean("atmosphereMeteor")
	def sshService = Holders.applicationContext.getBean("sshService")

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String mapping
		// TODO define your mapping
		//mapping = URLDecoder.decode(request.getHeader("X-AtmosphereMeteor-Mapping"), "UTF-8")
		mapping = "/wsa/def" + request.pathInfo

		Meteor meteor = Meteor.build(request)
		Broadcaster broadcaster = atmosphereMeteor.broadcasterFactory.lookup(DefaultBroadcaster.class, mapping, true)
		meteor.addListener(new AtmosphereResourceEventListenerAdapter())
		meteor.setBroadcaster(broadcaster)
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String mapping
		//mapping = URLDecoder.decode(request.getHeader("X-AtmosphereMeteor-Mapping"), "UTF-8")
		mapping = "/wsa/def" + request.pathInfo

		Channel channel = sshService.getChannel(1)
		//sshService.setChannelOut(1,mapping)
		//InputStream input=channel.getInputStream()
		OutputStream out=channel.getOutputStream()

		def richiesta = request.getInputStream().eachLine {it}

		out << richiesta
		out.flush()

		Broadcaster broadcaster = atmosphereMeteor.broadcasterFactory.lookup(DefaultBroadcaster.class, mapping)

		//def risposta = ''

		//risposta = inputString(input)

		//broadcaster.broadcast(risposta)
		broadcaster.broadcast(richiesta)
		
		//println risposta
		println richiesta

	}
}
