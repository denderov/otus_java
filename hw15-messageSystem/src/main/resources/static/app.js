var stompClient = null;

function initWS() {
    var socket = new SockJS('/user-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/user', function (msg) {
            console.log('resp: ' + JSON.parse(msg.body));
            showResult(JSON.parse(msg.body));
        });

        stompClient.subscribe('/topic/allUsers', function (msg) {
            console.log('resp: ' + JSON.parse(msg.body));
            showAllUsers(JSON.parse(msg.body));
        });
    });

    getUsers();
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

function getUsers() {
            console.log('getUsers');
    stompClient.send("/app/getAllUsers", {}, null);
}

$(function () {
    $( "#send" ).click(function() { createUser(); });
    $( "#getUsers" ).click(function() { getUsers() ; });
});

function showResult(message) {
    $("#result").html("<tr><td>Created new user. ID:" + message + "</td></tr>");
}



function showAllUsers(json) {

    $('#table_head').empty();
    $('#table_content').empty();

    // Get Table headers and print
    for (var k = 0; k < Object.keys(json[0]).length; k++) {
      $('#table_head').append('<td style="width: 100px">' + Object.keys(json[0])[k] + '</td>');
    }

    // Get table body and print
    for (var i = 0; i < Object.keys(json).length; i++) {
      $('#table_content').append('<tr>');
      for (var j = 0; j < Object.keys(json[0]).length; j++) {
        $('#table_content').append('<td>' + json[i][Object.keys(json[0])[j]] + '</td>');
      }
      $('#table_content').append('</tr>');
    }
}


