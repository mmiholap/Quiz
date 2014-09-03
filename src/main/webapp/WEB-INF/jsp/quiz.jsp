<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<div class="jumbotron">
    <div class="container">
        <br><br>
        <div class="row">
            <div id="timer" class="col-md-2 col-lg-offset-1">
                <span id="minutes" class="label label-info">00</span>
                <span class="label label-info">:</span>
                <span id="seconds" class="label label-info">00</span>
            </div>
        </div>
        <div id="result" ></div>
        <p id="forButton" class="col-md-5 col-md-offset-5">
            <button id="nextBtn" type="button" class="btn btn-default btn-xs col-md-6">
                <h4>next question</h4>
            </button>
        </p>
    </div>
</div>

<script src="<spring:url value='/resources/js/quiz.js'/>"></script>