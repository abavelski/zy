angular.module('signup', ['notifications'])
    .config(function($stateProvider) {

        $stateProvider
            .state('signup', {
                url: '/signup',
                templateUrl: 'signup/packages.tpl.html',
                controller: 'PackagesCtrl'
            })
            .state('wizard', {
                url: '/wizard/:package',
                templateUrl: 'signup/wizard.tpl.html',
                controller: 'SignupCtrl'
                })
                .state('wizard.anumber', {
                    url: '/anumber',
                    templateUrl: 'signup/anumber.tpl.html',
                    controller: 'ChooseNumberCtrl'
                })
                .state('wizard.userdata', {
                    url: '/userdata',
                    templateUrl: 'signup/userdata.tpl.html',
                    controller: 'UserDataCtrl'
                })
                .state('wizard.confirmation', {
                    url: '/confirmation',
                    templateUrl: 'signup/confirmation.tpl.html',
                    controller: 'ConfirmationCtrl'
            });
    })

    .controller('SignupCtrl', function ($scope, $stateParams, $location, notifications, $http) {

        var reservationKey;
        $scope.isActive = function(str){ return $location.path().search(str)>-1; };
        $scope.packageCode = $stateParams.package;
        $scope.selectedNumber = '';
        $scope.steps = ['anumber', 'userdata', 'confirmation'];
        $scope.currentStep = 1;
        $scope.btnName=function() {
          return $scope.isActive('confirmation')?'Finish':'Next';
        };

        $scope.next = function() {
            var step = $scope.steps[($scope.currentStep++)];
            if ($scope.currentStep===2) {
                console.log('reserving: '+ $scope.selectedNumber);
                $http.post('/api/a-number/'+$scope.selectedNumber+'/reserve')
                    .success(function(data){
                        reservationKey = data.reservationKey;
                    })
                    .error(function(err){
                        console.log('error reserving a phone number', err);
                    });

            }
            if ($scope.currentStep===4) {
                $http.post('/api/signup', {
                    reservationKey : reservationKey,
                    packageCode : $scope.packageCode,
                    user : $scope.user

                })
                    .success(function(){
                        notifications.set('Signup successful!');
                        $location.path('/');
                    })
                    .error(function(){
                        notifications.set('Error!');
                        $location.path('/');
                    });

            } else {
                $location.path('/wizard/'+$scope.packageCode+'/'+step);
            }
        };
        $scope.user = {};
    })
    .controller('PackagesCtrl', function($scope, $http, notifications){
        $scope.notifications = notifications;
        $http.get('/api/signup/packages').success(function(data){
            $scope.packages = data;
        });
    })
    .controller('ChooseNumberCtrl', function($scope, $http){
        $http.get('/api/a-number/type/normal/random/10').success(function(data){
            $scope.numbers = data;
        });
    })
    .controller('UserDataCtrl', function($scope, $http){})
    .controller('ConfirmationCtrl', function($scope, $http){
        $http.get('/api/signup/packages/'+$scope.packageCode).success(function(data){
                $scope.package = data;
        });
    });