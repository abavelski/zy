angular.module('customers', [])

    .config(function($stateProvider) {

        $stateProvider
            .state('search', {
                url: '/search',
                templateUrl: 'customers/search.tpl.html',
                controller : 'SearchCtrl'
            })
            .state('customer', {
                url: '/customer/:anumber',
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
                    templateUrl: 'customers/user.tpl.html'
                })
                .state('customer.usage', {
                    url: '/usage',
                    templateUrl: 'customers/usage.tpl.html'
                })
    })
    .controller('SearchCtrl', function($scope, $location){
        $scope.toSubscription = function() {
            $location.path('/customer/1234/subscription');
        }
    })
    .controller('CustomerCtrl', function($scope, $location){
        $scope.isActive = function(str){ return $location.path().search(str)>-1; };
    })
    .controller('SubscriptionCtrl', function($scope, $http){

    });

