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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class GadgetShop extends JFrame implements ActionListener
{
    private ArrayList<Gadget> gadgets;

    private CardLayout cardLayout;
    private JPanel cardPanel;
    private String currentPage;
    private Stack<String> pageHistory;

    private JButton homeAddNewGadgetButton;
    private JButton homeDisplayAllGadgetsButton;
    private JButton homeInstructionsButton;

    private JButton instructionsBackButton;
    private JButton instructionsHomeButton;

    private JButton addGadgetBackButton;
    private JButton addGadgetHomeButton;
    private JButton addGadgetMobileButton;
    private JButton addGadgetMP3Button;

    private JButton createMobileBackButton;
    private JButton createMobileHomeButton;
    private JButton createMobileButton;
    private JButton clearMobileButton;
    private JTextField mobileModelField;
    private JTextField mobilePriceField;
    private JTextField mobileWeightField;
    private JTextField mobileSizeField;
    private JTextField mobileCreditField;

    private JButton createMP3BackButton;
    private JButton createMP3HomeButton;
    private JButton createMP3Button;
    private JButton clearMP3Button;
    private JTextField mp3ModelField;
    private JTextField mp3PriceField;
    private JTextField mp3WeightField;
    private JTextField mp3SizeField;
    private JTextField mp3MemoryField;

    private JButton displayAllBackButton;
    private JButton displayAllHomeButton;
    private JTextArea displayAllArea;

    private JButton successBackButton;
    private JButton successHomeButton;
    private JTextArea successMessageArea;
    private JButton successActionButton;
    private JButton successSecondActionButton;
    private JButton successDisplayAllButton;
    private JButton successAddAnotherButton;

    private JButton makeCallBackButton;
    private JButton makeCallHomeButton;
    private JButton makeCallButton;
    private JButton clearMakeCallButton;
    private JTextField callDisplayNumberField;
    private JTextField callPhoneNumberField;
    private JTextField callDurationField;

    private JButton addCreditBackButton;
    private JButton addCreditHomeButton;
    private JButton addCreditButton;
    private JButton clearAddCreditButton;
    private JTextField creditDisplayNumberField;
    private JTextField creditAmountField;

    private JButton downloadSongBackButton;
    private JButton downloadSongHomeButton;
    private JButton downloadSongButton;
    private JButton clearDownloadSongButton;
    private JTextField downloadDisplayNumberField;
    private JTextField songSizeField;

    private JButton freeSpaceBackButton;
    private JButton freeSpaceHomeButton;
    private JButton freeSpaceButton;
    private JButton clearFreeSpaceButton;
    private JTextField freeSpaceDisplayNumberField;
    private JTextField freeSpaceAmountField;

    private String successActionPage;
    private String successSecondActionPage;

    private final String HOME_PAGE = "HOME_PAGE";
    private final String INSTRUCTIONS_PAGE = "INSTRUCTIONS_PAGE";
    private final String ADD_GADGET_PAGE = "ADD_GADGET_PAGE";
    private final String CREATE_MOBILE_PAGE = "CREATE_MOBILE_PAGE";
    private final String CREATE_MP3_PAGE = "CREATE_MP3_PAGE";
    private final String DISPLAY_ALL_PAGE = "DISPLAY_ALL_PAGE";
    private final String SUCCESS_PAGE = "SUCCESS_PAGE";
    private final String MAKE_CALL_PAGE = "MAKE_CALL_PAGE";
    private final String ADD_CREDIT_PAGE = "ADD_CREDIT_PAGE";
    private final String DOWNLOAD_SONG_PAGE = "DOWNLOAD_SONG_PAGE";
    private final String FREE_SPACE_PAGE = "FREE_SPACE_PAGE";

    private final Color WINDOW_TOP = new Color(235, 238, 245);
    private final Color WINDOW_BOTTOM = new Color(196, 202, 214);

    private final Color SCREEN_TOP = new Color(155, 185, 225);
private final Color SCREEN_BOTTOM = new Color(120, 155, 205);

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
        cardPanel.add(createMobilePage(), CREATE_MOBILE_PAGE);
        cardPanel.add(createMP3Page(), CREATE_MP3_PAGE);
        cardPanel.add(createDisplayAllPage(), DISPLAY_ALL_PAGE);
        cardPanel.add(createSuccessPage(), SUCCESS_PAGE);
        cardPanel.add(createMakeCallPage(), MAKE_CALL_PAGE);
        cardPanel.add(createAddCreditPage(), ADD_CREDIT_PAGE);
        cardPanel.add(createDownloadSongPage(), DOWNLOAD_SONG_PAGE);
        cardPanel.add(createFreeSpacePage(), FREE_SPACE_PAGE);

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

private JPanel createGroupBox(String title)
{
    JPanel panel = new JPanel();
    panel.setOpaque(false);
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    javax.swing.border.Border etchedBorder = BorderFactory.createEtchedBorder(
        EtchedBorder.LOWERED,
        new Color(230, 235, 245),
        new Color(110, 130, 170)
    );

    javax.swing.border.Border groupBorder = etchedBorder;

    if (title != null && title.length() > 0)
    {
        groupBorder = BorderFactory.createTitledBorder(
            etchedBorder,
            title,
            TitledBorder.LEFT,
            TitledBorder.TOP,
            new Font("Monospaced", Font.BOLD, 12),
            WHITE_TEXT
        );
    }

    panel.setBorder(BorderFactory.createCompoundBorder(
        groupBorder,
        BorderFactory.createEmptyBorder(12, 12, 12, 12)
    ));

    return panel;
}

  private JPanel createHomePage()
{
    JPanel contentPanel = new JPanel();
    contentPanel.setOpaque(false);
    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
    contentPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

    homeAddNewGadgetButton = createTextButton(">ADD NEW GADGET");
    homeDisplayAllGadgetsButton = createTextButton(">DISPLAY ALL GADGETS");
    homeInstructionsButton = createTextButton(">INSTRUCTIONS");

    homeAddNewGadgetButton.addActionListener(this);
    homeDisplayAllGadgetsButton.addActionListener(this);
    homeInstructionsButton.addActionListener(this);

    JPanel titlePanel = new JPanel();
    titlePanel.setOpaque(false);
    titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 2, 0));

    JLabel leftSymbols1 = new JLabel("˚₊✩‧₊");
    leftSymbols1.setForeground(Home_Title_Symbols1);
    leftSymbols1.setFont(new Font("Dialog", Font.PLAIN, 10));

    JLabel leftSymbols2 = new JLabel("(￣ω￣)");
    leftSymbols2.setForeground(Home_Title_Symbols2);
    leftSymbols2.setFont(new Font("Dialog", Font.PLAIN, 10));

    JLabel mainTitle = new JLabel("HappyHappy Gadget Land");
    mainTitle.setForeground(Home_Title_Green);
    mainTitle.setFont(createHomeTitleFont());

    JLabel rightSymbols1 = new JLabel("(￣ω￣)");
    rightSymbols1.setForeground(Home_Title_Symbols2);
    rightSymbols1.setFont(new Font("Dialog", Font.PLAIN, 10));

    JLabel rightSymbols2 = new JLabel("₊✩‧₊˚");
    rightSymbols2.setForeground(Home_Title_Symbols1);
    rightSymbols2.setFont(new Font("Dialog", Font.PLAIN, 10));

    titlePanel.add(leftSymbols1);
    titlePanel.add(leftSymbols2);
    titlePanel.add(mainTitle);
    titlePanel.add(rightSymbols1);
    titlePanel.add(rightSymbols2);
    titlePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

    JPanel homeGroupBox = createGroupBox(null);
    homeGroupBox.setAlignmentX(Component.CENTER_ALIGNMENT);

    homeAddNewGadgetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    homeDisplayAllGadgetsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    homeInstructionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    homeGroupBox.add(homeAddNewGadgetButton);
    homeGroupBox.add(Box.createVerticalStrut(6));
    homeGroupBox.add(homeDisplayAllGadgetsButton);
    homeGroupBox.add(Box.createVerticalStrut(6));
    homeGroupBox.add(homeInstructionsButton);

    contentPanel.add(titlePanel);
    contentPanel.add(Box.createVerticalStrut(4));
    contentPanel.add(homeGroupBox);

    return createPageShell(null, false, null, null, contentPanel);
}

   private JPanel createInstructionsPage()
{
    instructionsBackButton = createNavButton("<");
    instructionsHomeButton = createNavButton("\u2302");

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
    addGadgetHomeButton = createNavButton("\u2302");

    addGadgetBackButton.addActionListener(this);
    addGadgetHomeButton.addActionListener(this);

    addGadgetMobileButton = createTextButton(">MOBILE");
    addGadgetMP3Button = createTextButton(">MP3");

    addGadgetMobileButton.addActionListener(this);
    addGadgetMP3Button.addActionListener(this);

    JPanel contentPanel = new JPanel();
    contentPanel.setOpaque(false);
    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

    JPanel groupBox = createGroupBox("WHAT GADGET?");
    groupBox.setAlignmentX(Component.CENTER_ALIGNMENT);

    ImageIcon icon = new ImageIcon("inspecture.png");
    Image scaled = icon.getImage().getScaledInstance(82, 82, Image.SCALE_SMOOTH);
    JLabel imageLabel = new JLabel(new ImageIcon(scaled));

    JPanel buttonPanel = new JPanel();
    buttonPanel.setOpaque(false);
    buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

    addGadgetMobileButton.setAlignmentX(Component.LEFT_ALIGNMENT);
    addGadgetMP3Button.setAlignmentX(Component.LEFT_ALIGNMENT);

    buttonPanel.add(addGadgetMobileButton);
    buttonPanel.add(Box.createVerticalStrut(6));
    buttonPanel.add(addGadgetMP3Button);

    JPanel chooserPanel = new JPanel(new BorderLayout(12, 0));
    chooserPanel.setOpaque(false);
    chooserPanel.add(buttonPanel, BorderLayout.CENTER);
    chooserPanel.add(imageLabel, BorderLayout.EAST);
    chooserPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

    groupBox.add(chooserPanel);

    contentPanel.add(Box.createVerticalStrut(2));
    contentPanel.add(groupBox);
    

    return createPageShell(null, true, addGadgetBackButton, addGadgetHomeButton, contentPanel);
}

private JPanel createMobilePage()
{
    createMobileBackButton = createNavButton("<");
    createMobileHomeButton = createNavButton("\u2302");
    createMobileButton = createTextButton(">CREATE MOBILE");
    clearMobileButton = createTextButton(">CLEAR");

    createMobileBackButton.addActionListener(this);
    createMobileHomeButton.addActionListener(this);
    createMobileButton.addActionListener(this);
    clearMobileButton.addActionListener(this);

    mobileModelField = createInputField();
    mobilePriceField = createInputField();
    mobileWeightField = createInputField();
    mobileSizeField = createInputField();
    mobileSizeField.setText("71x137x9");
    mobileCreditField = createInputField();

    JPanel formBox = createGroupBox("MOBILE DETAILS");
    formBox.setAlignmentX(Component.CENTER_ALIGNMENT);
    formBox.add(createFieldRow("Model", mobileModelField));
    formBox.add(createFieldRow("Price", mobilePriceField));
    formBox.add(createFieldRow("Weight (g)", mobileWeightField));
    formBox.add(createFieldRow("Size (mm)", mobileSizeField));
    formBox.add(createFieldRow("Credit", mobileCreditField));
    formBox.add(Box.createVerticalStrut(8));
    formBox.add(createButtonRow(createMobileButton, clearMobileButton));

    JPanel contentPanel = new JPanel();
    contentPanel.setOpaque(false);
    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
    contentPanel.add(formBox);

    return createPageShell("CREATE MOBILE", true, createMobileBackButton, createMobileHomeButton, contentPanel);
}

private JPanel createMP3Page()
{
    createMP3BackButton = createNavButton("<");
    createMP3HomeButton = createNavButton("\u2302");
    createMP3Button = createTextButton(">CREATE MP3");
    clearMP3Button = createTextButton(">CLEAR");

    createMP3BackButton.addActionListener(this);
    createMP3HomeButton.addActionListener(this);
    createMP3Button.addActionListener(this);
    clearMP3Button.addActionListener(this);

    mp3ModelField = createInputField();
    mp3PriceField = createInputField();
    mp3WeightField = createInputField();
    mp3SizeField = createInputField();
    mp3SizeField.setText("71x137x9");
    mp3MemoryField = createInputField();

    JPanel formBox = createGroupBox("MP3 DETAILS");
    formBox.setAlignmentX(Component.CENTER_ALIGNMENT);
    formBox.add(createFieldRow("Model", mp3ModelField));
    formBox.add(createFieldRow("Price", mp3PriceField));
    formBox.add(createFieldRow("Weight (g)", mp3WeightField));
    formBox.add(createFieldRow("Size (mm)", mp3SizeField));
    formBox.add(createFieldRow("Memory", mp3MemoryField));
    formBox.add(Box.createVerticalStrut(8));
    formBox.add(createButtonRow(createMP3Button, clearMP3Button));

    JPanel contentPanel = new JPanel();
    contentPanel.setOpaque(false);
    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
    contentPanel.add(formBox);

    return createPageShell("CREATE MP3", true, createMP3BackButton, createMP3HomeButton, contentPanel);
}

private JPanel createDisplayAllPage()
{
    displayAllBackButton = createNavButton("<");
    displayAllHomeButton = createNavButton("\u2302");
    displayAllBackButton.addActionListener(this);
    displayAllHomeButton.addActionListener(this);

    displayAllArea = new JTextArea();
    displayAllArea.setEditable(false);
    displayAllArea.setFont(new Font("Monospaced", Font.PLAIN, 11));
    displayAllArea.setForeground(DARK_TEXT);
    displayAllArea.setBackground(new Color(245, 248, 252));
    displayAllArea.setMargin(new Insets(8, 8, 8, 8));
    displayAllArea.setBorder(BorderFactory.createLineBorder(new Color(166, 176, 196), 1));

    JScrollPane scrollPane = new JScrollPane(displayAllArea);
    scrollPane.setPreferredSize(new Dimension(310, 120));
    scrollPane.setBorder(BorderFactory.createEmptyBorder());

    return createPageShell("ALL GADGETS", true, displayAllBackButton, displayAllHomeButton, scrollPane);
}

private JPanel createSuccessPage()
{
    successBackButton = createNavButton("<");
    successHomeButton = createNavButton("\u2302");
    successActionButton = createTextButton(">ACTION");
    successSecondActionButton = createTextButton(">ACTION");
    successDisplayAllButton = createTextButton(">DISPLAY ALL GADGETS");
    successAddAnotherButton = createTextButton(">ADD ANOTHER GADGET");

    successBackButton.addActionListener(this);
    successHomeButton.addActionListener(this);
    successActionButton.addActionListener(this);
    successSecondActionButton.addActionListener(this);
    successDisplayAllButton.addActionListener(this);
    successAddAnotherButton.addActionListener(this);

    successMessageArea = new JTextArea();
    successMessageArea.setEditable(false);
    successMessageArea.setOpaque(false);
    successMessageArea.setForeground(WHITE_TEXT);
    successMessageArea.setFont(new Font("Monospaced", Font.BOLD, 12));
    successMessageArea.setLineWrap(true);
    successMessageArea.setWrapStyleWord(true);
    successMessageArea.setAlignmentX(Component.CENTER_ALIGNMENT);

    JPanel messageBox = createGroupBox("SAVED");
    messageBox.setAlignmentX(Component.CENTER_ALIGNMENT);
    messageBox.add(successMessageArea);
    messageBox.add(Box.createVerticalStrut(8));

    successActionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    successSecondActionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    successDisplayAllButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    successAddAnotherButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    messageBox.add(successActionButton);
    messageBox.add(Box.createVerticalStrut(4));
    messageBox.add(successSecondActionButton);
    messageBox.add(Box.createVerticalStrut(4));
    messageBox.add(successDisplayAllButton);
    messageBox.add(Box.createVerticalStrut(4));
    messageBox.add(successAddAnotherButton);

    JPanel contentPanel = new JPanel();
    contentPanel.setOpaque(false);
    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
    contentPanel.add(Box.createVerticalStrut(12));
    contentPanel.add(messageBox);

    return createPageShell(null, true, successBackButton, successHomeButton, contentPanel);
}

private JPanel createMakeCallPage()
{
    makeCallBackButton = createNavButton("<");
    makeCallHomeButton = createNavButton("\u2302");
    makeCallButton = createTextButton(">MAKE CALL");
    clearMakeCallButton = createTextButton(">CLEAR");
    makeCallBackButton.addActionListener(this);
    makeCallHomeButton.addActionListener(this);
    makeCallButton.addActionListener(this);
    clearMakeCallButton.addActionListener(this);

    callDisplayNumberField = createInputField();
    callPhoneNumberField = createInputField();
    callDurationField = createInputField();

    JPanel formBox = createGroupBox("CALL DETAILS");
    formBox.setAlignmentX(Component.CENTER_ALIGNMENT);
    formBox.add(createFieldRow("Display", callDisplayNumberField));
    formBox.add(createPhoneFieldRow("Phone", callPhoneNumberField));
    formBox.add(createFieldRow("Duration", callDurationField));
    formBox.add(Box.createVerticalStrut(8));
    formBox.add(createButtonRow(makeCallButton, clearMakeCallButton));

    JPanel contentPanel = new JPanel();
    contentPanel.setOpaque(false);
    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
    contentPanel.add(formBox);

    return createPageShell("MAKE CALL", true, makeCallBackButton, makeCallHomeButton, contentPanel);
}

private JPanel createAddCreditPage()
{
    addCreditBackButton = createNavButton("<");
    addCreditHomeButton = createNavButton("\u2302");
    addCreditButton = createTextButton(">ADD CREDIT");
    clearAddCreditButton = createTextButton(">CLEAR");
    addCreditBackButton.addActionListener(this);
    addCreditHomeButton.addActionListener(this);
    addCreditButton.addActionListener(this);
    clearAddCreditButton.addActionListener(this);

    creditDisplayNumberField = createInputField();
    creditAmountField = createInputField();

    JPanel formBox = createGroupBox("CREDIT DETAILS");
    formBox.setAlignmentX(Component.CENTER_ALIGNMENT);
    formBox.add(createFieldRow("Display", creditDisplayNumberField));
    formBox.add(createFieldRow("Credit", creditAmountField));
    formBox.add(Box.createVerticalStrut(8));
    formBox.add(createButtonRow(addCreditButton, clearAddCreditButton));

    JPanel contentPanel = new JPanel();
    contentPanel.setOpaque(false);
    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
    contentPanel.add(formBox);

    return createPageShell("ADD CREDIT", true, addCreditBackButton, addCreditHomeButton, contentPanel);
}

private JPanel createDownloadSongPage()
{
    downloadSongBackButton = createNavButton("<");
    downloadSongHomeButton = createNavButton("\u2302");
    downloadSongButton = createTextButton(">DOWNLOAD SONG");
    clearDownloadSongButton = createTextButton(">CLEAR");
    downloadSongBackButton.addActionListener(this);
    downloadSongHomeButton.addActionListener(this);
    downloadSongButton.addActionListener(this);
    clearDownloadSongButton.addActionListener(this);

    downloadDisplayNumberField = createInputField();
    songSizeField = createInputField();

    JPanel formBox = createGroupBox("SONG DETAILS");
    formBox.setAlignmentX(Component.CENTER_ALIGNMENT);
    formBox.add(createFieldRow("Display", downloadDisplayNumberField));
    formBox.add(createFieldRow("Song size", songSizeField));
    formBox.add(Box.createVerticalStrut(8));
    formBox.add(createButtonRow(downloadSongButton, clearDownloadSongButton));

    JPanel contentPanel = new JPanel();
    contentPanel.setOpaque(false);
    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
    contentPanel.add(formBox);

    return createPageShell("DOWNLOAD", true, downloadSongBackButton, downloadSongHomeButton, contentPanel);
}

private JPanel createFreeSpacePage()
{
    freeSpaceBackButton = createNavButton("<");
    freeSpaceHomeButton = createNavButton("\u2302");
    freeSpaceButton = createTextButton(">FREE SPACE");
    clearFreeSpaceButton = createTextButton(">CLEAR");
    freeSpaceBackButton.addActionListener(this);
    freeSpaceHomeButton.addActionListener(this);
    freeSpaceButton.addActionListener(this);
    clearFreeSpaceButton.addActionListener(this);

    freeSpaceDisplayNumberField = createInputField();
    freeSpaceAmountField = createInputField();

    JPanel formBox = createGroupBox("FREE SPACE");
    formBox.setAlignmentX(Component.CENTER_ALIGNMENT);
    formBox.add(createFieldRow("Display", freeSpaceDisplayNumberField));
    formBox.add(createFieldRow("Amount", freeSpaceAmountField));
    formBox.add(Box.createVerticalStrut(8));
    formBox.add(createButtonRow(freeSpaceButton, clearFreeSpaceButton));

    JPanel contentPanel = new JPanel();
    contentPanel.setOpaque(false);
    contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
    contentPanel.add(formBox);

    return createPageShell("FREE SPACE", true, freeSpaceBackButton, freeSpaceHomeButton, contentPanel);
}

private JPanel createFieldRow(String labelText, JTextField textField)
{
    JPanel row = new JPanel(new BorderLayout(8, 0));
    row.setOpaque(false);
    row.setMaximumSize(new Dimension(280, 26));

    JLabel label = new JLabel(labelText);
    label.setPreferredSize(new Dimension(90, 20));
    label.setForeground(WHITE_TEXT);
    label.setFont(new Font("Monospaced", Font.BOLD, 11));

    row.add(label, BorderLayout.WEST);
    row.add(textField, BorderLayout.CENTER);
    row.setAlignmentX(Component.CENTER_ALIGNMENT);

    return row;
}

private JPanel createPhoneFieldRow(String labelText, JTextField textField)
{
    JPanel row = new JPanel(new BorderLayout(8, 0));
    row.setOpaque(false);
    row.setMaximumSize(new Dimension(280, 26));

    JLabel label = new JLabel(labelText);
    label.setPreferredSize(new Dimension(90, 20));
    label.setForeground(WHITE_TEXT);
    label.setFont(new Font("Monospaced", Font.BOLD, 11));

    JLabel plusLabel = new JLabel("+");
    plusLabel.setForeground(WHITE_TEXT);
    plusLabel.setFont(new Font("Monospaced", Font.BOLD, 13));

    JPanel inputPanel = new JPanel(new BorderLayout(4, 0));
    inputPanel.setOpaque(false);
    inputPanel.add(plusLabel, BorderLayout.WEST);
    inputPanel.add(textField, BorderLayout.CENTER);

    row.add(label, BorderLayout.WEST);
    row.add(inputPanel, BorderLayout.CENTER);
    row.setAlignmentX(Component.CENTER_ALIGNMENT);

    return row;
}

private JPanel createButtonRow(JButton leftButton, JButton rightButton)
{
    JPanel row = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 0));
    row.setOpaque(false);
    row.setMaximumSize(new Dimension(280, 26));
    row.add(leftButton);
    row.add(rightButton);
    row.setAlignmentX(Component.CENTER_ALIGNMENT);
    return row;
}

private JTextField createInputField()
{
    JTextField textField = new JTextField();
    textField.setPreferredSize(new Dimension(150, 20));
    textField.setMaximumSize(new Dimension(150, 20));
    textField.setFont(new Font("Monospaced", Font.PLAIN, 11));
    textField.setForeground(DARK_TEXT);
    textField.setBackground(new Color(245, 248, 252));
    textField.setBorder(BorderFactory.createCompoundBorder(
        BorderFactory.createLineBorder(new Color(120, 131, 150), 1),
        BorderFactory.createEmptyBorder(1, 4, 1, 4)
    ));
    return textField;
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
        return new Font("Monospaced", Font.PLAIN, 15);
    }

    private Font createNavFont()
    {
        return new Font("Monospaced", Font.BOLD, 17);
    }

    private void createMobileFromForm()
    {
        if (allFieldsEmpty(mobileModelField, mobilePriceField, mobileWeightField, mobileSizeField, mobileCreditField))
        {
            showValidationMessage("Please complete all fields.");
            return;
        }

        String model = getRequiredText(mobileModelField, "Model");
        String size = getRequiredText(mobileSizeField, "Size");
        Double price = getPositiveDoubleFromField(mobilePriceField, "Price");
        Integer weight = getIntegerFromField(mobileWeightField, "Weight", true);
        Integer credit = getIntegerFromField(mobileCreditField, "Credit", false);

        if (model == null || size == null || price == null || weight == null || credit == null)
        {
            return;
        }
        if (!isValidSize(size))
        {
            showValidationMessage("Add size in shown format.");
            mobileSizeField.requestFocusInWindow();
            return;
        }

        Mobile mobile = new Mobile(model, price.doubleValue(), weight.intValue(), size, credit.intValue());
        gadgets.add(mobile);
        int displayNumber = gadgets.size() - 1;
        clearMobileForm();
        showSuccess(
            "You've added a new Mobile gadget.\nGadget display number: " + displayNumber,
            "MAKE A CALL",
            MAKE_CALL_PAGE
        );
    }

    private void createMP3FromForm()
    {
        if (allFieldsEmpty(mp3ModelField, mp3PriceField, mp3WeightField, mp3SizeField, mp3MemoryField))
        {
            showValidationMessage("Please complete all fields.");
            return;
        }

        String model = getRequiredText(mp3ModelField, "Model");
        String size = getRequiredText(mp3SizeField, "Size");
        Double price = getPositiveDoubleFromField(mp3PriceField, "Price");
        Integer weight = getIntegerFromField(mp3WeightField, "Weight", true);
        Integer memory = getIntegerFromField(mp3MemoryField, "Memory", true);

        if (model == null || size == null || price == null || weight == null || memory == null)
        {
            return;
        }
        if (!isValidSize(size))
        {
            showValidationMessage("Add size in shown format.");
            mp3SizeField.requestFocusInWindow();
            return;
        }

        MP3 mp3 = new MP3(model, price.doubleValue(), weight.intValue(), size, memory.intValue());
        gadgets.add(mp3);
        int displayNumber = gadgets.size() - 1;
        clearMP3Form();
        showSuccess(
            "You've added a new MP3 gadget.\nGadget display number: " + displayNumber,
            "DOWNLOAD A SONG",
            DOWNLOAD_SONG_PAGE
        );
    }

    private String getRequiredText(JTextField field, String label)
    {
        String text = field.getText().trim();
        if (text.length() == 0)
        {
            showValidationMessage(label + " cannot be empty.");
            field.requestFocusInWindow();
            return null;
        }
        return text;
    }

    private Double getDoubleFromField(JTextField field, String label, boolean mustBePositive)
    {
        String text = field.getText().trim();
        if (text.length() == 0)
        {
            showValidationMessage(label + " cannot be empty.");
            field.requestFocusInWindow();
            return null;
        }

        try
        {
            double value = Double.parseDouble(text);
            if ((mustBePositive && value <= 0) || (!mustBePositive && value < 0))
            {
                showValidationMessage(label + " must be " + (mustBePositive ? "positive." : "zero or more."));
                field.requestFocusInWindow();
                return null;
            }
            return Double.valueOf(value);
        }
        catch (NumberFormatException exception)
        {
            showValidationMessage(label + " must be a number.");
            field.requestFocusInWindow();
            return null;
        }
    }

    private Double getPositiveDoubleFromField(JTextField field, String label)
    {
        String text = field.getText().trim();
        if (text.length() == 0)
        {
            showValidationMessage(label + " cannot be empty.");
            field.requestFocusInWindow();
            return null;
        }

        try
        {
            double value = Double.parseDouble(text);
            if (value <= 0)
            {
                showValidationMessage(label + " must be positive.");
                field.requestFocusInWindow();
                return null;
            }
            return Double.valueOf(value);
        }
        catch (NumberFormatException exception)
        {
            showValidationMessage(label + " must be a number.");
            field.requestFocusInWindow();
            return null;
        }
    }

    private Integer getIntegerFromField(JTextField field, String label, boolean mustBePositive)
    {
        String text = field.getText().trim();
        if (text.length() == 0)
        {
            showValidationMessage(label + " cannot be empty.");
            field.requestFocusInWindow();
            return null;
        }

        try
        {
            int value = Integer.parseInt(text);
            if ((mustBePositive && value <= 0) || (!mustBePositive && value < 0))
            {
                showValidationMessage(label + " must be " + (mustBePositive ? "positive." : "zero or more."));
                field.requestFocusInWindow();
                return null;
            }
            return Integer.valueOf(value);
        }
        catch (NumberFormatException exception)
        {
            showValidationMessage(label + " must be a whole number.");
            field.requestFocusInWindow();
            return null;
        }
    }

    private void showValidationMessage(String message)
    {
        JOptionPane.showMessageDialog(this, message, "Check details", JOptionPane.WARNING_MESSAGE);
    }

    private boolean allFieldsEmpty(JTextField firstField, JTextField secondField, JTextField thirdField, JTextField fourthField, JTextField fifthField)
    {
        return isEmptyOrSizeExample(firstField)
            && isEmptyOrSizeExample(secondField)
            && isEmptyOrSizeExample(thirdField)
            && isEmptyOrSizeExample(fourthField)
            && isEmptyOrSizeExample(fifthField);
    }

    private boolean isEmptyOrSizeExample(JTextField field)
    {
        String text = field.getText().trim();
        return text.length() == 0 || text.equals("71x137x9");
    }

    private boolean isValidSize(String size)
    {
        String[] parts = size.split("x");
        if (parts.length < 2)
        {
            return false;
        }

        for (int i = 0; i < parts.length; i++)
        {
            try
            {
                int value = Integer.parseInt(parts[i]);
                if (value <= 0)
                {
                    return false;
                }
            }
            catch (NumberFormatException exception)
            {
                return false;
            }
        }

        return true;
    }

    private void showSuccess(String message, String actionText, String actionPage)
    {
        showSuccess(message, actionText, actionPage, null, null);
    }

    private void showSuccess(String message, String actionText, String actionPage, String secondActionText, String secondActionPage)
    {
        successMessageArea.setText(message);
        successActionPage = actionPage;
        successSecondActionPage = secondActionPage;

        if (actionPage == null)
        {
            successActionButton.setVisible(false);
        }
        else
        {
            successActionButton.setText(">" + actionText);
            successActionButton.setVisible(true);
        }

        if (secondActionPage == null)
        {
            successSecondActionButton.setVisible(false);
        }
        else
        {
            successSecondActionButton.setText(">" + secondActionText);
            successSecondActionButton.setVisible(true);
        }

        showPage(SUCCESS_PAGE, true);
    }

    private void makeCallFromForm()
    {
        int displayNumber = getDisplayNumber(callDisplayNumberField);
        if (displayNumber == -1)
        {
            return;
        }

        Gadget gadget = gadgets.get(displayNumber);
        if (!(gadget instanceof Mobile))
        {
            showValidationMessage("That display number does not belong to a Mobile.");
            return;
        }

        String phoneNumber = callPhoneNumberField.getText().trim();
        if (phoneNumber.length() == 0)
        {
            showValidationMessage("Enter a phone number to make a call.");
            callPhoneNumberField.requestFocusInWindow();
            return;
        }
        if (!phoneNumber.matches("[0-9]+"))
        {
            showValidationMessage("Invalid phone input. Only 0 or whole positive numbers allowed.");
            callPhoneNumberField.requestFocusInWindow();
            return;
        }

        Integer duration = getPositiveWholeNumberFromField(
            callDurationField,
            "Enter phone call duration to make a call.",
            "Invalid duration input. Enter only positive whole numbers."
        );
        if (duration == null)
        {
            return;
        }

        Mobile mobile = (Mobile) gadget;
        if (duration.intValue() > mobile.getCredit())
        {
            showValidationMessage("Not enough credit. Add more credit.");
            return;
        }

        mobile.makeCall(phoneNumber, duration.intValue());
        clearMakeCallForm();
        showSuccess(
            "Thank you for using Mobile display number " + displayNumber + ".\n"
                + "Remaining calling credit: " + mobile.getCredit() + " minute(s).",
            "ADD CREDIT",
            ADD_CREDIT_PAGE,
            "MAKE CALL",
            MAKE_CALL_PAGE
        );
    }

    private void addCreditFromForm()
    {
        int displayNumber = getDisplayNumber(creditDisplayNumberField);
        if (displayNumber == -1)
        {
            return;
        }

        Gadget gadget = gadgets.get(displayNumber);
        if (!(gadget instanceof Mobile))
        {
            showValidationMessage("That display number does not belong to a Mobile.");
            return;
        }

        Integer amount = getPositiveIntegerFromField(
            creditAmountField,
            "Credit amount cannot be empty.",
            "Invalid input. Enter only positive whole numbers."
        );
        if (amount == null)
        {
            return;
        }

        Mobile mobile = (Mobile) gadget;
        mobile.addCredit(amount.intValue());
        clearAddCreditForm();
        showSuccess(
            "Thank you for using Mobile display number " + displayNumber + ".\n"
                + "Credit added: " + amount + " minute(s).\n"
                + "New calling credit balance: " + mobile.getCredit() + " minute(s).",
            "MAKE A CALL",
            MAKE_CALL_PAGE
        );
    }

    private void downloadSongFromForm()
    {
        int displayNumber = getDisplayNumber(downloadDisplayNumberField);
        if (displayNumber == -1)
        {
            return;
        }

        Gadget gadget = gadgets.get(displayNumber);
        if (!(gadget instanceof MP3))
        {
            showValidationMessage("That display number does not belong to an MP3.");
            return;
        }

        Integer songSize = getPositiveIntegerFromField(
            songSizeField,
            "Enter song size to download music.",
            "Invalid song size input. Enter positive whole number."
        );
        if (songSize == null)
        {
            return;
        }

        MP3 mp3 = (MP3) gadget;
        if (songSize.intValue() > mp3.getMemory())
        {
            showValidationMessage("Not enough memory space. Free up memory.");
            return;
        }

        mp3.downloadMusic(songSize.intValue());
        clearDownloadSongForm();
        showSuccess(
            "Thank you for using MP3 display number " + displayNumber + ".\n"
                + "Remaining available memory: " + mp3.getMemory() + ".",
            "FREE SPACE",
            FREE_SPACE_PAGE
        );
    }

    private void freeSpaceFromForm()
    {
        int displayNumber = getDisplayNumber(freeSpaceDisplayNumberField);
        if (displayNumber == -1)
        {
            return;
        }

        Gadget gadget = gadgets.get(displayNumber);
        if (!(gadget instanceof MP3))
        {
            showValidationMessage("That display number does not belong to an MP3.");
            return;
        }

        Integer amount = getPositiveIntegerFromField(
            freeSpaceAmountField,
            "Amount to free cannot be empty.",
            "Invalid input. Enter only positive whole numbers."
        );
        if (amount == null)
        {
            return;
        }

        MP3 mp3 = (MP3) gadget;
        mp3.deleteMusic(amount.intValue());
        clearFreeSpaceForm();
        showSuccess(
            "Thank you for using MP3 display number " + displayNumber + ".\n"
                + "Memory freed: " + amount + ".\n"
                + "Available memory is now: " + mp3.getMemory() + ".",
            "DOWNLOAD A SONG",
            DOWNLOAD_SONG_PAGE
        );
    }

    private int getDisplayNumber(JTextField displayNumberField)
    {
        int displayNumber = -1;
        String displayNumberText = displayNumberField.getText().trim();
        try
        {
            displayNumber = Integer.parseInt(displayNumberText);
            if (displayNumber < 0 || displayNumber >= gadgets.size())
            {
                JOptionPane.showMessageDialog(this, "Display number is out of range.");
                displayNumber = -1;
            }
        }
        catch (NumberFormatException exception)
        {
            JOptionPane.showMessageDialog(this, "Display number must be an integer.");
        }

        return displayNumber;
    }

    private Integer getWholeNumberFromField(JTextField field, String emptyMessage, String invalidMessage)
    {
        String text = field.getText().trim();
        if (text.length() == 0)
        {
            showValidationMessage(emptyMessage);
            field.requestFocusInWindow();
            return null;
        }

        try
        {
            return Integer.valueOf(Integer.parseInt(text));
        }
        catch (NumberFormatException exception)
        {
            showValidationMessage(invalidMessage);
            field.requestFocusInWindow();
            return null;
        }
    }

    private Integer getPositiveIntegerFromField(JTextField field, String emptyMessage, String invalidMessage)
    {
        Integer value = getWholeNumberFromField(field, emptyMessage, invalidMessage);
        if (value == null)
        {
            return null;
        }

        if (value.intValue() <= 0)
        {
            showValidationMessage(invalidMessage);
            field.requestFocusInWindow();
            return null;
        }

        return value;
    }

    private Integer getPositiveWholeNumberFromField(JTextField field, String emptyMessage, String invalidMessage)
    {
        return getPositiveIntegerFromField(field, emptyMessage, invalidMessage);
    }

    private void clearMobileForm()
    {
        mobileModelField.setText("");
        mobilePriceField.setText("");
        mobileWeightField.setText("");
        mobileSizeField.setText("71x137x9");
        mobileCreditField.setText("");
    }

    private void clearMP3Form()
    {
        mp3ModelField.setText("");
        mp3PriceField.setText("");
        mp3WeightField.setText("");
        mp3SizeField.setText("71x137x9");
        mp3MemoryField.setText("");
    }

    private void clearMakeCallForm()
    {
        callDisplayNumberField.setText("");
        callPhoneNumberField.setText("");
        callDurationField.setText("");
    }

    private void clearAddCreditForm()
    {
        creditDisplayNumberField.setText("");
        creditAmountField.setText("");
    }

    private void clearDownloadSongForm()
    {
        downloadDisplayNumberField.setText("");
        songSizeField.setText("");
    }

    private void clearFreeSpaceForm()
    {
        freeSpaceDisplayNumberField.setText("");
        freeSpaceAmountField.setText("");
    }

    private void updateDisplayAllArea()
    {
        StringBuilder text = new StringBuilder();

        for (int i = 0; i < gadgets.size(); i++)
        {
            Gadget gadget = gadgets.get(i);
            text.append("Display number: ").append(i).append("\n");
            text.append("Type: ").append(gadget instanceof Mobile ? "Mobile" : "MP3").append("\n");
            text.append("Model: ").append(gadget.getModel()).append("\n");
            text.append("Price: £").append(gadget.getPrice()).append("\n");
            text.append("Weight: ").append(gadget.getWeight()).append("g\n");
            text.append("Size: ").append(gadget.getSize()).append("mm\n");

            if (gadget instanceof Mobile)
            {
                text.append("Credit: ").append(((Mobile) gadget).getCredit()).append(" minutes\n");
            }
            else if (gadget instanceof MP3)
            {
                text.append("Memory: ").append(((MP3) gadget).getMemory()).append("\n");
            }

            text.append("\n");
        }

        displayAllArea.setText(text.toString());
        displayAllArea.setCaretPosition(0);
    }

    public void actionPerformed(ActionEvent e)
    {
        Object source = e.getSource();

        if (source == homeAddNewGadgetButton)
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
                updateDisplayAllArea();
                showPage(DISPLAY_ALL_PAGE, true);
            }
        }
        else if (source == instructionsBackButton || source == addGadgetBackButton
            || source == createMobileBackButton || source == createMP3BackButton
            || source == displayAllBackButton || source == successBackButton
            || source == makeCallBackButton || source == addCreditBackButton
            || source == downloadSongBackButton || source == freeSpaceBackButton)
        {
            goBack();
        }
        else if (source == instructionsHomeButton || source == addGadgetHomeButton
            || source == createMobileHomeButton || source == createMP3HomeButton
            || source == displayAllHomeButton || source == successHomeButton
            || source == makeCallHomeButton || source == addCreditHomeButton
            || source == downloadSongHomeButton || source == freeSpaceHomeButton)
        {
            pageHistory.clear();
            showPage(HOME_PAGE, false);
        }
        else if (source == addGadgetMobileButton)
        {
            showPage(CREATE_MOBILE_PAGE, true);
        }
        else if (source == addGadgetMP3Button)
        {
            showPage(CREATE_MP3_PAGE, true);
        }
        else if (source == createMobileButton)
        {
            createMobileFromForm();
        }
        else if (source == createMP3Button)
        {
            createMP3FromForm();
        }
        else if (source == clearMobileButton)
        {
            clearMobileForm();
        }
        else if (source == clearMP3Button)
        {
            clearMP3Form();
        }
        else if (source == makeCallButton)
        {
            makeCallFromForm();
        }
        else if (source == addCreditButton)
        {
            addCreditFromForm();
        }
        else if (source == downloadSongButton)
        {
            downloadSongFromForm();
        }
        else if (source == freeSpaceButton)
        {
            freeSpaceFromForm();
        }
        else if (source == clearMakeCallButton)
        {
            clearMakeCallForm();
        }
        else if (source == clearAddCreditButton)
        {
            clearAddCreditForm();
        }
        else if (source == clearDownloadSongButton)
        {
            clearDownloadSongForm();
        }
        else if (source == clearFreeSpaceButton)
        {
            clearFreeSpaceForm();
        }
        else if (source == successActionButton && successActionPage != null)
        {
            showPage(successActionPage, true);
        }
        else if (source == successSecondActionButton && successSecondActionPage != null)
        {
            showPage(successSecondActionPage, true);
        }
        else if (source == successDisplayAllButton)
        {
            updateDisplayAllArea();
            showPage(DISPLAY_ALL_PAGE, true);
        }
        else if (source == successAddAnotherButton)
        {
            showPage(ADD_GADGET_PAGE, true);
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
