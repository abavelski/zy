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
    .controller('SearchCtrl', function($scope, $location, notifications, $http){
        $scope.notifications = notifications;

        $http.get('/api/accounts').success(function(accounts){
            $scope.accounts = accounts;
        });

        $scope.toSubscription = function() {
            $location.path('/customers/'+$scope.phoneNumber+'/subscription');
        }
    })
    .controller('CustomerCtrl', function($scope, notifications, $location, $http, $stateParams){
        $scope.isActive = function(str){ return $location.path().search(str)>-1; };
        $http.get('/api/accounts/'+$stateParams.anumber)
            .success(function(data){
                $scope.account = data;
            })
            .error(function(err){
                notifications.set('Could not find subscription');
                $location.path('/search');
            });

    })
    .controller('SubscriptionCtrl', function($scope, $http, notifications){

        $scope.activateSubscription = function() {
            console.log('activating');
            $http.post('/api/accounts/'+$scope.account.service.phoneNumber+'/activate')
                .success(function(data){
                    notifications.set('Account activated');
                    $scope.account = data;
                })
                .error(function(){
                    notifications.set('Failed activating account');
                });
        }
    })
    .controller('UserCtrl', function($scope){})
    .controller('UsageCtrl', function($scope, $http){
        $scope.lines = [];
        $http.get('/api/invoices?subscriptionId='+$scope.account.subscription.id+'&status=OPEN')
            .success(function(invoices){
                if (invoices.length>0) {
                    console.log(invoices);
                    $scope.invoice = invoices[0];
                    $http.get('/api/invoices/'+invoices[0].id+'/lines')
                        .success(function(lines){
                            $scope.lines = lines;
                        })
                        .error(function(err){
                            console.log('error getting invoice lines', err);
                        });
                }
            })
            .error(function(err){
                console.log('error getting invoices', err);
            });
    });

