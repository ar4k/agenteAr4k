<div>
	<div class="row">
		<div class="col-lg-12">
			<h3>Contenitori servizi attivi su AR4K</h3>
			<p class="text-justify" style="text-align: justify;">tramite
				questo pannello è possibile gestire i sistemi attivi, Server,
				installazioni cloud, account virtual box... Lorem ipsum dolor sit
				amet, consectetur adipiscing elit. Integer posuere erat a ante.
				Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer
				posuere erat a ante. Lorem ipsum dolor sit amet, consectetur
				adipiscing elit. Integer posuere erat a ante.</p>
		</div>
		<!-- /.row -->
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-primary">
					<!--<div class="panel-heading">DataTables Advanced Tables</div> -->
					<!-- /.panel-heading -->
					<div class="panel-body">
						<!-- 
				Contenuti pannello gestione oggetti
				In funzione della variabile funzione si attivano o meno
				 -->
						<div ng-controller="NewOggettoCtrl" ng-show="funzione == 'crea'">
							<form class="ng-pristine ng-valid">
								<!--  <h4 class="page-header">Aggiungi connessione SSH</h4> -->
								<div class="col-lg-4">
									
										<div class="form-group">
											<label class="control-label" for="title">Etichetta</label> <input
												id="etichetta" type="text" class="form-control"
												required="true" ng-model="oggetto.etichetta"></input>

										</div>
										<div class="form-group">
											<label class="control-label" for="title">Descrizione</label>

											<textarea id="descrizione" class="form-control"
												required="true" ng-model="oggetto.descrizione" rows="5"></textarea>

										</div>
								</div>
								<div class="col-lg-4">
									
										<div class="form-group">
											<label class="control-label" for="title">Nome Utente</label>

											<input id="utente" type="text" class="form-control"
												required="true" ng-model="oggetto.utente"></input>

										</div>

										<div class="form-group">
											<label class="control-label" for="title">Host Target</label>

											<input id="target" type="text" class="form-control"
												required="true" ng-model="oggetto.target"></input>

										</div>

										<div class="form-group">
											<label class="control-label" for="title">Porta</label> <input
												id="porta" type="text" class="form-control" required="true"
												ng-model="oggetto.porta"></input>

										</div>

										<div class="form-group">
											<label class="control-label" for="title">Password</label> <input
												id="password" type="password" class="form-control"
												required="true" ng-model="oggetto.password"></input>

										</div>
									
								</div>
								<div class="col-lg-4">
									<div class="panel panel-red">

										<div class="panel-heading text-justify"
											style="text-align: justify;">E' possibile aggiungere un
											server SSH bla, bla</div>

										<div class="panel-body">
											<div class="form-group">
												<button class="btn btn-default btn-lg btn-block" ng-click="saveOggettoSSH()">Aggiungi
													server via SSH</button>
											</div>
											<div class="form-group">
												<button class="btn btn-default btn-lg btn-block" ng-click="testSsh()">Test
													connessione SSH</button>
											</div>
											<div class="form-group">
												<button class="btn btn-danger btn-lg btn-block" type="reset" ng-click="resetNew()">Annulla</button>
											</div>
										</div>
									</div>
								</div>
							</form>
						</div>







					</div>
				</div>

				<div class="panel panel-default">
					<!--<div class="panel-heading">DataTables Advanced Tables</div> -->
					<!-- /.panel-heading -->
					<div class="panel-body">
						<div class="dataTable_wrapper">
							<p>

								<button type="button" class="btn btn-warning"
									ng-click="addOggettoAWS()">
									<b class="fa fa-cloud-upload"></b> Nuova Connessione AWS
								</button>
								<button type="button" class="btn btn-success"
									ng-click="addOggettoJcloud()">
									<b class="fa fa-dropbox"></b> Nuova Oggetto JCloud
								</button>
								<button type="button" class="btn btn-success"
									ng-click="addOggettoSSH()">
									<b class="fa fa-terminal"></b> Nuova Connessione SSH
								</button>
								<button type="button" class="btn btn-success"
									ng-click="addOggettoOpenShift()">
									<b class="fa fa-server"></b> Nuova Connessione OpenShift
								</button>
							</p>
							<table class="table table-striped table-bordered table-hover"
								id="dataTables-oggetti">
								<thead>
									<tr>
										<th>Etichetta</th>
										<th>Tipo</th>
										<th></th>
										<th></th>
										<th>Azioni</th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="oggetto in oggetti">
										<span ng-class-odd="odd" ng-class-even="even">
											<td>{{oggetto.etichetta}}</td>
											<td>{{oggetto.tipo}}</td>
											<td></td>
											<td></td>
											<td class="text-right"><a ng-click="editOggetto($index)"><i
													class="fa fa-pencil"></i></a> <a ng-click="delOggetto($index)"><i
													class="fa fa-remove"></i></a> <a ng-click="saveOggetto($index)"><i
													class="fa fa-save"></i></a></td>
										</span>
									</tr>
								</tbody>
							</table>

						</div>
					</div>
				</div>
				<!-- /.panel-body -->
			</div>
			<!-- /.panel -->
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->

	<div class="row">
		<div class="col-lg-12"></div>
	</div>
</div>