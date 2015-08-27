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
			<div class="modal-body">
				<div class="dataTable_wrapper">
					<div class="table-responsive">
						<table class="table">
							<thead>
								<tr>
									<th>Etichetta</th>
									<th>Descrizione</th>
									<th>Versione</th>
									<th class="text-right">Azioni</th>
								</tr>
							</thead>
							<tbody>
								<tr ng-repeat="seme in elencosemi" ng-class-odd="'dispari'"
									ng-class-even="'pari'">
									<td>{{seme.meme.etichetta}}</td>
									<td>{{seme.meme.descrizione}}</td>
									<td>{{seme.meme.versione}}</td>
									<td class="text-right">
										<button class="btn btn-circle btn-xs" type="button"
											ng-click="creameme(seme.meme.idMeme)"
											tooltip-placement="bottom"
											tooltip="crea un meme da questo seme.">
											<i class="fa fa-flask"></i>
										</button>
									</td>
								</tr>
							</tbody>
						</table>
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



<div class="row">
	<div style="margin-top: 5px;">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="text-right">
						<i class="fa fa-book fa-3" /> RICETTARI
						<button style="margin: 0.1em;"
							class="btn btn-circle btn-default btn-xs" type="button"
							ng-click="focusDocumentazione=!focusDocumentazione"
							tooltip-placement="bottom"
							tooltip="visualizza la documentazione sui ricettari e i repository git.">
							<i class="fa fa-comment"></i>
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
					<div class="dataTable_wrapper">
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th>Ricettario</th>
										<th>Descrizione</th>
										<th class="text-right">Azioni</th>
									</tr>
								</thead>
								<tbody>
									<div class="col-lg-5 text-left">
										<button class="btn btn-outline btn-primary" ng-hide="nuovo"
											type="button" ng-click="nuovo=true"
											tooltip-placement="bottom"
											tooltip="collega un nuovo repository git al sistema.">NUOVO
											RICETTARIO</button>
										<button class="btn btn-success btn-primary" ng-hide="nuovo"
											type="button" ng-click="nuovo=true"
											tooltip-placement="bottom"
											tooltip="collega un abbonamento Rossonet Ar4k.">AGGIUNGI
											CODICE AR4K</button>
									</div>
									<div class="col-lg-2 text-center"></div>
									<div class="col-lg-5 text-right">
										<input placeholder="filtra i vasi per parola chiave"
											class="form-control">
									</div>
									<tr ng-repeat="ricettario in ricettari"
										ng-class-odd="'dispari'" ng-class-even="'pari'">
										<td>{{ricettario.etichetta}}</td>
										<td>{{ricettario.descrizione}}</td>
										<td class="text-right">
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
											<button style="margin: 0.1em;"
												class="btn btn-circle btn-danger btn-xs" type="button"
												ng-click="$parent.cancella(dato)" tooltip-placement="bottom"
												tooltip="elimina il ricettario. Tutti i memi collegati saranno eliminati!">
												<i class="fa fa-trash-o"></i>
											</button>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
