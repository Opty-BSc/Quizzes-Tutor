package pt.ulisboa.tecnico.socialsoftware.tutor.clarification.service

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.beans.factory.annotation.Autowired
import pt.ulisboa.tecnico.socialsoftware.tutor.user.dto.UserDto
import spock.lang.Specification
import java.time.LocalDateTime

import pt.ulisboa.tecnico.socialsoftware.tutor.course.Course
import pt.ulisboa.tecnico.socialsoftware.tutor.course.CourseRepository

import pt.ulisboa.tecnico.socialsoftware.tutor.course.CourseExecution
import pt.ulisboa.tecnico.socialsoftware.tutor.course.CourseExecutionRepository

import pt.ulisboa.tecnico.socialsoftware.tutor.user.User
import pt.ulisboa.tecnico.socialsoftware.tutor.user.UserRepository

import pt.ulisboa.tecnico.socialsoftware.tutor.quiz.domain.Quiz
import pt.ulisboa.tecnico.socialsoftware.tutor.quiz.repository.QuizRepository

import pt.ulisboa.tecnico.socialsoftware.tutor.answer.domain.QuizAnswer
import pt.ulisboa.tecnico.socialsoftware.tutor.answer.repository.QuizAnswerRepository

import pt.ulisboa.tecnico.socialsoftware.tutor.answer.domain.QuestionAnswer
import pt.ulisboa.tecnico.socialsoftware.tutor.answer.repository.QuestionAnswerRepository

import pt.ulisboa.tecnico.socialsoftware.tutor.clarification.domain.ClarificationRequest
import pt.ulisboa.tecnico.socialsoftware.tutor.clarification.repository.ClarificationRequestRepository

import pt.ulisboa.tecnico.socialsoftware.tutor.clarification.dto.ClarificationCommentDto
import pt.ulisboa.tecnico.socialsoftware.tutor.clarification.ClarificationCommentService
import pt.ulisboa.tecnico.socialsoftware.tutor.clarification.repository.ClarificationCommentRepository

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean

@DataJpaTest
class SubmitClarificationCommentPerformanceTest extends Specification {

    public static final String CLARIFICATION_CONTENT = "ClarificationRequest Question"
    public static final String COMMENT_CONTENT = "Teacher Answer"

    @Autowired
    ClarificationCommentService clarificationCommentService

    @Autowired
    UserRepository userRepository

    @Autowired
    CourseRepository courseRepository

    @Autowired
    CourseExecutionRepository courseExecutionRepository

    @Autowired
    QuizRepository quizRepository

    @Autowired
    QuizAnswerRepository quizAnswerRepository

    @Autowired
    QuestionAnswerRepository questionAnswerRepository

    @Autowired
    ClarificationRequestRepository clarificationRequestRepository

    @Autowired
    ClarificationCommentRepository clarificationCommentRepository


    def user
    def userDto
    def questionAnswer
    def clarificationRequest
    def clarificationCommentDto

    def setup (){
        def course = new Course()
        course.setName("course")
        courseRepository.save(course)

        def courseExecution = new CourseExecution()
        courseExecution.setCourse(course)
        courseExecutionRepository.save(courseExecution)

        user = new User("Name", "Username", 1, User.Role.TEACHER)
        user.addCourse(courseExecution)
        courseExecution.addUser(user)
        userRepository.save(user)
        userDto = new UserDto(user)

        def quiz = new Quiz()
        quiz.setKey(1)
        quiz.setCourseExecution(courseExecution)
        quizRepository.save(quiz)

        def quizAnswer = new QuizAnswer()
        quizAnswer.setQuiz(quiz)
        quizAnswerRepository.save(quizAnswer)

        questionAnswer = new QuestionAnswer()
        questionAnswer.setQuizAnswer(quizAnswer)
        questionAnswerRepository.save(questionAnswer)

        1.upto(1, {
            clarificationRequest = new ClarificationRequest()
            clarificationRequest.setState(ClarificationRequest.State.UNRESOLVED)
            clarificationRequest.setContent(CLARIFICATION_CONTENT)
            clarificationRequest.setQuestionAnswer(questionAnswer)
            clarificationRequestRepository.save(clarificationRequest)
        })
    }

    def "performance test creating 10000 clarification comments"() {
        given: "a clarification comment dto"
        def creationDate = LocalDateTime.now()
        clarificationCommentDto = new ClarificationCommentDto()
        clarificationCommentDto.setContent(COMMENT_CONTENT)
        clarificationCommentDto.setUser(userDto)
        clarificationCommentDto.setCreationDate(creationDate)

        when:
        1.upto(1, {
            clarificationCommentService.createClarificationComment(it, clarificationCommentDto)
        })

        then:
        true
    }

    @TestConfiguration
    static class SubmitClarificationCommentServiceImplPerformanceTestContextConfiguration {

        @Bean
        ClarificationCommentService ClarificationCommentService() {
            return new ClarificationCommentService()
        }
    }
}