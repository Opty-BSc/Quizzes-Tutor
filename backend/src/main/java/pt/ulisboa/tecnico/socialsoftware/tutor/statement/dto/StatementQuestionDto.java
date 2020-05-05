package pt.ulisboa.tecnico.socialsoftware.tutor.statement.dto;

import pt.ulisboa.tecnico.socialsoftware.tutor.answer.domain.QuestionAnswer;
import pt.ulisboa.tecnico.socialsoftware.tutor.image.dto.ImageDto;
import pt.ulisboa.tecnico.socialsoftware.tutor.question.domain.Question;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

public class StatementQuestionDto implements Serializable {
    private String content;
    private List<StatementOptionDto> options;
    private ImageDto image;
    private Integer sequence;
    private Integer questionAnswerId;

    public StatementQuestionDto(QuestionAnswer questionAnswer) {

        this.questionAnswerId = questionAnswer.getId();
        this.sequence = questionAnswer.getSequence();

        Question question = questionAnswer.getQuestion();

        this.content = question.getContent();

        if (question.getImage() != null) this.image = new ImageDto(question.getImage());

        this.options = question.getOptions().stream()
                .map(StatementOptionDto::new)
                .collect(Collectors.toList());
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<StatementOptionDto> getOptions() {
        return options;
    }

    public void setOptions(List<StatementOptionDto> options) {
        this.options = options;
    }

    public ImageDto getImage() {
        return image;
    }

    public void setImage(ImageDto image) {
        this.image = image;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public Integer getQuestionAnswerId() {
        return questionAnswerId;
    }

    public void setQuestionAnswerId(Integer questionAnswerId) {
        this.questionAnswerId = questionAnswerId;
    }

    @Override
    public String toString() {
        return "StatementQuestionDto{" +
                ", content='" + content + '\'' +
                ", options=" + options +
                ", image=" + image +
                ", sequence=" + sequence +
                ", questionAnswerId=" + questionAnswerId+
                '}';
    }
}
