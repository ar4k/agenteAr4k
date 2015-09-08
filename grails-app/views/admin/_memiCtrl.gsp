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
    
    $scope.pannelloPlay=false;
    
    $scope.azioneMemePlay=false;
    
    $scope.processfocus = '';
    
    $scope.titolo = 'meme';
    
    $scope.focus = '';
    
    $scope.focusPlay = '';
    
    $scope.azioneMemeFocus = '';
    
  	$scope.dettagli = function(idProcesso) {
  			$scope.processfocus = $scope.splitta(idProcesso,0);
  			$scope.titolo = "Diagramma processo "+$scope.processfocus;
        	$scope.focus = "${createLink(controller:'Ar4kActiviti',action:'diagrammaProcesso',absolute:'true')}?idProcesso="+idProcesso;
        	$scope.pannello = true;
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
  		
  	$scope.splitta = function(string, nb) {
    		$scope.array = string.split(':');
    		return $scope.result = $scope.array[nb];
		};
		
	$http.get("${createLink(controller:'documentazione',action:'meme.md',absolute:'true')}")
    .success(function (response) {$scope.memiHelp = response;});
    
    $scope.focusDocumentazione=false;
  });