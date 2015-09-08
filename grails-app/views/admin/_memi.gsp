<div aria-hidden="false" aria-labelledby="Meme" role="dialog"
	tabindex="-1" id="memeModal" class="modal fade in" ng-show="pannello"
	style="display: block; padding-right: 13px;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button aria-hidden="true" data-dismiss="modal"
					ng-click="pannello=false" class="close" type="button">×</button>
				<h4 id="memiModalLabel" class="modal-title">{{titolo}}</h4>
			</div>
			<div class="modal-body">
				<img ng-src="{{focus}}" />
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

<div aria-hidden="false" aria-labelledby="Istanze" role="dialog"
	tabindex="-1" id="istanzeModal" class="modal fade in"
	ng-show="pannelloMaschera" style="display: block; padding-right: 13px">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button aria-hidden="true" data-dismiss="modal"
					ng-click="pannelloMaschera=false" class="close" type="button">×</button>
				<h4 id="memiModalLabel" class="modal-title">{{titoloMaschera}}</h4>
			</div>
			<div class="modal-body">
				<div class="modal-body">
					<div class="dataTable_wrapper">
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th>Id</th>
										<th>Attività</th>
										<th class="text-right">Azioni</th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="istanza in focusMaschera"
										ng-class-odd="'dispari'" ng-class-even="'pari'">
										<td>{{istanza.id}}</td>
										<td>{{istanza.activityId}}</td>
										<td class="text-right">
											<button class="btn btn-circle btn-success btn-xs" type="button"
												ng-click="svolgiistanza(istanza.id)"
												tooltip-placement="bottom" tooltip="esegui il prossimo compito per questa istanza">
												<i class="fa fa-play"></i>
											</button>
											<button class="btn btn-circle btn-warning btn-xs" type="button"
												ng-click="sospendiistanza(istanza.id)"
												tooltip-placement="bottom" tooltip="sospendi questa istanza">
												<i class="fa fa-pause"></i>
											</button>
											<button class="btn btn-circle btn-danger btn-xs" type="button"
												ng-click="eliminaistanza(istanza.id)"
												tooltip-placement="bottom" tooltip="cancella questa istanza">
												<i class="fa fa-stop"></i>
											</button>
											<button class="btn btn-circle btn-info btn-xs" type="button"
												ng-click="assegnaistanza(istanza.id)"
												tooltip-placement="bottom" tooltip="assegna questa istanza ad un utente">
												<i class="fa fa-user"></i>
											</button>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div class="modal-footer">
				<button data-dismiss="modal" ng-click="pannelloMaschera=false"
					class="btn btn-default" type="button">Chiudi</button>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>

<div aria-hidden="false" aria-labelledby="Meme" role="dialog"
	tabindex="-1" id="avvioProcessoModal" class="modal fade in"
	ng-show="pannelloPlay"
	style="display: block; left: 9em; right: 9em; bottom: 1em; top: 1em; border-radius: 6px; background-color: rgba(255, 255, 255, .9);">
	<div
		style="position: absolute; z-index: 1; padding: .4em; padding-right: 2em; top: 0px;"
		class="col-lg-12 col-md-12 col-sm-12 text-right"
		ng-show="pannelloPlay">
		<button data-dismiss="modal" ng-click="pannelloPlay=false"
			class="btn btn-warning btm-sm" type="button">Chiudi</button>
	</div>
	<div class="embed-responsive embed-responsive-16by9">
		<iframe class="embed-responsive-item" ng-src="{{focusPlay}}"
			style="bottom: 10px"></iframe>
	</div>
</div>

<div aria-hidden="false" aria-labelledby="Meme" role="dialog"
	tabindex="-1" id="azioneMemeModal" class="modal fade in"
	ng-show="azioneMemePlay"
	style="display: block; left: 9em; right: 9em; bottom: 1em; top: 1em; border-radius: 6px; background-color: rgba(255, 255, 255, .9);">
	<div
		style="position: absolute; z-index: 1; padding: .4em; padding-right: 2em; top: 0px;"
		class="col-lg-12 col-md-12 col-sm-12 text-right"
		ng-show="azioneMemePlay">
		<button data-dismiss="modal" ng-click="azioneMemePlay=false"
			class="btn btn-warning btm-sm" type="button">Chiudi</button>
	</div>
	<div class="embed-responsive embed-responsive-16by9">
		<iframe class="embed-responsive-item" ng-src="{{azioneMemeFocus}}"
			style="bottom: 10px"></iframe>
	</div>
</div>


<div class="row aggiorna-su-messaggio">
	<div style="margin-top: 5px;">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="text-right">
						<i class="fa fa-flask fa-3" /> MEMI
						<button style="margin: 0.1em;"
							class="btn btn-circle btn-default btn-xs" type="button"
							ng-click="focusDocumentazione=!focusDocumentazione"
							tooltip-placement="bottom"
							tooltip="visualizza la documentazione sui memi.">
							<i class="fa fa-question"></i>
						</button>
					</h3>
					<p class="text-justify" style="text-align: justify;">
						Un <strong>meme</strong> AR4K è un processo in esecuzione
						permanente (servizio) o temporaneo su un <strong>vaso</strong>.
					<p>
					<div marked="memiHelp" ng-show="focusDocumentazione"></div>
				</div>
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th>Meme</th>
										<th>Descrizione</th>
										<th>Processi</th>
										<th>Stato</th>
										<th>Funzionalità</th>
										<th>Dipendenze</th>
										<th class="text-right">Azioni</th>
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
										<input
											placeholder="ricerca in tutti i parametri e le definizioni"
											class="form-control">
									</div>
									<tr ng-repeat="meme in memi" ng-class-odd="'dispari'"
										ng-class-even="'pari'">
										<td><i class="fa {{meme.meme.icona}}"></i>
											{{meme.meme.etichetta}}</td>
										<td>{{meme.meme.descrizione }}</td>
										<td><div ng-repeat="processo in meme.processi">
												<div>
													{{processo.processo}}
													<button style="margin: 0.1em;"
														class="btn btn-circle btn-warning btn-xs" type="button"
														ng-click="$parent.maschera(processo.processo)"
														tooltip-placement="bottom"
														tooltip="visualizza le istanze attive per questo processo.">
														<i>{{processo.istanze}}</i>
													</button>
													<button style="margin: 0.1em;"
														class="btn btn-circle btn-info btn-xs" type="button"
														ng-click="$parent.dettagli(processo.processo)"
														tooltip-placement="top"
														tooltip="visualizza la definizione del processo.">
														<i class="fa fa-eye"></i>
													</button>
													<button style="margin: 0.1em;"
														class="btn btn-circle btn-success btn-xs" type="button"
														ng-click="$parent.mascheraNuovo(processo.processo)"
														tooltip-placement="bottom"
														tooltip="crea una nuova istanza per questo processo.">
														<i class="fa fa-play"></i>
													</button>
												</div>
											</div></td>
										<td>{{meme.meme.stato}}</td>
										<td><div
												ng-repeat="funzionalitaSingola in meme.meme.funzionalita">{{funzionalitaSingola}}</div></td>
										<td><div ng-repeat="dipendenza in meme.meme.dipendenze">{{dipendenza}}</div></td>
										<td class="text-right">
											<button style="margin: 0.1em;" class="btn btn-circle btn-xs"
												type="button"
												ng-click="$parent.azioneMeme(meme.meme.idMeme)"
												tooltip-placement="top" tooltip="{{meme.calcolati.tooltip}}">
												<i class="fa {{meme.calcolati.iconaStato}}"></i>
											</button>
											<button style="margin: 0.1em;"
												class="btn btn-circle btn-info btn-xs" type="button"
												ng-click="$parent.azioneMeme(meme.meme.idMeme)"
												tooltip-placement="top"
												tooltip="gestisci i link e i qr per questo meme.">
												<i class="fa fa-qrcode"></i>
											</button>
											<button style="margin: 0.1em;"
												class="btn btn-circle btn-danger btn-xs" type="button"
												ng-click="$parent.azioneMeme(meme.idMeme)"
												tooltip-placement="bottom"
												tooltip="elimina questo meme, i processi e le istanze collegate ad esso.">
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

