<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="jumbotron">
    <div class="container">
        <h1>Quizzes  </h1>
        <p class="text-justify">
            Your route map to the exciting exploration journey into the fascinating world of Internet tests!
        Here you can find a lot of tests in different categories, and you can choose any of them depending where your interests lay.
        Fun, Personality, IQ tests, Love and Relationship, Quizzes, Fan Tests - everything is possible on quizzes.com.
        Want to check your knowledge in particular disciplines - here you are,
        anxious about your new relationship - don’t hesitate and test your second half,
        just in a mood to relax on a lazy sunny afternoon - a perfect fun test collection is waiting for you.
        </p>
        <p>
            <a class="btn btn-lg btn-primary" href="/" role="button">Home</a>
        </p>
    </div>
</div>

<div class="container">
<!-- Carousel
================================================== -->
<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner">
        <c:forEach items="${quizs}" var="quiz" >
            <div class="item active">
                <img src="/resources/images/capitalsQuiz.jpg" alt="First slide">
                <div class="container">
                    <div class="carousel-caption">
                        <h1>${quiz.title}</h1>
                        <p> ${quiz.description}</p>
                        <p><a class="btn btn-lg btn-primary" href="/quiz/${quiz.id}" role="button">Start quiz</a></p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
</div><!-- /.carousel -->

<hr>
</div>