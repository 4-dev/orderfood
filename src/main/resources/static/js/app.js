var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
        return true;
        alert('aqui setConnected');
    }
    else {
        $("#conversation").hide();
        return false;
    }
    $("#greetings").html(""); 
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
//    alert('aqui connect');
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
            
            var msg = JSON.parse(greeting.body).content;
            var idDiv = "#mesaStatus"+msg;
            var descStatus = "#descStatus"+msg;
            var desc = $(descStatus).text();
//            alert('aqui stompClient.connect '+idDiv);
            if(JSON.parse(greeting.body).content = '') {
//            	$("#cardStatus").attr("class", "card yellow darken-1");
            	console.log(desc);
            	$(descStatus).html('<span class="green-text">Disponivel</span>');
            	$(idDiv).attr("class", "card blue-grey darken-1");            	
            } else {
            	$(idDiv).attr("class", "card deep-orange darken-1");
            	$(descStatus).html('<span class="red-text">Ocupada</span>');
            }
        });
    });
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
    $("#cardStatus").attr("class", "card yellow darken-1");
//    alert("Hello! I am an alert box!!");
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
    $("#cardStatus").attr("class", "card yellow darken-1");
//    alert("Hello! I am an alert box!!");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});

