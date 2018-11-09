

var express = require('express');
var path = require('path');
var app = express();
app.use(express.static(__dirname + 'client'));
app.get('/', function(req, res) {
    res.sendFile(path.join(__dirname + '/client/index.html'));
});

app.get('/client/client.js', function(req, res) {
    res.sendFile(path.join(__dirname + '/client.js'));
});

app.get('/', function(req, res) {
    res.sendFile(path.join(__dirname + '/client/index.html'));
});
//5.
app.listen(8090);
 
console.log('Server Started listening on 8090');
