package com.sgu.quanlytracnghiem.GUI;

        import com.sgu.quanlytracnghiem.BUS.Exam_BUS;
        import com.sgu.quanlytracnghiem.DTO.Question;
        import com.sgu.quanlytracnghiem.Interface.BUS.IExam;
        import javafx.animation.KeyFrame;
        import javafx.animation.Timeline;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.layout.Pane;
        import javafx.scene.layout.VBox;
        import javafx.util.Duration;
        import lombok.extern.slf4j.Slf4j;

        import java.util.ArrayList;
@Slf4j
public class ExamFormController {

            @FXML
            public Label countdownLabel;
            @FXML
            public VBox questionContainer;
            @FXML
            public Button submitButton;

            private int timeRemaining;
            private Timeline timeline;
            IExam examBUS = new Exam_BUS();
            ArrayList<Question> questions = new ArrayList<>();
            @FXML
            public void initialize() {
                timeRemaining = ExamItem.getTest().getTestTime() * 60; // Convert minutes to seconds
                countdownLabel.setText(formatTime(timeRemaining));
                    questions = examBUS.getRandomExamByTest(ExamItem.getTest().getTestCode()).getQuestions();
                    questions.forEach(this::loadFXML);


                // Countdown
                timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                    timeRemaining--;
                    countdownLabel.setText(formatTime(timeRemaining));
                    if (timeRemaining <= 0) {
                        // Time is up, handle the end of the exam
                        timeline.stop();
                        handleTimeUp();
                    }
                }));
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();
            }

            private String formatTime(int seconds) {
                int hours = seconds / 3600;
                int minutes = (seconds % 3600) / 60;
                int remainingSeconds = seconds % 60;
                return String.format("%02d:%02d:%02d", hours, minutes, remainingSeconds);
            }

            private void handleTimeUp() {
                // Handle what happens when the time is up
                submitButton.fire(); // Automatically submit the exam
            }

            public void loadFXML(Question question){
                QAForm qaForm = new QAForm(question);
                FXMLLoader loader = new FXMLLoader(getClass().getResource("QAForm.fxml"));
                loader.setController(qaForm);

                try {
                    Pane pane = loader.load(); // Load FXML trước khi lấy root
                    //Border , padding
                    pane.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-border-radius: 5; -fx-padding: 10; -fx-background-radius: 5;-fx-end-margin: 10");
                    questionContainer.getChildren().add(pane);
                } catch (Exception e) {
                    log.error("Error: ", e);
                }
            }
        }