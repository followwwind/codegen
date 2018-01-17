<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>add or update</title>
<style type="text/css">
	table {
		width: 70%;
		border-spacing:0; 
		color:green;
		margin: 10px auto;
	}
	td {
		padding-top:10px;
		padding-left: 10px;
		padding-bottom: 10px;	
	}
	font {
		color: black;
	}
</style>
</head>
<body>
	<div>
		<fieldset>
			<legend align="center">
				注册页面
			</legend>
			<form action="http://127.0.0.1:8080" method="post">
				<table border="1">
					<tr>
						<th colspan="3">
							<font>注册页面</font>
						</th>
					</tr>
					<tr>
						<td>
							<font>用户名:</font>
						</td>
						<td colspan="2">
							<input type="text" name="user" style="width: 300px;">
						</td>
					</tr>
					
					<tr>
						<td>
							<font>密码:</font>
						</td>
						<td colspan="2">
							<input type="password" name="psw" style="width: 300px;">
						</td>
					</tr>
					<tr>
						<td>
							<font>确认密码:</font>
						</td>
						<td colspan="2">
							<input type="password" name="ps" style="width: 300px;">
						</td>
					</tr>
					<tr>
						<td>
							<font>性别:</font>
						</td>
						<td colspan="2">
							<input type="radio" name="sex" value="man">
							<font>男</font>
							<input type="radio" name="sex" value="women">
							<font>女</font>
						</td>
					</tr>
					<tr>
						<td>
							<font>技术:</font>
						</td>
						<td colspan="2">
							<input type="checkbox" name="technology" value="java">
							<font>java</font>
							<input type="checkbox" name="technology" value="html">
							<font>html</font>
							<input type="checkbox" name="technology" value="jsp">
							<font>jsp</font>
						</td>
					</tr>
					<tr>
						<td>
							<font>国家:</font>
						</td>
						<td colspan="2">
							<select name="country">
								<option value="select">
									---选择国家---
								</option>
								<option value="USA">
									美国
								</option>
								<option value="England">
									英国
								</option>
								<option value="China">
									中国
								</option>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="3" align="center">
							<input type="submit" value="提交数据">
							<input type="reset" value="清除数据">
						</td>
					</tr>
				</table>
			</form>
		</fieldset>
	</div>
</body>
</html>


