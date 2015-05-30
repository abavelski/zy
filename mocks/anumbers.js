var anumbers = [
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
];

module.exports = function(router) {

    router.route('/a-number/type/normal/random/10').get(function(req, res) {
        res.json(anumbers);
    });

};