var net = require('net');
var util = require('util');
var path = require('path');
var http = require('http');
var fs   = require('fs');

var HOST = '10.26.42.110';///Set host ip to computers ip addrress
//var HOST = 'localhost';
var PORT = 8000;
var playerWaiting;

var server = http.createServer();
net.createServer(function(sock){ 
  console.log('CONNECTED: ' + sock.remoteAddress +':'+ sock.remotePort);
  
    sock.on('data', function(data) {
      
      console.log("Buffer to string: "+data.toString('utf-8'));
      var str = data.toString('utf-8');
   
  
    sock.on('error', function(data){
      if(sock.partner != undefined){
         console.log("Writing end game to partner");
         sock.partner.write("End Game\n");
         sock.partner.partner = null;
      }
      console.log('CLOSED: ' + sock.remoteAddress +' '+ sock.remotePort);
    });
      
    var joinGame(){
      if(playerWaiting == undefined || playerWaiting.destroyed){
        playerWaiting = sock;
      }else{
        sock.partner = playerWaiting;
        playerWaiting = sock;
        playerWaiting = undefined;
      }
    }
      
    var sendMove(message){
      sock.partner.write(message);
    }
    
}).listen(PORT, HOST);

console.log('Server listening on ' + HOST +':'+ PORT);

