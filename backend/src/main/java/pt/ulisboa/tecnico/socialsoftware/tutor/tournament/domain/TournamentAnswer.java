package pt.ulisboa.tecnico.socialsoftware.tutor.tournament.domain;

import pt.ulisboa.tecnico.socialsoftware.tutor.answer.domain.QuestionAnswer;
import pt.ulisboa.tecnico.socialsoftware.tutor.config.DateHandler;
import pt.ulisboa.tecnico.socialsoftware.tutor.exceptions.TutorException;
import pt.ulisboa.tecnico.socialsoftware.tutor.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

import static pt.ulisboa.tecnico.socialsoftware.tutor.exceptions.ErrorMessage.QUESTION_ANSWER_NOT_FOUND;


@Entity
@Table(name = "tournament_answers")
public class TournamentAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private TournamentQuiz quiz;

    @Column(name = "begin_time")
    private LocalDateTime beginTime;

    @Column(name = "finish_date")
    private LocalDateTime finishTime;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tournamentAnswer", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<QuestionAnswer> questionAnswers = new HashSet<>();

    public TournamentAnswer() {}

    public TournamentAnswer(TournamentQuiz quiz, User user) {
        setQuiz(quiz);
        setUser(user);
        quiz.addTournamentAnswer(this);
        user.addTournamentAnswer(this);

        List<TournamentQuestion> tournamentQuestions = quiz.getTournamentQuestions();

        for (int seq = 0; seq < tournamentQuestions.size(); seq++) {
            new QuestionAnswer(this, tournamentQuestions.get(seq), seq);
        }
    }

    public Integer getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) { this.user = user; }

    public TournamentQuiz getQuiz() {
        return quiz;
    }

    public void setQuiz(TournamentQuiz quiz) { this.quiz = quiz; }

    public Set<QuestionAnswer> getQuestionsAnswers() { return questionAnswers; }

    public void addQuestionAnswer(QuestionAnswer questionAnswer) {
        questionAnswers.add(questionAnswer);
    }

    public LocalDateTime getBeginTime() {
        return beginTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public boolean hasStarted() { return beginTime != null; }

    public void start() { beginTime = DateHandler.now(); }

    public boolean isFinished() { return finishTime != null; }

    public void finish() { finishTime = DateHandler.now(); }

    public QuestionAnswer getQuestionAnswer(int sequence) {
        return questionAnswers.stream()
                .filter(questionAnswer -> questionAnswer.getSequence().equals(sequence))
                .findAny()
                .orElseThrow(() -> new TutorException(QUESTION_ANSWER_NOT_FOUND, sequence));
    }

    @Override
    public int hashCode() {
        return user.getId().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof TournamentAnswer &&
                user.getId().equals(((TournamentAnswer) obj).getUser().getId()) &&
                quiz.getTournament().getId().equals(((TournamentAnswer) obj).getQuiz().getTournament().getId());
    }
}
