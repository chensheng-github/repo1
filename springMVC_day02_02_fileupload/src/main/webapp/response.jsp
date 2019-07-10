<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery.min.js"></script>
    <script>
        $(function () {
            $("#btn").click(function () {
               $.ajax({
                   type:"post",
                   url:"user/testAjax",
                   contentType:"application/json;charset=utf-8",
                   data:'{"username":"张三","password":"123"}',
                   dataType:"json",
                   success:function (data) {

                       alert(data.username);
                       alert(data.password);


                   }




               })


            });

        });
    </script>
</head>
<body>
<a href="user/testString">testString</a><br>
<a href="user/testVoid">testVoid</a><br>
<a href="user/testModelAndView">testModelAndView</a><br>
<a href="user/testForwardOrRedirect">testForwardOrRedirect</a><br>
<button id="btn">发送ajax请求</button>

</body>
</html>
