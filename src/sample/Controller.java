package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Controller {

    private static final String line = "\n";

    @FXML
    public javafx.scene.control.Button but;

    @FXML
    public TextArea logs_area;

    /**
     * 线程调用方法
     */
    class TestTask extends Thread {

        @FXML
        private TextArea logs_are;


        public TextArea getLogs_are() {
            return logs_are;
        }

        public void setLogs_are(TextArea logs_are) {
            this.logs_are = logs_are;
        }

        public void run() {

            for (int i = 0; i< 1000; i++) {

                try {
                    sleep(1000);

                }catch (RuntimeException | InterruptedException e){

                    e.printStackTrace();
                }

                this.logs_are.appendText("任务结束，登陆发生错误，请检查用户名密码是否正确"+line);

            }


        }
    }

    // TODO: 2019-09-10 线程调用
    private synchronized void call (ExecutorService executor) {

        TestTask testTask = new TestTask();
        testTask.logs_are = logs_area;
        executor.execute(testTask);
    }

    public void run(ActionEvent actionEvent) throws InterruptedException {

        ExecutorService executor = Executors.newCachedThreadPool();

            call(executor);
    }


}
