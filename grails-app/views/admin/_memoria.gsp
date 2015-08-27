<div class="row">
	<div style="margin-top: 5px;">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="text-right">
						<i class="fa fa-database fa-3" /> Dati (Consul KV)
						<button style="margin: 0.1em;"
							class="btn btn-circle btn-default btn-xs" type="button"
							ng-click="focusDocumentazione=!focusDocumentazione"
							tooltip-placement="bottom"
							tooltip="visualizza la documentazione sui dati memorizzati in Consul.">
							<i class="fa fa-comment"></i>
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
									<input placeholder="Etichetta" ng-model="ricettario.etichetta"
										class="form-control">
								</div>
								<div class="form-group">
									<input placeholder="Valore" ng-model="ricettario.descrizione"
										class="form-control">
								</div>
								<div class="form-group text-right">
									<input type="submit" class="btn btn-default"
										ng-click="nuovorivalore(ricettario);nuovo=false" value="Salva" />
									<input type="button" class="btn btn-default"
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
										<th>Chiave</th>
										<th>Valore</th>
										<th>Creato idx</th>
										<th>Modifica idx</th>
										<th class="text-right">Azioni</th>
									</tr>
								</thead>
								<tbody>
									<div class="col-lg-5 text-left">
										<button class="btn btn-outline btn-primary" ng-hide="nuovo"
											type="button" ng-click="nuovo=true"
											tooltip-placement="bottom"
											tooltip="aggiungi nuovo key value nel sistema Consul distribuito tra i nodi gestiti.">NUOVO
											VALORE</button>
										<button style="margin: 0.1em;"
											class="btn btn-circle btn-success btn-xs" type="button"
											ng-click="" tooltip-placement="bottom"
											tooltip="salva contesto come KV Consul.">
											<i class="fa fa-save"></i>
										</button>
									</div>
									<div class="col-lg-2 text-center"></div>
									<div class="col-lg-5 text-right">
										<input placeholder="ricerca per chiave" class="form-control">
									</div>
									<tr ng-repeat="dato in storedati" ng-class-odd="'dispari'"
										ng-class-even="'pari'">
										<td>{{dato.key}}</td>
										<td>{{dato.value}}</td>
										<td>{{dato.createIndex}}</td>
										<td>{{dato.modifyIndex}}</td>
										<td class="text-right">
											<button style="margin: 0.1em;"
												class="btn btn-circle btn-warning btn-xs" type="button"
												ng-click="$parent.cancella(dato)" tooltip-placement="top"
												tooltip="modifica il valore per questa chiave.">
												<i class="fa fa-edit"></i>
											</button>
											<button style="margin: 0.1em;"
												class="btn btn-circle btn-danger btn-xs" type="button"
												ng-click="$parent.cancella(dato)" tooltip-placement="top"
												tooltip="elimina questa chiave.">
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