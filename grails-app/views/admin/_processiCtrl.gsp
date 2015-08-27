'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the sbAdminApp
 */
angular.module('sbAdminApp')
  .controller('ProcessiCtrl', function($scope, $http) {
    $http.get("${createLink(controller:'admin',action:'listaProcessi',absolute:'true')}")
    .success(function (response) {$scope.processi = response.processi});
    $http.get("${createLink(controller:'documentazione',action:'servizio.md',absolute:'true')}")
    .success(function (response) {$scope.serviziHelp = response;});
    
    $scope.focusDocumentazione=false;
  });