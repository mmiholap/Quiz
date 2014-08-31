package com.miholap.quiz.controller;

import com.miholap.quiz.componentBeans.QuizMB;
import com.miholap.quiz.persistence.entities.Answer;
import com.miholap.quiz.persistence.entities.Question;
import com.miholap.quiz.persistence.entities.Quiz;
import com.miholap.quiz.services.IAnswerService;
import com.miholap.quiz.services.IQuestionService;
import com.miholap.quiz.services.IQuizService;
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
    IAnswerService answerService;

    @RequestMapping(value = "/quiz/{id}")
    public String startQuizWithId(@PathVariable("id") int id) {
        quizManager.initManager(id,5);
        return "quiz";
    }


    @RequestMapping(value = "/quiz/ajaxquestion", method = RequestMethod.GET)
    public @ResponseBody String getQuestions() {
        System.out.println("Debug Message from AjaxJQuery Controller.." + new Date().toString());

        StringBuilder result= new StringBuilder(500);

        if(!quizManager.hasNext()){
            return "<p>Test complete</p>";
        }

        Question question = quizManager.next();
        System.out.println("DEBUG "+question);
        result.append("<label for=\"name\"><h3>"+question.getText()+"</h3> </label>\n");
        for(Answer answer : answerService.getActiveAnswers(question)){
            result.append(  "<div class=\"radio\">\n" +
                    "   <label>\n" +
                    "      <input type=\"radio\" name=\"answer\" id=\"answer_"+answer.getId()+"\" \n" +
                    "         value=\""+answer.getId()+"\" ><p>"+ answer.getText()+"</p>\n" +
                    "   </label>\n" +
                    "</div>\n");
        }
        result.append("<hr>\n");

        return result.toString();
    }

/*    @RequestMapping(value = "/quiz/ajaxquestion", method = RequestMethod.GET)
    public @ResponseBody String getQuestions() {
        System.out.println("Debug Message from AjaxJQuery Controller.." + new Date().toString());
        Quiz quiz = quizService.findById(1);

        StringBuilder result= new StringBuilder(500);

        for(Question question : questionService.getNRandomQuestions(quiz,3)){
            result.append("<label for=\"name\"><h3>"+question.getText()+"</h3> </label>\n");
            for(Answer answer : answerService.getActiveAnswers(question)){
                result.append(  "<div class=\"radio\">\n" +
                        "   <label>\n" +
                        "      <input type=\"radio\" name=\"answer\" id=\"answer_"+answer.getId()+"\" \n" +
                        "         value=\""+answer.getId()+"\" ><p>"+ answer.getText()+"</p>\n" +
                        "   </label>\n" +
                        "</div>\n");
            }
            result.append("<hr>\n");
        }
        return result.toString();
    }*/
}
