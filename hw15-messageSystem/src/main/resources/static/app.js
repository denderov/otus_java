var stompClient = null;

function initWS() {
    showHeadUser();
    var socket = new SockJS('/user-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/user', function (msg) {
            console.log('resp: ' + JSON.parse(msg.body));
            showAddedUser(JSON.parse(msg.body));
//            showResult(JSON.parse(msg.body));
        });
    });
}

function showAddedUser(json) {
    $("#users").append(
        "<tr>" +
        "<td>" + JSON.parse(json).id + "</td>" +
        "<td>" + JSON.parse(json).name + "</td>" +
        "<td>" + JSON.parse(json).login + "</td>" +
        "<td>" + JSON.parse(json).password + "</td>" +
        "</tr>"
        );
}

function showHeadUser() {
    $("#users").append(
        "<tr>" +
        "<td>id</td>" +
        "<td>name</td>" +
        "<td>login</td>" +
        "<td>password</td>" +
        "</tr>"
        );
}

function createUser() {
            console.log('createUser');
    stompClient.send("/app/createUser", {}, JSON.stringify({
                    'name': $("#name").val() ,
                    'login': $("#login").val() ,
                    'password': $("#password").val() ,
                  }));
}

$(function () {
    $( "#send" ).click(function() { createUser(); });
});

function showResult(message) {
    $("#result").html("<tr><td>Created new user. ID:" + message + "</td></tr>");
}

