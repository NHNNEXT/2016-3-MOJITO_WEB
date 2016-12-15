$(document).ready(function () {
	var $listBtn = $('.friend-classify');
	
	$listBtn.on('click', showRequestedList);
	
	$('#requestToUserListBtn').trigger("click");
	$('#myFriendListBtn').trigger("click");
	
	var $searchBtn = $('#searchBtn');
	$searchBtn.on('click', searchByUserName);
});

function searchByUserName(e) {
	var url = $('#searchForm').prop('action');
	var search = {
			"userName" : $("#searchValue").val()
	}
	console.log(search);
	console.log(url);
	e.preventDefault();
	$.ajax({
		type: 'post',
		url: url,
		data: search,
		dataType: 'json',
		error: onError,
		success: printRequestedList
	})
}

function showRequestedList(e) {
	var url = $(e.target).prop('href');
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
            var template = $requestToUser.format(data.list[i].userName, data.list[i].userEmail, data.list[i].id);
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
