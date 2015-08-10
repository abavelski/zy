exports.config = {
    seleniumAddress: 'http://localhost:4444/wd/hub',
    specs: [
        'features/*.feature'
    ],

    capabilities: {
        'browserName': 'chrome'
    },
    framework: 'cucumber'

};