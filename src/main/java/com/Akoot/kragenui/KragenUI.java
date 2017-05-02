package com.Akoot.kragenui;

import com.Akoot.ui.elements.CloseButton;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
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

		drawContent(stage);

		/* Finally, build the scene */
		Scene scene = new Scene(root);
		stage.setTitle("Kragen-UI");
		stage.initStyle(StageStyle.UNDECORATED);
		stage.getIcons().add(new Image("file:assets/icons/icon.png"));
		stage.setScene(scene);
		for(Node node: root.getChildren())
		{
			System.out.println(node.toString());
		}
		stage.show();
	}

	private void drawContent(Stage stage)
	{
		Pane bg = new Pane();
		bg.setStyle("-fx-background-color: #2b2b2b;");
		Node close = new CloseButton().node();
		close.setOnMouseClicked(e -> stage.close());
		bg.getChildren().add(close);
		root.getChildren().addAll(bg);
	}
	
	class Delta { double x, y; } 
}
