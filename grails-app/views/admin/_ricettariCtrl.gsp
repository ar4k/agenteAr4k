'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the sbAdminApp
 */
angular.module('sbAdminApp')
  .controller('RicettariCtrl', function($scope, $http, $filter) {
    $http.post("${createLink(controller:'admin',action:'listaRicettari',absolute:'true')}")
    .success(function (response) {$scope.ricettari = response.ricettari;});
    
    $scope.nuovo=false;
    
    $scope.pannello=false;
    
    $scope.titolo = 'ricettario';
    
    $scope.aggiorna = function(idricettario) {
    	$http.post("${createLink(controller:'admin',action:'aggiornaRicettario',absolute:'true')}", {idricettario:idricettario})
        .success(function(response) {
    		    $http.post("${createLink(controller:'admin',action:'listaRicettari',absolute:'true')}")
    			.success(function (response) {$scope.ricettari = response.ricettari;});
  		})
  		.error(function(data, status, headers, config) {
    		// called asynchronously if an error occurs
    		// or server returns response with an error status.
  		});
      };
    
    $scope.nuovoricettario = function(ricettario) {
        $http.post("${createLink(controller:'admin',action:'aggiungiRicettario',absolute:'true')}", {ricettario:ricettario})
        .success(function(response) {
    		    $http.get("${createLink(controller:'admin',action:'listaRicettari',absolute:'true')}")
    			.success(function (response) {$scope.ricettari = response.ricettari;});
  		})
  		.error(function(data, status, headers, config) {
    		// called asynchronously if an error occurs
    		// or server returns response with an error status.
  		});
      };
      
    $scope.creameme = function(seme) {
        $http.post("${createLink(controller:'admin',action:'creaMeme',absolute:'true')}", {seme:seme})
        .success(function(response) {
    		// implementare il redirect alla configurazione del meme nel pannello apposito
  		})
  		.error(function(data, status, headers, config) {
    		// called asynchronously if an error occurs
    		// or server returns response with an error status.
  		});
     };
      
    $scope.semi = function(idricettario) {
        $http.post("${createLink(controller:'admin',action:'listaSemi',absolute:'true')}", {idricettario:idricettario})
        .success(function(response) {
        	if (response != 'none') {
				$scope.elencosemi = response.semi;
				$scope.titolo = 'Elenco semi in '+$filter('filter')($scope.ricettari, {idRicettario: idricettario}, true)[0].etichetta; 
				$scope.pannello = true;
			} else {
			    $scope.titolo = 'Nessun seme presente nel Ricettario...';
			    $scope.elencosemi = '';
				$scope.pannello = true;
			}
  		})
  		.error(function(data, status, headers, config) {
    		// called asynchronously if an error occurs
    		// or server returns response with an error status.
  		});
      };
  });