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
				<img ng-src="{{focus}}" style="width: 100%;" />
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
					<div class="container-fluid">

						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="col-xs-4 col-sm-2 col-md-2 col-lg-2 div4-padding"><h4>Id</h4></div>
							<div class="col-xs-8 col-sm-4 col-md-4 col-lg-4 div4-padding"><h4>Attività</h4></div>
							<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 text-right div4-padding"><h4>Azioni</h4></div>
						</div>

						<div ng-repeat="istanza in focusMaschera" ng-class-odd="'col-xs-12 col-sm-12 col-md-12 col-lg-12 dispari'"
							ng-class-even="'col-xs-12 col-sm-12 col-md-12 col-lg-12 pari'">
							<div class="col-xs-4 col-sm-2 col-md-2 col-lg-2 div4-padding">{{istanza.id}}</div>
							<div class="col-xs-8 col-sm-4 col-md-4 col-lg-4 div4-padding">{{istanza.activityId}}</div>
							<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6 text-right div4-padding">
								<button class="btn btn-circle btn-success btn-xs" type="button"
									ng-click="svolgiistanza(istanza.id)" tooltip-placement="bottom"
									tooltip="esegui il prossimo compito per questa istanza">
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
									tooltip-placement="bottom"
									tooltip="assegna questa istanza ad un utente">
									<i class="fa fa-user"></i>
								</button>
							</div>
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

<div aria-hidden="false" aria-labelledby="AvviaMeme" role="dialog"
	tabindex="-1" id="avvioProcessoModal" class="modal fade in"
	ng-show="pannelloPlay"
	style="display: block; left: 1em; right: 1em; bottom: 1em; top: 1em; border-radius: 6px; background-color: rgba(255, 255, 255, .9);">
	<div
		style="position: absolute; z-index: 1; padding: .4em; padding-right: 2em; top: 0px;"
		class="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-right"
		ng-show="pannelloPlay">
		<button data-dismiss="modal" ng-click="pannelloPlay=false"
			class="btn btn-warning btm-sm" type="button">Chiudi</button>
	</div>
	<div class="embed-container-full">
		<iframe class="embed-iframe-full" ng-src="{{focusPlay}}"></iframe>
	</div>
</div>

<div aria-hidden="false" aria-labelledby="AzioneMeme" role="dialog"
	tabindex="-1" id="azioneMemeModal" class="modal fade in"
	ng-show="azioneMemePlay"
	style="display: block; left: 1em; right: 1em; bottom: 1em; top: 1em; border-radius: 6px; background-color: rgba(255, 255, 255, .9);">
	<div
		style="position: absolute; z-index: 1; padding: .4em; padding-right: 2em; top: 0px;"
		class="col-lg-12 col-md-12 col-sm-12 text-right"
		ng-show="azioneMemePlay">
		<button data-dismiss="modal" ng-click="azioneMemePlay=false"
			class="btn btn-warning btm-sm" type="button">Chiudi</button>
	</div>
	<div class="embed-container-full">
		<iframe class="embed-iframe-full" ng-src="{{azioneMemeFocus}}" ></iframe>
	</div>
</div>


<div class="row aggiorna-su-messaggio">
	<div style="margin-top: 5px;">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
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
					<div class="container-fluid">
						<div
							class="col-xs-12 col-sm-10 col-md-8 col-lg-6 col-lg-offset-3 col-md-offset-2 col-sm-offset-1 form-group input-group div4-padding">
							<input placeholder="ricerca testuale" ng-model="queryRicerca"
								class="form-control" /><span class="input-group-btn">
								<button class="btn btn-default" type="button">
									<i class="fa fa-times"></i>
								</button>
							</span>
						</div>


						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="col-xs-4 col-sm-4 col-md-3 col-lg-3 div4-padding">
								<h4>Meme</h4>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-3 col-lg-3 div4-padding">
								<h4>Descrizione</h4>
							</div>

							<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2 div4-padding">
								<h4>Stato</h4>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-2 col-lg-2 div4-padding">
								<h4>Funzionalità</h4>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-2 col-lg-2 div4-padding">
								<h4>Dipendenze</h4>
							</div>
							<div class="col-xs-12 col-sm-8 col-md-9 col-lg-10 div4-padding">
								<h4>Processi</h4>
							</div>
							<div
								class="col-xs-12 col-sm-4 col-md-3 col-lg-2 text-right div4-padding">
								<h4>Azioni</h4>
							</div>
						</div>



						<div ng-repeat="meme in memi | filter:queryRicerca"
							ng-class-odd="'col-xs-12 col-sm-12 col-md-12 col-lg-12 dispari'"
							ng-class-even="'col-xs-12 col-sm-12 col-md-12 col-lg-12 pari'">
							<div class="col-xs-4 col-sm-4 col-md-3 col-lg-3 div4-padding">
								<i class="fa {{meme.meme.icona}}"></i> <strong>{{meme.meme.etichetta}}</strong>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-3 col-lg-3 div4-padding">{{meme.meme.descrizione
								}}</div>

							<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2 div4-padding">{{meme.meme.stato}}</div>
							<div class="col-xs-6 col-sm-6 col-md-2 col-lg-2 div4-padding">
								<!--  -->
								&nbsp;
								<p ng-repeat="funzionalitaSingola in meme.meme.funzionalita">
									<strong>{{funzionalitaSingola}}</strong></p>
							</div>
							<div class="col-xs-6 col-sm-6 col-md-2 col-lg-2 div4-padding">
								<!--  -->
								&nbsp;
								<p ng-repeat="dipendenza in meme.meme.dipendenze">{{dipendenza}}</p>
							</div>
							<div class="col-xs-12 col-sm-8 col-md-9 col-lg-10 div4-padding">
								<div ng-repeat="processo in meme.processi">
									<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 well">
										<div class="col-xs-12 col-sm-12 col-md-7 col-lg-7"><i>{{processo.processo}}</i></div>
										<div class="col-xs-12 col-sm-12 col-md-5 col-lg-5 text-right">
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
									</div>
								</div>
							</div>
							<div
								class="col-xs-12 col-sm-4 col-md-3 col-lg-2 text-right div4-padding">
								<button style="margin: 0.1em;" class="btn btn-circle btn-xs"
									type="button" ng-click="$parent.azioneMeme(meme.meme.idMeme)"
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
							</div>
						</div>


					</div>
				</div>
			</div>
		</div>
	</div>
</div>

