var questionTimeInterval = 0;
var answer_id = "-1";
var timerInterval;
var totalSeconds = 30;
var minutesLabel = document.getElementById("minutes");
var secondsLabel = document.getElementById("seconds");

function getQuestionAjax() {
    $.ajax({
        url : 'htmlquestion.html',
        success : function(data) {
            if(!isNaN(data)){
                $.get("statistics/"+data, function(data){
                    $('#result').html(data);
                });

                $.removeCookie('test');
                $.removeCookie('state');
                $.removeCookie('time');

                window.clearInterval(questionTimeInterval);
                $("#nextBtn").remove();
                $("#timer").remove();

            } else {
                totalSeconds = 30;
                window.clearInterval(questionTimeInterval);
                questionTimeInterval = setInterval(stackedAction, totalSeconds*1000);
                $('#result').html(data);
                $.cookie('state',data);
            }

        }
    });
}

function sendAnswerId(){
    $.post('checkanswer/'+answer_id);
}

function stackedAction(){
    sendAnswerId();
    getQuestionAjax();
    answer_id = "-1";
}
$('#nextBtn').click( function(){
    stackedAction();
});


$("body").on("change","input[name='answer']" ,function() {
    answer_id = $(this).val();
});



window.onload = function(){
    var value = $.cookie('test');
    if(value === undefined){
        $.cookie('test','run');
        $.removeCookie('time');
        getQuestionAjax();
    }

    var stateOnReload = $.cookie('state');
    if(!(stateOnReload === undefined)){
        $('#result').html(stateOnReload);
    }

    var secondsLeft = $.cookie('time');
    if(!(secondsLeft === undefined)){

        totalSeconds = parseInt(secondsLeft);
//        alert(totalSeconds);
        window.clearInterval(questionTimeInterval);
    }
    questionTimeInterval = setInterval(stackedAction, totalSeconds*1000);
    setTime();

};


timerInterval = setInterval(setTime, 1000);

function setTime()
{
    secondsLabel.innerHTML = pad(totalSeconds%60);
    minutesLabel.innerHTML = pad(parseInt(totalSeconds/60));
    --totalSeconds;
    $.cookie('time',totalSeconds+'');
}

function pad(val)
{
    var valString = val + "";
    if(valString.length < 2)
    {
        return "0" + valString;
    }
    else
    {
        return valString;
    }
}


