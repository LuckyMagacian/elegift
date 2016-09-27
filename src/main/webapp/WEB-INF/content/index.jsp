<%--
  Created by IntelliJ IDEA.
  User: summer
  Date: 16-8-15
  Time: 上午11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="//cdn.bootcss.com/jquery/1.12.4/jquery.js"></script>
</head>
<body>
<script>
    $().ready(function () {
        $.ajax({
            url:'user/register',
            type:'POST',
            data:"{'username':'sumasdfmer','password':'suadsfmmer','age':25}",
            dateType:'json',
            success:function (data) {
                alert(data);
            }
        });
    });
</script>
</body>
</html>
