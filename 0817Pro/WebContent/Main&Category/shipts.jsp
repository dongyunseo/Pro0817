<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="../css/header.css">
<link rel="stylesheet" href="../css/category.css">
<link rel="stylesheet" href="../css/footer.css">
</head>
<body>
<body>
	<div id="top">
		<div id="header">
			<jsp:include page="../section/header.jsp" />
		</div>
	</div>
	<div class="listbox">
		<h3 style="width: 100%; text-align: center;">SHIPTS</h3>
		<c:forEach items="${dtos}" var="dto">
			<ul class="dressimg">
				<li><a
					href="../dressDetails/dressDetails_view.do?dressid=${dto.dressid }">${dto.dressimg }</a>
				</li>
				<li>${dto.dressname }</li>
				<li>${dto.price }원</li>
			</ul>
		</c:forEach>
	</div>
	<div id="bott">
		<jsp:include page="../section/footer.jsp" />
	</div>
	</div>

</body>
</body>
</html>