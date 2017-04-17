//main module
var smartclock = angular.module('smartclock', ['ngMaterial', 'ds.clock']);

//controllers
smartclock.controller("clockController", function($scope, $http) {
  $scope.clock = {};
  $scope.history = [];
  $scope.currentNavItem = 'page1';

  //[GET] /clock
  $scope.getClockAngle = function() {
    $http({method:'GET', url:'http://localhost:8080/clock'})
    .then(function succes(response) {
      //$scope.clock
    }, function (response) {
      console.log(response.data);
      console.log(response.status);
    });
  };

  //[POST] /clock
  $scope.postClock = function() {
    $http({method:'POST', url:'http://localhost:8080/clock', data:$scope.clock})
    .then(function(response){
      $scope.getClockHistory();
    }, function(response) {
      console.log(response.data);
      console.log(response.status);
    });
  };

  //[GET] /clockhistory
  $scope.getClockHistory = function() {
    $http({method:'GET', url:'http://localhost:8080/clockhistory'})
    .then(function succes(response) {
      $scope.history = response.data;
    }, function (response) {
      console.log(response.data);
      console.log(response.status);
    });
  };

  //[GET] /clockhistory/{id}
  $scope.getClockById = function() {
    $http({method:'GET', url:'http://localhost:8080/clockhistory/'})
    .then(function succes(response) {

    }, function (response) {
      console.log(response.data);
      console.log(response.status);
    });
  };

  $scope.getClockHistory();

});

//resetting headers so preflight doesn't happen
smartclock.config(function ($httpProvider, $mdThemingProvider) {
  $httpProvider.defaults.headers.common = {};
  $httpProvider.defaults.headers.post = {};
  $httpProvider.defaults.headers.put = {};
  $httpProvider.defaults.headers.patch = {};
});
