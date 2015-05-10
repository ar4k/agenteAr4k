<div aria-hidden="false" aria-labelledby="Ricettario" role="dialog"
	tabindex="-1" id="ricettarioModal" class="modal fade in"
	ng-show="pannello" style="display: block; padding-right: 13px;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button aria-hidden="true" data-dismiss="modal"
					ng-click="pannello=false" class="close" type="button">×</button>
				<h4 id="RicettarioModalLabel" class="modal-title">Semi in {{titolo}}</h4>
			</div>
			<div class="modal-body">{{elencosemi}}</div>
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
					</h3>
					<p class="text-justify" style="text-align: justify;">
						Un <strong>ricettario</strong> AR4K corrisponde a un repository
						git. Il ricettario mette a disposizione i <strong>semi</strong>.
						Un seme può essere associato ad un <strong>vaso</strong> per
						diventare un <strong>meme</strong> attivo.
					<p>
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
										<th>Etichetta</th>
										<th>Descrizione</th>
										<th class="text-right">Azioni</th>
									</tr>
								</thead>
								<tbody>
									<div class="col-lg-3 text-left">
										<button class="btn btn-outline btn-primary" ng-hide="nuovo"
											type="button" ng-click="nuovo=true">NUOVO RICETTARIO</button>
									</div>
									<div class="col-lg-4 text-center"></div>
									<div class="col-lg-5 text-right">
										<input placeholder="ricerca in etichetta e descrizione"
											class="form-control">
									</div>
									<tr ng-repeat="ricettario in ricettari"
										ng-class-odd="'dispari'" ng-class-even="'pari'">
										<td>{{ricettario.etichetta}}</td>
										<td>{{ricettario.descrizione}}</td>
										<td class="text-right">
											<button class="btn btn-circle btn-xs" type="button"
												ng-click="$parent.nuovo=true">
												<i class="fa  fa-pencil "></i>
											</button>
											<button class="btn btn-circle btn-xs" type="button"
												ng-click="$parent.semi(ricettario.idRicettario)">
												<i class="fa fa-eye"></i>
											</button>
											<button class="btn btn-circle btn-xs" type="button"
												ng-click="$parent.aggiorna(ricettario.idRicettario)">
												<i class="fa fa-refresh"></i>
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
