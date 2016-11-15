$("#loginButton").click(tryLogin);

function tryLogin(e) {
	console.log("login button clicked!");
	e.preventDefault();
	
	var emailString = $("#userEmail").val();
	console.log("userEmail: " + emailString);
	
	var passwordString = $("#userPassword").val();
	console.log("userPassword: " + passwordString);
	
	var jsonString = JSON.stringify({"userEmail" : emailString,
									"userPassword" : passwordString});
	console.log("jsonString: " + jsonString);
	
	var url = $("#loginForm").attr("action");
	console.log("url : " + url);
	
	$.ajax({
		type: 'post',
		url: url,
		data : jsonString,
		contentType: 'application/json',
		dataType: 'json', // response data type
		error: onError,
		success: onSuccess
	});
}

function onSuccess(data, status) {
	console.log(data);
	var signup = function(e) { location.href="/signup"};
	var findPassword = function(e) { location.href="/find"};
	
	if (data.statusCode === 0) {
		addSnackbar(data.errorMessage);
	}
	
	if (data.statusCode === 1) {
		location.href="/";
	}
	
	if (data.statusCode == 2) {
		addSnackbar(data.errorMessage, '회원가입', signup);
	}
	
	if (data.statusCode == 3) {
		addSnackbar(data.errorMessage, '비밀번호 찾기', findPassword);
	}
}

function onError(xhr, status) {
	alert("error");
}

function addSnackbar(errorMessage, actionText, actionHandler) {
	var snackbarContainer = document.querySelector('#login_status_snackbar');
	var data = { message: errorMessage, timeout: 5000, actionHandler: actionHandler, actionText: actionText};
	snackbarContainer.MaterialSnackbar.showSnackbar(data);
}