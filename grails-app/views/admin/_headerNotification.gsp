
<ul class="nav navbar-top-links navbar-right">
	<li class="dropdown"><a class="dropdown-toggle"
		data-toggle="dropdown"> <i class="fa fa-hand-o-up fa-fw"></i> <i
			id="contatoreticket">0</i> <i class="fa fa-caret-down"></i>
	</a>
		<ul class="dropdown-menu dropdown-messages">
			<li><a href="#">
					<div>
						<strong>John Smith</strong> <span class="pull-right text-muted">
							<em>Ieri</em>
						</span>
					</div>
					<div>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
						Pellentesque eleifend...</div>
			</a></li>
			<li class="divider"></li>
			<li><a href="#">
					<div>
						<strong>John Smith</strong> <span class="pull-right text-muted">
							<em>Ieri</em>
						</span>
					</div>
					<div>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
						Pellentesque eleifend...</div>
			</a></li>
			<li class="divider"></li>
			<li><a href="#">
					<div>
						<strong>John Smith</strong> <span class="pull-right text-muted">
							<em>Ieri</em>
						</span>
					</div>
					<div>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
						Pellentesque eleifend...</div>
			</a></li>
			<li class="divider"></li>
			<li><a class="text-center" href="#"> <strong>Leggi
						tutti i messaggi</strong> <i class="fa fa-angle-right"></i>
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
			<li><a class="text-center" onClick="$('.messaggioElemento').remove();document.getElementById('contatoremessaggi').textContent='0';"> <i
					class="fa fa-trash-o"></i> <strong>cancella tutti i
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
				<li><g:link controller='base' absolute='true' target="_console">
						<i class="fa fa-trophy fa-fw"></i> Pagina base Grails</g:link></li>
			</g:if>
			<li class="divider"></li>
			<form name="submitForm" method="POST"
				action="${createLink(controller: 'logout')}">
				<li><a href="javascript:document.submitForm.submit()"
					style="color: black;"><i class="fa fa-sign-out fa-fw"></i> Esci</a></li>
			</form>
		</ul> <!-- /.dropdown-user --></li>
	<!-- /.dropdown -->
</ul>


