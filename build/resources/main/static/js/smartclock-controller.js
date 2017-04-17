//main module
var smartclock = angular.module('smartclock', ['ngMaterial', 'moment-picker']);

//controllers
smartclock.controller("clockController", function($scope, $http, $mdToast) {
  $scope.clock = {};
  $scope.history = [];
  $scope.currentNavItem = 'page1';

  $scope.showToast = function() {
    $mdToast.show($mdToast.simple().textContent($scope.message).hideDelay(3000));
  }

  //[GET] /clock
  $scope.getCurrentTimeAngle = function() {
    $http({method:'GET', url:'http://localhost:8080/clock'})
    .then(function succes(response) {
      $scope.clock = response.data;
    }, function (response) {

    });
  };

  //[POST] /clock
  $scope.postClock = function() {
    $http({method:'POST', url:'http://localhost:8080/clock', data:$scope.clock})
    .then(function(response){
      $scope.getClockHistory();
      $scope.message = "Success!";
    }, function(response) {
      $scope.message = "POST failed";
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
