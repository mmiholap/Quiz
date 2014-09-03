package com.miholap.quiz.controller;

import com.miholap.quiz.componentBeans.QuizMB;
import com.miholap.quiz.persistence.entities.Answer;
import com.miholap.quiz.persistence.entities.Question;
import com.miholap.quiz.persistence.entities.Quiz;
import com.miholap.quiz.persistence.entities.Statistics;
import com.miholap.quiz.services.IAnswerService;
import com.miholap.quiz.services.IQuestionService;
import com.miholap.quiz.services.IQuizService;
import com.miholap.quiz.services.IStatisticsService;
import com.sun.org.glassfish.external.statistics.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@Scope("request")
public class QuizController {

    @Autowired
    QuizMB quizManager;
    @Autowired
    IStatisticsService statisticsService;

    @RequestMapping(value = "/quiz/{id}")
    public String startQuizWithId(@PathVariable("id") int id) {
        if(quizManager.getQuiz() == null) {
            quizManager.initManager(id,5);
        }

        return "quiz";
    }


    @RequestMapping(value = "/quiz/htmlquestion", method = RequestMethod.GET)
    public @ResponseBody String getQuestions() {
        System.out.println("Debug Message from AjaxJQuery Controller.." + new Date().toString());
        StringBuilder result= new StringBuilder(500);

        if(!quizManager.hasNext()){
            quizManager.calculateStatistics();
            String statisticsId = ""+quizManager.getStatistics().getId();
            quizManager.resetManager();
            return statisticsId;
        }

        Question question = quizManager.next();
        System.out.println("DEBUG "+question);

        result.append("<hr class=\"featurette-divider\">\n");
        result.append(
                "<div class=\"row featurette\">\n" +
                "   <div class=\"col-md-5 \">\n" +
                "          <img class=\"img-circle\" src=\""+question.getImagePath()+"\" style=\"width: 320px; height: 320px;\" >\n" +
                "   </div>\n" +
                "   <div class=\"col-md-7\">\n");
        result.append(
                "   <h2 class=\"featurette-heading\">"+question.getText()+"</h2>");
        for(Answer answer : quizManager.getAnswersForCurrentQuestion()){
            result.append(
                    "   <div class=\"radio \" >\n" +
                    "       <label>\n" +
                    "           <input type=\"radio\" name=\"answer\" id=\"answer_"+answer.getId()+"\" \n" +
                    "               value=\""+answer.getId()+"\" /><p>"+ answer.getText()+"</p>\n" +
                    "       </label>\n" +
                    "   </div>\n");
        }
        result.append(
                    "</div>\n" +//close <div class="col-md-7">
                "</div>\n");// close <div class="row featurette">
        result.append("<hr class=\"featurette-divider\">\n");


        return result.toString();
    }


    @RequestMapping(value = "/quiz/checkanswer/{id}", method = RequestMethod.POST)
    public @ResponseBody String checkAnswer(@PathVariable("id") int answer_id) {
        System.out.println("ANSWER ID = "+answer_id);
        quizManager.addAnswerToStatistics(answer_id);
        return "good";
    }


    @RequestMapping(value = "/quiz/statistics/{id}", method = RequestMethod.GET)
    public @ResponseBody String quizStatistics(@PathVariable("id") int statistics_id) {
        Statistics statistics = statisticsService.findById(statistics_id);
        if(statistics == null){
            return "statistics not found";
        }
        int percent = (statistics.getRightAnswers()*100)/statistics.getQuestions();

        String progressBarStatus="progress-bar-success";
        String panelStatus = "panel-success";
        if(percent < 20){
            progressBarStatus = "progress-bar-danger";
            panelStatus = "panel-danger";
        } else if(percent < 60){
            progressBarStatus = "progress-bar-warning";
            panelStatus = "panel-warning";
        } else if (percent < 90){
            progressBarStatus = "progress-bar-info";
            panelStatus="panel-info";
        }

        //statistics.getRightAnswers()+"/"+statistics.getQuestions()
        String result =
                "<div class=\"panel "+panelStatus+"\">"+
                    "<div class=\"panel-heading\"><h3 class=\"text-center\">Quiz result</h3></div>"+
                    "<div class=\"panel-body\">"+
                        "<div class=\"progress\">\n" +
                        "  <div class=\"progress-bar "+ progressBarStatus +"\""+
                                " role=\"progressbar\"  aria-valuemin=\"0\" aria-valuemax=\""
                                +statistics.getQuestions()+"\" style=\"width: "+percent+"%\">\n" +
                        "    <span style=\"color:black;\">"+percent+"%</span>\n" +
                        "  </div>\n" +
                        "</div>"+
                        "<h4 class=\"text-center\">You have correctly answered "+statistics.getRightAnswers()+
                                        " of "+statistics.getQuestions()+" questions.</h4>"+
                    "</div>"+
                "</div>";
        return result;
    }
}
