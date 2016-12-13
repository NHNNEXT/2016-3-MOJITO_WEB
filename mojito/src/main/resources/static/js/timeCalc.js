alert('connect OK');

var date;
date = new Date();
date.setHours(0);
date.setMinutes(0);
date.setSeconds(0);

date.setDate(date.getDate() + 1);
var time2 = date.getTime(); // 내일 0시 0분 으로 설정

// 오늘과 내일을 미리 구하기 때문에 자정을 넘어갈 경우 이 소스는 오작동 할 수 있습니다.
// 위 날짜 구하는 부분이 계속 호출되도록 하면됩니다.


// 남은 시간 카운터
function remain() {
	var now = new Date();
	var gap = Math.round((time2 - now.getTime()) / 1000);

	var D = Math.floor(gap / 86400);
	var H = Math.floor((gap - D * 86400) / 3600 % 3600);
	var M = Math.floor((gap - H * 3600) / 60 % 60);
	var S = Math.floor((gap - M * 60) % 60);
	
	document.getElementById('text1').innerHTML = 'test';
	document.getElementById('text2').innerHTML = '오늘 당신에게 주어진 시간은 ' + D + '일 '
			+ H + '시간 ' + M + '분 ' + S + '초 남았습니다.';
}

remain();
setInterval(remain, 1000);