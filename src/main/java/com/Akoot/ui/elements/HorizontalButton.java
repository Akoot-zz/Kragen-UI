package com.Akoot.ui.elements;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.util.Duration;

public class HorizontalButton
{
	private Pane stack;
	private Rectangle bg, fg;
	private Text text;
	protected double width, height;
	protected Color bgColor, fgColor, fontColor;
	protected boolean enabled;
	protected String label;
	protected EventHandler<? super MouseEvent> onClick;
	protected Timeline overAnimation, outAnimation, downAnimation, upAnimation;

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
		overAnimation = new Timeline();
		outAnimation = new Timeline();
		downAnimation = new Timeline();
		upAnimation = new Timeline();
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
		if(enable) 
		{
			fg.setFill(fgColor);
			if(!enabled)
			{
				text.setOpacity(1.0);
				outAnimation.play();
			}
			stack.setOnMouseClicked(onClick);
		}
		else
		{
			fg.setFill(Color.LIGHTGRAY);
			if(enabled)
			{
				text.setOpacity(0.35);
				overAnimation.play();
			}
			stack.setOnMouseClicked(e -> System.out.println("y'all " + label + " is disabled"));
		}
		this.enabled = enable;
	}

	public void setText(String text)
	{
		this.label = text;
	}

	public void onClick(EventHandler<? super MouseEvent> value)
	{
		onClick = value;
	}
	
	public boolean isEnabled()
	{
		return enabled;
	}

	public Node node()
	{
		long now = System.currentTimeMillis();

		/* Create Background */
		bg = new Rectangle(0, 0, width, height);
		bg.setFill(bgColor);

		/* Create Foreground */
		fg = new Rectangle(0, 0, width, height);
		fg.setFill(fgColor);
		fg.setWidth(5.0);

		/* Create Text */
		text = new Text();
		text.setFont(Font.font("Phenomena", height / 2.0));
		text.setTextAlignment(TextAlignment.LEFT);
		text.setX(15.0);
		text.setY(height / 1.5);
		text.setFill(fontColor);
		text.setText(label);

		/* Animation */
		List<KeyValue> kv = new ArrayList<KeyValue>();

		/* Over */
		kv.add(new KeyValue(fg.widthProperty(), width, Interpolator.EASE_OUT));
		kv.add(new KeyValue(text.fillProperty(), Color.BLACK, Interpolator.EASE_BOTH));
		overAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(250), kv.toArray(new KeyValue[kv.size()])));
		kv.clear();

		/* Out */
		kv.add(new KeyValue(fg.widthProperty(), 5.0, Interpolator.EASE_OUT));
		kv.add(new KeyValue(text.fillProperty(), fontColor, Interpolator.EASE_BOTH));
		outAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(250), kv.toArray(new KeyValue[kv.size()])));
		kv.clear();

		/* Down */
		kv.add(new KeyValue(fg.opacityProperty(), 0.75, Interpolator.EASE_BOTH));
		downAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(250), kv.toArray(new KeyValue[kv.size()])));
		kv.clear();

		/* Up */
		kv.add(new KeyValue(fg.opacityProperty(), 1.0, Interpolator.EASE_BOTH));
		upAnimation.getKeyFrames().add(new KeyFrame(Duration.millis(250), kv.toArray(new KeyValue[kv.size()])));
		kv.clear();

		/* Create the node */
		stack = new Pane();
		stack.setOnMouseEntered(event -> {if(enabled) overAnimation.play();});
		stack.setOnMouseExited(event -> {if(enabled) outAnimation.play();});
		stack.setOnMousePressed(e -> {if(enabled) downAnimation.play();});
		stack.setOnMouseReleased(e -> {if(enabled) upAnimation.play();});
		stack.setOnMouseClicked(onClick);
		stack.getChildren().addAll(bg, fg, text);
		System.out.println("took " + (double) (System.currentTimeMillis() - now) + "ms to make the " + label + " button");
		return stack;
	}
}
