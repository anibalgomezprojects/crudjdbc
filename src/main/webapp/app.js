var app = angular.module("myApp", []);
app.controller("myCtrl", function($scope, $http) {
	
	$scope.cargar = function() {
		$http.get('/crudjdbc/resources/rest')
		.then(function(response) {
			$scope.datos = response.data;
		});	
	}
	
	$scope.cargar();
	
	$scope.create = function(obj) {
		$http.post('/crudjdbc/resources/rest', obj)
			.then(function(response) {
				$scope.status = 'OK';
			});
		$scope.cargar();
	}
	
	$scope.update = function(obj) {
		//console.log(id);
		console.log(obj);
		$http.post('/crudjdbc/resources/rest/' + obj.id, obj)
		.then(function(response) {
			$scope.status = 'OK';
		});
		$scope.cargar();
	}
	
	$scope.borrar = function(nombre) {
		console.log(nombre);
		$http.delete('/crudjdbc/resources/rest/' + nombre)
		.then(function(response) {
			$scope.status = 'OK';
		});
		$scope.cargar();
	}
	
	$scope.readById = function(id) {
		console.log(id);
		$http.get('/crudjdbc/resources/rest/' + id)
		.then(function(response) {
			$scope.dato = response.data;
		});
	}
	
});