package mypcakage;

import javafx.application.Application;
import javafx.scene.Scene;
//import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

public class Dashboard extends Application {
	
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Dashboard");

        BorderPane root = new BorderPane();
        
        GridPane mainPage = new GridPane();
        mainPage.setAlignment(Pos.CENTER);
        mainPage.setPadding(new Insets(20));
        mainPage.setHgap(10);
        mainPage.setVgap(10);
        
        
        // Add a label to the main page
        Label titleLabel = new Label("Welcome to the Dashboard");
        mainPage.add(titleLabel, 0, 1);
        

        root.setCenter(mainPage);
        
        Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dashboard");
        primaryStage.show();
        
        MenuBar menuBar = new MenuBar();

        Menu userMenu = new Menu("User");
        MenuItem registerMenuItem = new MenuItem("Register");
        MenuItem updateProfileMenuItem = new MenuItem("Update Profile");
        userMenu.getItems().addAll(registerMenuItem, updateProfileMenuItem);

        Menu quizMenu = new Menu("Quiz");
        MenuItem startQuizMenuItem = new MenuItem("Start Exam");
        quizMenu.getItems().add(startQuizMenuItem);
        
        Menu LogoutMenu = new Menu("Logout");
        MenuItem LogoutMenuItem = new MenuItem("logout");
        LogoutMenu.getItems().addAll(LogoutMenuItem);
        
        Menu LogInMenu = new Menu("LogIn");
        MenuItem LogInItem = new MenuItem("logInt");
        LogInMenu.getItems().addAll(LogInItem);
        
        Menu AdLogInMenu = new Menu("Admin");
        MenuItem AdLogInItem = new MenuItem("Add Questions");
        AdLogInMenu.getItems().addAll(AdLogInItem);
        
        menuBar.getMenus().addAll(LogInMenu,userMenu, quizMenu,LogoutMenu,AdLogInMenu);

       
        BorderPane contentPane = new BorderPane();
        
        // Set the menu bar, content, and sidebar in the root layout
        BorderPane root1 = new BorderPane();
        root1.setTop(menuBar);
        root1.setLeft(contentPane);
        

        // Event handler for Register menu item
        registerMenuItem.setOnAction(e -> {
            // Open the register window or dialog
            openRegisterPage();
        });

        // Event handler for Update Profile menu item
        updateProfileMenuItem.setOnAction(e -> {
           updateProfilePage();
        });

        // Event handler for Start Quiz menu item
        startQuizMenuItem.setOnAction(e -> {
            // Perform start quiz action
            openQuizPage();
            // You can open a new window or invoke a method here
        });
        
        LogoutMenuItem.setOnAction(e -> {
            // Perform start quiz action
            openlogoutPage();
            // You can open a new window or invoke a method here
        });
        LogInItem.setOnAction(e -> {
            // Perform start quiz action
        	LoginForm();
            // You can open a new window or invoke a method here
        });
        AdLogInItem.setOnAction(e -> {
            // Perform start quiz action
        	CreateLoginForm();
            // You can open a new window or invoke a method here
        });

        Scene scene1 = new Scene(root1, 800, 600);
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

    private void openRegisterPage() {
        UserRegistration signupPage = new UserRegistration();
        signupPage.setVisible(true);
    }
    
    private void openQuizPage() {
    	MCQQuiz mcqQuiz = new MCQQuiz();
        mcqQuiz.setVisible(true);
        mcqQuiz.startTimer();
    }
    private void updateProfilePage() {
    	UpdateProfileForm updateprofile = new UpdateProfileForm();
    	updateprofile.setVisible(true);
    }
    private void openlogoutPage(){
    	LogoutFormGUI frame =  new LogoutFormGUI();
        frame.setVisible(true);
    }
    private void LoginForm(){
    	LoginForm frame = new LoginForm();
        frame.setVisible(true);
    }
    private void CreateLoginForm(){
    	CreateLoginForm form = new CreateLoginForm();  
    	form.setSize(300,100);  //set size of the frame  
        form.setVisible(true);
    }
    
    public static void main(String[] args) {
        launch(args);
    	};
}

