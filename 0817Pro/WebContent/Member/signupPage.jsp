<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">
<title>SignUp</title>
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/signupPage.css">
<link rel="stylesheet" href="../css/footer.css">

<script type="text/javascript">
	function idCheck() {
		if (document.registerform.id.value == "") {
			alert("아이디를 입력해주세요");
			document.registerform.id.focus();
			return;
		}

		var url = "../idCheck.so?userid=" + document.registerform.id.value;
		window
				.open(url, "_blank_1",
						"toolbar=no, menubar=no, scrollbars=no, resizable=no, width=500, height=300");
	}

	function inputIdChk() {
		document.registerform.id_Check.value = "idUncheck";
	}
</script>
</head>

<body>
	<div id="top">
		<div id="header">
			<jsp:include page="../section/header.jsp" />
		</div>
		<div class="container">
			<h5>
				<span>회원 가입</span> 페이지입니다.
			</h5>
			<hr />
			<br />
			<form method="post" name="registerform" action="signupNew.so"
				onsubmit="return signUp_Check()" style="text-align: center;">
				<input type="text" placeholder="아이디" name="id" required
					id="join_text_box" onkeydown="inputIdChk()"> <br> <input
					type="button" class="validateIdSubmit" name="validateIdSubmit"
					onclick="idCheck()" value="중복확인"><input type="hidden"
					name="id_Check" value="idUncheck"> <br /> <br /> <input
					type="password" placeholder="비밀번호" name="pwd" required
					id="join_text_box" /><br /> <br /> <input type="text"
					placeholder="이 름" name="name" required id="join_text_box" /><br />
				<br /> <input type="email" placeholder="이 메 일" name="email"
					required id="join_text_box" /><br /> <br /> <input type="text"
					placeholder="지 번" name="zip_num" required id="join_text_box" /><br />
				<br /> <input type="text" placeholder="주 소" name="address" required
					id="join_text_box" /><br /> <br /> <input type="text"
					placeholder="전화 번호" name="phone" required id="join_text_box" /><br />
				<br />

				<p>
					<input type="submit" value="가입하기" id="signup1" /><br /> <br />
				</p>
			</form>
			<hr />
		</div>

		<div id="bott">
			<jsp:include page="../section/footer.jsp" />
		</div>
	</div>

</body>
</html>