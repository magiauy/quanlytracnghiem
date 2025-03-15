package com.sgu.quanlytracnghiem.GUI;

        import com.sgu.quanlytracnghiem.BUS.Exam_BUS;
        import com.sgu.quanlytracnghiem.BUS.Logs_BUS;
        import com.sgu.quanlytracnghiem.BUS.Result_BUS;
        import com.sgu.quanlytracnghiem.BUS.TempResult_BUS;
        import com.sgu.quanlytracnghiem.DTO.*;
        import com.sgu.quanlytracnghiem.Interface.BUS.CRUD;
        import com.sgu.quanlytracnghiem.Interface.BUS.IExam;
        import com.sgu.quanlytracnghiem.Interface.BUS.IResult;
        import com.sgu.quanlytracnghiem.Interface.BUS.IdGenerate;
        import com.sgu.quanlytracnghiem.Util.ValidationUtil;
        import javafx.animation.KeyFrame;
        import javafx.animation.Timeline;
        import javafx.application.Platform;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.layout.Pane;
        import javafx.scene.layout.VBox;
        import javafx.stage.Stage;
        import javafx.util.Duration;
        import lombok.Getter;
        import lombok.Setter;
        import lombok.extern.slf4j.Slf4j;

        import java.time.LocalDate;
        import java.time.LocalDateTime;
        import java.time.temporal.ChronoUnit;
        import java.util.ArrayList;
        import java.util.Collections;

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
            CRUD<Exam> exam_CRUD = new Exam_BUS();
            @Getter
            private static CRUD<Logs> logs_CRUD = new Logs_BUS();

            ArrayList<Question> questions = new ArrayList<>();
            @Getter
            public static IResult result;
            IdGenerate idGenerate = new Result_BUS();
            @Getter
            private static Exam exam;
            private final Test test;
            int i=1;
            private Result result1 ;

            @Getter
            @Setter
            private static boolean isSubmit = false;

            public ExamFormController(Test test) {
                this.test = test;
            }

            public ExamFormController(Result result,Test test) {
                this.test = test;
                this.result1 = result;
            }

            @FXML
            public void initialize() {
                ArrayList<Answers> list ;
                result = new TempResult_BUS();
                if (ExamItem.isSubmit()){
                    exam = examBUS.getRandomExamByTest(test.getTestCode());
                    list = new ArrayList<>(Collections.nCopies(exam.getQuestions().size(), null));

                    result1 = new Result();
                    result1.setResultID(idGenerate.generateId());
                    result1.setExamID(exam.getExamID());
                    result1.setUserID(Login.getUser().getId());
                    //Get dd/mm/yyyy hh:mm:ss
                    result1.setResultDate(LocalDateTime.now());
                    result1.setAnswers(list);
                    result1.setSubmit(false);
                    result.add(result1);
                    timeRemaining = test.getTestTime() * 60; // Convert minutes to seconds

                }else {
                    // time remaining = now - result . getdatetime
                    LocalDateTime expiryDate = result1.getResultDate().plusMinutes(test.getTestTime());
                    result.setResult(result1);
                    timeRemaining = (int) ChronoUnit.MINUTES.between(LocalDateTime.now(), expiryDate)*60;
                    exam = exam_CRUD.getByID(result1.getExamID());
                }

                 Platform.runLater(() -> {
                     Stage stage = (Stage) submitButton.getScene().getWindow();
                     stage.setOnCloseRequest(event -> {
                         if (!isSubmit) {
                             if (ValidationUtil.showConfirmAlert("Xác nhận thoát", "Bạn chưa hoàn thành bài thi, bạn có chắc chắn muốn thoát không?")) {
                                 timeline.stop();
                                 stage.close();
                             } else {
                                 event.consume();
                             }
                         }
                     });
                 });

                submitButton.setOnAction(event -> {
                    int i=0;
                    for (Answers a: result1.getAnswers()) {
                        i++;
                        if (a==null){
                            ValidationUtil.showErrorAlert("Chưa hoàn thành bài thi", "Vui lòng hoàn thành tất cả câu hỏi trước khi nộp bài");
                            return;
                        }
                    }

                    if (ValidationUtil.showConfirmAlert("Xác nhận nộp bài", "Bạn có chắc chắn muốn nộp bài không?")) {
                        submit();
                    }

                });

                questions = exam.getQuestions();
                countdownLabel.setText(formatTime(timeRemaining));
                    questions.forEach(this::loadFXML);


                // Countdown
                timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                    timeRemaining--;
                    countdownLabel.setText(formatTime(timeRemaining));
                    if (timeRemaining <= 0) {
                        // Time is up, handle the end of the exam
                        timeline.stop();
                        handleTimeUp();
                        submit();
                    }
                }));
                timeline.setCycleCount(Timeline.INDEFINITE);
                timeline.play();
            }

            private void submit(){
                result1.setSubmit(true);
                result.submitResult(result1);
                timeline.stop();
                isSubmit = true;
                Stage stage = (Stage) submitButton.getScene().getWindow();
                stage.close();

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
                QAForm qaForm;
                if (result1.getAnswers().get(i-1)!=null) {
                    qaForm = new QAForm(question,i,result1.getAnswers().get(i-1));
                }else {
                    qaForm = new QAForm(question,i);
                }
                i++;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("QAForm.fxml"));
                loader.setController(qaForm);

                try {
                    Pane pane = loader.load(); // Load FXML trước khi lấy root
                    //Border , padding
                    questionContainer.getChildren().add(pane);
                } catch (Exception e) {
                    log.error("Error: ", e);
                }
            }

        }