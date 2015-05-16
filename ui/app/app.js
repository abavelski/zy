'use strict';
angular.module('app', [ 'templates.app', 'templates.common', 'admin'])

    .controller('AppCtrl', ['$scope', function($scope) {}])

    .controller('HeaderCtrl', function ($scope, $location) {
        $scope.isActive = function(str){ return $location.path().search(str)>-1; };
        $location.path('/admin');
    });