<div>
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header">Nodo Master (connessione SSH)</h1>
		</div>

	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="col-lg-6" ng-include src="'admin/terminale'"></div>

			<div class="col-lg-6">
				<h1>Stato</h1>
				<ul>
					<li>App versione: <g:meta name="app.version" /></li>
					<li>Grails versione: <g:meta name="app.grails.version" /></li>
					<li>Groovy versione: ${GroovySystem.getVersion()}</li>
					<li>JVM versione: ${System.getProperty('java.version')}</li>
					<li>
						${org.apache.catalina.util.ServerInfo.getServerInfo()}
					</li>
					<li>Reloading active: ${grails.util.Environment.reloadingAgentEnabled}</li>
					<li>Controllers: ${grailsApplication.controllerClasses.size()}</li>
					<li>Domains: ${grailsApplication.domainClasses.size()}</li>
					<li>Services: ${grailsApplication.serviceClasses.size()}</li>
					<li>Tag Libraries: ${grailsApplication.tagLibClasses.size()}</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-lg-12">
			<div class="col-lg-6">
				<h2>Controller:</h2>
				<ul>
					<g:each var="c"
						in="${grailsApplication.controllerClasses.sort { it.fullName } }">
						<li><g:link controller="${c.logicalPropertyName}">
								${c.fullName}
							</g:link></li>
					</g:each>
				</ul>
			</div>

			<div class="col-lg-6">
				<h1>Plugin Installati</h1>
				<ul>
					<g:each var="plugin"
						in="${applicationContext.getBean('pluginManager').allPlugins}">
						<li>
							${plugin.name} - ${plugin.version}
						</li>
					</g:each>
				</ul>
			</div>
		</div>
	</div>
</div>