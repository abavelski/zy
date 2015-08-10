var myStepDefinitionsWrapper = function () {
    var chai = require('chai');
    var chaiAsPromised = require('chai-as-promised');
    chai.use(chaiAsPromised);

    var expect = chai.expect;

    this.Given(/^I open web app$/, function (callback) {
        browser.get('http://localhost:8080');
        callback();
    });

    this.Given(/^I see (\d+) packages$/, function (nr, callback) {
        var packages = element.all(by.repeater('pkg in packages'));
        expect(packages.count()).to.eventually.equal(parseInt(nr)).and.notify(callback);
    });

    this.Then(/^I select first package$/, function (callback) {
        var packages = element.all(by.repeater('pkg in packages'));
        packages.first().element(by.css('a')).click();
        callback();

    });

    this.Then(/^see the list of (\d+) numbers$/, function (nr, callback) {
        var numbers = element.all(by.repeater('number in numbers'));
        expect(numbers.count()).to.eventually.equal(parseInt(nr)).and.notify(callback);
    });


    this.Then(/^choose the first number$/, function (callback) {
        var numbers = element.all(by.repeater('number in numbers'));
        numbers.first().element(by.css('input')).click();
        callback();

    });

    this.Then(/^click "([^"]*)" button$/, function (btnText, callback) {
        element(by.buttonText(btnText)).click();
        callback();
    });
};
module.exports = myStepDefinitionsWrapper;