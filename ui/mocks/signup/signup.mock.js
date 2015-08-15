'use strict';

angular.module('signup.mocks', [])
    .run(function run($httpBackend) {
        $httpBackend.whenGET('/api/signup/packages')
            .respond([
            {
                priority: 1,
                "code": "mini",
                "name": "Mini",
                "fees": [
                    "mini",
                    "signup"
                ],
                "pricePlanCode": "pp1",
                "descriptions": {
                    "monthlyPrice": "29",
                    "voice": "2 hours",
                    "data": "2 GB",
                    "other": [
                        "4G"
                    ]
                }
            },
            {   priority : 2,
                "code": "standard",
                "name": "Standard",
                "fees": [
                    "standard",
                    "signup"
                ],
                "pricePlanCode": "pp1",
                "descriptions": {
                    "monthlyPrice": "69",
                    "voice": "6 hours",
                    "data": "5 GB",
                    "other": [
                        "4G"
                    ]
                }
            },
            {
                priority: 3,
                "code": "mega",
                "name": "Mega",
                "fees": [
                    "mega",
                    "signup"
                ],
                "pricePlanCode": "pp1",
                "descriptions": {
                    "monthlyPrice": "99",
                    "voice": "12 hours",
                    "data": "20 GB",
                    "other": [
                        "4G"
                    ]
                }
            }
        ]);

        $httpBackend.whenGET('/api/a-number/type/normal/random/10')
            .respond([
            61660030,
            61660022,
            61660021,
            61660025,
            61660029,
            61660024,
            61660020,
            61660023,
            61660028,
            61660026
        ]);

        $httpBackend.whenGET(new RegExp('/api/signup/packages' + "/[a-z][A-Z]*"))
            .respond(200, {
            priority: 1,
            "code": "mini",
            "name": "Mini",
            "fees": [
                "mini",
                "signup"
            ],
            "pricePlanCode": "pp1",
            "descriptions": {
                "monthlyPrice": "29",
                "voice": "2 hours",
                "data": "2 GB",
                "other": [
                    "4G"
                ]
            }
        });

        $httpBackend.whenPOST(new RegExp('/api/a-number'+"/[0-9]*/"+'reserve'))
            .respond(function(method, url, data, headers){
            return [201, {
                reservationKey: "123"
            }, {}];
        });

        $httpBackend.whenPOST('/api/signup')
            .respond(201);

    });