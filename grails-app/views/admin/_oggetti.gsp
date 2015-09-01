<div class="row">
	<div style="margin-top: 5px;">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="text-right">
						<i class="fa fa-linux fa-3" /> Vasi (connessioni SSH)
						<button style="margin: 0.1em;"
							class="btn btn-circle btn-default btn-xs" type="button"
							ng-click="focusDocumentazione=!focusDocumentazione"
							tooltip-placement="bottom"
							tooltip="visualizza la documentazione sui vasi.">
							<i class="fa fa-comment"></i>
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
					<div class="dataTable_wrapper">
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th>Vaso</th>
										<th>Descrizione</th>
										<th class="text-center">Azioni</th>
										<th class="text-right">Stato</th>
									</tr>
								</thead>
								<tbody>
									<div class="col-lg-5 text-left">
										<button alt="Aggiungi un nuovo nodo SSH al contesto"
											class="btn btn-outline btn-primary" ng-hide="nuovo"
											type="button" ng-click="nuovo=true"
											tooltip-placement="bottom"
											tooltip="aggiunge un nuovo vaso. Richiede la configurazione dell'accesso SSH al nodo.">NUOVO
											VASO</button>
										<button class="btn btn-success btn-primary" ng-hide="nuovo"
											type="button" ng-click="nuovo=true"
											tooltip-placement="bottom"
											tooltip="aggiungi una risorsa di calcolo fornita da Rossonet."><i class="fa fa-shopping-cart"></i> CODICE AR4K</button>

									</div>
									<div class="col-lg-2 text-center"></div>
									<div class="col-lg-5 text-right">
										<input placeholder="ricerca in etichetta e descrizione"
											class="form-control">
									</div>
									<tr ng-repeat="vaso in vasi" ng-class-odd="'dispari'"
										ng-class-even="'pari'">
										<td>{{vaso.vaso.etichetta}}</td>
										<td>{{vaso.vaso.descrizione}}</td>
										<td class="text-center">
											<button style="margin: 0.1em;"
												class="btn btn-circle btn-info btn-xs" type="button"
												ng-click="$parent.dettagli(meme.idMeme)"
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
											<button style="margin: 0.1em;"
												class="btn btn-circle btn-xs" type="button"
												ng-click="$parent.dettagli(meme.idMeme)"
												tooltip-placement="bottom"
												tooltip="accesso SSH al vaso.">
												<i class="fa fa-slack"></i>
											</button>
											<button style="margin: 0.1em;"
												class="btn btn-circle btn-warning btn-xs" type="button"
												ng-click="$parent.dettagli(meme.idMeme)"
												tooltip-placement="bottom"
												tooltip="resetta l'interfaccia attuale e inizia il bootstrap sul nodo.">
												<i class="fa fa-trophy"></i>
											</button>
											<button style="margin: 0.1em;"
												class="btn btn-circle btn-danger btn-xs" type="button"
												ng-click="$parent.cancella(dato)" tooltip-placement="top"
												tooltip="elimina il collegamento con il nodo.">
												<i class="fa fa-trash-o"></i>
											</button>
										</td>
										<td>
											<div
												ng-class="vaso.stato[0].status.name == 'PASSING'?'panel panel-green text-right':'panel panel-red text-right'" style="margin-bottom:unset;">
												<div class="panel-heading">{{vaso.stato[0].output}}</div>
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