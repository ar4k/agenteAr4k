'use strict';
/**
 * @ngdoc function
 * @name sbAdminApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the sbAdminApp
 */
angular.module('sbAdminApp')
  .controller('ChartCtrl', ['$scope', '$timeout', function ($scope, $timeout) {
    $scope.line = {
	    labels: ['Gennaio', 'Febbraio', 'Marzo', 'Aprile', 'Maggio', 'Giugno', 'Luglio'],
	    series: ['Serie A', 'Serie B'],
	    data: [
	      [65, 59, 80, 81, 56, 55, 40],
	      [28, 48, 40, 19, 86, 27, 90]
	    ],
	    onClick: function (points, evt) {
	      console.log(points, evt);
	    }
    };

    $scope.bar = {
	    labels: ['2006', '2007', '2008', '2009', '2010', '2011', '2012'],
		series: ['Serie A', 'Serie B'],

		data: [
		   [65, 59, 80, 81, 56, 55, 40],
		   [28, 48, 40, 19, 86, 27, 90]
		]
    	
    };

    $scope.donut = {
    	labels: ["Vendite per download", "Vendite al punto vendita", "Ordini per posta"],
    	data: [300, 500, 100]
    };

    $scope.radar = {
    	labels:["Mangiare", "Bere", "Dormire", "Disegnare", "Scrivere", "Pedalare", "Correre"],

    	data:[
    	    [65, 59, 90, 81, 56, 55, 40],
    	    [28, 48, 40, 19, 96, 27, 100]
    	]
    };

    $scope.pie = {
    	labels : ["Vendite per download", "Vendite al punto vendita", "Ordini per posta"],
    	data : [300, 500, 100]
    };

    $scope.polar = {
    	labels : ["Vendite per download", "Vendite al punto vendita", "Ordini per posta", "Vendite televisive", "Vendite dirette B2B"],
    	data : [300, 500, 100, 40, 120]
    };

    $scope.dynamic = {
    	labels : ["Vendite per download", "Vendite al punto vendita", "Ordini per posta", "Vendite televisive", "Vendite dirette B2B"],
    	data : [300, 500, 100, 40, 120],
    	type : 'PolarArea',

    	toggle : function () 
    	{
    		this.type = this.type === 'PolarArea' ?
    	    'Pie' : 'PolarArea';
		}
    };
}]);
