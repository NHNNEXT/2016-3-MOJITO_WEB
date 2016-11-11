$(document).ready(function () {
	var $requestToUserListBtn = $('#requestToUserListBtn');
	var $myFriendListBtn = $('#myFriendListBtn');
	$requestToUserListBtn.on('click', showRequestToUserList);
	$('#requestToMeListBtn').on('click', showRequestToMeList);
	$myFriendListBtn.on('click', showFriendList);
	$('#metUserListBtn').on('click', showMetUsers);
	$requestToUserListBtn.trigger('click');
	$myFriendListBtn.trigger('click');
});

function showRequestToUserList(e) {
	var url = $(e.target).attr('href');
	e.preventDefault();
    $.ajax({
        type: 'get',
        url: url,
        dataType: 'json',
        error: onError,
        success: printRequestToUserList
    });
}

function printRequestToUserList(data) {
    var $list = $('.friend-request-list');
    if (data[0] === undefined) {
        var nothingTemplate = $('#non-list').html();
        $list.append(nothingTemplate);
    } else {
        var $requestToUser = $('#requestToUser').html();
        var insertTemplate = "";
        $('.friend-request-box').css('height', 200);
        for (var i in data) {
            var template = $requestToUser.format(data[i].userName, data[i].userEmail);
            insertTemplate += template;
            $('.friend-request-box').css('height', '+=90');
        }
        $list.children().remove();
        $list.append(insertTemplate);
    }
}

function showRequestToMeList(e) {
	var url = $(e.target).attr('href');
	e.preventDefault();
	$.ajax({
		type: 'get',
		url: url,
		dataType: 'json',
		error: onError,
		success: printRequestToMeList
	});
}

function printRequestToMeList(data) {
    var $list = $('.friend-request-list');
    $list.children().remove();
    if (data[0] === undefined) {
        var nothingTemplate = $('#non-list').html();
        $list.replaceWith(nothingTemplate);
    } else {
        var $requestToUser = $('#requestToMe').html();
        $('.friend-request-box').css('height', 200);
        for (var i in data) {
        	var template = $requestToUser.format(data[i].userName, data[i].userEmail);
            $('.friend-request-box').css('height', '+=90');
            $list.append(template);
        }
    }
}

function showFriendList(e) {
	var url = $(e.target).attr('href');
	e.preventDefault();
	$.ajax({
		type: 'get',
		url: url,
		dataType: 'json',
		error: onError,
		success: printFriendList
	});
}

function printFriendList(data) {
    var $list = $('.friend-list');
    $list.children().remove();
    if (data[0] === undefined) {
        var nothingTemplate = $('#non-list').html();
        $list.replaceWith(nothingTemplate);
    } else {
        var $requestToUser = $('#myFriend').html();
        $('.friend-list-box').css('height', 200);
        for (var i in data) {
        	var template = $requestToUser.format(data[i].userName, data[i].userEmail);
            $('.friend-list-box').css('height', '+=90');
            $list.append(template);
        }
    }
}

function showMetUsers(e) {
	var url = $(e.target).attr('href');
	e.preventDefault();
	$.ajax({
		type: 'get',
		url: url,
		dataType: 'json',
		error: onError,
		success: printMetUsers
	});
}

function printMetUsers(data) {
    var $list = $('.friend-list');
    $list.children().remove();
    if (data[0] === undefined) {
        var nothingTemplate = $('#non-list').html();
        $list.replaceWith(nothingTemplate);
    } else {
        var $requestToUser = $('#metUser').html();
        $('.friend-list-box').css('height', 200);
        for (var i in data) {
        	var template = $requestToUser.format(data[i].userName, data[i].userEmail);
            $('.friend-list-box').css('height', '+=90');
            $list.append(template);
        }
    }
}

function onError(data, status) {
    console.log('onError execute!');
}

String.prototype.format = function () {
    var args = arguments;
    return this.replace(/{(\d+)}/g, function (match, number) {
        return typeof args[number] != 'undefined'
            ? args[number]
            : match
            ;
    });
};
