$(document).ready(function () {
    var requestToUserListTag = document.getElementById('requestToUserListBtn');
    requestToUserListTag.addEventListener('click', showRequestToUserList);
});

function showRequestToUserList(e) {
    e.preventDefault();
    $.ajax({
        type: 'get',
        url: '/api/user/showRequestToUser',
        dataType: 'json',
        error: onError,
        success: function (data) {
            var $list = $(this).parent().find('.friend-list');
            if (data[0] === null) {
                var nothingTemplate = $('#non-list').html();
                $list.append(nothingTemplate);
            } else {
                var $requestToUser = $('#requestToUser').html();
                for (var i in data) {
                    var template = $requestToUser.format(
                        data[i].userName, data[i].userEmail
                    );
                    $list.append(template);
                }
            }
        }.bind(this)
    });
}

function onError(data, status) {
    alert('로그인을 해주세요');
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
