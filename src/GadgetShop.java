import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.Image;
import javax.swing.ImageIcon;

public class GadgetShop extends JFrame implements ActionListener
{
    private ArrayList<Gadget> gadgets;

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private String currentPage;
    private Stack<String> pageHistory;

    private JButton minimiseButton;
    private JButton closeButton;

    private JButton homeAddNewGadgetButton;
    private JButton homeDisplayAllGadgetsButton;
    private JButton homeInstructionsButton;

    private JButton instructionsBackButton;
    private JButton instructionsHomeButton;

    private JButton addGadgetBackButton;
    private JButton addGadgetHomeButton;
    private JButton addGadgetMobileButton;
    private JButton addGadgetMP3Button;

    private final String HOME_PAGE = "HOME_PAGE";
    private final String INSTRUCTIONS_PAGE = "INSTRUCTIONS_PAGE";
    private final String ADD_GADGET_PAGE = "ADD_GADGET_PAGE";

    private final Color WINDOW_TOP = new Color(235, 238, 245);
    private final Color WINDOW_BOTTOM = new Color(196, 202, 214);

    private final Color TITLEBAR_TOP = new Color(159, 180, 214);
    private final Color TITLEBAR_BOTTOM = new Color(111, 138, 184);

    private final Color SCREEN_TOP = new Color(155, 185, 225);
private final Color SCREEN_BOTTOM = new Color(120, 155, 205);

    private final Color CONTENT_TOP = new Color(246, 248, 252);
    private final Color CONTENT_BOTTOM = new Color(223, 229, 240);

    private final Color BORDER_DARK = new Color(92, 104, 128);
    private final Color BORDER_LIGHT = new Color(248, 250, 255);

    private final Color WHITE_TEXT = new Color(250, 252, 255);
    private final Color DARK_TEXT = new Color(35, 48, 71);

    private final Color Home_Title_Green = new Color(152, 225, 160);
    private final Color Home_Title_Symbols1 = new Color(255, 243, 18);

    private final Color Home_Title_Symbols2 = new Color(255, 155, 235);
    private Point dragOffset;

    public GadgetShop()
    {
        gadgets = new ArrayList<Gadget>();
        pageHistory = new Stack<String>();

        setUndecorated(true);
        setTitle("(⁠￣⁠ω⁠￣) Happy Happy Gadget Land (⁠￣⁠ω⁠￣)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(430, 260);
   setShape(new java.awt.geom.RoundRectangle2D.Double(0, 0, 430, 260, 20, 20));
        setResizable(false);
        setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setOpaque(false);

        cardPanel.add(createHomePage(), HOME_PAGE);
        cardPanel.add(createInstructionsPage(), INSTRUCTIONS_PAGE);
        cardPanel.add(createAddGadgetPage(), ADD_GADGET_PAGE);

        XPWindowPanel rootPanel = new XPWindowPanel();
        rootPanel.setLayout(new BorderLayout());
        rootPanel.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
       
        rootPanel.add(cardPanel, BorderLayout.CENTER);

        setContentPane(rootPanel);

        showPage(HOME_PAGE, false);
    }

  
    private void showPage(String pageName, boolean addToHistory)
    {
        if (addToHistory && currentPage != null)
        {
            pageHistory.push(currentPage);
        }

        currentPage = pageName;
        cardLayout.show(cardPanel, pageName);
    }

    private void goBack()
    {
        if (!pageHistory.isEmpty())
        {
            currentPage = pageHistory.pop();
            cardLayout.show(cardPanel, currentPage);
        }
    }

   private JPanel createHomePage()
{
    JPanel contentPanel = new JPanel();
    contentPanel.setOpaque(false);
    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
    contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

    JPanel titlePanel = new JPanel();
    titlePanel.setOpaque(false);
    titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 4, 0));

    JLabel leftSymbols1 = new JLabel("˚₊✩‧₊");
    leftSymbols1.setForeground(Home_Title_Symbols1);
    leftSymbols1.setFont(new Font("Dialog", Font.PLAIN, 12));

    JLabel leftSymbols2 = new JLabel("(￣ω￣)");
    leftSymbols2.setForeground(Home_Title_Symbols2);
    leftSymbols2.setFont(new Font("Dialog", Font.PLAIN, 12));


    JLabel mainTitle = new JLabel("HappyHappy Gadget Land");
    mainTitle.setForeground(Home_Title_Green);
    mainTitle.setFont(createHomeTitleFont());

    JLabel rightSymbols1 = new JLabel("(￣ω￣)");
    rightSymbols1.setForeground(Home_Title_Symbols2);
    rightSymbols1.setFont(new Font("Dialog", Font.PLAIN, 12));

    JLabel rightSymbols2 = new JLabel("₊✩‧₊˚");
    rightSymbols2.setForeground(Home_Title_Symbols1);
    rightSymbols2.setFont(new Font("Dialog", Font.PLAIN, 12));

    titlePanel.add(leftSymbols1);
    titlePanel.add(leftSymbols2);
    titlePanel.add(mainTitle);
    titlePanel.add(rightSymbols1);
    titlePanel.add(rightSymbols2);
    titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

    homeAddNewGadgetButton = createTextButton(">ADD NEW GADGET");
    homeDisplayAllGadgetsButton = createTextButton(">DISPLAY ALL GADGETS");
    homeInstructionsButton = createTextButton(">INSTRUCTIONS");

    homeAddNewGadgetButton.addActionListener(this);
    homeDisplayAllGadgetsButton.addActionListener(this);
    homeInstructionsButton.addActionListener(this);

    homeAddNewGadgetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    homeDisplayAllGadgetsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    homeInstructionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    contentPanel.add(titlePanel);
    contentPanel.add(Box.createVerticalStrut(26));
    contentPanel.add(homeAddNewGadgetButton);
    contentPanel.add(Box.createVerticalStrut(6));
    contentPanel.add(homeDisplayAllGadgetsButton);
    contentPanel.add(Box.createVerticalStrut(6));
    contentPanel.add(homeInstructionsButton);

    return createPageShell(null, false, null, null, contentPanel);
}

   private JPanel createInstructionsPage()
{
    instructionsBackButton = createNavButton("<");
    instructionsHomeButton = createNavButton("⌂");

    instructionsBackButton.addActionListener(this);
    instructionsHomeButton.addActionListener(this);

    JTextArea instructionsArea = new JTextArea();
    instructionsArea.setText(
        "Create gadgets in gadget land!\n\n"
        + "You can create a Mobile phone or a MP3.\n\n"
        + "When you create a gadget you will get a display number for it.\n\n"
        + "Use the display number for the gadget to perform actions with it.\n\n"
        + "If you forget the display number for a gadget, select \"Display all gadgets\" to locate it.\n\n"
        + "Enjoy!\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⠀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣀⡤⠶⠚⠉⢉⣩⠽⠟⠛⠛⠛⠃⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠞⠉⠀⢀⣠⠞⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⡞⠁⠀⠀⣰⠋⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠀⠀⠀⡼⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⣠⡤⠤⠄⢤⣄⣀⣀⣀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⠀⠀⢰⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⠴⠒⠋⠉⠀⠀⠀⣀⣤⠴⠒⠋⠉⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠻⡄⠀⠀⣧⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠞⢳⡄⢀⡴⠚⠉⠀⠀⠀⠀⠀⣠⠴⠚⠉⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠙⢦⡀⠘⣧⠀⠀⠀⠀⠀⠀⠀⠀⣰⠃⠀⠀⠹⡏⠀⠀⠀⠀⠀⣀⣴⠟⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⠳⢬⣳⣄⣠⠤⠤⠶⠶⠒⠋⠀⠀⠀⠀⠹⡀⠀⠀⠀⠀⠈⠉⠛⠲⢦⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢀⣠⠤⠖⠋⠉⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠱⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠉⢳⠦⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠖⠋⠀⠀⠀⣠⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢱⠀⠀⠀⠀⠀⠀⠀⠀⢀⣀⠀⢃⠈⠙⠲⣄⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⠞⠁⠀⠀⠀⢀⢾⠃⠀⠀⠀⠀⠀⠀⠀⠀⢢⠀⠀⠀⠀⠀⠀⠀⢣⠀⠀⠀⠀⠀⠀⠀⠀⠀⣹⠮⣄⠀⠀⠀⠙⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣰⠋⠀⠀⢀⡤⡴⠃⠈⠦⣀⠀⠀⠀⠀⠀⠀⢀⣷⢸⠀⠀⠀⠀⢀⣀⠘⡄⠤⠤⢤⠔⠒⠂⠉⠁⠀⠀⠀⠑⢄⡀⠀⠀⠙⢦⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣼⠃⠀⠀⢠⣞⠟⠀⠀⠀⡄⠀⠉⠒⠢⣤⣤⠄⣼⢻⠸⠀⠀⠀⠀⠉⢤⠀⢿⡖⠒⠊⢦⠤⠤⣀⣀⡀⠀⠀⠀⠈⠻⡝⠲⢤⣀⠙⢦⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠃⠀⠀⣴⣿⠎⠀⠀⢀⣜⠤⠄⢲⠎⠉⠀⠀⡼⠸⠘⡄⡇⠀⠀⠀⠀⢸⠀⢸⠘⢆⠀⠘⡄⠀⠀⠀⢢⠉⠉⠀⠒⠒⠽⡄⠀⠈⠙⠮⣷⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡟⠀⠀⣼⢻⠧⠐⠂⠉⡜⠀⠀⡰⡟⠀⠀⠀⡰⠁⡇⠀⡇⡇⠀⠀⠀⠀⢺⠇⠀⣆⡨⢆⠀⢽⠀⠀⠀⠈⡷⡄⠀⠀⠀⠀⠹⡄⠀⠀⠀⠈⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠃⠀⠀⢃⠎⠀⠀⠀⣴⠃⠀⡜⠹⠁⠀⠀⡰⠁⢠⠁⠀⢸⢸⠀⠀⠀⢠⡸⢣⠔⡏⠀⠈⢆⠀⣇⠀⠀⠀⢸⠘⢆⠀⠀⠀⠀⢳⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⡜⠀⠀⢀⡜⡞⠀⡜⠈⠏⠀⠈⡹⠑⠒⠼⡀⠀⠀⢿⠀⠀⠀⢀⡇⠀⢇⢁⠀⠀⠈⢆⢰⠀⠀⠀⠈⡄⠈⢢⠀⠀⠀⠈⣇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡀⠀⢰⠁⠀⢀⢮⠀⠇⡜⠀⠘⠀⠀⢰⠃⠀⠀⡇⠈⠁⠀⢘⡄⠀⠀⢸⠀⠀⣘⣼⠤⠤⠤⣈⡞⡀⠀⠀⠀⡇⠰⡄⢣⡀⠀⠀⢻⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⡇⠀⡜⠀⢀⠎⢸⢸⢰⠁⠀⠄⠀⢠⠃⠀⠀⢸⠀⠀⠀⠀⠀⡇⠀⠀⡆⠀⠀⣶⣿⡿⠿⡛⢻⡟⡇⠀⠀⠀⡇⠀⣿⣆⢡⠀⠀⢸⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⡏⠀⠉⢢⡎⠀⡇⣿⠊⠀⠀⠀⢠⡏⠀⠀⠀⠎⠀⠀⠀⠀⠀⡇⠀⡸⠀⠀⠀⡇⠀⢰⡆⡇⢸⢠⢹⠀⠀⠀⡇⠀⢹⠈⢧⣣⠀⠘⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡇⠀⠀⠀⡇⠀⡇⢹⠀⠀⠀⢀⡾⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⡇⢠⠃⠀⠀⠠⠟⡯⣻⣇⢃⠇⢠⠏⡇⠀⢸⡆⠀⢸⠀⠈⢳⡀⠀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣇⠀⡔⠋⡇⠀⢱⢼⠀⠀⡂⣼⡇⢹⣶⣶⣶⣤⣤⣀⠀⠀⠀⣇⠇⠀⠀⠀⠀⣶⡭⢃⣏⡘⠀⡎⠀⠇⠀⡾⣷⠀⣼⠀⠀⠀⢻⡄⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣹⠜⠋⠉⠓⢄⡏⢸⠀⠀⢳⡏⢸⠹⢀⣉⢭⣻⡽⠿⠛⠓⠀⠋⠀⠀⠀⠀⠀⠘⠛⠛⠓⠀⡄⡇⠀⢸⢰⡇⢸⡄⡟⠀⠀⠀⠀⢳⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣠⠞⠁⠀⠀⠀⠀⠀⢙⠌⡇⠀⣿⠁⠀⡇⡗⠉⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠰⠀⠀⠀⠀⠀⠀⠁⠁⠀⢸⣼⠀⠈⣇⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠁⠀⠀⢀⡠⠔⠚⠉⠉⢱⣇⢸⢧⠀⠀⠸⣱⠀⠀⠀⠀⠀⠀⠀⠀⣀⣀⡤⠦⡔⠀⠀⠀⠀⠀⢀⡼⠀⠀⣼⡏⠀⠀⢹⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⠀⠀⠀⠋⠀⠀⠀⢀⡠⠤⣿⣾⣇⣧⠀⠀⢫⡆⠀⠀⠀⠀⠀⠀⠀⢨⠀⠀⣠⠇⠀⠀⢀⡠⣶⠋⠀⠀⡸⣾⠁⠀⠀⠈⠁⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢸⡄⠀⠀⠀⠀⠠⠊⠁⠀⠀⢸⢃⠘⡜⡵⡀⠈⢿⡱⢲⡤⠤⢀⣀⣀⡀⠉⠉⣀⡠⡴⠚⠉⣸⢸⠀⠀⢠⣿⣿⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢧⠀⠀⠀⠀⠀⠀⠀⣀⠤⠚⠚⣤⣵⡰⡑⡄⠀⢣⡈⠳⡀⠀⠀⠀⢨⡋⠙⣆⢸⠀⠀⣰⢻⡎⠀⠀⡎⡇⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠈⢷⡀⠀⠀⠀⠀⠀⠁⠀⠀⠀⡸⢌⣳⣵⡈⢦⡀⠳⡀⠈⢦⡀⠀⠘⠏⠲⣌⠙⢒⠴⡧⣸⡇⠀⡸⢸⠇⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢠⣿⠢⡀⠀⠀⠀⠠⠄⡖⠋⠀⠀⠙⢿⣳⡀⠑⢄⠹⣄⡀⠙⢄⡠⠤⠒⠚⡖⡇⠀⠘⣽⡇⢠⠃⢸⢀⡇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⣾⠃⠀⠀⠀⠀⠀⢀⡼⣄⠀⠀⠀⠀⠀⠑⣽⣆⠀⠑⢝⡍⠒⠬⢧⣀⡠⠊⠀⠸⡀⠀⢹⡇⡎⠀⡿⢸⠇⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⡼⠁⠀⠀⠀⠀⠀⠀⢀⠻⣺⣧⠀⠀⠀⠰⢢⠈⢪⡷⡀⠀⠙⡄⠀⠀⠱⡄⠀⠀⠀⢧⠀⢸⡻⠀⢠⡇⣾⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
        + "⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⢰⠇⠀⠀⠀⠀⠀⠀⠀⢸⠀⡏⣿⠀⠀⠀⠀⢣⢇⠀⠑⣄⠀⠀⠸⡄⠀⠀⠘⡄⠀⠀⠸⡀⢸⠁⠀⡾⢰⡏⢳⡀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀⠀\n"
    );
    instructionsArea.setEditable(false);
    instructionsArea.setFont(new Font("Monospaced", Font.PLAIN, 8));
    instructionsArea.setForeground(DARK_TEXT);
    instructionsArea.setBackground(new Color(245, 248, 252));
    instructionsArea.setLineWrap(false);
    instructionsArea.setWrapStyleWord(false);
    instructionsArea.setMargin(new Insets(8, 8, 8, 8));
    instructionsArea.setBorder(BorderFactory.createLineBorder(new Color(166, 176, 196), 1));

    JScrollPane scrollPane = new JScrollPane(instructionsArea);
    scrollPane.setBorder(BorderFactory.createEmptyBorder());
    scrollPane.getVerticalScrollBar().setUnitIncrement(14);
    scrollPane.getHorizontalScrollBar().setUnitIncrement(14);

    return createPageShell(null, true, instructionsBackButton, instructionsHomeButton, scrollPane);
}

    private JPanel createAddGadgetPage()
    {
        addGadgetBackButton = createNavButton("<");
        addGadgetHomeButton = createNavButton("⌂");

        addGadgetBackButton.addActionListener(this);
        addGadgetHomeButton.addActionListener(this);

        addGadgetMobileButton = createTextButton(">MOBILE");
        addGadgetMP3Button = createTextButton(">MP3");

        addGadgetMobileButton.addActionListener(this);
        addGadgetMP3Button.addActionListener(this);

        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        addGadgetMobileButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addGadgetMP3Button.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPanel.add(Box.createVerticalStrut(8));
contentPanel.add(addGadgetMobileButton);
contentPanel.add(Box.createVerticalStrut(6));
contentPanel.add(addGadgetMP3Button);

ImageIcon icon = new ImageIcon("inspecture.png");
Image scaled = icon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
JLabel imageLabel = new JLabel(new ImageIcon(scaled));

JPanel imagePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
imagePanel.setOpaque(false);
imagePanel.add(imageLabel);
imagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

contentPanel.add(Box.createVerticalStrut(0));
contentPanel.add(imagePanel);

        return createPageShell("WHAT GADGET?", true, addGadgetBackButton, addGadgetHomeButton, contentPanel);
    }

 private JPanel createPageShell(String pageTitle, boolean showNavigation, JButton backButton, JButton homeButton, JComponent content)
{
    JPanel outerPanel = new JPanel(new BorderLayout());
    outerPanel.setOpaque(false);
    outerPanel.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

    XPScreenPanel screenPanel = new XPScreenPanel();
    screenPanel.setLayout(new BorderLayout());
    screenPanel.setBorder(BorderFactory.createEmptyBorder(8, 12, 12, 12));

    JPanel topChromePanel = new JPanel(new BorderLayout());
    topChromePanel.setOpaque(false);
    topChromePanel.setBorder(BorderFactory.createEmptyBorder(0, 4, 6, 4));

    JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
    leftPanel.setOpaque(false);
    leftPanel.setPreferredSize(new Dimension(70, 28));

    JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 2, 0));
    rightPanel.setOpaque(false);
    rightPanel.setPreferredSize(new Dimension(110, 28));
    rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 10));

    if (showNavigation && backButton != null)
    {
        leftPanel.add(backButton);
    }

    if (showNavigation && homeButton != null)
    {
        rightPanel.add(homeButton);
    }

    JButton pageMinimiseButton = createWindowControlButton("_");
    JButton pageCloseButton = createWindowControlButton("X");

    pageMinimiseButton.addActionListener(new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            setState(JFrame.ICONIFIED);
        }
    });

    pageCloseButton.addActionListener(new ActionListener()
    {
        public void actionPerformed(ActionEvent e)
        {
            dispose();
        }
    });

    rightPanel.add(pageMinimiseButton);
    rightPanel.add(pageCloseButton);

    topChromePanel.add(leftPanel, BorderLayout.WEST);
    topChromePanel.add(rightPanel, BorderLayout.EAST);

    MouseAdapter dragListener = new MouseAdapter()
    {
        public void mousePressed(MouseEvent e)
        {
            dragOffset = e.getPoint();
        }

        public void mouseDragged(MouseEvent e)
        {
            Point screenPoint = e.getLocationOnScreen();
            setLocation(screenPoint.x - dragOffset.x, screenPoint.y - dragOffset.y);
        }
    };

    outerPanel.addMouseListener(dragListener);
 outerPanel.addMouseMotionListener(dragListener);

    JPanel titleAndContentPanel = new JPanel();
    titleAndContentPanel.setOpaque(false);
    titleAndContentPanel.setLayout(new BoxLayout(titleAndContentPanel, BoxLayout.Y_AXIS));

    if (pageTitle != null)
    {
        JLabel titleLabel = createPageTitleLabel(pageTitle);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleAndContentPanel.add(titleLabel);
        titleAndContentPanel.add(Box.createVerticalStrut(8));
    }

    content.setAlignmentX(Component.CENTER_ALIGNMENT);
    titleAndContentPanel.add(content);

    JPanel contentWrapper = new JPanel(new BorderLayout());
    contentWrapper.setOpaque(false);
    contentWrapper.setBorder(BorderFactory.createEmptyBorder(4, 14, 8, 14));
    contentWrapper.add(titleAndContentPanel, BorderLayout.NORTH);

    JScrollPane scrollPane = new JScrollPane(contentWrapper);
    scrollPane.setBorder(BorderFactory.createEmptyBorder());
    scrollPane.setOpaque(false);
    scrollPane.getViewport().setOpaque(false);
    scrollPane.getVerticalScrollBar().setUnitIncrement(14);
    scrollPane.getHorizontalScrollBar().setUnitIncrement(14);

    screenPanel.add(topChromePanel, BorderLayout.NORTH);
    screenPanel.add(scrollPane, BorderLayout.CENTER);

    outerPanel.add(screenPanel, BorderLayout.CENTER);
    return outerPanel;
}

    private JLabel createPageTitleLabel(String text)
{
    JLabel label = new JLabel(text, SwingConstants.CENTER);
    label.setForeground(WHITE_TEXT);
    label.setFont(createPageTitleFont());
    return label;
}
    private JButton createTextButton(String text)
    {
        JButton button = new JButton(text);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setForeground(WHITE_TEXT);
        button.setFont(createButtonFont());
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private JButton createNavButton(String text)
    {
        JButton button = new JButton(text);
        button.setOpaque(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setForeground(WHITE_TEXT);
        button.setFont(createNavFont());
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private JButton createWindowControlButton(String text)
    {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(18, 14));
        button.setFont(new Font("Tahoma", Font.BOLD, 10));
        button.setForeground(DARK_TEXT);
        button.setFocusPainted(false);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(120, 131, 150), 1),
            BorderFactory.createEmptyBorder(1, 1, 1, 1)
        ));
        return button;
    }

    private Font createWindowTitleFont()
    {
        return new Font("Tahoma", Font.BOLD, 14);
    }

    private Font createHomeTitleFont()
{
    return new Font("Dialog", Font.BOLD | Font.ITALIC, 16);
}

private Font createPageTitleFont()
{
    return new Font("Monospaced", Font.BOLD | Font.BOLD, 14);
}

    private Font createButtonFont()
    {
        return new Font("Segoe UI", Font.PLAIN, 13);
    }

    private Font createBodyFont()
    {
        return new Font("Dialog", Font.PLAIN, 15);
    }

    private Font createNavFont()
    {
        return new Font("Monospaced", Font.BOLD, 17);
    }

    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();

        if (source == minimiseButton)
        {
            setState(JFrame.ICONIFIED);
        }
        else if (source == closeButton)
        {
            dispose();
        }
        else if (source == homeAddNewGadgetButton)
        {
            showPage(ADD_GADGET_PAGE, true);
        }
        else if (source == homeInstructionsButton)
        {
            showPage(INSTRUCTIONS_PAGE, true);
        }
        else if (source == homeDisplayAllGadgetsButton)
        {
            if (gadgets.size() == 0)
            {
                JOptionPane.showMessageDialog(this, "No gadgets in the realm yet.");
            }
            else
            {
                System.out.println("Display all gadgets page will be added in the next phase.");
            }
        }
        else if (source == instructionsBackButton || source == addGadgetBackButton)
        {
            goBack();
        }
        else if (source == instructionsHomeButton || source == addGadgetHomeButton)
        {
            pageHistory.clear();
            showPage(HOME_PAGE, false);
        }
        else if (source == addGadgetMobileButton)
        {
            System.out.println("Mobile page will be added in the next phase.");
        }
        else if (source == addGadgetMP3Button)
        {
            System.out.println("MP3 page will be added in the next phase.");
        }
    }

    public static void main(String[] args)
    {
        GadgetShop shop = new GadgetShop();
        shop.setVisible(true);
    }

 private class XPWindowPanel extends JPanel
{
    public XPWindowPanel()
    {
        setOpaque(false);
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint gradient = new GradientPaint(
            0, 0, WINDOW_TOP,
            0, getHeight(), WINDOW_BOTTOM
        );

        g2.setPaint(gradient);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);

        g2.setColor(BORDER_DARK);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 8, 8);

        g2.setColor(BORDER_LIGHT);
        g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 6, 6);

        g2.dispose();
    }
}

    private class XPTitleBarPanel extends JPanel
    {
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g.create();

            GradientPaint gradient = new GradientPaint(
                0, 0, TITLEBAR_TOP,
                0, getHeight(), TITLEBAR_BOTTOM
            );

            g2.setPaint(gradient);
            g2.fillRect(0, 0, getWidth(), getHeight());

            g2.setColor(new Color(255, 255, 255, 110));
            g2.drawLine(0, 0, getWidth(), 0);

            g2.setColor(new Color(70, 90, 120));
            g2.drawLine(0, getHeight() - 1, getWidth(), getHeight() - 1);

            g2.dispose();
        }
    }

   private class XPScreenPanel extends JPanel
{
    public XPScreenPanel()
    {
        setOpaque(false);
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        GradientPaint gradient = new GradientPaint(
            0, 0, SCREEN_TOP,
            0, getHeight(), SCREEN_BOTTOM
        );

        g2.setPaint(gradient);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);

        g2.setColor(new Color(230, 238, 248));
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 8, 8);

        g2.setColor(new Color(255, 255, 255, 60));
        g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 6, 6);

        g2.dispose();
    }
}}