package com.sgu.quanlytracnghiem.GUI;

        import javafx.animation.KeyFrame;
        import javafx.animation.Timeline;
        import javafx.fxml.FXML;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.layout.VBox;
        import javafx.util.Duration;

        public class ExamFormController {

            @FXML
            public Label countdownLabel;
            @FXML
            public VBox questionContainer;
            @FXML
            public Button submitButton;

            private int timeRemaining;
            private Timeline timeline;

            @FXML
            public void initialize() {
                timeRemaining = ExamItem.getTest().getTestTime() * 60; // Convert minutes to seconds
                countdownLabel.setText(formatTime(timeRemaining));

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
        }