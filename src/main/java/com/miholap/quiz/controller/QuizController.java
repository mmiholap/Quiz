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
        result.append("<label for=\"name\"><h3>"+question.getText()+"</h3> </label>\n");
        for(Answer answer : quizManager.getAnswersForCurrentQuestion()){
            result.append(  "<div class=\"radio\">\n" +
                    "   <label>\n" +
                    "      <input type=\"radio\" name=\"answer\" id=\"answer_"+answer.getId()+"\" \n" +
                    "         value=\""+answer.getId()+"\" /><p>"+ answer.getText()+"</p>\n" +
                    "   </label>\n" +
                    "</div>\n");
        }
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
        //aria-valuenow=""+statistics.getRightAnswers()+""
        //class="sr-only"
        String result =
                "<div class=\"progress\">\n" +
                "  <div class=\"progress-bar progress-bar-success\" role=\"progressbar\"  aria-valuemin=\"0\" aria-valuemax=\""+statistics.getQuestions()+"\" style=\"width: "+percent+"%\">\n" +
                "    <span >"+statistics.getRightAnswers()+"</span>\n" +
                "  </div>\n" +
                "</div>";
        return result;
    }
}
