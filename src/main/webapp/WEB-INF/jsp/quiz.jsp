<div class="jumbotron">
    <div class="container">
        <br><br><br><br>
        <div id="result" ></div>
        <p>
            <button id="nextBtn" type="button" class="btn btn-default btn-xs">
                <h4>next question</h4>
            </button>
        </p>
    </div>
</div>

<script type="text/javascript">
    function getQuestionAjax() {
        $.ajax({
            url : 'htmlquestion.html',
            success : function(data) {
                if(!isNaN(data)){
                    $.get("statistics/"+data, function(data){
                        $('#result').html(data);
                    });

                    if($.removeCookie('test')){
                    }

                } else {
                    $('#result').html(data);
                }
                $.cookie('state',data);
            }
        });
    }

    var answer_id = "-1";
    function sendAnswerId(){
        $.post('checkanswer/'+answer_id);
    }

    $('#nextBtn').click( function(){
        sendAnswerId();
        getQuestionAjax();
        answer_id = "-1";
    });


    $("body").on("change","input[name='answer']" ,function() {
        answer_id = $(this).val();
    });


    window.onload = function(){
        var value = $.cookie('test');
        if(value === undefined){
            $.cookie('test','run');
            getQuestionAjax();
        }

        var stateOnReload = $.cookie('state');
        if(!(stateOnReload === undefined)){
            $('#result').html(stateOnReload);
        }
    };
</script>

<script type="text/javascript">
    var intervalId = 0;
    //intervalId = setInterval(getQuestionAjax, 10000);
</script>