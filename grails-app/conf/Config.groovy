// locations to search for config files that get merged into the main config;
// config files can be ConfigSlurper scripts, Java properties files, or classes
// in the classpath in ConfigSlurper format

// grails.config.locations = [ "classpath:${appName}-config.properties",
//                             "classpath:${appName}-config.groovy",
//                             "file:${userHome}/.grails/${appName}-config.properties",
//                             "file:${userHome}/.grails/${appName}-config.groovy"]

// if (System.properties["${appName}.config.location"]) {
//    grails.config.locations << "file:" + System.properties["${appName}.config.location"]
// }
grails.config.locations = [
	"classpath:${appName}-config.properties",
	"classpath:${appName}-config.groovy",
	"file:rossonet-config.groovy",
	"file:${userHome}/.rossonet/${appName}-config.properties",
	"file:${userHome}/.rossonet/${appName}-config.groovy"
]

grails.project.groupId = appName // change this to alter the default package name and Maven publishing destination

// The ACCEPT header will not be used for content negotiation for user agents containing the following strings (defaults to the 4 major rendering engines)
grails.mime.disable.accept.header.userAgents = [
	'Gecko',
	'WebKit',
	'Presto',
	'Trident'
]
grails.mime.types = [ // the first one is the default format
	all:           '*/*', // 'all' maps to '*' or the first available format in withFormat
	atom:          'application/atom+xml',
	css:           'text/css',
	csv:           'text/csv',
	form:          'application/x-www-form-urlencoded',
	html:          [
		'text/html',
		'application/xhtml+xml'
	],
	js:            'text/javascript',
	json:          [
		'application/json',
		'text/json'
	],
	multipartForm: 'multipart/form-data',
	rss:           'application/rss+xml',
	text:          'text/plain',
	hal:           [
		'application/hal+json',
		'application/hal+xml'
	],
	xml:           [
		'text/xml',
		'application/xml']
]

//grails.server.port.http = 6661

// URL Mapping Cache Max Size, defaults to 5000
//grails.urlmapping.cache.maxsize = 1000

// Legacy setting for codec used to encode data with ${}
grails.views.default.codec = "html"

// The default scope for controllers. May be prototype, session or singleton.
// If unspecified, controllers are prototype scoped.
grails.controllers.defaultScope = 'singleton'


// GSP settings
grails {
	views {
		gsp {
			encoding = 'UTF-8'
			htmlcodec = 'xml' // use xml escaping instead of HTML4 escaping
			codecs {
				expression = 'html' // escapes values inside ${}
				scriptlet = 'html' // escapes output from scriptlets in GSPs
				taglib = 'none' // escapes output from taglibs
				staticparts = 'none' // escapes output from static template parts
			}
		}
		// escapes all not-encoded output at final stage of outputting
		// filteringCodecForContentType.'text/html' = 'html'
	}
}


grails.converters.encoding = "UTF-8"
// scaffolding templates configuration
grails.scaffolding.templates.domainSuffix = 'Instance'

// Set to false to use the new Grails 1.2 JSONBuilder in the render method
grails.json.legacy.builder = false
// enabled native2ascii conversion of i18n properties files
grails.enable.native2ascii = true
// packages to include in Spring bean scanning
grails.spring.bean.packages = []
// whether to disable processing of multi part requests
grails.web.disable.multipart=false

// request parameters to mask when logging exceptions
grails.exceptionresolver.params.exclude = ['password']

// configure auto-caching of queries by default (if false you can cache individual queries with 'cache: true')
grails.hibernate.cache.queries = false

// configure passing transaction's read-only attribute to Hibernate session, queries and criterias
// set "singleSession = false" OSIV mode in hibernate configuration after enabling
grails.hibernate.pass.readonly = false
// configure passing read-only to OSIV session by default, requires "singleSession = false" OSIV mode
grails.hibernate.osiv.readonly = false

environments {
	development {
		grails.logging.jul.usebridge = true
	}
	production {
		grails.logging.jul.usebridge = false
		// TODO: grails.serverURL = "http://www.changeme.com"
	}
}

// log4j configuration
log4j.main = {
	// Example of changing the log pattern for the default console appender:
	//
	//appenders {
	//    console name:'stdout', layout:pattern(conversionPattern: '%c{2} %m%n')
	//}

	error  'org.codehaus.groovy.grails.web.servlet',        // controllers
			'org.codehaus.groovy.grails.web.pages',          // GSP
			'org.codehaus.groovy.grails.web.sitemesh',       // layouts
			'org.codehaus.groovy.grails.web.mapping.filter', // URL mapping
			'org.codehaus.groovy.grails.web.mapping',        // URL mapping
			'org.codehaus.groovy.grails.commons',            // core / classloading
			'org.codehaus.groovy.grails.plugins',            // plugins
			'org.codehaus.groovy.grails.orm.hibernate',      // hibernate integration
			'org.springframework',
			'org.hibernate',
			'net.sf.ehcache.hibernate'
}

plugin.crash.config = [
	'crash.ssh.port': 2666,
	'crash.auth.simple.password': 'secretPassword'
]

///////////////////////////////////////////////////////////////////////////
///////////// INIZIO CONFIGURAZIONI ROSSONET //////////////////////////////
///////////////////////////////////////////////////////////////////////////

// Configurazioni Kettle
kettle.repository = "GrailsKettleRepo"
kettle.username = ""
kettle.password = ""
kettle.directory = "kettle"
kettle.repoConfFile = "kettleRepository.xml"
kettle.repoFS = "si" // Repository su filesytem in caso contrario su database


// Utente
demoUser.utente="admin" // Se vuoto non viene creato
demoUser.password="rossonet2012"

// Olark
olark.key='1445-771-10-6904'
google.analytics='UA-55822070-1'

// Facebook sdk plugin
//grails.plugin.facebooksdk.appPermissions = "email,read_friendlists,read_insights,read_mailbox,read_requests,read_stream,xmpp_login,user_online_presence,friends_online_presence,ads_management,create_event,manage_friendlists,manage_notifications,publish_actions,publish_stream,rsvp_event,user_actions.music,user_actions.news,user_actions.video,user_games_activity,friends_actions.news,friends_actions.music,friends_actions.video,friends_games_activity,user_about_me,friends_about_me,user_activities,user_birthday,user_checkins,user_education_history,user_events,user_groups,user_hometown,user_interests,user_likes,user_location,user_notes,user_photos,user_questions,user_relationships,user_relationship_details,user_religion_politics,user_status,user_subscriptions,user_videos,user_website,user_work_history,friends_work_history,friends_website,friends_videos,friends_subscriptions,friends_status,friends_religion_politics,friends_relationship_details,friends_relationships,friends_questions,friends_photos,friends_notes,friends_location,friends_likes,friends_interests,friends_hometown,friends_groups,friends_events,friends_education_history,friends_checkins,friends_birthday,friends_activities,friends_about_me"
environments {
	development {
		grails.plugin.facebooksdk.appId = 33333333333
		grails.plugin.facebooksdk.appPermissions = "email"
		grails.plugin.facebooksdk.appSecret = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
	}
	production {
		grails.plugin.facebooksdk.appId = 33333333333
		grails.plugin.facebooksdk.appPermissions = "email"
		grails.plugin.facebooksdk.appSecret = "xxxxxxxxxxxxxxxxxxxxxxxxxxxxx"
		//grails.plugins.activemq.active = false
	}
}

// Account globali AWS Amazon
grails.plugin.awssdk.accessKey = "ACCESS_KEY"
grails.plugin.awssdk.secretKey = "SECRET_KEY"

// Per mappe google
googleMap.key = "xxxxxxxxxxxxxxxxxxxxx"
googleMap.demo0 = "via Farsetti 7, 40026 Imola, Bologna"
googleMap.demo1 = "Stazione Imola, Bologna"
googleMap.demo2 = "Via Mazzini 30, 40026 Imola, Bologna"

oauth {
	providers {
		twitter {
			api = org.scribe.builder.api.TwitterApi
			key = 'oauth_twitter_key'
			secret = 'oauth_twitter_secret'
			successUri = '/oauth/twitter/success'
			failureUri = '/oauth/twitter/error'
			//callback = "${baseURL}/oauth/twitter/callback"
			callback = "http://localhost:8080/AgenteAr4k/oauth/twitter/callback"
		}
		linkedin {
			api = org.scribe.builder.api.LinkedInApi
			key = 'oauth_linkedin_key'
			secret = 'oauth_linkedin_secret'
			successUri = '/oauth/linkedin/success'
			failureUri = '/oauth/linkedin/error'
			//callback = "${baseURL}/oauth/linkedin/callback"
			callback = "http://localhost:8080/AgenteAr4k/oauth/linkedin/callback"
		}
		google {
			api = org.grails.plugin.springsecurity.oauth.GoogleApi20
			//api = org.scribe.builder.api.GoogleApi
			key = 'oauth_google_key'
			secret = 'oauth_google_secret'
			successUri = '/oauth/google/success'
			failureUri = '/oauth/google/error'
			//callback = "${baseURL}/oauth/google/callback"
			callback = "http://localhost:8080/AgenteAr4k/oauth/google/callback"
			scope = 'https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email'
		}
		facebook {
			api = org.scribe.builder.api.FacebookApi
			key = 'oauth_facebook_key'
			secret = 'oauth_facebook_secret'
			successUri = '/oauth/facebook/success'
			failureUri = '/oauth/facebook/error'
			//callback = "${baseURL}/oauth/facebook/callback"
			callback = "http://localhost:8080/AgenteAr4k/oauth/facebook/callback"
		}
	}
}

// Configurazione invio mail
grails {
	mail {
		host = "smtp.gmail.com"
		port = 465
		username = "youracount@gmail.com"
		password = "yourpassword"
		props = ["mail.smtp.auth":"true",
			"mail.smtp.socketFactory.port":"465",
			"mail.smtp.socketFactory.class":"javax.net.ssl.SSLSocketFactory",
			"mail.smtp.socketFactory.fallback":"false"]
	}
}

simpleCaptcha { chars = "ABCDEFGHIJKLMNPQRSTUVWXYZ23456789" }

grails.camel.camelContextId = 'ar4k'

grails.plugins.activemq.useJmx=true
grails.plugins.activemq.persistent=false
// toglie activemq integrato
// grails.plugins.activemq.active = false

///////////////////////////////////////////////////////////////////////////
///////////// FINE CONFIGURAZIONI ROSSONET ////////////////////////////////
///////////////////////////////////////////////////////////////////////////



// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'org.ar4k.Utente'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'org.ar4k.UtenteRuolo'
grails.plugin.springsecurity.authority.className = 'org.ar4k.Ruolo'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	'/':                              ['permitAll'],
	'/index':                         ['permitAll'],
	'/index.gsp':                     ['permitAll'],
	'/assets/**':                     ['permitAll'],
	'/**/js/**':                      ['permitAll'],
	'/**/css/**':                     ['permitAll'],
	'/**/images/**':                  ['permitAll'],
	// by Andrea Ambrosini
	'/register/**':                   ['permitAll'],
	'/login/**':     		  ['permitAll'],
	'/logout/**':                     ['permitAll'],
	'/oauth/**':                      ['permitAll'],
	'/atterraggio/**':                ['permitAll'],
	'/springSecurityOAuth/**':	  ['permitAll'],
	'/app/codeqr':			  ['permitAll'],
	// Accesso a Admin -per utente demo-
	'/**':                            ['ROLE_ADMIN'],
	//'/admin/**':                    ['ROLE_USER'],
	'/admin/**':                      [
		'ROLE_USER',
		'ROLE_REGISTRATO'
	],
	// Accesso a tutti autenticati
	//'/**':                          ['IS_AUTHENTICATED_REMEMBERED'],
	// Accesso a tutti
	//'/**':                          ['permitAll'],
	// favicon
	'/**/favicon.ico':                ['permitAll']]


// Added by the Spring Security OAuth plugin:
grails.plugin.springsecurity.oauth.domainClass = 'org.ar4k.OAuthID'

// Testo per registrazione
grails.plugin.springsecurity.ui.register.emailBody = 'Benvenuto $user,<br>per completare la procedura di registrazione in AR4K selezionare <a href="$url">questo link</a>.<br><br><bold>BOT AR4K</bold><br>(sistema automatico)'
grails.plugin.springsecurity.ui.register.emailSubject = 'Completa la registrazione in AR4K'
grails.plugin.springsecurity.ui.register.defaultRoleNames = ['ROLE_REGISTRATO']

grails.plugin.springsecurity.ui.forgotPassword.emailBody = 'Salve $user,<br>per completare la procedura di cambio della password AR4K selezionare <a href="$url">questo link</a>.<br><br><bold>BOT AR4K</bold><br>(sistema automatico)'
grails.plugin.springsecurity.ui.forgotPassword.emailSubject = 'Completa il reset della password AR4K'

