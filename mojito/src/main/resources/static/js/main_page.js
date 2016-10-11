/**
 * click a main_page list and show detail page.
 * 
 */

$(document).ready(function() {
	$(".join_button").click(function() {
		if($(this).text() == '조인') {
			$(this).text('취소');
		} else {
			$(this).text('조인');
		}
	});
	$(".detail_button").click(function() {
		if($(this).text() == '자세히 보기') {
			$(this).text('접기');
			$(".meeting_detail").css("display", "block");
		} else {
			$(this).text('자세히 보기');
			$(".meeting_detail").css("display", "none");
		}
	})
});
