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

    private final Color SCREEN_TOP = new Color(123, 152, 198);
    private final Color SCREEN_BOTTOM = new Color(86, 117, 166);

    private final Color CONTENT_TOP = new Color(246, 248, 252);
    private final Color CONTENT_BOTTOM = new Color(223, 229, 240);

    private final Color BORDER_DARK = new Color(92, 104, 128);
    private final Color BORDER_LIGHT = new Color(248, 250, 255);

    private final Color WHITE_TEXT = new Color(250, 252, 255);
    private final Color DARK_TEXT = new Color(35, 48, 71);

    private Point dragOffset;

    public GadgetShop()
    {
        gadgets = new ArrayList<Gadget>();
        pageHistory = new Stack<String>();

        setUndecorated(true);
        setTitle("(⁠￣⁠ω⁠￣) Happy Happy Gadget Land (⁠￣⁠ω⁠￣)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(490, 330);
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
        rootPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(BORDER_DARK, 2),
            BorderFactory.createLineBorder(BORDER_LIGHT, 1)
        ));

        rootPanel.add(createCustomTitleBar(), BorderLayout.NORTH);
        rootPanel.add(cardPanel, BorderLayout.CENTER);

        setContentPane(rootPanel);

        showPage(HOME_PAGE, false);
    }

    private JPanel createCustomTitleBar()
    {
        XPTitleBarPanel titleBar = new XPTitleBarPanel();
        titleBar.setLayout(new BorderLayout());
        titleBar.setPreferredSize(new Dimension(840, 34));
        titleBar.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 6));

        JLabel titleLabel = new JLabel();
        titleLabel.setForeground(WHITE_TEXT);
        titleLabel.setFont(createWindowTitleFont());

        minimiseButton = createWindowControlButton("_");
        closeButton = createWindowControlButton("X");

        minimiseButton.addActionListener(this);
        closeButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 4, 0));
        buttonPanel.setOpaque(false);
        buttonPanel.add(minimiseButton);
        buttonPanel.add(closeButton);

        titleBar.add(titleLabel, BorderLayout.WEST);
        titleBar.add(buttonPanel, BorderLayout.EAST);

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

        titleBar.addMouseListener(dragListener);
        titleBar.addMouseMotionListener(dragListener);

        return titleBar;
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
    contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

    JLabel titleLabel = createPageTitleLabel("(⁠￣⁠ω⁠￣) Happy Happy Gadget Land (⁠￣⁠ω⁠￣)");

    homeAddNewGadgetButton = createTextButton(">Add new gadget");
    homeDisplayAllGadgetsButton = createTextButton(">Display all gadgets");
    homeInstructionsButton = createTextButton(">Instructions");

    homeAddNewGadgetButton.addActionListener(this);
    homeDisplayAllGadgetsButton.addActionListener(this);
    homeInstructionsButton.addActionListener(this);

    titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    homeAddNewGadgetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    homeDisplayAllGadgetsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    homeInstructionsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

    contentPanel.add(titleLabel);
    contentPanel.add(Box.createVerticalStrut(24));
    contentPanel.add(homeAddNewGadgetButton);
    contentPanel.add(Box.createVerticalStrut(8));
    contentPanel.add(homeDisplayAllGadgetsButton);
    contentPanel.add(Box.createVerticalStrut(14));
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
            + "If you forget the display number for a gadget, select "
            + "\"Display all gadgets\" to locate it.\n\n"
            + "Enjoy!\n"
        );
        instructionsArea.setEditable(false);
        instructionsArea.setLineWrap(true);
        instructionsArea.setWrapStyleWord(true);
        instructionsArea.setFont(createBodyFont());
        instructionsArea.setForeground(DARK_TEXT);
        instructionsArea.setBackground(new Color(245, 248, 252));
        instructionsArea.setMargin(new Insets(12, 12, 12, 12));
        instructionsArea.setBorder(BorderFactory.createLineBorder(new Color(166, 176, 196), 1));

        JScrollPane scrollPane = new JScrollPane(instructionsArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getVerticalScrollBar().setUnitIncrement(12);

        return createPageShell("Instructions", true, instructionsBackButton, instructionsHomeButton, scrollPane);
    }

    private JPanel createAddGadgetPage()
    {
        addGadgetBackButton = createNavButton("<");
        addGadgetHomeButton = createNavButton("⌂");

        addGadgetBackButton.addActionListener(this);
        addGadgetHomeButton.addActionListener(this);

        addGadgetMobileButton = createTextButton(">Mobile");
        addGadgetMP3Button = createTextButton(">MP3");

        addGadgetMobileButton.addActionListener(this);
        addGadgetMP3Button.addActionListener(this);

        JPanel contentPanel = new JPanel();
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        addGadgetMobileButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addGadgetMP3Button.setAlignmentX(Component.CENTER_ALIGNMENT);

        contentPanel.add(Box.createVerticalStrut(20));
        contentPanel.add(addGadgetMobileButton);
        contentPanel.add(Box.createVerticalStrut(14));
        contentPanel.add(addGadgetMP3Button);

        return createPageShell("Add new gadget", true, addGadgetBackButton, addGadgetHomeButton, contentPanel);
    }

   private JPanel createPageShell(String pageTitle, boolean showNavigation, JButton backButton, JButton homeButton, JComponent content)
{
    JPanel outerPanel = new JPanel(new BorderLayout());
    outerPanel.setOpaque(false);
    outerPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));

    XPScreenPanel screenPanel = new XPScreenPanel();
    screenPanel.setLayout(new BorderLayout());
    screenPanel.setBorder(BorderFactory.createEmptyBorder(8, 10, 10, 10));

    JPanel topPanel = new JPanel(new BorderLayout());
    topPanel.setOpaque(false);

    if (showNavigation)
    {
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        leftPanel.setOpaque(false);
        leftPanel.add(backButton);

        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        rightPanel.setOpaque(false);
        rightPanel.add(homeButton);

        topPanel.add(leftPanel, BorderLayout.WEST);
        topPanel.add(rightPanel, BorderLayout.EAST);
    }

    if (pageTitle != null)
    {
        JLabel titleLabel = createPageTitleLabel(pageTitle);
        topPanel.add(titleLabel, BorderLayout.CENTER);
    }

    JPanel contentWrapper = new JPanel(new BorderLayout());
    contentWrapper.setOpaque(false);
    contentWrapper.setBorder(BorderFactory.createEmptyBorder(8, 14, 8, 14));
    contentWrapper.add(content, BorderLayout.NORTH);

    JScrollPane scrollPane = new JScrollPane(contentWrapper);
    scrollPane.setBorder(BorderFactory.createEmptyBorder());
    scrollPane.setOpaque(false);
    scrollPane.getViewport().setOpaque(false);
    scrollPane.getVerticalScrollBar().setUnitIncrement(14);
    scrollPane.getHorizontalScrollBar().setUnitIncrement(14);

    screenPanel.add(topPanel, BorderLayout.NORTH);
    screenPanel.add(scrollPane, BorderLayout.CENTER);

    outerPanel.add(screenPanel, BorderLayout.CENTER);
    return outerPanel;
}

    private JLabel createPageTitleLabel(String text)
    {
        JLabel label = new JLabel(text, SwingConstants.CENTER);
        label.setForeground(WHITE_TEXT);
        label.setFont(createTitleFont());
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
        button.setPreferredSize(new Dimension(28, 22));
        button.setFont(new Font("Tahoma", Font.BOLD, 12));
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

    private Font createTitleFont()
    {
        return new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 24);
    }

    private Font createButtonFont()
    {
        return new Font("Monospaced", Font.BOLD, 20);
    }

    private Font createBodyFont()
    {
        return new Font("Tahoma", Font.PLAIN, 15);
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
            g2.fillRect(0, 0, getWidth(), getHeight());

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
        protected void paintComponent(Graphics g)
        {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g.create();

            GradientPaint gradient = new GradientPaint(
                0, 0, SCREEN_TOP,
                0, getHeight(), SCREEN_BOTTOM
            );

            g2.setPaint(gradient);
            g2.fillRect(0, 0, getWidth(), getHeight());

            g2.setColor(new Color(230, 238, 248));
            g2.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

            g2.setColor(new Color(255, 255, 255, 60));
            g2.drawRect(1, 1, getWidth() - 3, getHeight() - 3);

            g2.dispose();
        }
    }
}