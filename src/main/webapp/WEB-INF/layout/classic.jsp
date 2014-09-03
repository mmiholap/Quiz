<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="title" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title:insertAttribute name="include"/>

    <title><tiles:getAsString name="title"/></title>
</head>

<body>
<div id="wrap">
    <div class="navbar navbar-default navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="/">Quiz Service</a>
            </div>
            <div id="links" class="collapse navbar-collapse">
                <ul  class="nav navbar-nav">
                    <li class="active"><a href="/">Home</a></li>
                    <li><a href="#">About</a></li>
                    <li><a href="#contact">Contact</a></li>
                </ul>
                <form class="navbar-form navbar-right" role="form">
                    <div class="form-group">
                        <input type="text" placeholder="Email" class="form-control">
                    </div>
                    <div class="form-group">
                        <input type="password" placeholder="Password" class="form-control">
                    </div>
                    <button type="submit" class="btn btn-success">Sign in</button>
                </form>
            </div><!--/.nav-collapse -->
        </div>
    </div>
</div>

    <script type="text/javascript">
        $('.navbar li').click(function(e) {
            $('.navbar li.active').removeClass('active');
            var $this = $(this);
            if (!$this.hasClass('active')) {
                $this.addClass('active');
            }

            var value = $.cookie('test');
            if(!(value === undefined)){
                e.preventDefault();
            }
        });
    </script>

    <%--<div class="container">--%>
                <tiles:insertAttribute name="body" />
    <%--</div>--%>


<tiles:insertAttribute name="footer"/>

</body>
</html>
