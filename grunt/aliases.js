module.exports = {
    'concatDev': ['concat:appMocks', 'concat:angularMocks', 'concat:jquery', 'concat:css'],
    'concatDist': ['concat:app', 'concat:angular', 'concat:jquery', 'concat:css'],
    'default': ['clean','html2js','concatDist','copy:assets', 'copy:app'],
    'dev' : ['clean', 'html2js', 'concatDev', 'copy:assets', 'copy:mockApp'],
    'dev-server' : ['clean', 'html2js', 'concatDev', 'copy:assets', 'copy:mockApp', 'connect', 'watch'],
    'd': ['dev-server']

};