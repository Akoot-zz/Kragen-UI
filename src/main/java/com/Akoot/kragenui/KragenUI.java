package com.Akoot.kragenui;

import com.Akoot.ui.elements.CloseButton;
import com.Akoot.ui.elements.HorizontalButton;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class KragenUI extends Application
{
	private StackPane root;

	public static void main(String[] args)
	{
		if(args.length > 0) Console.main(args);
		else launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception
	{
		/* Root of UI */
		root = new StackPane();
		root.setPrefSize(1100, 700);
		
		final Delta dragDelta = new Delta();
		root.setOnMousePressed(e -> 
		{
			dragDelta.x = stage.getX() - e.getScreenX();
		    dragDelta.y = stage.getY() - e.getScreenY();
		});
		root.setOnMouseDragged(e -> 
		{
			stage.setX(e.getScreenX() + dragDelta.x);
		    stage.setY(e.getScreenY() + dragDelta.y);
		});

		drawContent(stage);

		/* Finally, build the scene */
		Scene scene = new Scene(root);
		stage.setTitle("Kragen-UI");
		stage.initStyle(StageStyle.UNDECORATED);
		stage.getIcons().add(new Image("file:assets/icons/icon.png"));
		stage.setScene(scene);
		stage.show();
	}

	private void drawContent(Stage stage)
	{
		Pane bg = new Pane();
		bg.setStyle("-fx-background-color: #2b2b2b;");
		Node close = new CloseButton().node();
		close.setOnMouseClicked(e -> stage.close());
		HorizontalButton test = new HorizontalButton();
		test.setSize(200, 50);
		test.setColor(Color.BLACK, Color.ORANGE, Color.WHITE);
		Node testButton = test.node();
		testButton.setOnMouseClicked(e -> System.out.println("mama luigi"));
		testButton.setLayoutX(100);
		testButton.setLayoutY(100);
		bg.getChildren().addAll(close, testButton);
		root.getChildren().addAll(bg);
	}
	
	class Delta { double x, y; } 
}
