// Imports used
import java.time.LocalDate;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

//*********************************************************************************************
// CalculatorGUI.java     Author: Hall/ Sherica
// Date: February 16, 2024
//*********************************************************************************************

public class CalculatorGUI extends Application {
    private Text message;
    private DatePicker datePicker;
    private ColorPicker colorPicker;
    private ImageView ageImageView;


    //Starting of compilation
    @Override
    public void start(Stage primaryStage) throws Exception {

        //Creating Date Picker
        datePicker = new DatePicker();
        datePicker.setOnAction(this::processDateChoice);

        //Creating Color Picker
        colorPicker = new ColorPicker(Color.BLACK);
        colorPicker.setOnAction(this::processColorChoice);

        //Creating Text for message to be displayed in GUI
        message = new Text(" You're: ");
        message.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.REGULAR, 24));

        //Upload of Image
        Image ageimg;
        ageimg = new Image("sun-2.png");

        //Creating Image View and its positioning
        ageImageView = new ImageView();
        ageImageView.setViewport(new Rectangle2D(5, 5, 250, 250));
        ageImageView.setVisible(false);
        ageImageView.setTranslateX(5);
        ageImageView.setFitWidth(200);
        ageImageView.setPreserveRatio(true);
        ageImageView.setImage(ageimg);

        //Placing HBox for Horizontal Positioning of Component
        HBox pickers = new HBox(datePicker, colorPicker, ageImageView);
        pickers.setSpacing(15);
        pickers.setTranslateX(5);
        pickers.setAlignment(Pos.CENTER);   // An HBox Method

        //Placing VBox for Vertical Positioning of Component
        VBox root = new VBox();
        root.setStyle("-fx-background-color: teal");
        root.setSpacing(20);              // A VBox Method
        root.setAlignment(Pos.CENTER);    //A VBox Method
        root.getChildren().addAll(pickers, message);

        Scene scene = new Scene(root, 500, 350);

        primaryStage.setTitle("Picker GUI"); //Stage Title
        primaryStage.setScene(scene);  //Placing Scene in Stage
        primaryStage.show(); //Display Stage
    }

    //Creating Event Handler for Date Picker
    public int processDateChoice(ActionEvent event) {
        LocalDate date = datePicker.getValue();

        if (date != null) {
            int age = calculateAge(date);
            message.setText(" You're: " + age + " Years Old Yay!!");
        }
        return 0;
    }

    //Creating Event Handler for Color Picker
    public void processColorChoice(ActionEvent event) {
        message.setFill(colorPicker.getValue());
        ageImageView.setVisible(true);  //Shows image when color is picked
    }

    //Creating calculator
    private int calculateAge(LocalDate birthdate) {
        LocalDate currentDate = LocalDate.now();
        int age = currentDate.getYear() - birthdate.getYear();  //Subtracts year of birth from current year
        if (birthdate.getMonthValue() > currentDate.getMonthValue() ||
                (birthdate.getMonthValue() == currentDate.getMonthValue() && birthdate.getDayOfMonth() > currentDate.getDayOfMonth())) {
            age--;   // Creating prompt for what should be displayed
        }
        return age;
    }
}
