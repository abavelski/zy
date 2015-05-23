angular.module('signup', [])
    .config(function($stateProvider) {

        $stateProvider
            .state('signup', {
                url: '/signup',
                templateUrl: 'signup/signup.tpl.html',
                controller: 'SignupCtrl'
            })
                .state('signup.package', {
                    url: '/package',
                    templateUrl: 'signup/package.tpl.html'
                })
                .state('signup.anumber', {
                    url: '/anumber',
                    templateUrl: 'signup/anumber.tpl.html'
                });
    })

    .controller('SignupCtrl', function ($scope) {
        $scope.bla = 5;
    });