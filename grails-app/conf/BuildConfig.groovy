grails.servlet.version = "3.0" // Change depending on target container compliance (2.5 or 3.0)
grails.tomcat.nio = true
grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.work.dir = "target/work"
grails.project.plugins.dir = 'target/plugins'
grails.project.target.level = 1.6
grails.project.source.level = 1.6
grails.project.war.file = "target/${appName}-${appVersion}.war"
grails.project.docs.output.dir = 'web-app/docs'

grails.server.port.http = 6630

grails.project.fork = [
	// configure settings for compilation JVM, note that if you alter the Groovy version forked compilation is required
	//  compile: [maxMemory: 256, minMemory: 64, debug: false, maxPerm: 256, daemon:true],

	// configure settings for the test-app JVM, uses the daemon by default
	test: [maxMemory: 1024, minMemory: 256, debug: false, maxPerm: 256, daemon:true],
	// configure settings for the run-app JVM
	run: [maxMemory: 1024, minMemory: 256, debug: false, maxPerm: 256, forkReserve:false],
	// configure settings for the run-war JVM
	war: [maxMemory: 1024, minMemory: 256, debug: false, maxPerm: 256, forkReserve:false],
	// configure settings for the Console UI JVM
	console: [maxMemory: 768, minMemory: 64, debug: false, maxPerm: 256]
]

grails.project.dependency.resolver = "maven" // or ivy
grails.project.dependency.resolution = {
	// inherit Grails' default dependencies
	inherits("global") {
		//excludes 'slf4j-log4j12'
		// specify dependency exclusions here; for example, uncomment this to disable ehcache:
		// excludes 'ehcache'
	}
	log "error" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
	checksums true // Whether to verify checksums on resolve
	legacyResolve true // whether to do a secondary resolve on plugin installation, not advised and here for backwards compatibility

	repositories {
		inherits true // Whether to inherit repository definitions from plugins

		grailsPlugins()
		grailsHome()
		mavenLocal()
		grailsCentral()
		mavenCentral()
		//mavenRepo "http://snapshots.repository.codehaus.org/"
		mavenRepo "http://repository.codehaus.org/"
		mavenRepo "http://download.java.net/maven/2/"
		mavenRepo "http://repository.jboss.com/maven2/"
		mavenRepo "http://repository.pentaho.org/artifactory/repo/"
		mavenRepo "https://repository.jboss.org/nexus/"
		mavenRepo 'http://repo.spring.io/milestone'
		// Per activiti
		mavenRepo "https://maven.alfresco.com/nexus/content/groups/public/"

	}

	dependencies {
		// specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes e.g.
		// runtime 'mysql:mysql-connector-java:5.1.29'
		// runtime 'org.postgresql:postgresql:9.3-1101-jdbc41'
		test "org.grails:grails-datastore-test-support:1.0.2-grails-2.4"

		// Aggiunte
		runtime 'com.jcraft:jsch:0.1.51'
		runtime ('com.openshift:openshift-java-client:2.5.0.Final') { excludes 'slf4j-log4j12' }

		//Kettle
		runtime 'pentaho-kettle:kettle-core:4.4.0-stable'
		runtime 'pentaho-kettle:kettle-db:4.4.0-stable'
		runtime 'pentaho-kettle:kettle-engine:4.4.0-stable'
		runtime 'commons-vfs:commons-vfs:1.0'
		runtime 'mysql:mysql-connector-java:5.1.27'
		runtime 'com.healthmarketscience.jackcess:jackcess:1.2.4'
		runtime 'javax.mail:mail:1.4.7'
		runtime 'net.sourceforge.jexcelapi:jxl:2.6.12'
		runtime 'commons-httpclient:commons-httpclient:3.1'

		compile 'org.jsoup:jsoup:1.8.1'
		// Vari ASP
		//compile('com.dropbox.core:dropbox-core-sdk:1.7.7')
		//compile('com.evernote:evernote-api:1.25.1')
		compile('org.apache.jclouds:jclouds-all:1.9.1')
		compile('org.apache.jclouds.labs:docker:1.9.1')

		// Per includere in Crash telnet
		//runtime 'org.crashub:crash.connectors.telnet:1.3.0-cr7'

		bundle('javax.websocket:javax.websocket-api:1.1') {
			// This line is necessary for deployment to Tomcat, since
			// Tomcat comes with its own version of javax.websocket-api.
			export = false
		}

		compile "org.atmosphere:atmosphere-runtime:2.2.3", { excludes "slf4j-api" }

		compile 'org.activiti:activiti-engine:5.18.0', { excludes "spring-beans" }
		
		compile 'org.activiti:activiti-image-generator:5.18.0', { excludes "spring-beans" }
	
		runtime ('org.activiti:activiti-spring:5.18.0') {
			excludes 'spring-context', 'spring-jdbc', 'spring-orm', 'slf4j-log4j12', 'commons-dbcp'
		}
		/*
		runtime ('org.activiti:activiti-rest:5.18.0') {
			excludes 'spring-context', 'spring-jdbc', 'spring-orm', 'slf4j-log4j12', 'commons-dbcp'
		}
		*/
		compile "com.ecwid.consul:consul-api:1.1.4"
	}

	plugins {
		// plugins for the build system only
		build ":tomcat:7.0.55"
		// build ":tomcat:8.0.15"

		// plugins for the compile step
		compile ":scaffolding:2.1.2"
		compile ':cache:1.1.8'
		compile ":asset-pipeline:1.9.9"

		// plugins needed at runtime but not for compilation
		runtime ":hibernate4:4.3.6.1" // or ":hibernate:3.6.10.18"
		runtime ":database-migration:1.4.0"
		runtime ":jquery:1.11.1"

		// Uncomment these to enable additional asset-pipeline capabilities
		//compile ":sass-asset-pipeline:1.9.0"
		//compile ":less-asset-pipeline:1.10.0"
		//compile ":coffee-asset-pipeline:1.8.0"
		//compile ":handlebars-asset-pipeline:1.3.0.3"

		//runtime ":aws-sdk:1.9.22"

		// Camel
		compile ":routing:1.4.0"
		compile ":mail:1.0.7"
		// scheduler
		compile ":quartz:1.0.2"

		// Standalone
		compile ":standalone:1.3"

		//Shell
		//compile ":crash:1.3.0"
		compile ":console:1.5.4"

		//compile ":rabbitmq-native:3.1.2"



		// autenticazione
		compile ":spring-security-core:2.0-RC4"
		compile ":spring-security-oauth:2.1.0-RC4"
		compile ":spring-security-oauth-facebook:0.2"
		compile ":spring-security-oauth-google:0.3.1"
		compile ":spring-security-oauth-linkedin:0.2"
		compile ":spring-security-oauth-twitter:0.2"
		compile ':spring-security-appinfo:2.0-RC2'
		compile ":spring-security-ui:1.0-RC2"
		//compile ":spring-security-acl:2.0-RC2"
		//compile ':spring-security-ldap:2.0-RC2'
		//compile ":spring-security-openid:2.0-RC2"

		// Icone varie
		compile ":fatcow-icons:0.1.0"

		// Aggiunge i plugin Jasper Report
		compile ":jasper:1.11.0"

		// Per HttpBuilder
		compile ":rest:0.8"

		// Socket
		compile ":atmosphere-meteor:1.0.4"

		// WebFlow
		compile ":webflow:2.1.0"
		
		runtime ":cors:1.1.8"

	}
}
