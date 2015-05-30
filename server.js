#!/bin/env node
var express = require('express'),
    app = express(),
    morgan  = require('morgan'),
    bodyParser = require('body-parser'),
    pjson = require('./package.json'),
    packages = require('./mocks/packages'),
    anumbers = require('./mocks/anumbers');

app.use(morgan('short'))
    .use(express.static(pjson.dist))
    .use(bodyParser.json())
    .use(bodyParser.urlencoded({ extended: true }));

    var router = express.Router();
    packages(router);
    anumbers(router);

    app.use('/api', router);
    app.listen(8000);
    console.log('Server started at port 8000');