package pt.ulisboa.tecnico.socialsoftware.tutor.studentquestion.dto;

import pt.ulisboa.tecnico.socialsoftware.tutor.studentquestion.domain.Evaluation;
import pt.ulisboa.tecnico.socialsoftware.tutor.studentquestion.domain.StudentQuestion;

import java.io.Serializable;

public class EvaluationDto implements Serializable {
    private int id;
    private StudentQuestionDto studentQuestionDto;
    private boolean accepted;
    private String justification;

    public EvaluationDto() {
    }

    public EvaluationDto(Evaluation evaluation) {
        this.id = evaluation.getId();
        this.accepted = evaluation.isAccepted();
        this.justification = evaluation.getJustification();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public StudentQuestionDto getStudentQuestionDto() {
        return studentQuestionDto;
    }

    public void setStudentQuestionDto(StudentQuestionDto studentQuestionDto) {
        this.studentQuestionDto = studentQuestionDto;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

}
