<div ng-controller="taskCtrl">
	<div aria-hidden="false" aria-labelledby="FullScreenModal" role="dialog"
		tabindex="-1" id="fullScreenModal" class="modal fade in"
		ng-show="pannelloMain"
		style="display: block; left: 1em; right: 1em; bottom: 1em; top: 1em; border-radius: 6px; background-color: rgba(255, 255, 255, .9);">
		<div
			style="position: absolute; z-index: 1; padding: .4em; padding-right: 2em; top: 0px;"
			class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-right"
			ng-show="pannelloMain">
			<button data-dismiss="modal" ng-click="pannelloMain=false"
				class="btn btn-warning btm-sm" type="button">Chiudi</button>
		</div>
		<div class="embed-container-full">
			<iframe class="embed-iframe-full" ng-src="{{focusMain}}"></iframe>
		</div>
	</div>
	<ul class="nav navbar-top-links navbar-right">
		<li class="dropdown aggiorna-su-messaggio"><a
			class="dropdown-toggle" data-toggle="dropdown"> <i
				class="fa fa-hand-o-up fa-fw"></i> <i id="contatoreticket">{{numeroTasks}}</i>
				<i class="fa fa-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-messages">
				<li ng-repeat="task in tasks"><a href=""
					ng-click="svolgiistanza(task.id)">
					
						<i class="fa {{task.icona}} fa-fw"></i> <strong>{{task.name}}</strong> <span
								class="pull-right text-muted"> <em>{{task.description}}</em>
							</span>
				
						<span class="pull-right text-muted small">{{task.createTime}}</span>
				</a></li>
			</ul> <!-- /.dropdown-messages --></li>
		<!-- /.dropdown -->
		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown"> <i class="fa fa-tasks fa-fw"></i> <i
				id="contatoreactiviti">0</i> <i class="fa fa-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-tasks" id="messaggiactiviti">

			</ul> <!-- /.dropdown-tasks --></li>
		<!-- /.dropdown -->
		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown"> <i class="fa fa-bell fa-fw"></i> <i
				id="contatoremessaggi">0</i> <i class="fa fa-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-alerts" id="messaggisistema">
				<li><a class="text-center"
					onClick="$('.messaggioElemento').remove();document.getElementById('contatoremessaggi').textContent='0';">
						<i class="fa fa-trash-o"></i> <strong>cancella tutti i
							messaggi</strong>
				</a></li>
			</ul> <!-- /.dropdown-alerts --></li>

		<!-- /.dropdown -->

		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown"> <i class="fa fa-user fa-fw"></i> <i
				class="fa fa-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-user">
				<li><a ui-sref="dashboard.utenti"><i
						class="fa fa-users fa-fw"></i> Edita profili utenti</a></li>
				<li><a ui-sref="dashboard.utenti"><i
						class="fa fa-unlink fa-fw"></i> Riavvia l'interfaccia Java</a></li>
				<g:if env="development">
					<li><a ui-sref="dashboard.rossonet"><i
							class="fa fa-cubes fa-fw"></i> Console Grails</a></li>
					<li><g:link controller='base' absolute='true'
							target="_console">
							<i class="fa fa-trophy fa-fw"></i> Pagina base Grails</g:link></li>
				</g:if>
				<li class="divider"></li>
				<form name="submitForm" method="POST"
					action="${createLink(controller: 'logout')}">
					<li><a href="javascript:document.submitForm.submit()"
						style="color: black;"><i class="fa fa-sign-out fa-fw"></i>
							Esci</a></li>
				</form>
			</ul> <!-- /.dropdown-user --></li>
		<!-- /.dropdown -->
	</ul>

</div>
