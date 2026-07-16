let stompClient = null;

let user = null;

const socket = new SockJS('/ws-endpoints');
stompClient = Stomp.over(socket);

stompClient.connect({}, ()=> {
    //console.log('Connected!');

    stompClient.subscribe('/topic/messages' , (message) => {

       // console.log('Received message message', message);

        const li = document.createElement('li');
        const msg = JSON.parse(message.body);
        li.innerHTML = msg.content + "( by " + msg.sender + " at " + msg.timeStamp + ")";

        const messageList = document.getElementById('messageList');
        messageList.appendChild(li);
    });
});



function sendMessage(){

    const message = document.getElementById("messageInput").value.trim();

    if(message === ""){
        return;
    }

    stompClient.send("/app/chat", {}, JSON.stringify({

        sender:user,
        content:message,
        type:"CHAT",
        timeStamp:new Date().toLocaleTimeString()

    }));

    document.getElementById("messageInput").value = "";
}

function connect(){

    const uname = document.getElementById("uname").value.trim();

    if(uname === ""){

        alert("Please enter username");

        return;
    }

    user=uname;
    document.getElementById("loginDiv").style.display = "none";
    document.getElementById("msgBlock").style.display = "flex";
    document.getElementById("userName").innerText = "Logged in as : "+user;
}