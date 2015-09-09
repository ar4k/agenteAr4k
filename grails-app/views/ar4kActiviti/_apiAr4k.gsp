<g:applyCodec encodeAs="none">
${dipendenze}
</g:applyCodec>

// codice Ar4k
'use strict';

var ar4kapi = angular.module('ar4kapi', ['restangular','oc.lazyLoad']);

ar4kapi.provider('ar4kService', function() {

 	function Ar4kService($http) {
    	var valore = 8;
    	var idProcesso = '';
	
  		this.setIdProcesso = function(s) {
    		idProcesso = s;
  		};
  		
  		this.diagrammaProcessoLink = function() {
  			return "${createLink(controller:'Ar4kActiviti',action:'diagrammaProcesso',absolute:'true')}?idProcesso="+idProcesso
  		};
    
    	this.testConnessione = function() {
    		var promise = $http.get("${createLink(controller:'Ar4kActiviti',action:'testConnessione',absolute:'true')}")
    			.then(function (response) {
    				return response.data;
    			});
    		return promise
    	};
    	
    	this.variabiliAvvioProcesso = function() { 
   		var promise = $http.get("${createLink(controller:'Ar4kActiviti',action:'variabiliAvvioProcesso',absolute:'true')}?idProcesso="+idProcesso)
   			.then(function (response) {
   				return response.data.variabili;
   			});
   		return promise
    	};
    	
    	this.listaIstanze = function() { 
   		var promise = $http.get("${createLink(controller:'Ar4kActiviti',action:'listaIstanze',absolute:'true')}?idProcesso="+idProcesso)
   			.then(function (response) {
   				return response.data.istanze;
   			});
   		return promise
    	};

    	this.listaProcessi = function() { 
   		var promise = $http.get("${createLink(controller:'Ar4kActiviti',action:'listaProcessi',absolute:'true')}")
   			.then(function (response) {
   				return response.data.processi;
   			});
   		return promise
    	};
    	
    	this.listaDataCenters = function() { 
   		var promise = $http.get("${createLink(controller:'Ar4kActiviti',action:'listaDataCenters',absolute:'true')}")
   			.then(function (response) {
   				return response.data.datacenters;
   			});
   		return promise
    	};
    	
    	this.listaStore = function() { 
   		var promise = $http.get("${createLink(controller:'Ar4kActiviti',action:'listaStore',absolute:'true')}")
   			.then(function (response) {
   				return response.data.storedati;
   			});
   		return promise
    	};
    	
    	this.salvaValoreKV = function(chiave,valore) { 
   		var promise = $http.post("${createLink(controller:'Ar4kActiviti',action:'salvaValoreKV',absolute:'true')}",{chiave:chiave,valore:valore})
   			.then(function (response) {
   				return 'ok';
   			});
   		return promise
    	};
    	
    	this.avviaProcesso = function(dati) {
    	var promise = $http.post("${createLink(controller:'Ar4kActiviti',action:'avviaProcesso',absolute:'true')}",{idProcesso:idProcesso,dati:dati})
   			.then(function (response) {
   				return response;
   			});
   		return promise
    	};
 	}; 
  
  	this.$get = function($http) {
    	return new Ar4kService($http);
  	};
});

// recupera i parametri dalla URL
var getUrlParameter = function getUrlParameter(sParam) {
	var sPageURL = decodeURIComponent(window.location.search
			.substring(1)), sURLVariables = sPageURL.split('&'), sParameterName, i;

	for (i = 0; i < sURLVariables.length; i++) {
		sParameterName = sURLVariables[i].split('=');

		if (sParameterName[0] === sParam) {
			return sParameterName[1] === undefined ? true
					: sParameterName[1];
		}
	}
};
