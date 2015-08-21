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
	JPanel textMenuPanel;
		JLabel displayFontLabel;
		JLabel inputFontLabel;
	final static String COLOR_MENU = "Color Menu";
	final static String TEXT_MENU = "Text Menu";
	
	final static String DISPLAY_SELECT = "Display select";
	final static String INPUT_SELECT = "Input select";
	
	JFrame parentFrame;
	
	public ConsoleSettingsMenu (JFrame parentFrame, ConsoleSettings settings) {
		super(parentFrame, "Console Settings", true);
		
		this.parentFrame = parentFrame;
		
		oldSettings = settings;
		newSettings = new ConsoleSettings(settings);
		
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
				colorMenuPanel.add(new JLabel("Color menu"));
			
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
		cancelBtn = new JButton("Cancel");
		applyBtn = new JButton("Apply");
		
		JPanel btnPanel = new JPanel();
			btnPanel.add(okayBtn);
			btnPanel.add(cancelBtn);
			btnPanel.add(applyBtn);
			
		add(btnPanel, BorderLayout.SOUTH);
		
		pack();
	}
	
	private void updateMenus() {
		Font displayFont = newSettings.getDisplayFont();
		displayFontLabel.setText(displayFont.getFontName() + ", " + displayFont.getSize() + "pt.");
		displayFontLabel.paintImmediately(displayFontLabel.getVisibleRect());
		
		Font inputFont = newSettings.getInputFont();
		inputFontLabel.setText(inputFont.getFontName() + ", " + inputFont.getSize() + "pt.");
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
		}
		
		updateMenus();
		
	}

	
	public static void main(String args[]) {
		JFrame frame = new JFrame();
//		JFrame frame = null;
		frame.setVisible(true);

		
		JTextArea ta = new JTextArea();
		JTextField tf = new JTextField();
		
		ConsoleSettingsMenu c = new ConsoleSettingsMenu(frame, new ConsoleSettings(ta.getFont(), tf.getFont()));
		c.setVisible(true);
		c.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		
		System.exit(0);
	}

}
