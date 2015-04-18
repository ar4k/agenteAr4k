'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:MainCtrl
 * @description # MainCtrl Controller of the sbAdminApp
 */
angular.module('sbAdminApp').controller('OggettoCtrl',function($scope, $http) {

			var load = function() {
				console.log('chiamata http...');
				$http.get('${createLink(controller:'admin',action: 'listaoggetti')}').success(
						function(data, status, headers, config) {
							$scope.oggetti = data;
							angular.copy($scope.oggetti, $scope.copy);
						});
			}
			
			load();			
			
			$scope.addOggettoSSH = function() {
    			console.log('call addOggettoSSH');
    			$scope.funzione='crea';
    		}
    		
    		$scope.editOggetto = function(index) {
   				 console.log('call editOggeto');
   				 $location.path('/edit/' + $scope.oggetti[index].id);
			}
    		
    		
    		
		});


angular.module('sbAdminApp').controller('NewOggettoCtrl', function($scope, $http) {

$scope.oggetto = {};

$scope.saveOggettoSSH = function() {
    console.log('call saveOggettoSSH');
    $http
     .post('${createLink(controller:'admin',action: 'salvassh')}',$scope.oggetto)
     .success(function(data, status, headers, config) {
  $scope.funzione = null;
     }).error(function(data, status, headers, config) {
    });
}
});