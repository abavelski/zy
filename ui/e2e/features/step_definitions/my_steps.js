var myStepDefinitionsWrapper = function () {
    var chai = require('chai');
    var chaiAsPromised = require('chai-as-promised');
    var chaiString = require('chai-string');
    chai.use(chaiString);
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

    this.Given(/^I go to admin tab$/, function (callback) {
        element(by.linkUiSref('admin.numberrange')).click();
        callback();
    });

    this.Given(/^input first number as "([^"]*)"$/, function (arg1, callback) {
        element(by.model('numberRange.firstNumber')).sendKeys(arg1);
        callback();
    });

    this.Given(/^input last number as "([^"]*)"$/, function (arg1, callback) {
        element(by.model('numberRange.lastNumber')).sendKeys(arg1);
        callback();
    });

    this.Then(/^I see message "([^"]*)"$/, function (arg1, callback) {
        var alertMessage = element(by.css('.alert'));
        expect(alertMessage.getText()).to.eventually.endWith(arg1).and.notify(callback);
    });

    this.Then(/^input city "([^"]*)"$/, function (arg1, callback) {
        element(by.model('$parent.user.city')).sendKeys(arg1);
        callback();
    });

    this.Then(/^input first name "([^"]*)"$/, function (arg1, callback) {
        element(by.model('$parent.user.firstName')).sendKeys(arg1);
        callback();
    });

    this.Then(/^input last name "([^"]*)"$/, function (arg1, callback) {
        element(by.model('$parent.user.lastName')).sendKeys(arg1);
        callback();
    });

    this.Then(/^input address "([^"]*)"$/, function (arg1, callback) {
        element(by.model('$parent.user.address')).sendKeys(arg1);
        callback();
    });

    this.Then(/^input zip "([^"]*)"$/, function (arg1, callback) {
        element(by.model('$parent.user.zip')).sendKeys(arg1);
        callback();
    });
};
module.exports = myStepDefinitionsWrapper;