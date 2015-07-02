angular.module('customers', ['ui.bootstrap'])

    .config(function($stateProvider) {

        $stateProvider
            .state('search', {
                url: '/search',
                templateUrl: 'customers/search.tpl.html',
                controller : 'SearchCtrl'
            })
            .state('customer', {
                url: '/customers/:anumber',
                templateUrl: 'customers/customer.tpl.html',
                controller : 'CustomerCtrl'
            })
                .state('customer.subscription', {
                    url: '/subscription',
                    templateUrl: 'customers/subscription.tpl.html',
                    controller : 'SubscriptionCtrl'
                })
                .state('customer.user', {
                    url: '/user',
                    templateUrl: 'customers/user.tpl.html',
                    controller: 'UserCtrl'
                })
                .state('customer.usage', {
                    url: '/usage',
                    templateUrl: 'customers/usage.tpl.html'
                })
    })
    .controller('SearchCtrl', function($scope, $location){
        $scope.toSubscription = function() {
            $location.path('/customers/1234/subscription');
        }
    })
    .controller('CustomerCtrl', function($scope, $location){
        $scope.isActive = function(str){ return $location.path().search(str)>-1; };
    })
    .controller('SubscriptionCtrl', function($scope, $modal){
        $scope.terminateSubscription = function() {
            $modal.open({
                animation: true,
                controller: 'TerminateSubscriptionCtrl',
                templateUrl: 'customers/terminateSubscription.tpl.html'
            });
        }

    })
    .controller('TerminateSubscriptionCtrl', function ($scope, $modalInstance) {
        $scope.terminate = function () {
            $modalInstance.close();
        };

        $scope.cancel = function () {
            $modalInstance.close();
        };
        $scope.open = function($event) {
            $event.preventDefault();
            $event.stopPropagation();

            $scope.opened = true;
        };

    })
    .controller('UserCtrl', function($scope, $modal){
        $scope.editUser = function() {

            $modal.open({
                animation: true,
                controller: 'EditUserCtrl',
                templateUrl: 'customers/editUser.tpl.html'
            });
        }
    })
    .controller('EditUserCtrl', function ($scope, $modalInstance) {
        $scope.save = function () {
            $modalInstance.close();
        };

        $scope.cancel = function () {
            $modalInstance.close();
        };
    });

