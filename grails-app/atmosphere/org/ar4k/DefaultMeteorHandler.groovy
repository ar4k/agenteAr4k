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
	def webSocketService = Holders.applicationContext.getBean("webSocketService")

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

		Broadcaster broadcaster = atmosphereMeteor.broadcasterFactory.lookup(DefaultBroadcaster.class, mapping)

		if (request.pathInfo == '/master') {
			//println 'ok'
			byte[] buf=new byte[1024]
			int i=0
			Channel canale = webSocketServer.post()

			InputStream richiesta = request.getInputStream()
			OutputStream out=canale.getOutputStream()

			i=richiesta.read(buf,0,1024)
			out << buf
			out.flush()
		}
	}
}