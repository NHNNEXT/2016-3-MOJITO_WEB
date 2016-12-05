$(document).ready(function () {
	var $joinBtn = $('.join_button');
	var $detailBtn = $('.detail_button');
	
	$joinBtn.bind('click', joinMeeting);
});

function joinMeeting(e) {
	var joinBtn = $(this);
	var url = joinBtn.attr('url');
	var curNum = joinBtn.parent().find('.current_participants');
	console.log(url);
	e.preventDefault();
	$.ajax({
		type: 'get',
		url: url,
		dataType: 'json',
		error: function(xhr, status) {
			console.log("error execute!");
		},
		success: function(data, status) {
			curNum.html(data);
			joinBtn.text(function(i, text) {
				return text === "조인" ? "조인 취소" : "조인";
			})
			console.log("success!!!");
		}
	});
}
