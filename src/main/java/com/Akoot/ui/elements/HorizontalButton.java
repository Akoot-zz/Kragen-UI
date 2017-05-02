package com.Akoot.ui.elements;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class HorizontalButton
{
	private StackPane stack;
	protected double width, height;
	protected Color bgColor, fgColor, fontColor;
	protected boolean enabled;
	protected String label;

	public HorizontalButton()
	{
		this("");
	}

	public HorizontalButton(String text)
	{
		this.width = 100;
		this.height = 50;
		this.bgColor = Color.WHITE;
		this.fgColor = Color.CORNFLOWERBLUE;
		this.fontColor = Color.DIMGRAY;
		this.enabled = true;
		this.label = text;
	}

	public void setSize(double width, double height)
	{
		this.width = width;
		this.height = height;
	}

	public void setColor(Color background, Color foreground, Color text)
	{
		this.bgColor = background;
		this.fgColor = foreground;
		this.fontColor = text;
	}

	public void setEnabled(boolean enable)
	{
		this.enabled = enable;
	}

	public void setText(String text)
	{
		this.label = text;
	}

	private void update()
	{
		long now = System.currentTimeMillis();
		/* Creates a timeline for animation */
		final Timeline overAnimation = new Timeline();
		final Timeline outAnimation = new Timeline();
		final Timeline downAnimation = new Timeline();
		final Timeline upAnimation = new Timeline();

		/* Create Background */
		final Rectangle bg = new Rectangle(0, 0, width, height);
		bg.setFill(bgColor);

		/* Create Background */
		final Rectangle fg = new Rectangle(0, 0, width, height);
		fg.setFill(fgColor);
		fg.setScaleX(0.1);

		/* Create Text */
		Text text = new Text();
		text.setFont(Font.font("Arial", height / 2.0));
		text.setFill(fontColor);
		text.setText(label);

		/* Animation */
		List<KeyValue> kv = new ArrayList<KeyValue>();

		/* Over */
		kv.add(new KeyValue(fg.scaleXProperty(), 1.0, Interpolator.EASE_OUT));
		overAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(250), kv.toArray(new KeyValue[kv.size()])));
		kv.clear();

		/* Out */
		kv.add(new KeyValue(fg.scaleXProperty(), 0.1, Interpolator.EASE_OUT));
		outAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(250), kv.toArray(new KeyValue[kv.size()])));
		kv.clear();

		/* Down */
		downAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(250), kv.toArray(new KeyValue[kv.size()])));
		kv.clear();

		/* Up */
		upAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(250), kv.toArray(new KeyValue[kv.size()])));
		kv.clear();

		/* Create the node */
		stack = new StackPane();
		stack.setOnMouseEntered(event -> overAnimation.play());
		stack.setOnMouseExited(event -> outAnimation.play());
		stack.setOnMousePressed(e -> downAnimation.play());
		stack.setOnMouseReleased(e -> upAnimation.play());
		stack.getChildren().addAll(bg, fg, text);
		stack.getChildren().clear();
		System.out.println("took " + (double) (System.currentTimeMillis() - now) + "ms to make the " + label + " button");
	}

	public Node node()
	{
		update();
		return stack;
	}
}
