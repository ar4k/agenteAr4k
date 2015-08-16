<div class="row">
	<div style="margin-top: 5px;">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="text-right">
						<i class="fa fa-database fa-3" /> Dati (Blobstore JCloud)</i>
					</h3>
					<p class="text-justify" style="text-align: justify;">
						Un <strong>servizio dati</strong> AR4K corrisponde ad una connessione con un Blobstore JCloud.
					<p>
				</div>
				<div class="panel-body">
					<div class="dataTable_wrapper">
						<div class="table-responsive">
							<table class="table">
								<thead>
									<tr>
										<th>Nome</th>
										<th>Percorso</th>
										<th class="text-right">Azioni</th>
									</tr>
								</thead>
								<tbody>
									<div class="col-lg-2 text-center"></div>
									<div class="col-lg-5 text-right">
										<input placeholder="ricerca in etichetta e descrizione"
											class="form-control">
									</div>
									<tr ng-repeat="dato in storedati" ng-class-odd="'dispari'"
										ng-class-even="'pari'">
										<td>{{dato.nome}}</td>
										<td>{{dato.nome}}</td>
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