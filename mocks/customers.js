var customer = {
    service : {
        phoneNumber : "61660020",
        status : "ACTIVE"
    },
    subscription : {
        status : "ACTIVE",
        package : "MINI",
        startDate : 1433017749000
    }
};
var user = {
    firstName : "Vladimir",
    lastName : "Putin",
    address : "Roarsvej 23",
    city : "Frederiksberg",
    zip : "2000"
};

module.exports = function(router) {

    router.route('/customers/:customerId').get(function(req, res) {
        res.json(customer);
    });

    router.route('/customers/:customerId/user').get(function(req, res){
        res.json(user);
    });

};