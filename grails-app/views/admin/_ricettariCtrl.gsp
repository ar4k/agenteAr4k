'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the sbAdminApp
 */
angular.module('sbAdminApp')
  .controller('RicettariCtrl', function($scope, $http) {
    $http.get("${createLink(controller:'admin',action:'listaRicettari',absolute:'true')}")
    .success(function (response) {$scope.ricettari = response.ricettari;});
    
    $scope.nuovo=false;
    
    $scope.pannello=false;
    
    $scope.titolo = 'ricettario';
    
    $scope.aggiorna = function(idricettario) {
    	$http.post("${createLink(controller:'admin',action:'aggiornaRicettario',absolute:'true')}", {idricettario:idricettario})
        .success(function(data, status, headers, config) {
    		    $http.get("${createLink(controller:'admin',action:'listaRicettari',absolute:'true')}")
    			.success(function (response) {$scope.ricettari = response.ricettari;});
  		})
  		.error(function(data, status, headers, config) {
    		// called asynchronously if an error occurs
    		// or server returns response with an error status.
  		});
      };
    
    $scope.nuovoricettario = function(ricettario) {
        $http.post("${createLink(controller:'admin',action:'aggiungiRicettario',absolute:'true')}", {ricettario:ricettario})
        .success(function(data, status, headers, config) {
    		    $http.get("${createLink(controller:'admin',action:'listaRicettari',absolute:'true')}")
    			.success(function (response) {$scope.ricettari = response.ricettari;});
  		})
  		.error(function(data, status, headers, config) {
    		// called asynchronously if an error occurs
    		// or server returns response with an error status.
  		});
      };
      
    $scope.semi = function(idricettario) {
    	$scope.titolo = idricettario;
        $http.post("${createLink(controller:'admin',action:'listaSemi',absolute:'true')}", {idricettario:idricettario})
        .success(function(response) {
        	if (response != 'none') {
				$scope.elencosemi = response.semi;
				$scope.pannello = true;
			} else {
			    $scope.elencosemi = 'Nessun seme presente nel Ricettario...';
				$scope.pannello = true;
			}
  		})
  		.error(function(data, status, headers, config) {
    		// called asynchronously if an error occurs
    		// or server returns response with an error status.
  		});
      };
  });