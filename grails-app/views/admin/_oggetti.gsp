<div class="row">
	<div style="margin-top: 5px;">
		<div class="col-lg-12">
			<div class="panel panel-default">
				<div class="panel-heading">
					<h3 class="text-right">
						<i class="fa fa-linux fa-3" /> OGGETTI (vasi e oggetti collegati)
					</h3>
					<p class="text-justify" style="text-align: justify;">
						Un <strong>vaso</strong> AR4K corrisponde a un accesso SSH su un
						nodo Unix. Nei vasi è possibile attivare i <strong>semi</strong>
						configurati nei <strong>ricettari</strong>.
					<p>
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
										ng-click="nuovovaso(vaso);nuovo=false"
										value="Salva" /> <input type="button" class="btn btn-default"
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
										<th>Etichetta</th>
										<th>Descrizione</th>
										<th class="text-right" width="104px">Azioni</th>
									</tr>
								</thead>
								<tbody>
									<div class="col-lg-3 text-left">
										<button class="btn btn-outline btn-primary" ng-hide="nuovo"
											type="button" ng-click="nuovo=true">NUOVO VASO</button>

									</div>
									<div class="col-lg-4 text-center"></div>
									<div class="col-lg-5 text-right">
										<input placeholder="ricerca in etichetta e descrizione"
											class="form-control">
									</div>
									<tr ng-repeat="vaso in vasi" ng-class-odd="'dispari'"
										ng-class-even="'pari'">
										<td>{{vaso.etichetta}}</td>
										<td>{{vaso.descrizione}}</td>
										<td width="104px"
											style="border-top-right-radius: 0px; border-bottom-right-radius: 0px;"
											ng-class="{'success panel': vaso.sudo,'danger panel': !vaso.sudo}"><i
											class="fa fa-desktop"></i> <i class="fa fa-flask"></i> <i
											class="fa fa-gears"></i> <i class="fa fa-clock-o"></i> <i
											class="fa fa-bar-chart-o"></i></td>
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