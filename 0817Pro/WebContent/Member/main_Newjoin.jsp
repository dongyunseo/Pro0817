<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Northwind</title>
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.6.3/css/all.css">
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/main_Newjoin.css">
<link rel="stylesheet" href="../css/footer.css">
</head>
<body>
	<div id="top">
		<div id="header">
			<jsp:include page="../section/header.jsp" />
		</div>
		<div id="wrap">
			<div id="container_newjoin" style="margin: atuo;">
				<div class="container">


					<h1 style="padding: 50px; margin-left: 100px;">
						<p>회원 가입 완료</p>
					</h1>

					<p class="hello">
						<span class="txt_emph_newjoin" id="name"><h3>동윤 쇼핑몰에
								회원이 되어 주셔서 감사합니다.</h3></span>
					</p>

					<p class="hello">
						동윤 쇼핑몰에서는 항상 좋은 상품과 서비스를 제공할 수 있도록 노력하겠습니다.<br>즐겁고 편리한 쇼핑을
						즐기세요!
					</p>
					<input type="submit" value="메인으로 가기" id="signup1"
						onclick="location.href = '../Main&Category/main.do'" /><br /> <br />
				</div>
			</div>
		</div>

		<div id="bott">
			<jsp:include page="../section/footer.jsp" />
		</div>
	</div>

	</div>
</body>
</html>