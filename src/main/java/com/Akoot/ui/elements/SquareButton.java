package com.Akoot.ui.elements;

import com.jfoenix.controls.JFXRippler;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;

public class SquareButton
{
	private JFXRippler rippler;
	private Label label;
	public String color;
	public String textColor;
	public String rippleColor;
	public String textHeight;
	public double padding;
	public double width, height;
	public boolean centered;

	public SquareButton(String text)
	{
		label = new Label();
		label.setText(text);
		rippler = new JFXRippler(label);
		width = -1;
		height = -1;
		rippleColor = "#ffffff";
		centered = true;
	}

	public SquareButton()
	{
		this("");
	}

	public void setSize(double width, double height)
	{
		this.width = width;
		this.height = height;
	}

	public void setColor(String bgColor, String fgColor, String textColor)
	{
		this.rippleColor = fgColor;
		this.color = bgColor;
		this.textColor = textColor;
	}

	private void update()
	{		
		label.setStyle("-fx-font-size: " + (int) (height / 2.0) + "px;" + 
				((color == null || color.isEmpty()) ? "" : "-fx-background-color: " + color + ";") + 
				((textColor == null || textColor.isEmpty()) ? "" : "-fx-text-fill: " + textColor + ";") + 
				((rippleColor == null || rippleColor.isEmpty()) ? "" : "-jfx-rippler-fill: " + rippleColor + ";"));
		label.setPadding(new Insets(padding));
		if(width >= 0) label.setPrefWidth(width);
		if(height >= 0) label.setPrefHeight(height);
		if(centered)
		{
			label.setTextAlignment(TextAlignment.CENTER);
			label.setAlignment(Pos.BASELINE_CENTER);
		}
		
	}

	public JFXRippler getButton()
	{
		update();
		return rippler;
	}
}
