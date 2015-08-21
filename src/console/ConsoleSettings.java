package console;

import java.awt.Color;
import java.awt.Font;

public class ConsoleSettings {
	private Font displayFont;
	private Font inputFont;
	private Color displayBG;
	private Color displayFG;
	private Color inputBG;
	private Color inputFG;
	
	
	public ConsoleSettings(Font displayFont, Font inputFont) {
		this.displayFont = displayFont;
		this.inputFont = inputFont;
	}
	
	public ConsoleSettings(Font displayFont, Font inputFont, Color displayBG, Color displayFG, Color inputBG, Color inputFG) {
		this.displayFont = displayFont;
		this.inputFont = inputFont;
		this.displayBG = displayBG;
		this.displayFG = displayFG;
		this.inputBG = inputBG;
		this.inputFG = inputFG;
	}
	
	public ConsoleSettings(ConsoleSettings cs) {
		setDisplayFont(cs.getDisplayFont());
		setInputFont(cs.getInputFont());
		setDisplayBG(cs.getDisplayBG());
		setDisplayFG(cs.getDisplayFG());
		setInputBG(cs.getInputBG());
		setInputFG(cs.getInputFG());
	}

	public Font getInputFont() {
		return inputFont;
	}

	public void setInputFont(Font inputFont) {
		this.inputFont = inputFont;
	}

	public Font getDisplayFont() {
		return displayFont;
	}

	public void setDisplayFont(Font displayFont) {
		this.displayFont = displayFont;
	}

	public Color getDisplayBG() {
		return displayBG;
	}

	public void setDisplayBG(Color displayBG) {
		this.displayBG = displayBG;
	}

	public Color getDisplayFG() {
		return displayFG;
	}

	public void setDisplayFG(Color displayFG) {
		this.displayFG = displayFG;
	}

	public Color getInputBG() {
		return inputBG;
	}

	public void setInputBG(Color inputBG) {
		this.inputBG = inputBG;
	}

	public Color getInputFG() {
		return inputFG;
	}

	public void setInputFG(Color inputFG) {
		this.inputFG = inputFG;
	}
}
