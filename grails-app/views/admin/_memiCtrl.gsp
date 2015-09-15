'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the sbAdminApp
 */
angular.module('sbAdminApp')
  .controller('MemiCtrl', function($scope,$http,$filter) {
  
    $http.post("${createLink(controller:'admin',action:'listaMemi',absolute:'true')}")
    .success(function (response) {$scope.memi = response.memi;});
    
    $scope.nuovo=false;
    
    $scope.pannello=false;
    
    $scope.pannelloMaschera=false;
    
    $scope.pannelloPlay=false;
    
    $scope.azioneMemePlay=false;
    
    $scope.processfocus = '';
    
    $scope.titolo = 'meme';
    
    $scope.titoloMaschera = 'istanze';
    
    $scope.focus = '';
    
    $scope.focusPlay = '';
    
    $scope.azioneMemeFocus = '';
    
    $scope.currentPage = 1;
    $scope.entryLimit = 5; // items per page
    $scope.totalItems = 0;
        
  	$scope.dettagli = function(idProcesso) {
  			$scope.processfocus = $scope.splitta(idProcesso,0);
  			$scope.titolo = "Diagramma processo "+$scope.processfocus;
        	$scope.focus = "${createLink(controller:'Ar4kActiviti',action:'diagrammaProcesso',absolute:'true')}?idProcesso="+idProcesso;
        	$scope.pannello = true;
  		};
  		
  	$scope.maschera = function(idProcesso) {
  			$scope.processfocus = $scope.splitta(idProcesso,0);
  			$scope.titoloMaschera = "Istanze attive di "+$scope.processfocus;
        	$http.get("${createLink(controller:'Ar4kActiviti',action:'listaIstanze',absolute:'true')}?idProcesso="+idProcesso).success(function (response) {
        		$scope.focusMaschera = response.istanze;
			    // Per task list
				$scope.totalItems = $scope.focusMaschera.length;
				//$scope.noOfPages = Math.ceil($scope.totalItems / $scope.entryLimit);
        	});
        	$scope.pannelloMaschera = true;
  	};
  	
    $scope.aggiornaDaMessaggio = function() {
    		$http.post("${createLink(controller:'admin',action:'listaMemi',absolute:'true')}")
    		.success(function (response) {$scope.memi = response.memi;});
  		};
  		
  	$scope.mascheraNuovo = function(idProcesso) {
  			$scope.processfocus = $scope.splitta(idProcesso,0);
  			$scope.titolo = "Nuova istanza di "+idProcesso;
        	$scope.focusPlay = "${createLink(controller:'Ar4kActiviti',action:'avvioProcessoForm',absolute:'true')}?idProcesso="+idProcesso;
        	$scope.pannelloPlay = true;
  		};
  		
  	$scope.azioneMeme = function(idMeme) {
        	$scope.azioneMemeFocus = "${createLink(controller:'Ar4kActiviti',action:'mascheraMeme',absolute:'true')}?idMeme="+idMeme;
        	$scope.azioneMemePlay = true;
  		};
  		
  	$scope.cancellaMeme = function(idMeme) {
        	$scope.azioneMemeFocus = "${createLink(controller:'Ar4kActiviti',action:'cancellaMeme',absolute:'true')}?idMeme="+idMeme;
        	$scope.azioneMemePlay = true;
  		};
  		
  	$scope.splitta = function(string, nb) {
    		$scope.array = string.split(':');
    		return $scope.result = $scope.array[nb];
		};
		
	$http.get("${createLink(controller:'documentazione',action:'meme.md',absolute:'true')}")
    	.success(function (response) {$scope.memiHelp = response;});
    
    $scope.focusDocumentazione=false;
    
    $scope.svolgiistanza = function(idIstanza) {
    	  	$scope.titolo = "Esegui il compito "+idIstanza;
        	$scope.focusPlay = "${createLink(controller:'Ar4kActiviti',action:'taskProcessoForm',absolute:'true')}?idTask="+idIstanza;
        	$scope.pannelloPlay = true;
    }
    
    $scope.setPage = function (pageNo) {
    	$scope.currentPage = pageNo;
  	};
  })
  .filter('startFrom', function () {
	return function (input, start) {
		if (input) {
			start = +start;
			return input.slice(start);
		}
		return [];
	};
  });