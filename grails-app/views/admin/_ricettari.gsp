
<div aria-hidden="false" aria-labelledby="Ricettario" role="dialog"
	tabindex="-1" id="ricettarioModal" class="modal fade in"
	ng-show="pannello" style="display: block; padding-right: 13px;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button aria-hidden="true" data-dismiss="modal"
					ng-click="pannello=false" class="close" type="button">×</button>
				<h4 id="RicettarioModalLabel" class="modal-title">{{titolo}}</h4>
			</div>
			<div class="modal-body container-fluid">
				<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2 div4-padding">Etichetta</div>
					<div class="col-xs-8 col-sm-8 col-md-6 col-lg-6 div4-padding">Descrizione</div>
					<div class="col-xs-6 col-sm-6 col-md-2 col-lg-2 div4-padding">Versione</div>
					<div
						class="col-xs-6 col-sm-6 col-md-2 col-lg-2 div4-padding text-right">Azioni</div>
				</div>
				<div ng-repeat="seme in elencosemi"
					ng-class-odd="'col-xs-12 col-sm-12 col-md-12 col-lg-12 dispari'"
					ng-class-even="'col-xs-12 col-sm-12 col-md-12 col-lg-12 pari'">
					<div class="col-xs-4 col-sm-4 col-md-2 col-lg-2 div4-padding">{{seme.meme.etichetta}}</div>
					<div class="col-xs-8 col-sm-8 col-md-6 col-lg-6 div4-padding">{{seme.meme.descrizione}}</div>
					<div class="col-xs-6 col-sm-6 col-md-2 col-lg-2 div4-padding">{{seme.meme.versione}}</div>
					<div
						class="col-xs-6 col-sm-6 col-md-2 col-lg-2 text-right div4-padding">
						<button class="btn btn-circle btn-xs" type="button"
							ng-click="creameme(seme.meme.idMeme)" tooltip-placement="bottom"
							tooltip="crea un meme da questo seme.">
							<i class="fa fa-flask"></i>
						</button>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button data-dismiss="modal" ng-click="pannello=false"
					class="btn btn-default" type="button">Chiudi</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>



<div class="row aggiorna-su-messaggio">
	<div style="margin-top: 5px;">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="text-right">
						<i class="fa fa-book fa-3"></i> RICETTARI
						<button style="margin: 0.1em;"
							class="btn btn-circle btn-default btn-xs" type="button"
							ng-click="focusDocumentazione=!focusDocumentazione"
							tooltip-placement="bottom"
							tooltip="visualizza la documentazione sui ricettari e i repository git.">
							<i class="fa fa-question"></i>
						</button>
					</h3>
					<p class="text-justify" style="text-align: justify;">
						Un <strong>ricettario</strong> AR4K corrisponde a un repository
						git. Il ricettario mette a disposizione i <strong>semi</strong>.
						Un seme può essere associato ad un <strong>vaso</strong> per
						diventare un <strong>meme</strong> attivo.
					<p>
					<div marked="ricettariHelp" ng-show="focusDocumentazione"></div>
				</div>
				<div class="panel-body">
					<div name="nuovo-ricettario" ng-show="nuovo"
						class="panel panel-yellow">
						<div class="panel-body">
							<form class="ng-pristine ng-valid" role="form">
								<div class="form-group">
									<input placeholder="Etichetta" ng-model="ricettario.etichetta"
										class="form-control">
								</div>
								<div class="form-group">
									<input placeholder="Descrizione"
										ng-model="ricettario.descrizione" class="form-control">
								</div>
								<div class="form-group">
									<input placeholder="URL repository GIT"
										ng-model="ricettario.repo" class="form-control">
								</div>
								<div class="form-group">
									<input placeholder="Cartella destinazione nel vaso"
										ng-model="ricettario.cartella" class="form-control">
								</div>
								<div class="form-group text-right">
									<input type="submit" class="btn btn-default"
										ng-click="nuovoricettario(ricettario);nuovo=false"
										value="Salva" /> <input type="button" class="btn btn-default"
										ng-click="nuovo=false;reset()" value="Annulla" />
								</div>
							</form>
						</div>
					</div>

					<div class="container-fluid">
						<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 text-left">
							<div
								class="col-xs-12 col-sm-6 col-md-6 col-lg-6 text-right div4-padding">
								<button class="btn btn-outline btn-primary" ng-hide="nuovo"
									type="button" ng-click="nuovo=true" tooltip-placement="bottom"
									tooltip="collega un nuovo repository git al sistema.">NUOVO
									RICETTARIO</button>
							</div>
							<div
								class="col-xs-12 col-sm-6 col-md-6 col-lg-6 text-left div4-padding">
								<button class="btn btn-success btn-primary" ng-hide="nuovo"
									type="button" ng-click="nuovo=true" tooltip-placement="bottom"
									tooltip="collega un abbonamento Rossonet Ar4k.">
									<i class="fa fa-shopping-cart"></i> CODICE AR4K
								</button>
							</div>
						</div>
						<div
							class="col-xs-12 col-sm-12 col-md-6 col-lg-6 text-right form-group input-group div4-padding">
							<input placeholder="ricerca testuale" class="form-control"
								ng-model="queryRicerca"/><span class="input-group-btn">
								<button class="btn btn-default" type="button">
									<i class="fa fa-times"></i>
								</button>
							</span>
						</div>
						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 div4-padding"><h4>Ricettario</h4></div>
							<div class="col-xs-8 col-sm-8 col-md-6 col-lg-6 div4-padding"><h4>Descrizione</h4></div>
							<div
								class="col-xs-4 col-sm-4 col-md-2 col-lg-2 text-right div4-padding"><h4>Azioni</h4></div>
							<div>
								<div ng-repeat="ricettario in ricettari | filter:queryRicerca"
									ng-class-odd="'col-xs-12 col-sm-12 col-md-12 col-lg-12 dispari'"
									ng-class-even="'col-xs-12 col-sm-12 col-md-12 col-lg-12 pari'">
									<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 div4-padding">{{ricettario.etichetta}}</div>
									<div class="col-xs-8 col-sm-8 col-md-6 col-lg-6 div4-padding">{{ricettario.descrizione}}</div>
									<div
										class="col-xs-4 col-sm-4 col-md-2 col-lg-2 text-right div4-padding">
										<!--  
											<button class="btn btn-circle btn-xs" type="button"
												ng-click="$parent.nuovo=true">
												<i class="fa  fa-pencil "></i>
											</button>
											-->
										<button class="btn btn-circle btn-success btn-xs"
											type="button"
											ng-click="$parent.semi(ricettario.idRicettario)"
											tooltip-placement="top"
											tooltip="visualizza i semi nel ricettario.">
											<i class="fa fa-eye"></i>
										</button>
										<button class="btn btn-circle btn-info btn-xs" type="button"
											ng-click="$parent.aggiorna(ricettario.idRicettario)"
											tooltip-placement="top"
											tooltip="aggiorna il repository git del ricettario e ricarica i semi.">
											<i class="fa fa-refresh"></i>
										</button>
										<button
											ng-hide="ricettario.repositoryGit.nomeCartella=='ar4k_open'"
											style="margin: 0.1em;"
											class="btn btn-circle btn-danger btn-xs" type="button"
											tooltip-placement="bottom"
											ng-click="$parent.cancella(ricettario.idRicettario)"
											tooltip="elimina il ricettario. Tutti i semi collegati saranno eliminati! il ricettario non sarà eliminato dai vasi per permettere ai memi il funzionamento.">
											<i class="fa fa-trash-o"></i>
										</button>
									</div>
								</div>

							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>