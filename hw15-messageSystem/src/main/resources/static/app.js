var stompClient = Stomp.over(new SockJS('/app/websocket'));

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/frontend/get_user', function (user) {
            showGreeting(JSON.parse(greeting.body).user);
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function addUser(json) {
    $("#users").append(
        "<td>" + JSON.parse(json).id + "</td>" +
        "<td>" + JSON.parse(json).name + "</td>" +
        "<td>" + JSON.parse(json).login + "</td>" +
        "<td>" + JSON.parse(json).password + "</td>"
        );
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});

