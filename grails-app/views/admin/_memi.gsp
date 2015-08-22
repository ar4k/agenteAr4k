<div aria-hidden="false" aria-labelledby="Ricettario" role="dialog"
	tabindex="-1" id="ricettarioModal" class="modal fade in"
	ng-show="pannello" style="display: block; padding-right: 13px;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button aria-hidden="true" data-dismiss="modal"
					ng-click="pannello=false" class="close" type="button">×</button>
				<h4 id="memiModalLabel" class="modal-title">{{titolo}}</h4>
			</div>
			<div class="modal-body">
				<div ng-include="focus"></div>
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
						<i class="fa fa-flask fa-3" /> MEMI
					</h3>
					<p class="text-justify" style="text-align: justify;">
						Un <strong>meme</strong> AR4K è un processo in esecuzione
						permanente (servizio) o temporaneo su un <strong>vaso</strong>.
					<p>
				</div>
				<div class="panel-body">
					<!--  
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
					-->
					<div class="dataTable_wrapper">
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th></th>
										<th>Meme</th>
										<th>Descrizione</th>
										<th>Processi</th>
										<th>Stato</th>
										<th>Funzionalità</th>
										<th>Dipendenze</th>
										<!--  <th class="text-right">Azioni</th> -->
									</tr>
								</thead>
								<tbody>
									<div class="col-lg-5 text-left">
										<!--
										<button class="btn btn-outline btn-primary" ng-hide="nuovo"
											type="button" ng-click="nuovo=true">Mappa Memi</button>
										
										<button class="btn btn-success btn-primary" ng-hide="nuovo"
											type="button" ng-click="nuovo=true">AGGIUNGI CODICE
											AR4K</button>
									-->
									</div>
									<div class="col-lg-2 text-center"></div>
									<div class="col-lg-5 text-right">
										<input placeholder="ricerca in etichetta e descrizione"
											class="form-control">
									</div>
									<tr ng-repeat="meme in memi" ng-class-odd="'dispari'"
										ng-class-even="'pari'">
										<td>
											<div>
												<button style="margin: 0.1em;" class="btn btn-circle btn-xs"
													type="button" ng-click="$parent.dettagli(meme.idMeme)">
													<i class="fa {{meme.meme.icona}}"></i>
												</button>
											</div>
											<div>
												<button style="margin: 0.1em;"
													class="btn btn-circle btn-info btn-xs" type="button"
													ng-click="$parent.dettagli(meme.idMeme)">
													<i class="fa fa-qrcode"></i>
												</button>
												<button style="margin: 0.1em;"
													class="btn btn-circle btn-info btn-xs" type="button"
													ng-click="$parent.dettagli(meme.idMeme)">
													<i class="fa fa-send"></i>
												</button>
												<button style="margin: 0.1em;"
													class="btn btn-circle btn-danger btn-xs" type="button"
													ng-click="$parent.dettagli(meme.idMeme)">
													<i class="fa fa-trash-o"></i>
												</button>
											</div>
										</td>
										<td>{{meme.meme.etichetta}}</td>
										<td>{{meme.meme.descrizione }}</td>
										<td><div ng-repeat="processo in meme.processi">
												<div>
													{{processo}}
													<button style="margin: 0.1em;"
														class="btn btn-circle btn-info btn-xs" type="button"
														ng-click="$parent.dettagli(meme.idMeme)">
														<i class="fa fa-eye"></i>
													</button>
													<button style="margin: 0.1em;"
														class="btn btn-circle btn-success btn-xs" type="button"
														ng-click="$parent.dettagli(meme.idMeme)">
														<i class="fa fa-play"></i>
													</button>
												</div>
											</div></td>
										<td>{{meme.meme.stato}}</td>
										<td><div
												ng-repeat="funzionalitaSingola in meme.meme.funzionalita">{{funzionalitaSingola}}</div></td>
										<td><div ng-repeat="dipendenza in meme.meme.dipendenze">{{dipendenza}}</div></td>
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
