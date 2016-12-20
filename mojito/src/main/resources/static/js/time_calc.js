var monthNames = ["January", "February", "March", "April", "May", "June",
  "July", "August", "September", "October", "November", "December"];


var meetingDateInfo = document.getElementById('meetingDate').value.match(/(\d\d\d\d)-(\d\d)-(\d\d)T(\d\d):(\d\d)/);
console.log('unformat: '+meetingDateInfo);

var meetingDate;
meetingDate = new Date();
meetingDate.setYear(meetingDateInfo[1]);
meetingDate.setMonth(meetingDateInfo[2]-1);
meetingDate.setDate(meetingDateInfo[3]);
meetingDate.setHours(meetingDateInfo[4]);
meetingDate.setMinutes(meetingDateInfo[5]);
console.log('format: '+meetingDate);

document.getElementById('meetingTime').innerHTML = monthNames[meetingDate.getMonth()]+" "
	+meetingDate.getDate()+", "+meetingDate.getFullYear()+" / "+meetingDate.getHours()+":"+meetingDate.getMinutes();

// 남은 시간 카운터
function remain() {
	var now = new Date();

	var D = meetingDate.getDate()-now.getDate();
	var H = D*24+meetingDate.getHours()-now.getHours();
	var M = meetingDate.getMinutes()-now.getMinutes();
	var S = meetingDate.getSeconds()-now.getSeconds();
	if(M<0 && H>0){
		H-=1;
		M+=60;
	}
	if(S<0 && M>0){
		M-=1;
		S+=60;
	}
	if(H<4){	
	document.getElementById('leftTime').innerHTML = H+'Hours '+M+'Minutes '+S+'Seconds left to bomb!';
	document.getElementById('leftTime').style.color = "#F22";
	}else{

	document.getElementById('leftTime').innerHTML = H+'Hours '+M+'Minutes '+S+'Seconds left to bomb!';
	}
}

remain();
setInterval(remain, 1000);