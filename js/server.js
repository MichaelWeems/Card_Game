var net = require('net');
var util = require('util');
var path = require('path');
var http = require('http');
var fs   = require('fs');

var HOST = '192.168.1.107';///Set host ip to computers ip addrress
//var HOST = 'localhost';
var PORT = 8000;
var playerWaiting;

var server = http.createServer();
net.createServer(function(sock){ 
  console.log('CONNECTED: ' + sock.remoteAddress +':'+ sock.remotePort);
    //Read socket info
    sock.on('data', function(data) {
      
      console.log("Buffer to string: "+data.toString('utf-8'));
      var obj = data.toString('utf-8').split("&");
      var func = obj[0];
      console.log("after split: "+func);
      if(func == "joinGame"){
        joinGame();
      }else if(func == "endSearch"){
        playerWaiting == undefined;
      }
    });
    //
  
    //On Close
    sock.on('close', function(data){
      if(sock.partner != undefined){
         console.log("Writing end game to partner");
         sock.partner.write("End Game\n");
         sock.partner.partner = null;
      }
      console.log('CLOSED: ' + sock.remoteAddress +' '+ sock.remotePort);
    });
    //
    
    //
    sock.on('error', function(data){
      console.log("Player is absent");
    });
    //
  
    var joinGame = function(){
      if(playerWaiting == undefined || playerWaiting.destroyed){
        console.log("Waiting to be paired");
        playerWaiting = sock;
      }else{
        console.log("paired with partner");
        sock.partner = playerWaiting;
        playerWaiting.partner = sock;
        
        sock.write("startGame\n");
        playerWaiting.write("startGame\n");
        playerWaiting = undefined;
        
      }
    }
      
    var sendMove = function(message){
      sock.partner.write(message);
    }
    
}).listen(PORT, HOST);

console.log('Server listening on ' + HOST +':'+ PORT);

