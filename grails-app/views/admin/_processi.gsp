<div class="row">
	<div style="margin-top: 5px;">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="text-right">
						<i class="fa fa-gears fa-3" /> Servizi (nodi/servizi Consul)
						<button style="margin: 0.1em;"
							class="btn btn-circle btn-default btn-xs" type="button"
							ng-click="focusDocumentazione=!focusDocumentazione"
							tooltip-placement="bottom"
							tooltip="visualizza la documentazione sui servizi.">
							<i class="fa fa-question"></i>
						</button>
					</h3>
					<p class="text-justify" style="text-align: justify;">
						Un <strong>servizio</strong> AR4K corrisponde ad un'unità di
						calcolo/servizio gestita dalla rete Consul.
					<p>
					<div marked="serviziHelp" ng-show="focusDocumentazione"></div>
				</div>
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th>Id</th>
										<th>Indirizzo</th>
										<th>Porta</th>
										<th>Servizio</th>
										<th>Tags</th>
										<th class="text-right">Gestione</th>
									</tr>
								</thead>
								<tbody>
									<div class="col-lg-5 text-left"></div>
									<div class="col-lg-2 text-center"></div>
									<div class="col-lg-5 text-right">
										<input placeholder="ricerca per nome servizio o tag"
											class="form-control">
									</div>
									<tr ng-repeat="processo in processi" ng-class-odd="'dispari'"
										ng-class-even="'pari'">
										<td>{{processo.processo.id}}</td>
										<td>{{processo.processo.address}}</td>
										<td>{{processo.processo.port}}</td>
										<td>{{processo.processo.service}}</td>
										<td><div ng-repeat="tag in processo.processo.tags">{{tag}}</div></td>
										<td class="text-right">
											<p>{{processo.stato}}</p>
											<p>
												<button style="margin: 0.1em;"
													class="btn btn-circle btn-info btn-xs" type="button"
													ng-click=""
													tooltip-placement="top"
													tooltip="gestisci i link e i qr per questo servizio.">
													<i class="fa fa-qrcode"></i>
												</button>
												<button style="margin: 0.1em;"
													class="btn btn-circle btn-xs" type="button"
													ng-click=""
													tooltip-placement="top"
													tooltip="copia link servizio.">
													<i class="fa fa-link"></i>
												</button>
											</p>
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