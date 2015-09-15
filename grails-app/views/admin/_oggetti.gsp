<div class="row">
	<div style="margin-top: 5px;">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="text-right">
						<i class="fa fa-linux fa-3" /> Vasi (connessioni SSH)
						<button style="margin: 0.1em;"
							class="btn btn-circle btn-default btn-xs" type="button"
							ng-click="focusDocumentazione=!focusDocumentazione"
							tooltip-placement="bottom"
							tooltip="visualizza la documentazione sui vasi.">
							<i class="fa fa-question"></i>
						</button>
					</h3>
					<p class="text-justify" style="text-align: justify;">
						Un <strong>vaso</strong> AR4K corrisponde a un accesso SSH su un
						nodo Unix. Nei vasi è possibile attivare i <strong>semi</strong>
						configurati nei <strong>ricettari</strong>.
					<p>
					<div marked="vasoHelp" ng-show="focusDocumentazione"></div>
				</div>
				<div class="panel-body">
					<div name="nuovo-vaso" ng-show="nuovo" class="panel panel-yellow">
						<div class="panel-body">
							<form class="ng-pristine ng-valid" role="form">
								<div class="form-group">
									<input placeholder="Etichetta" ng-model="vaso.etichetta"
										class="form-control">
								</div>
								<div class="form-group">
									<input placeholder="Descrizione" ng-model="vaso.descrizione"
										class="form-control">
								</div>
								<div class="form-group">
									<input
										placeholder="indirizzo host ( ess. 127.0.0.1 o nome.rossonet.com )"
										ng-model="vaso.hostssh" class="form-control">
								</div>
								<div class="form-group">
									<input placeholder="porta accesso ssh ( ess. 22)"
										ng-model="vaso.porta" class="form-control">
								</div>
								<div class="form-group">
									<input placeholder="utente accesso ssh ( ess. root)"
										ng-model="vaso.utente" class="form-control">
								</div>
								<div class="form-group">
									<textarea
										placeholder='copiare la chiave privata ssh comprese le linee di intestazione e chiusura "-----BEGIN RSA PRIVATE KEY-----" e "-----END RSA PRIVATE KEY-----"'
										ng-model="vaso.key" class="form-control"></textarea>
								</div>
								<div class="form-group text-right">
									<input type="submit" class="btn btn-default"
										ng-click="nuovovaso(vaso);nuovo=false" value="Salva" /> <input
										type="button" class="btn btn-default"
										ng-click="nuovo=false;reset()" value="Annulla" />
								</div>
							</form>
						</div>
					</div>
					<div class="container-fluid">



						<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 text-left">
							<div
								class="col-xs-12 col-sm-6 col-md-6 col-lg-6 text-right div4-padding">
								<button alt="Aggiungi un nuovo nodo SSH al contesto"
									class="btn btn-outline btn-primary" ng-hide="nuovo"
									type="button" ng-click="nuovo=true" tooltip-placement="bottom"
									tooltip="aggiunge un nuovo vaso. Richiede la configurazione dell'accesso SSH al nodo.">NUOVO
									VASO</button>
							</div>
							<div
								class="col-xs-12 col-sm-6 col-md-6 col-lg-6 text-left div4-padding">
								<button class="btn btn-success btn-primary" ng-hide="nuovo"
									type="button" ng-click="nuovo=true" tooltip-placement="bottom"
									tooltip="aggiungi una risorsa di calcolo fornita da Rossonet.">
									<i class="fa fa-shopping-cart"></i> CODICE AR4K
								</button>
							</div>
						</div>

						<div
							class="col-xs-12 col-sm-12 col-md-6 col-lg-6 text-right form-group input-group div4-padding">
							<input placeholder="ricerca testuale" ng-model="queryRicerca"
								class="form-control"/><span class="input-group-btn">
								<button class="btn btn-default" type="button" ng-click="queryRicerca=''">
									<i class="fa fa-times"></i>
								</button>
							</span>
						</div>

						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="col-xs-12 col-sm-12 col-md-5 col-lg-5 div4-padding"><h4>Vaso</h4></div>
							<div class="col-xs-12 col-sm-12 col-md-2 col-lg-2 div4-padding"><h4>Descrizione</h4></div>
							<div
								class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-center div4-padding"><h4>Azioni</h4></div>
							<div
								class="col-xs-6 col-sm-6 col-md-2 col-lg-2 text-right div4-padding"><h4>Stato</h4></div>
						</div>

						<div ng-repeat="vaso in vasi | filter:queryRicerca"
							ng-class-odd="'col-xs-12 col-sm-12 col-md-12 col-lg-12 dispari'"
							ng-class-even="'col-xs-12 col-sm-12 col-md-12 col-lg-12 pari'">
							<div class="col-xs-12 col-sm-12 col-md-5 col-lg-5 div4-padding">{{vaso.vaso.etichetta}}</div>
							<div class="col-xs-12 col-sm-12 col-md-2 col-lg-2 div4-padding">{{vaso.vaso.descrizione}}</div>
							<div
								class="col-xs-6 col-sm-6 col-md-3 col-lg-3 text-center div4-padding">
								<button style="margin: 0.1em;"
									class="btn btn-circle btn-info btn-xs" type="button"
									ng-click="$parent.salvacontestosuvaso(vaso.vaso.idVaso)"
									tooltip-placement="top"
									tooltip="salva il contesto attuale sul nodo.">
									<i class="fa fa-save"></i>
								</button>
								<button style="margin: 0.1em;"
									class="btn btn-circle btn-info btn-xs" type="button"
									ng-click="$parent.dettagli(meme.idMeme)"
									tooltip-placement="bottom"
									tooltip="installa e configura un'interfaccia su questo nodo.">
									<i class="fa fa-desktop "></i>
								</button>
								<button style="margin: 0.1em;" class="btn btn-circle btn-xs"
									type="button" ng-click="$parent.dettagli(meme.idMeme)"
									tooltip-placement="bottom" tooltip="accesso SSH al vaso.">
									<i class="fa fa-terminal"></i>
								</button>
								<button style="margin: 0.1em;"
									class="btn btn-circle btn-warning btn-xs" type="button"
									ng-click="$parent.resettasuvaso(vaso.vaso.idVaso)"
									tooltip-placement="bottom"
									tooltip="resetta l'interfaccia attuale e inizia il bootstrap sul nodo.">
									<i class="fa fa-bolt"></i>
								</button>
								<button style="margin: 0.1em;"
									class="btn btn-circle btn-danger btn-xs" type="button"
									ng-click="$parent.eliminavaso(vaso.vaso.idVaso)" tooltip-placement="top"
									tooltip="elimina il collegamento con il nodo." ng-hide="vaso.master">
									<i class="fa fa-trash-o"></i>
								</button>
							</div>
							<div
								class="col-xs-6 col-sm-6 col-md-2 col-lg-2 text-right div4-padding">
								<div
									ng-class="vaso.stato[0].status.name == 'PASSING'?'panel panel-green text-right':'panel panel-red text-right'"
									style="margin-bottom: unset;">
									<div class="panel-heading">{{vaso.stato[0].output}}</div>
								</div>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
</div>
