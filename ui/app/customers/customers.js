angular.module('customers', ['ui.bootstrap', 'notifications'])

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
                    templateUrl: 'customers/usage.tpl.html',
                    controller: 'UsageCtrl'
                })
    })
    .controller('SearchCtrl', function($scope, $location, notifications){
        $scope.notifications = notifications;

        $scope.toSubscription = function() {
            $location.path('/customers/'+$scope.phoneNumber+'/subscription');
        }
    })
    .controller('CustomerCtrl', function($scope, notifications, $location, $http, $stateParams){
        $scope.isActive = function(str){ return $location.path().search(str)>-1; };
        console.log($stateParams);
        $http.get('/api/accounts/?phone='+$stateParams.anumber)
            .success(function(data){
                console.log(data);
                $scope.account = data;
            })
            .error(function(err){
                console.log(err);
                notifications.set('Could not find subscription');
                $location.path('/search');
            });

    })
    .controller('SubscriptionCtrl', function($scope){})
    .controller('UserCtrl', function($scope){})
    .controller('UsageCtrl', function($scope, $http){
        $http.get('/api/invoices?subscriptionId='+$scope.account.subscription.id+'&status=OPEN')
            .success(function(invoices){
                $scope.invoice = invoices[0];
                $http.get('/api/invoices/'+invoices[0].id+'/lines')
                    .success(function(lines){
                        $scope.lines = lines;
                    })
                    .error(function(err){
                        console.log('error getting invoice lines', err);
                    });
            })
            .error(function(err){
                console.log('error getting invoices', err);
            });
    });

