<div class="row aggiorna-su-messaggio">
	<div style="margin-top: 5px;">
		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="text-right">
						<i class="fa fa-database fa-3" /> Dati (Consul KV)
						<button style="margin: 0.1em;"
							class="btn btn-circle btn-default btn-xs" type="button"
							ng-click="focusDocumentazione=!focusDocumentazione"
							tooltip-placement="bottom"
							tooltip="visualizza la documentazione sui dati memorizzati in Consul.">
							<i class="fa fa-question"></i>
						</button>
					</h3>
					<p class="text-justify" style="text-align: justify;">
						Un <strong>servizio dati</strong> AR4K corrisponde ad una
						connessione con un KV Consul.
					<p>
					<div marked="memoriaHelp" ng-show="focusDocumentazione"></div>
				</div>
				<div class="panel-body">
					<div name="nuovo-dato" ng-show="nuovo" class="panel panel-yellow">
						<div class="panel-body">
							<form class="ng-pristine ng-valid" role="form">
								<div class="form-group">
									<input placeholder="Etichetta" ng-model="chiave"
										class="form-control"value"{{chiave}}">
								</div>
								<div class="form-group">
									<!-- <textarea
										class="form-control ng-pristine ng-untouched ng-valid"
										ng-model="valore" placeholder="Valore per la chiave"
										style="width: 100%; height: 151px;">{{valore}}</textarea> -->
										<div id="jsoneditor" style="width: 100%; height: 250px;"></div>
								</div>
								<div class="form-group text-right">
									<input type="submit" class="btn btn-default"
										ng-click="salva(chiave);nuovo=false" value="Salva" />
									<input type="button" class="btn btn-default"
										ng-click="nuovo=false;reset()" value="Annulla" />
								</div>
							</form>
						</div>
					</div>

					<div class="container-fluid">



						<div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 text-left">
							<button class="btn btn-outline btn-primary" ng-hide="nuovo"
								type="button" ng-click="editor.setText(false);chiave='';nuovo=true" tooltip-placement="bottom"
								tooltip="aggiungi nuovo key value nel sistema Consul distribuito tra i nodi gestiti.">NUOVO
								VALORE</button>
							<button style="margin: 0.1em;"
								class="btn btn-circle btn-success btn-xs" type="button"
								ng-click="salvaContestoinKV()" tooltip-placement="bottom"
								tooltip="salva contesto come KV Consul.">
								<i class="fa fa-save"></i>
							</button>
						</div>
						<div
							class="col-xs-12 col-sm-12 col-md-6 col-lg-6 text-right form-group input-group div4-padding">

							<input placeholder="ricerca testuale" class="form-control"
								ng-model="queryRicerca"/><span class="input-group-btn">
								<button class="btn btn-default" type="button" ng-click="queryRicerca=''">
									<i class="fa fa-times"></i>
								</button>
							</span>
						</div>



						<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 div4-padding"><h4>Chiave</h4></div>
							<div class="col-xs-6 col-sm-6 col-md-2 col-lg-2 div4-padding"><h4>Progressivo creazione</h4></div>
							<div class="col-xs-6 col-sm-6 col-md-2 col-lg-2 div4-padding"><h4>Progressivo ultima modifica</h4></div>
							<div
								class="col-xs-12 col-sm-12 col-md-4 col-lg-4 text-right div4-padding"><h4>Azioni</h4></div>
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 div4-padding"><h4>Valore</h4></div>
						</div>


						<div ng-repeat="dato in storedati | filter:queryRicerca"
							ng-class-odd="'col-xs-12 col-sm-12 col-md-12 col-lg-12 dispari'"
							ng-class-even="'col-xs-12 col-sm-12 col-md-12 col-lg-12 pari'">
							<div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 div4-padding">{{dato.key}}</div>
							<div class="col-xs-6 col-sm-6 col-md-2 col-lg-2 div4-padding">{{dato.createIndex}}</div>
							<div class="col-xs-6 col-sm-6 col-md-2 col-lg-2 div4-padding">{{dato.modifyIndex}}</div>
							<div
								class="col-xs-12 col-sm-12 col-md-4 col-lg-4 text-right div4-padding">
								<button style="margin: 0.1em;"
									class="btn btn-circle btn-warning btn-xs" type="button"
									ng-show="dato.key.substr(0,9) == 'contesto_'"
									ng-click="$parent.salvasumaster(dato.value)" tooltip-placement="top"
									tooltip="salva il contesto sul vaso master.">
									<i class="fa fa-save"></i>
								</button>
								<button style="margin: 0.1em;"
									class="btn btn-circle btn-info btn-xs" type="button"
									ng-click="$parent.modifica(dato.key,dato.value)"
									tooltip-placement="top"
									tooltip="modifica il valore per questa chiave.">
									<i class="fa fa-edit"></i>
								</button>
								<button style="margin: 0.1em;"
									class="btn btn-circle btn-danger btn-xs" type="button"
									ng-click="$parent.cancella(dato.key)" tooltip-placement="top"
									tooltip="elimina questa chiave.">
									<i class="fa fa-trash-o"></i>
								</button>
							</div>
							<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 div4-padding">{{dato.value
								| prettyJSON}}</div>
						</div>



					</div>
				</div>
				<div class="div4-padding">
					<div class="panel panel-green">
						<div class="panel-heading text-center">Storage Dati Remoti -
							Servizi Cloud -</div>
						<div class="panel-body">
							<p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.
								Vestibulum tincidunt est vitae ultrices accumsan. Aliquam ornare
								lacus adipiscing, posuere lectus et, fringilla augue.</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
