var http = require('http');
var fs = require('fs');
var dt = require('./ResourceMapping.js');
var server = http.createServer(function (req, resp) {
    if (req.url === "/") {
        fs.readFile("index.html", function (error, pgResp) {
           if (error) {
                resp.writeHead(404);
                resp.write('Contents you are looking are Not Found');
            } else {
                resp.writeHead(200, { 'Content-Type': 'text/html' });
                resp.write(pgResp);
            }
             
            resp.end();
        });
    } else {
        //4.
        resp.writeHead(200, { 'Content-Type': 'text/html' });
        resp.write(req.url);
        resp.end();
    }
    
});
//5.
server.listen(8090);
 
console.log('Server Started listening on 8090');
