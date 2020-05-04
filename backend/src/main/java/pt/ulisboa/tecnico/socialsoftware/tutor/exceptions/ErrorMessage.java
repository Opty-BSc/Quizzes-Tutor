package pt.ulisboa.tecnico.socialsoftware.tutor.exceptions;

public enum ErrorMessage {

    INVALID_ACADEMIC_TERM_FOR_COURSE_EXECUTION("Invalid academic term for course execution"),
    INVALID_ACRONYM_FOR_COURSE_EXECUTION("Invalid acronym for course execution"),
    INVALID_CONTENT_FOR_OPTION("Invalid content for option"),
    INVALID_CONTENT_FOR_QUESTION("Invalid content for question"),
    INVALID_NAME_FOR_COURSE("Invalid name for course"),
    INVALID_NAME_FOR_TOPIC("Invalid name for topic"),
    INVALID_SEQUENCE_FOR_OPTION("Invalid sequence for option"),
    INVALID_SEQUENCE_FOR_QUESTION_ANSWER("Invalid sequence for question answer"),
    INVALID_TITLE_FOR_ASSESSMENT("Invalid title for assessment"),
    INVALID_TITLE_FOR_QUESTION("Invalid title for question"),
    INVALID_URL_FOR_IMAGE("Invalid url for image"),
    INVALID_TYPE_FOR_COURSE("Invalid type for course"),
    INVALID_TYPE_FOR_COURSE_EXECUTION("Invalid type for course execution"),
    INVALID_AVAILABLE_DATE_FOR_QUIZ("Invalid available date for quiz"),
    INVALID_CONCLUSION_DATE_FOR_QUIZ("Invalid conclusion date for quiz"),
    INVALID_RESULTS_DATE_FOR_QUIZ("Invalid results date for quiz"),
    INVALID_TITLE_FOR_QUIZ("Invalid title for quiz"),
    INVALID_TYPE_FOR_QUIZ("Invalid type for quiz"),
    INVALID_QUESTION_SEQUENCE_FOR_QUIZ("Invalid question sequence for quiz"),

    ASSESSMENT_NOT_FOUND("Assessment not found with id %d"),
    COURSE_EXECUTION_NOT_FOUND("Course execution not found with id %d"),
    OPTION_NOT_FOUND("Option not found with id %d"),
    QUESTION_ANSWER_NOT_FOUND("Question answer not found with id %d"),
    QUESTION_NOT_FOUND("Question not found with id %d"),
    QUIZ_ANSWER_NOT_FOUND("Quiz answer not found with id %d"),
    QUIZ_NOT_FOUND("Quiz not found with id %d"),
    QUIZ_QUESTION_NOT_FOUND("Quiz question not found with id %d"),
    TOPIC_CONJUNCTION_NOT_FOUND("Topic Conjunction not found with id %d"),
    TOPIC_NOT_FOUND("Topic not found with id %d"),
    USER_NOT_FOUND("User not found with id %d"),
    COURSE_NOT_FOUND("Course not found with name %s"),

    CANNOT_DELETE_COURSE_EXECUTION("The course execution cannot be deleted %s"),
    USERNAME_NOT_FOUND("Username %d not found"),

    QUIZ_USER_MISMATCH("Quiz %s is not assigned to student %s"),
    QUIZ_MISMATCH("Quiz Answer Quiz %d does not match Quiz Question Quiz %d"),
    QUESTION_OPTION_MISMATCH("Question %d does not have option %d"),
    COURSE_EXECUTION_MISMATCH("Course Execution %d does not have quiz %d"),

    DUPLICATE_TOPIC("Duplicate topic: %s"),
    DUPLICATE_USER("Duplicate user: %s"),
    DUPLICATE_COURSE_EXECUTION("Duplicate course execution: %s"),

    USERS_IMPORT_ERROR("Error importing users: %s"),
    QUESTIONS_IMPORT_ERROR("Error importing questions: %s"),
    TOPICS_IMPORT_ERROR("Error importing topics: %s"),
    ANSWERS_IMPORT_ERROR("Error importing answers: %s"),
    QUIZZES_IMPORT_ERROR("Error importing quizzes: %s"),

    QUESTION_IS_USED_IN_QUIZ("Question is used in quiz %s"),
    USER_NOT_ENROLLED("%s - Not enrolled in any available course"),
    QUIZ_NO_LONGER_AVAILABLE("This quiz is no longer available"),
    QUIZ_NOT_YET_AVAILABLE("This quiz is not yet available"),

    NO_CORRECT_OPTION("Question does not have a correct option"),
    NOT_ENOUGH_QUESTIONS("Not enough questions to create a quiz"),
    ONE_CORRECT_OPTION_NEEDED("Questions need to have 1 and only 1 correct option"),
    CANNOT_CHANGE_ANSWERED_QUESTION("Can not change answered question"),
    QUIZ_HAS_ANSWERS("Quiz already has answers"),
    QUIZ_ALREADY_COMPLETED("Quiz already completed"),
    QUIZ_ALREADY_STARTED("Quiz was already started"),
    QUIZ_QUESTION_HAS_ANSWERS("Quiz question has answers"),
    FENIX_ERROR("Fenix Error"),
    AUTHENTICATION_ERROR("Authentication Error"),
    FENIX_CONFIGURATION_ERROR("Incorrect server configuration files for fenix"),

    INVALID_DTO("The dto given is invalid"),
    ACCESS_DENIED("You do not have permission to view this resource"),
    CANNOT_OPEN_FILE("Cannot open file"),

    NO_TOURNAMENT_IN_EXECUTION("There is no tournament with id %d in the specified course execution"),
    TOURNAMENT_NOT_FOUND("Tournament not found with id %d"),
    TOURNAMENT_NOT_OPENED("The Tournament is not Opened for Enrollments"),
    USER_NOT_STUDENT("The user %d should be a student"),
    DUPLICATE_TOURNAMENT_ENROLL("A Student can not enroll twice in the same Tournament"),
    TOURNAMENT_NR_QUESTIONS_INVALID("The tournament number of questions should be greater than zero. Given: %d"),
    TOURNAMENT_START_TIME_INVALID("The tournament start time should be greater than the system's time. Given: %s"),
    TOURNAMENT_END_TIME_INVALID("The tournament end time should be greater than it's start time. Given: %s"),
    TOPIC_NOT_AVAILABLE("The topic %d not available"),
    COURSE_EXECUTION_NOT_ACTIVE("The course execution with id %d is not active"),
    TOURNAMENT_NOT_ACCEPTING_RESPONSES("The Tournament with id %d is not accepting responses"),
    TOURNAMENT_ALREADY_STARTED("The Tournament with id %d has already been started by the user with id %d"),
    TOURNAMENT_ALREADY_FINISHED("The Tournament with id %d is already finished by the user with id %d"),

    CLARIFICATION_INVALID_USER("User associated to clarification is invalid"),
    CLARIFICATION_INVALID_CONTENT("ClarificationRequest content invalid"),
    CLARIFICATION_INVALID_QUESTION_ANSWER("Question Answer associated to clarification is invalid"),
    CLARIFICATION_INVALID_STATE("ClarificationRequest state invalid"),
    CLARIFICATION_QUESTION_ANSWER_NOT_IN_USER("User has no question answer with id %d"),
    CLARIFICATION_QUIZ_NOT_COMPLETED("Quiz must be completed to write a clarification"),
    CLARIFICATION_IS_EMPTY("ClarificationRequest is empty"),
    CLARIFICATION_NOT_FOUND("ClarificationRequest with id %d not found"),

    COMMENT_INVALID_CLARIFICATION("ClarificationRequest associated to comment is invalid"),
    COMMENT_INVALID_CONTENT("ClarificationComment content is invalid"),
    COMMENT_IS_EMPTY("ClarificationComment is empty"),
    COMMENT_INVALID_USER_COURSE("User is not associated with clarification course"),
    COMMENT_INVALID_CLARIFICATION_STATE("ClarificationRequest state invalid for comment"),
    COMMENT_INVALID_USER("User associated to comment is invalid"),

    QUESTION_IS_EMPTY("The question is empty"),
    STUDENT_QUESTION_IS_EMPTY("Student question is empty"),
    STUDENT_QUESTION_NOT_FOUND("Student question not in the database"),
    JUSTIFICATION_NOT_FOUND("Justification not found");

    public final String label;

    ErrorMessage(String label) {
        this.label = label;
    }
}