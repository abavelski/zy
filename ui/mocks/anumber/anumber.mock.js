'use strict';

angular.module('anumber.mocks', [])
    .run(function run($httpBackend) {
        $httpBackend.whenPOST('/api/number-range').respond(function(method, url, data, headers){
            return [201, {}, {}];
        });


    });