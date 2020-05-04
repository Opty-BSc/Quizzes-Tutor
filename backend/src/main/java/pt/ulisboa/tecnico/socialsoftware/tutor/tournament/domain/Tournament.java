package pt.ulisboa.tecnico.socialsoftware.tutor.tournament.domain;

import static pt.ulisboa.tecnico.socialsoftware.tutor.exceptions.ErrorMessage.*;

import pt.ulisboa.tecnico.socialsoftware.tutor.config.DateHandler;
import pt.ulisboa.tecnico.socialsoftware.tutor.course.CourseExecution;
import pt.ulisboa.tecnico.socialsoftware.tutor.exceptions.TutorException;
import pt.ulisboa.tecnico.socialsoftware.tutor.question.domain.Topic;
import pt.ulisboa.tecnico.socialsoftware.tutor.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(name = "tournaments")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "course_execution_id")
    private CourseExecution courseExecution;

    @ManyToOne
    @JoinColumn(name = "creator_id")
    private User creator;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Topic> topics;

    @Column(name = "nr_questions")
    private Integer nrQuestions;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "quiz_id")
    private TournamentQuiz quiz;

    public Tournament() {}

    public Tournament(String name, User creator, Set<Topic> topics, CourseExecution courseExecution, Integer nrQuestions, LocalDateTime startTime, LocalDateTime endTime) {
        setName(name);
        setCreator(creator);
        setTopics(topics);
        setCourseExecution(courseExecution);
        setNrQuestions(nrQuestions);
        setStartTime(startTime);
        setEndTime(endTime);

        if (!isOpened()) { throw new TutorException(TOURNAMENT_START_TIME_INVALID, startTime.toString()); }
        if (startTime.compareTo(endTime) >= 0) {
            throw new TutorException(TOURNAMENT_END_TIME_INVALID, endTime.toString());
        }
    }

    public Integer getId() { return id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Set<Topic> getTopics() { return topics; }

    public void setTopics(Set<Topic> topics) {
        if (topics == null || topics.isEmpty()) { throw new TutorException(TOPIC_NOT_FOUND, -1); }
        topics.forEach(topic -> {
            if (topic == null || topic.getStatus() != null && topic.getStatus() != Topic.Status.AVAILABLE) {
                throw new TutorException(TOPIC_NOT_AVAILABLE, topic == null ? -1 : topic.getId());
            }
        });
        this.topics = topics;
    }

    public Integer getNrQuestions() { return nrQuestions; }

    public void setNrQuestions(Integer nrQuestions) {
        if (nrQuestions == null || nrQuestions <= 0) { throw new TutorException(TOURNAMENT_NR_QUESTIONS_INVALID); }
        this.nrQuestions = nrQuestions;
    }

    public TournamentQuiz getQuiz() {
        if (quiz == null) { setQuiz(); }
        return quiz;
    }

    public void setQuiz() { quiz = new TournamentQuiz(this); }

    public Set<User> getPlayers() {
        return getQuiz().getTournamentAnswers().stream()
                .map(TournamentAnswer::getUser)
                .collect(Collectors.toSet());
    }

    public CourseExecution getCourseExecution() { return courseExecution; }

    public void setCourseExecution(CourseExecution courseExecution) {
        if (courseExecution == null) { throw new TutorException(COURSE_EXECUTION_NOT_FOUND, -1); }
        if (courseExecution.getStatus() != CourseExecution.Status.ACTIVE) {
            throw new TutorException(COURSE_EXECUTION_NOT_ACTIVE, courseExecution.getId());
        }
        this.courseExecution = courseExecution;
    }

    public User getCreator() { return creator; }

    public void setCreator(User creator) {
        if (creator == null || creator.getRole() != User.Role.STUDENT) {
            throw new TutorException(USER_NOT_STUDENT, creator == null ? -1 : creator.getId());
        }
        this.creator = creator;
    }

    public LocalDateTime getStartTime() { return startTime; }

    public void setStartTime(LocalDateTime startTime) {
        if (startTime == null) { throw new TutorException(TOURNAMENT_START_TIME_INVALID, ""); }
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() { return endTime; }

    public void setEndTime(LocalDateTime endTime) {
        if (endTime == null) { throw new TutorException(TOURNAMENT_END_TIME_INVALID, ""); }
        this.endTime = endTime;
    }

    public boolean isOpened() { return startTime.compareTo(DateHandler.now()) > 0; }

    public boolean isClosed() { return DateHandler.now().compareTo(endTime) > 0; }

    public boolean isInProgress() { return !(isOpened() || isClosed()); }

    public boolean isAcceptingResponses() { return isInProgress() && getQuiz().isGenerated(); }

    public void enroll(User player) {
        if (player == null || player.getRole() != User.Role.STUDENT) {
            throw new TutorException(USER_NOT_STUDENT, player == null ? -1 : player.getId());
        }
        if (!isOpened()) { throw new TutorException(TOURNAMENT_NOT_OPENED); }
        new TournamentAnswer(getQuiz(), player);
    }
}
