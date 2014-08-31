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
    function callAjax() {
        $.ajax({
            url : 'ajaxquestion.html',
            success : function(data) {
                $('#result').html(data);
            }
        });
    }

    $('#nextBtn').click( function(){
        callAjax();
    });

    window.onload = callAjax;
</script>

<script type="text/javascript">
    var intervalId = 0;
    intervalId = setInterval(callAjax, 10000);
</script>