package com.Akoot.ui.elements;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class CloseButton
{
	private StackPane stack;

	public CloseButton()
	{
		long now = System.currentTimeMillis();
		/* Creates a timeline for animation */
		final Timeline overAnimation = new Timeline();
		final Timeline outAnimation = new Timeline();
		final Timeline downAnimation = new Timeline();
		final Timeline upAnimation = new Timeline();

		/* Create Background */
		final Rectangle bg = new Rectangle(0, 0, 35, 35);
		bg.setFill(Color.color(0.87, 0.15, 0.27));

		/* Create X */
		Group x = new Group();
		Line lineV = new Line(0, 0, 12, 12);
		Line lineH = new Line(0, 0, 12, 12);
		lineV.setStroke(Color.WHITESMOKE);
		lineH.setStroke(Color.WHITESMOKE);
		lineV.setRotate(90.0);
		x.getChildren().addAll(lineV, lineH);

		/* Animation */
		List<KeyValue> kv = new ArrayList<KeyValue>();

		/* Over */
		kv.add(new KeyValue(x.rotateProperty(), 90.0, Interpolator.EASE_OUT));
		kv.add(new KeyValue(bg.fillProperty(), Color.color(0.31, 0.90, 0.75), Interpolator.EASE_BOTH));
		for(Node node: x.getChildren())
		{
			kv.add(new KeyValue(((Line) node).strokeProperty(), Color.BLACK, Interpolator.EASE_BOTH));
		}
		overAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(250), kv.toArray(new KeyValue[kv.size()])));
		kv.clear();

		/* Out */
		kv.add(new KeyValue(x.rotateProperty(), 0.0, Interpolator.EASE_IN));
		kv.add(new KeyValue(bg.fillProperty(), Color.color(0.87, 0.15, 0.27), Interpolator.EASE_BOTH));
		for(Node node: x.getChildren())
		{
			kv.add(new KeyValue(((Line) node).strokeProperty(), Color.WHITESMOKE, Interpolator.EASE_BOTH));
		}
		outAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(250), kv.toArray(new KeyValue[kv.size()])));
		kv.clear();

		/* Down */
		kv.add(new KeyValue(bg.opacityProperty(), 0.75, Interpolator.EASE_IN));
		kv.add(new KeyValue(x.scaleXProperty(), 1.2, Interpolator.EASE_OUT));
		kv.add(new KeyValue(x.scaleYProperty(), 1.2, Interpolator.EASE_OUT));
		downAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(250), kv.toArray(new KeyValue[kv.size()])));
		kv.clear();

		/* Up */
		kv.add(new KeyValue(bg.opacityProperty(), 1.0, Interpolator.EASE_OUT));
		kv.add(new KeyValue(x.scaleXProperty(), 1.0, Interpolator.EASE_IN));
		kv.add(new KeyValue(x.scaleYProperty(), 1.0, Interpolator.EASE_IN));
		upAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(250), kv.toArray(new KeyValue[kv.size()])));
		kv.clear();

		/* Create the node */
		stack = new StackPane();
		stack.setOnMouseEntered(event -> overAnimation.play());
		stack.setOnMouseExited(event -> outAnimation.play());
		stack.setOnMousePressed(e -> downAnimation.play());
		stack.setOnMouseReleased(e -> upAnimation.play());
		stack.getChildren().addAll(bg, x);
		System.out.println("took " + (double) (System.currentTimeMillis() - now) + "ms to make the close button");
	}

	public Node node()
	{
		return stack;
	}
}
