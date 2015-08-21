package console;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeSelectionModel;

import net.miginfocom.swing.MigLayout;
import say.swing.JFontChooser;

import com.bric.swing.ColorPicker;

@SuppressWarnings("serial")
public class ConsoleSettingsMenu extends JDialog implements TreeSelectionListener, ActionListener {
	
	ConsoleSettings oldSettings;
	ConsoleSettings newSettings;
	
	JButton okayBtn;
	JButton cancelBtn;
	JButton applyBtn;
	
	JTree menuSelector;
	
	JPanel menuPanel;
	JPanel colorMenuPanel;
		JButton displayBGBtn;
		JButton displayFGBtn;
		JButton inputBGBtn;
		JButton inputFGBtn;
	JPanel textMenuPanel;
		JLabel displayFontLabel;
		JLabel inputFontLabel;
		
	final static String COLOR_MENU = "Color Menu";
	final static String TEXT_MENU = "Text Menu";
	
	final static String DISPLAY_SELECT = "Display select";
	final static String INPUT_SELECT = "Input select";
	
	final static String DISPLAY_BG = "Display BG";
	final static String DISPLAY_FG = "Display FG";
	final static String INPUT_BG = "Input BG";
	final static String INPUT_FG = "INPUT FG";
	
	
	final static String OKAY_OPTION = "OKAY_OPTION";
	final static String CANCEL_OPTION = "CANCEL_OPTION";
	
	
	JFrame parentFrame;
	
	public ConsoleSettingsMenu (JFrame parentFrame, ConsoleSettings settings) {
		super(parentFrame, "Console Settings", true);
		
		this.parentFrame = parentFrame;
		
		oldSettings = settings;
		newSettings = new ConsoleSettings(settings);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		setPreferredSize(new Dimension(400, 300));
		
		setLayout(new BorderLayout());
		
		//Tree Selector
		DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("root");
		DefaultMutableTreeNode colorsNode = new DefaultMutableTreeNode("Colors");
		DefaultMutableTreeNode textNode = new DefaultMutableTreeNode("Text");
			rootNode.add(colorsNode);
			rootNode.add(textNode);
			
		menuSelector = new JTree(rootNode);
			menuSelector.setRootVisible(false);
			menuSelector.setPreferredSize(new Dimension(100, 210));
			menuSelector.setBorder(BorderFactory.createLineBorder(Color.black));
			menuSelector.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
			menuSelector.addTreeSelectionListener(this);
			DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer() {
				private Border border = BorderFactory.createEmptyBorder(0,5,0,0);
				public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
					JLabel label = (JLabel) super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
					label.setBorder(border);
					return label;
				}
			};
			menuSelector.setCellRenderer(renderer);
			renderer.setLeafIcon(null);
	        renderer.setClosedIcon(null);
	        renderer.setOpenIcon(null);
	        //puts a margin around the tree
	        JPanel menuSelectorPanel = new JPanel();
	        menuSelectorPanel.add(menuSelector);
		add(menuSelectorPanel, BorderLayout.WEST);
		
		//Menu Panel
		menuPanel = new JPanel();
			colorMenuPanel = new JPanel();
				colorMenuPanel.setLayout(new MigLayout("wrap 2, fillx"));
				colorMenuPanel.add(new JLabel("Color menu"), "align center, wrap, span");
				
				//Display BG
				JPanel displayBGPanel = new JPanel();
					displayBGPanel.setBorder(BorderFactory.createTitledBorder("Display BG"));
					displayBGPanel.setLayout(new MigLayout("", "[grow]", ""));
					displayBGBtn = new JButton(" ");
						displayBGBtn.addActionListener(this);
						displayBGBtn.setActionCommand(DISPLAY_BG);
						displayBGBtn.setBorderPainted(false);
						displayBGBtn.setFocusPainted(false);
						displayBGBtn.setContentAreaFilled(false);
						displayBGBtn.setBackground(oldSettings.getDisplayBG());
						displayBGBtn.setOpaque(true);
					displayBGPanel.add(displayBGBtn, "growx");
				
				colorMenuPanel.add(displayBGPanel, "growx");
				
				//Display FG
				JPanel displayFGPanel = new JPanel();
				displayFGPanel.setBorder(BorderFactory.createTitledBorder("Display FG"));
				displayFGPanel.setLayout(new MigLayout("", "[grow]", ""));
				displayFGBtn = new JButton(" ");
					displayFGBtn.addActionListener(this);
					displayFGBtn.setActionCommand(DISPLAY_FG);
					displayFGBtn.setBorderPainted(false);
					displayFGBtn.setFocusPainted(false);
					displayFGBtn.setContentAreaFilled(false);
					displayFGBtn.setBackground(oldSettings.getDisplayFG());
					displayFGBtn.setOpaque(true);
				displayFGPanel.add(displayFGBtn, "growx");
			
				colorMenuPanel.add(displayFGPanel, "growx");
				
				//Input FG
				JPanel inputBGPanel = new JPanel();
				inputBGPanel.setBorder(BorderFactory.createTitledBorder("Input BG"));
				inputBGPanel.setLayout(new MigLayout("", "[grow]", ""));
				inputBGBtn = new JButton(" ");
					inputBGBtn.addActionListener(this);
					inputBGBtn.setActionCommand(INPUT_BG);
					inputBGBtn.setBorderPainted(false);
					inputBGBtn.setFocusPainted(false);
					inputBGBtn.setContentAreaFilled(false);
					inputBGBtn.setBackground(oldSettings.getInputBG());
					inputBGBtn.setOpaque(true);
				inputBGPanel.add(inputBGBtn, "growx");
			
				colorMenuPanel.add(inputBGPanel, "growx");
				
				//Input FG
				JPanel inputFGPanel = new JPanel();
				inputFGPanel.setBorder(BorderFactory.createTitledBorder("Input FG"));
				inputFGPanel.setLayout(new MigLayout("", "[grow]", ""));
				inputFGBtn = new JButton(" ");
					inputFGBtn.addActionListener(this);
					inputFGBtn.setActionCommand(INPUT_FG);
					inputFGBtn.setBorderPainted(false);
					inputFGBtn.setFocusPainted(false);
					inputFGBtn.setContentAreaFilled(false);
					inputFGBtn.setBackground(oldSettings.getInputFG());
					inputFGBtn.setOpaque(true);
				inputFGPanel.add(inputFGBtn, "growx");
			
				colorMenuPanel.add(inputFGPanel, "growx");
			
			//Text Menu Card
			textMenuPanel = new JPanel();
				textMenuPanel.setLayout(new MigLayout("wrap 1, fillx"));
				textMenuPanel.add(new JLabel("Text menu"), "align center");
				
				//Display Font
				JPanel displayFontMenu = new JPanel();
				
					displayFontMenu.setLayout(new MigLayout("", "[grow]", ""));
					displayFontMenu.setBorder(BorderFactory.createTitledBorder("Display font"));
					
					Font displayFont = oldSettings.getDisplayFont();
					displayFontLabel = new JLabel(displayFont.getFontName() + ", " + displayFont.getSize() + "pt.");
					
					displayFontMenu.add(displayFontLabel, "dock west");
					
					JButton displaySelectBtn = new JButton("Select");
						displaySelectBtn.addActionListener(this);
						displaySelectBtn.setActionCommand(DISPLAY_SELECT);
					displayFontMenu.add(displaySelectBtn, "dock east");
					
				textMenuPanel.add(displayFontMenu, "growx");
				
				//Input Font
				JPanel inputFontMenu = new JPanel();
					inputFontMenu.setLayout(new MigLayout("", "[grow]", ""));
					inputFontMenu.setBorder(BorderFactory.createTitledBorder("Input font"));
					
					Font inputFont = oldSettings.getInputFont();
					inputFontLabel = new JLabel(inputFont.getFontName() + ", " + inputFont.getSize() + "pt.");
					
					inputFontMenu.add(inputFontLabel, "dock west");
					
					JButton inputSelectBtn = new JButton("Select");
						inputSelectBtn.addActionListener(this);
						inputSelectBtn.setActionCommand(INPUT_SELECT);
					inputFontMenu.add(inputSelectBtn, "dock east");
				textMenuPanel.add(inputFontMenu, "growx");
			
			menuPanel.setBorder(new EmptyBorder(0,0,8,7));
//			menuPanel.setBorder(BorderFactory.createLineBorder(Color.black));
			menuPanel.setPreferredSize(new Dimension(260, 100));
			menuPanel.setLayout(new CardLayout());
			menuPanel.add(textMenuPanel, TEXT_MENU);
			menuPanel.add(colorMenuPanel, COLOR_MENU);
		add(menuPanel, BorderLayout.EAST);
		
		//Button Panel
		okayBtn = new JButton("Okay");
			okayBtn.addActionListener(this);
			okayBtn.setActionCommand(OKAY_OPTION);
			
		cancelBtn = new JButton("Cancel");
			cancelBtn.addActionListener(this);
			cancelBtn.setActionCommand(CANCEL_OPTION);
//		applyBtn = new JButton("Apply");
		
		JPanel btnPanel = new JPanel();
			btnPanel.setLayout(new MigLayout("align right"));
			btnPanel.add(okayBtn);
			btnPanel.add(cancelBtn);
//			btnPanel.add(applyBtn);
			
		add(btnPanel, BorderLayout.SOUTH);
		
		pack();
		
		setLocationRelativeTo(parentFrame);
	}
	
	public ConsoleSettings showDialog() {
		setVisible(true);
		return newSettings;
	}
	
	
	private void updateMenus() {
		Font displayFont = newSettings.getDisplayFont();
		displayFontLabel.setText(displayFont.getFontName() + ", " + displayFont.getSize() + "pt.");
		displayFontLabel.paintImmediately(displayFontLabel.getVisibleRect());
		
		Font inputFont = newSettings.getInputFont();
		inputFontLabel.setText(inputFont.getFontName() + ", " + inputFont.getSize() + "pt.");
		
		displayBGBtn.setBackground(newSettings.getDisplayBG());
		displayFGBtn.setBackground(newSettings.getDisplayFG());
		inputBGBtn.setBackground(newSettings.getInputBG());
		inputFGBtn.setBackground(newSettings.getInputFG());
	}
	
	@Override
	public void valueChanged(TreeSelectionEvent e) {
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) menuSelector.getLastSelectedPathComponent();
		
		
		if (node == null) {
		    //Nothing is selected.  
		    return;
		}
		
		String name = (String) node.getUserObject();
		CardLayout cl = (CardLayout) menuPanel.getLayout();
		switch(name) {
			case "Colors":
				cl.show(menuPanel, COLOR_MENU);
				break;
			case "Text":
				cl.show(menuPanel, TEXT_MENU);
				break;
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
			case OKAY_OPTION:
				setVisible(false);
				dispose();
				break;
			case CANCEL_OPTION:
				setVisible(false);
				dispose();
				break;
			case DISPLAY_SELECT:
				JFontChooser jc = new JFontChooser();
				jc.setSelectedFont(newSettings.getDisplayFont());
				jc.showDialog(parentFrame);
				newSettings.setDisplayFont(jc.getSelectedFont());
				break;
			case INPUT_SELECT:
				JFontChooser jc2 = new JFontChooser();
				jc2.setSelectedFont(newSettings.getInputFont());
				jc2.showDialog(parentFrame);
				newSettings.setInputFont(jc2.getSelectedFont());
				break;
			case DISPLAY_BG:
				newSettings.setDisplayBG(ColorPicker.showDialog(parentFrame, newSettings.getDisplayBG()));
				break;
			case DISPLAY_FG:
				newSettings.setDisplayFG(ColorPicker.showDialog(parentFrame, newSettings.getDisplayFG()));
				break;
			case INPUT_BG:
				newSettings.setInputBG(ColorPicker.showDialog(parentFrame, newSettings.getInputBG()));
				break;
			case INPUT_FG:
				newSettings.setInputFG(ColorPicker.showDialog(parentFrame, newSettings.getInputFG()));
				break;
		}
		
		updateMenus();
		
	}

	
	public static void main(String args[]) {
		JFrame frame = new JFrame();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		
		JTextArea textArea = new JTextArea();
		JTextField textField = new JTextField();
		
		ConsoleSettings cs = new ConsoleSettings(textArea.getFont(), textField.getFont(), textArea.getBackground(), textArea.getForeground(), textField.getBackground(), textField.getForeground());
		ConsoleSettingsMenu c = new ConsoleSettingsMenu(frame, cs);
		c.showDialog();
		
		System.exit(0);
	}

}
