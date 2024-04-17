<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<style>
	table,th,td{
		border: 1px solid black;
		border-collapse: collapse;
	}
	th,td{
		padding: 5px 10px;
	}
	input[name="id"] {
		width: 70%;
	}
</style>
</head>
<body>
	<h3>회원가입</h3>
	<hr>
	<form action="join" method="post">
		<table>
			<tr>
				<th>ID</th>
				<td>
					<input type="text" name="id" >
					<input type="button" value="중복체크" onclick="overlay()">
				</td>
			</tr>
			<tr>
				<th>PW</th>
				<td><input type="password" name="pw"></td>
			</tr>
			<tr>
				<th>PW 확인</th>
				<td>
					<input type="password" id="confirm">
					<span id="msg"></span>
				</td>
			</tr>
			<tr>
				<th>관리자</th>
				<td><input type="checkbox" name="auth">관리자 여부</td>
			</tr>
			<tr>
				<th>NAME</th>
				<td><input type="text" name="name"></td>
			</tr>
			<tr>
				<th>AGE</th>
				<td><input type="text" name="age"></td>
			</tr><tr>
				<th>GENDER</th>
				<td>
				<input type="radio" name="gender" value="남"/>남자
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="radio" name="gender" value="여"/>여자	
				</td>
			</tr>
			<tr>
				<th>EMAIL</th>
				<td><input type="text" name="email"></td>
			</tr>
			<tr>
				<th colspan="2"><button type="button" onclick="join()">회원가입</button></th>
			</tr>
		</table>
	</form>
</body>
<script>
	function overlay() {
		var id = $('input[name="id"]').val();
		
		$.ajax({
			type:'post',
			url:'overlay',
			data:{'id':id},
			success:function(data){
				console.log(data.use);
				if (data.use>0) {
					alert('이미 있는 아이디 입니다.');
				}else{
					alert('사용 가능한 아이디 입니다.');
					overChk = true;
				}
			},
			error:function(error){
				console.log(error);
			}
		})
		
	}
	
	var overChk = false;
	
	function join() {
		
		var $id = $('input[name="id"]');
		var $pw = $('input[name="pw"]');
		var $auth = $('input[name="auth"]:checked');
		var $name = $('input[name="name"]');		
		var $age = $('input[name="age"]');
		var $gender = $('input[name="gender"]:checked');
		var $email = $('input[name="email"]');
		
		if (overChk == false) {
			alert('중복 체크를 해주세요');
			$id.focus();
			return false;
		}else if ($id.val() == '') {
			alert('아이디를 입력 해 주세요!');
			$id.focus();
		}else if ($pw.val() == '') {
			alert('비밀번호를 입력 해 주세요!');
			$pw.focus();
		}else if ($name.val() == '') {
			alert('이름을 입력 해 주세요!');
			$name.focus();
		}else if ($age.val() == '') {
			alert('나이를 입력 해 주세요!');
			$age.focus();	
		}else if ($gender.val() == null) {
			alert('성별을 체크 해 주세요!');	
		}else if ($email.val() == '') {
			alert('이메일을 입력 해 주세요!');	
			$email.focus();
		}else{
			var regExp = new RegExp('[a-zA-zㄱ-ㅎ가-힣]');
			if (regExp.test($age.val())) {
				alert('숫자만 입력하세요!');
				$age.val('');
				$age.focus();
				return false;
			}
			
			$('form').submit();
		
		}
		
	}
	
	$('#confirm').on('keyup',function(){
		if ($('input[name="pw"]').val() == $(this).val()) {
			$('#msg').html('비밀번호가 일치합니다.');
			$('#msg').css({'color':'green'});
			
		}else{
			$('#msg').html('비밀번호가 일치하지 않습니다..');
			$('#msg').css({'color':'red'});
		}
	})
	
	var msg = '${msg}';
	if (msg !== '') {
	    alert(msg); 
	}
	
	
</script>
</html>