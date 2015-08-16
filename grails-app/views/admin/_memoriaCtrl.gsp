'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the sbAdminApp
 */
angular.module('sbAdminApp')
  .controller('MemoriaCtrl', function($scope, $http) {
    $http.get("${createLink(controller:'admin',action:'listaStore',absolute:'true')}")
    .success(function (response) {$scope.storedati = response.storedati});
  });