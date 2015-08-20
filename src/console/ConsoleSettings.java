package console;

import java.awt.Font;

public class ConsoleSettings {
	private Font displayFont;
	private Font inputFont;
	
	public ConsoleSettings(Font displayFont, Font inputFont) {
		this.displayFont = displayFont;
		this.inputFont = inputFont;
	}
	
	public ConsoleSettings(ConsoleSettings cs) {
		setDisplayFont(cs.getDisplayFont());
		setInputFont(cs.getInputFont());
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
}
