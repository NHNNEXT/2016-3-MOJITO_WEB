//$("#demo-show-snackbar").click(loginFail);
//
//function loginFail(e) {
//  e.preventDefault();
//  console.log("click!");
//  
//  var params = $("#loginForm").serialize();
//  console.log("params : " + params);
//  
//  var url = $("#loginForm").attr("action");
//  console.log("url : " + url);
//  
//  'use strict';
//  var snackbarContainer = document.querySelector('#demo-snackbar-example');
//  var showSnackbarButton = document.querySelector('#demo-show-snackbar');
//  var handler = function(event) {
//	  location.href='/find'
//  };
//  showSnackbarButton.addEventListener('click', function() {
//    'use strict';
//    var data = {
//      message: '잘못된 패스워드입니다.',
//      timeout: 2000,
//      actionHandler: handler,
//      actionText: '비밀번호 찾기'
//    };
//    snackbarContainer.MaterialSnackbar.showSnackbar(data);
//  });
//};

$("#loginForm").keyup(function(e) {
	if (e.keyCode == 13) {
		$("#demo-show-snackbar").click();
	}
});