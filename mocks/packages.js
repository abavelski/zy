var packages = [
    {
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
    },
    {
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
    {
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
    }
];

module.exports = function(router) {

    router.route('/signup/packages').get(function(req, res) {
        res.json(packages);
    });

    router.route('/signup/packages/:package').get(function(req, res) {
        res.json(packages[2]);
    });



};