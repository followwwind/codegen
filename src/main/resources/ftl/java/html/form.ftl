<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>add or update</title>
    <style type="text/css">
        .content{
            margin: 240px auto;
        }
        table {
            width: 30%;
            border-spacing:0;
            color:green;
            margin: 10px auto;
        }
        td {
            padding-top:10px;
            padding-left: 10px;
            padding-bottom: 10px;
        }
        .black {
            color: black;
            text-align: right;
        }
    </style>
</head>
<body>
<div class="content">
    <!--<fieldset class="content">
        <legend align="center">
            注册页面
        </legend>-->
    <form action="http://127.0.0.1:8080" method="post">
        <table border="1">
            <tr>
                <th colspan="3">
                    <span class="black">注册页面</span>
                </th>
            </tr>
            <tr>
                <td>
                    <span class="black">用户名:</span>
                </td>
                <td colspan="2">
                    <input type="text" name="user" style="width: 300px;">
                </td>
            </tr>

            <tr>
                <td>
                    <span class="black">密码:</span>
                </td>
                <td colspan="2">
                    <input type="password" name="psw" style="width: 300px;">
                </td>
            </tr>
            <tr>
                <td>
                    <span class="black">确认密码:</span>
                </td>
                <td colspan="2">
                    <input type="password" name="ps" style="width: 300px;">
                </td>
            </tr>
            <tr>
                <td>
                    <span class="black">性别:</span>
                </td>
                <td colspan="2">
                    <input type="radio" name="sex" value="man">
                    <span class="black">男</span>
                    <input type="radio" name="sex" value="women">
                    <span class="black">女</span>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="black">技术:</span>
                </td>
                <td colspan="2">
                    <input type="checkbox" name="technology" value="java">
                    <span class="black">java</span>
                    <input type="checkbox" name="technology" value="html">
                    <span class="black">html</span>
                    <input type="checkbox" name="technology" value="jsp">
                    <span class="black">jsp</span>
                </td>
            </tr>
            <tr>
                <td>
                    <span class="black">国家:</span>
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
    <!--</fieldset>-->
</div>
</body>
</html>


