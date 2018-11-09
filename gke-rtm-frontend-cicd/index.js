//1.
var http = require('http');
var fs = require('fs');
var app = express();
app.use(express.static(__dirname));
//2.
var server = http.createServer(function (req, resp) {
     //3.
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
    app.get('ResourceMapping.js', function(req, res) {
    res.sendFile(path.join(__dirname + '/ResourceMapping.js'));
});
});
//5.
server.listen(8090);
 
console.log('Server Started listening on 8090');
