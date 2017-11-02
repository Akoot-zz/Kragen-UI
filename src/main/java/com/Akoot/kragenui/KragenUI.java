package com.Akoot.kragenui;

import com.Akoot.ui.elements.CloseButton;
import com.Akoot.ui.elements.HorizontalButton;
import com.Akoot.util.RandomUtils;
import com.Akoot.util.Range;

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
		/* Background */
		Pane bg = new Pane();
		bg.setStyle("-fx-background-color: #2b2b2b;");
		
		/* Topbar */
		Node close = new CloseButton().node();
		close.setOnMouseClicked(e -> stage.close());
		
		/* Add children */
		bg.getChildren().addAll(close);
		
		for(int i = 0; i < 10; i++)
		{
			HorizontalButton test = new HorizontalButton(RandomUtils.randomString(8));
			test.setSize(500, 40);
			test.setColor(Color.color(0.15, 0.15, 0.15), Color.color((double) (RandomUtils.randomNumber(new Range(50, 100))) / 100.0, (double) (RandomUtils.randomNumber(new Range(50, 100))) / 100.0, (double) (RandomUtils.randomNumber(new Range(50, 100))) / 100.0), Color.WHITESMOKE);
			test.onClick(e -> System.out.println("Button is TRIGGERED"));
			Node button = test.getNode();
			button.setLayoutX(100);
			button.setLayoutY(100 + (40 * i));
			bg.getChildren().add(button);
		}
		root.getChildren().addAll(bg);
	}
	
	class Delta { double x, y; } 
}
