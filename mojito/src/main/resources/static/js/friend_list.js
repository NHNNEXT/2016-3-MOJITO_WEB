$(document).ready(function () {
	var $requestToUserListBtn = $('#requestToUserListBtn');
	var $requestToMeListBtn = $('#requestToMeListBtn');
	var $myFriendListBtn = $('#myFriendListBtn');
	var $metUserListBtn = $('#metUserListBtn');
	
	$requestToUserListBtn.add($requestToMeListBtn).add($myFriendListBtn).add($metUserListBtn).bind('click', showRequestedList);
	
	$requestToUserListBtn.trigger('click');
	$myFriendListBtn.trigger('click');
});

function showRequestedList(e) {
	var url = $(e.target).attr('href');
	e.preventDefault();
	$.ajax({
		type: 'get',
		url: url,
		dataType: 'json',
		error: onError,
		success: printRequestedList
	});
}

function printRequestedList(data, status) {
	console.log(data);
	var $list = $(data.listName);
	$list.children().remove();
    if (data.list[0] === undefined) {
        var nothingTemplate = $('#non-list').html();
        $list.replaceWith(nothingTemplate);
    } else {
        var $requestToUser = $(data.requestedList).html();
        $(data.listBoxName).css('height', 200);
        for (var i in data.list) {
            var template = $requestToUser.format(data.list[i].userName, data.list[i].userEmail);
            $(data.listBoxName).css('height', '+=90');
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
