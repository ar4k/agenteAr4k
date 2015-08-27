<form name="submitForm" method="POST"
	action="${createLink(controller: 'logout')}">
	<ul class="nav navbar-top-links navbar-right">
		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown"> <i class="fa fa-envelope fa-fw"></i> <i
				class="fa fa-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-messages">
				<li><a href="#">
						<div>
							<strong>John Smith</strong> <span class="pull-right text-muted">
								<em>Ieri</em>
							</span>
						</div>
						<div>Lorem ipsum dolor sit amet, consectetur adipiscing
							elit. Pellentesque eleifend...</div>
				</a></li>
				<li class="divider"></li>
				<li><a href="#">
						<div>
							<strong>John Smith</strong> <span class="pull-right text-muted">
								<em>Ieri</em>
							</span>
						</div>
						<div>Lorem ipsum dolor sit amet, consectetur adipiscing
							elit. Pellentesque eleifend...</div>
				</a></li>
				<li class="divider"></li>
				<li><a href="#">
						<div>
							<strong>John Smith</strong> <span class="pull-right text-muted">
								<em>Ieri</em>
							</span>
						</div>
						<div>Lorem ipsum dolor sit amet, consectetur adipiscing
							elit. Pellentesque eleifend...</div>
				</a></li>
				<li class="divider"></li>
				<li><a class="text-center" href="#"> <strong>Leggi
							tutti i messaggi</strong> <i class="fa fa-angle-right"></i>
				</a></li>
			</ul> <!-- /.dropdown-messages --></li>
		<!-- /.dropdown -->
		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown"> <i class="fa fa-tasks fa-fw"></i> <i
				class="fa fa-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-tasks">
				<li><a href="#">
						<div>
							<p>
								<strong>Task 1</strong> <span class="pull-right text-muted">40%
									Completo</span>
							</p>
							<div class="progress progress-striped active">
								<div class="progress-bar progress-bar-success"
									role="progressbar" aria-valuenow="40" aria-valuemin="0"
									aria-valuemax="100" style="width: 40%">
									<span class="sr-only">40% Completo (successo)</span>
								</div>
							</div>
						</div>
				</a></li>
				<li class="divider"></li>
				<li><a href="#">
						<div>
							<p>
								<strong>Task 2</strong> <span class="pull-right text-muted">20%
									Completo</span>
							</p>
							<div class="progress progress-striped active">
								<div class="progress-bar progress-bar-info" role="progressbar"
									aria-valuenow="20" aria-valuemin="0" aria-valuemax="100"
									style="width: 20%">
									<span class="sr-only">20% Completo</span>
								</div>
							</div>
						</div>
				</a></li>
				<li class="divider"></li>
				<li><a href="#">
						<div>
							<p>
								<strong>Task 3</strong> <span class="pull-right text-muted">60%
									Completo</span>
							</p>
							<div class="progress progress-striped active">
								<div class="progress-bar progress-bar-warning"
									role="progressbar" aria-valuenow="60" aria-valuemin="0"
									aria-valuemax="100" style="width: 60%">
									<span class="sr-only">60% Completo (Attenzione)</span>
								</div>
							</div>
						</div>
				</a></li>
				<li class="divider"></li>
				<li><a href="#">
						<div>
							<p>
								<strong>Task 4</strong> <span class="pull-right text-muted">80%
									Completo</span>
							</p>
							<div class="progress progress-striped active">
								<div class="progress-bar progress-bar-danger" role="progressbar"
									aria-valuenow="80" aria-valuemin="0" aria-valuemax="100"
									style="width: 80%">
									<span class="sr-only">80% Completo (Pericolo)</span>
								</div>
							</div>
						</div>
				</a></li>
				<li class="divider"></li>
				<li><a class="text-center" href="#"> <strong>Vesualizza
							tutti i task</strong> <i class="fa fa-angle-right"></i>
				</a></li>
			</ul> <!-- /.dropdown-tasks --></li>
		<!-- /.dropdown -->
		<li class="dropdown"><a class="dropdown-toggle"
			data-toggle="dropdown"> <i class="fa fa-bell fa-fw"></i> <i
				class="fa fa-caret-down"></i>
		</a>
			<ul class="dropdown-menu dropdown-alerts">
				<li><a href="#">
						<div>
							<i class="fa fa-comment fa-fw"></i> Nuovo Commento <span
								class="pull-right text-muted small">4 minuti fa</span>
						</div>
				</a></li>
				<li class="divider"></li>
				<li><a href="#">
						<div>
							<i class="fa fa-twitter fa-fw"></i> 3 Nuovi Iscritti <span
								class="pull-right text-muted small">12 minuti fa</span>
						</div>
				</a></li>
				<li class="divider"></li>
				<li><a href="#">
						<div>
							<i class="fa fa-envelope fa-fw"></i> Messaggi Inviati <span
								class="pull-right text-muted small">4 minuti fa</span>
						</div>
				</a></li>
				<li class="divider"></li>
				<li><a href="#">
						<div>
							<i class="fa fa-tasks fa-fw"></i> Nuove Lavorazione <span
								class="pull-right text-muted small">4 minuti fa</span>
						</div>
				</a></li>
				<li class="divider"></li>
				<li><a href="#">
						<div>
							<i class="fa fa-upload fa-fw"></i> Server Riavviato <span
								class="pull-right text-muted small">4 minuti fa</span>
						</div>
				</a></li>
				<li class="divider"></li>
				<li><a class="text-center" href="#"> <strong>Vedi
							tutti gli allarmi</strong> <i class="fa fa-angle-right"></i>
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
				<li><a href="javascript:document.submitForm.submit()"><i
						class="fa fa-sign-out fa-fw"></i> Esci</a></li>
			</ul> <!-- /.dropdown-user --></li>
		<!-- /.dropdown -->
	</ul>
</form>

