<div class="row">
	<div style="margin-top: 5px;">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="text-right">
						<i class="fa fa-gears fa-3" /> Servizi (nodi/servizi Consul)
					</h3>
					<p class="text-justify" style="text-align: justify;">
						Un <strong>servizio</strong> AR4K corrisponde ad un'unità di calcolo/servizio gestita dalla rete Consul.
					<p>
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
										<th class="text-right">Azioni</th>
									</tr>
								</thead>
								<tbody>
									<div class="col-lg-2 text-center"></div>
									<div class="col-lg-5 text-right">
										<input placeholder="ricerca in etichetta e descrizione"
											class="form-control">
									</div>
									<tr ng-repeat="processo in processi" ng-class-odd="'dispari'"
										ng-class-even="'pari'">
										<td>{{processo.id}}</td>
										<td>{{processo.address}}</td>
										<td>{{processo.port}}</td>
										<td>{{processo.service}}</td>
										<td><div ng-repeat="tag in processo.tags">{{tag}}</div></td>
										<td class="text-right">
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