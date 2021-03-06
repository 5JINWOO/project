<!DOCTYPE html>
<html lang="ko">
<head>
<title>TWOHAND</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" type="image/x-icon" href="../../image/favicon.png" />
<script src="https://code.jquery.com/jquery-3.5.1.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="/css/style.css">
<link rel="stylesheet" type="text/css" href="/css/index.css">
<link rel="stylesheet" type="text/css" href="/css/bootstrap.css">

<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://kit.fontawesome.com/6efa8ca364.js"></script>
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.text.SimpleDateFormat" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal" />
</sec:authorize>
</head>
<body>
	<nav class="navbar navbar-expand-md hd-navbar">
	    <div class = "navbar_logo">
            <i class="fa-solid fa-hands-clapping"></i>
            <a href="/"> TWOHAND</a>
        </div>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse navbar_menu" id="collapsibleNavbar">
			<c:choose>
				<c:when test="${empty principal }">
					<ul class="navbar-nav" id="na">
					    <li class="nav-item"><a class="nav-link"
                            href="/">HOME</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/auth/loginForm">LOGIN</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/auth/joinForm">REGISTER</a></li>
					</ul>
				</c:when>
				<c:otherwise>
					<ul class="navbar-nav">
					    <li class="nav-item"><a class="nav-link" href="/">HOME</a>
					    </li>
						<li class="nav-item"><a class="nav-link" href="/board/list">Market</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="/user/updateForm">My account</a>
						</li>
						<li class="nav-item"><a class="nav-link" href="/logout">Logout</a>
						</li>
					</ul>
				</c:otherwise>
			</c:choose>
		</div>

	</nav>
