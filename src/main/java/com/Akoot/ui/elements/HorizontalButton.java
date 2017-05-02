package com.Akoot.ui.elements;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class HorizontalButton
{
	private StackPane stack;
	
	public HorizontalButton()
	{
		final Rectangle rectBasicTimeline = new Rectangle(100, 50, 10, 45);
		//create a text inside a circle
        final Text text = new Text ("eeee");
        text.setStroke(Color.BLACK);
		rectBasicTimeline.setFill(Color.RED);
		final Timeline timeline = new Timeline();
		final KeyValue kv = new KeyValue(rectBasicTimeline.widthProperty(), 300);
		final KeyValue kv2 = new KeyValue(rectBasicTimeline.xProperty(), 300);
		final KeyFrame kf = new KeyFrame(Duration.millis(2000), kv, kv2);
		timeline.getKeyFrames().add(kf);
		timeline.play();
		stack = new StackPane();
		stack.getChildren().addAll(rectBasicTimeline, text);
	}
	
	public Node node()
	{
		return stack;
	}
}
