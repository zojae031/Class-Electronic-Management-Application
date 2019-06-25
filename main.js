const express = require('express');
const app = express();
const port = 3000;
const port_pc = 3001;
const net = require('net');
var javaServer;

var server = net.createServer((socket)=>{
    javaServer = socket;  

    server.on('', (data)=>{
        console.log(data);
    })
});


server.on('error', (err)=>{
    console.log(err);
});

app.get('/', (req, res) => {
    res.send('Hello World!');
    console.log('chec');
    javaServer.write('test\n');
});


server.listen(3001, ()=>{
    console.log('Socket Server ....');
});

app.listen(port, () => {
    console.log(`Example app listening on port ${port}!`)
});

