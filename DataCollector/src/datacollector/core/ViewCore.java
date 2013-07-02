/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ViewCore.java
 *
 * Created on Jun 12, 2013, 2:22:31 PM
 */

package datacollector.core;
import datacollector.listeners.dialogs.SetupWizardListener;
import datacollector.listeners.menubar.MenubarActionListener;
import datacollector.constants.StringConstants;
import datacollector.factories.ActionListenerFactory;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Abner
 */
public class ViewCore extends JFrame {

    /** Creates new form ViewCore */
    public ViewCore() {
        initComponents();
        initSystemProperties();
        initActionListeners();
    }

    /**
     *  This method will inject any system properties that needs to be inserted.
     */
    public void initSystemProperties()
    {
        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) (tk.getScreenSize().getWidth()* 0.75) );
        int ySize = ((int) (tk.getScreenSize().getHeight() * 0.95));
        Dimension windowSize = new Dimension(xSize,ySize);
        this.setSize(windowSize);

        this.setMaximumSize(windowSize);
        this.setMinimumSize(windowSize);
        
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

       
        DefaultTableCellRenderer dataCenterRenderer = new DefaultTableCellRenderer();
        dataCenterRenderer.setHorizontalAlignment( JLabel.CENTER );
        dataTable.setDefaultRenderer(String.class, dataCenterRenderer);

    }

    /**
     * This method will initialize the components action listeners
     */
    public void initActionListeners()
    {
        // Action Toolbars
        connectAction.addActionListener((ActionListener) ActionListenerFactory.getInstance("ActionToolbar"));
        connectAction.setActionCommand(StringConstants.CONNECT_ACTION);

        disconnectAction.addActionListener((ActionListener) ActionListenerFactory.getInstance("ActionToolbar"));
        disconnectAction.setActionCommand(StringConstants.DISCONNECT_ACTION);
        
        autoDetectAction.addActionListener((ActionListener) ActionListenerFactory.getInstance("ActionToolbar"));
        autoDetectAction.setActionCommand(StringConstants.AUTO_DETECT_ACTION);

        exportAction.addActionListener((ActionListener) ActionListenerFactory.getInstance("ActionToolbar"));
        exportAction.setActionCommand(StringConstants.EXPORT_ACTION);

        importAction.addActionListener((ActionListener) ActionListenerFactory.getInstance("ActionToolbar"));
        importAction.setActionCommand(StringConstants.IMPORT_ACTION);

        printAction.addActionListener((ActionListener) ActionListenerFactory.getInstance("ActionToolbar"));
        printAction.setActionCommand(StringConstants.PRINT_ACTION);

        startAction.addActionListener((ActionListener) ActionListenerFactory.getInstance("ActionToolbar"));
        startAction.setActionCommand(StringConstants.START_ACTION);

        stopAction.addActionListener((ActionListener) ActionListenerFactory.getInstance("ActionToolbar"));
        stopAction.setActionCommand(StringConstants.STOP_ACTION);

        // Menu Items
        connectMenu.addActionListener((MenubarActionListener) ActionListenerFactory.getInstance("Menubar"));
        connectMenu.setActionCommand(StringConstants.CONNECT_ACTION);

        disconnectMenu.addActionListener((MenubarActionListener) ActionListenerFactory.getInstance("Menubar"));
        disconnectMenu.setActionCommand(StringConstants.DISCONNECT_ACTION);

        autoDetectMenu.addActionListener((MenubarActionListener) ActionListenerFactory.getInstance("Menubar"));
        autoDetectMenu.setActionCommand(StringConstants.AUTO_DETECT_ACTION);

        exportMenu.addActionListener((MenubarActionListener) ActionListenerFactory.getInstance("Menubar"));
        exportMenu.setActionCommand(StringConstants.EXPORT_ACTION);

        importMenu.addActionListener((MenubarActionListener) ActionListenerFactory.getInstance("Menubar"));
        importMenu.setActionCommand(StringConstants.IMPORT_ACTION);

        printMenu.addActionListener((MenubarActionListener) ActionListenerFactory.getInstance("Menubar"));
        printMenu.setActionCommand(StringConstants.PRINT_ACTION);

        startMenu.addActionListener((MenubarActionListener) ActionListenerFactory.getInstance("Menubar"));
        startMenu.setActionCommand(StringConstants.START_ACTION);

        stopMenu.addActionListener((MenubarActionListener) ActionListenerFactory.getInstance("Menubar"));
        stopMenu.setActionCommand(StringConstants.STOP_ACTION);

        setupWizardMenu.addActionListener((SetupWizardListener) ActionListenerFactory.getInstance("SetupWizard"));
        setupWizardMenu.setActionCommand(StringConstants.SETUP_WIZARD);

        deviceSettingMenu.addActionListener((MenubarActionListener) ActionListenerFactory.getInstance("Menubar"));
        deviceSettingMenu.setActionCommand(StringConstants.DEVICE_SETTING);

    }

    /**
     *  This method is the launcher of the view
     */
    public void launch(){}

    


    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        actionsBar = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        importAction = new javax.swing.JButton();
        exportAction = new javax.swing.JButton();
        printAction = new javax.swing.JButton();
        jToolBar2 = new javax.swing.JToolBar();
        autoDetectAction = new javax.swing.JButton();
        connectAction = new javax.swing.JButton();
        disconnectAction = new javax.swing.JButton();
        jToolBar3 = new javax.swing.JToolBar();
        startAction = new javax.swing.JButton();
        stopAction = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        importMenu = new javax.swing.JMenuItem();
        exportMenu = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        printMenu = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu8 = new javax.swing.JMenu();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        autoDetectMenu = new javax.swing.JMenuItem();
        connectMenu = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JSeparator();
        disconnectMenu = new javax.swing.JMenuItem();
        configureMenu = new javax.swing.JMenu();
        setupWizardMenu = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JSeparator();
        deviceSettingMenu = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        startMenu = new javax.swing.JMenu();
        jMenuItem13 = new javax.swing.JMenuItem();
        stopMenu = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JSeparator();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem11 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setRollover(true);

        importAction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/import_action.png"))); // NOI18N
        importAction.setToolTipText("Import");
        importAction.setFocusable(false);
        importAction.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        importAction.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(importAction);

        exportAction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/export_action.png"))); // NOI18N
        exportAction.setToolTipText("Export");
        exportAction.setFocusable(false);
        exportAction.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        exportAction.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(exportAction);

        printAction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/print_action.png"))); // NOI18N
        printAction.setToolTipText("Print");
        printAction.setFocusable(false);
        printAction.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        printAction.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(printAction);

        jToolBar2.setRollover(true);

        autoDetectAction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/autodetect_action.png"))); // NOI18N
        autoDetectAction.setToolTipText("Auto Detect");
        autoDetectAction.setFocusable(false);
        autoDetectAction.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        autoDetectAction.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(autoDetectAction);

        connectAction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/port_action.png"))); // NOI18N
        connectAction.setToolTipText("Connect");
        connectAction.setFocusable(false);
        connectAction.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        connectAction.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(connectAction);

        disconnectAction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/disconnect_action.png"))); // NOI18N
        disconnectAction.setToolTipText("Disconnect");
        disconnectAction.setFocusable(false);
        disconnectAction.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        disconnectAction.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(disconnectAction);

        jToolBar3.setRollover(true);

        startAction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/start_action.png"))); // NOI18N
        startAction.setFocusable(false);
        startAction.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        startAction.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar3.add(startAction);

        stopAction.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stop_action.png"))); // NOI18N
        stopAction.setFocusable(false);
        stopAction.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        stopAction.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar3.add(stopAction);

        javax.swing.GroupLayout actionsBarLayout = new javax.swing.GroupLayout(actionsBar);
        actionsBar.setLayout(actionsBarLayout);
        actionsBarLayout.setHorizontalGroup(
            actionsBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(actionsBarLayout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(403, Short.MAX_VALUE))
        );
        actionsBarLayout.setVerticalGroup(
            actionsBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);

        dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Address", "Block", "Minimum Voltage (mV)", "Maximum Voltage (mV)", "Voltage Reading (mV)", "Current Reading (A)", "Alert", "Actions"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(dataTable);
        dataTable.getColumnModel().getColumn(0).setPreferredWidth(1);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Bulk Actions"));

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/view_db_icon.png"))); // NOI18N
        jButton3.setText("View Logs");

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/graph_icon.png"))); // NOI18N
        jButton4.setText("View Graph");

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stop_icon.png"))); // NOI18N
        jButton5.setText("Stop Reading");

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/start_icon.png"))); // NOI18N
        jButton6.setText("Start Reading");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton6)
                .addContainerGap(269, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton5)
                    .addComponent(jButton6))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 735, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Tabular View", jPanel3);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 745, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 501, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Block View", jPanel4);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 745, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 501, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Graphs", jPanel2);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 745, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 501, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Logs", jPanel6);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Real Time Graph"));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 815, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 49, Short.MAX_VALUE)
        );

        jPanel8.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 821, Short.MAX_VALUE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 18, Short.MAX_VALUE)
        );

        jMenu1.setText("File");

        importMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/import_menu.png"))); // NOI18N
        importMenu.setMnemonic('O');
        importMenu.setText("Import");
        importMenu.setToolTipText("Open file");
        jMenu1.add(importMenu);

        exportMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/export_menu.png"))); // NOI18N
        exportMenu.setText("Export");
        jMenu1.add(exportMenu);
        jMenu1.add(jSeparator1);

        printMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/print_menu.png"))); // NOI18N
        printMenu.setText("Print");
        jMenu1.add(printMenu);

        jMenuItem4.setText("Exit");
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("View");

        jMenu6.setText("Toolbars");

        jMenuItem5.setText("Status bar");
        jMenu6.add(jMenuItem5);

        jMenuItem6.setText("Alert bar");
        jMenu6.add(jMenuItem6);

        jMenuItem7.setText("Actions bar");
        jMenu6.add(jMenuItem7);

        jMenu2.add(jMenu6);

        jMenu8.setText("Tabs");

        jMenuItem17.setText("Monitor");
        jMenu8.add(jMenuItem17);

        jMenuItem20.setText("Logs");
        jMenu8.add(jMenuItem20);

        jMenu2.add(jMenu8);
        jMenu2.add(jSeparator3);

        jMenuItem16.setText("Full Screen");
        jMenu2.add(jMenuItem16);

        jMenuBar1.add(jMenu2);

        jMenu7.setText("Connect");

        autoDetectMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/autodetect_menu.png"))); // NOI18N
        autoDetectMenu.setText("Auto Detect");
        jMenu7.add(autoDetectMenu);

        connectMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/port_menu.png"))); // NOI18N
        connectMenu.setText("Configure COM");
        jMenu7.add(connectMenu);
        jMenu7.add(jSeparator4);

        disconnectMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/disconnect_menu.png"))); // NOI18N
        disconnectMenu.setText("Disconnect");
        jMenu7.add(disconnectMenu);

        jMenuBar1.add(jMenu7);

        configureMenu.setText("Configure");

        setupWizardMenu.setText("Setup Wizard");
        configureMenu.add(setupWizardMenu);
        configureMenu.add(jSeparator7);

        deviceSettingMenu.setText("Device Settings");
        configureMenu.add(deviceSettingMenu);

        jMenuItem2.setText("Preferences");
        configureMenu.add(jMenuItem2);

        jMenuBar1.add(configureMenu);

        startMenu.setText("Run");

        jMenuItem13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/start_menu.png"))); // NOI18N
        jMenuItem13.setText("Start Logging");
        startMenu.add(jMenuItem13);

        stopMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/stop_menu.png"))); // NOI18N
        stopMenu.setText("Stop Logging");
        startMenu.add(stopMenu);
        startMenu.add(jSeparator5);

        jMenuItem21.setText("View Logs");
        startMenu.add(jMenuItem21);

        jMenuBar1.add(startMenu);

        jMenu3.setText("Tools");

        jMenuItem10.setText("Read Address");
        jMenu3.add(jMenuItem10);

        jMenuItem12.setText("Write Address");
        jMenu3.add(jMenuItem12);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Help");

        jMenuItem11.setText("About");
        jMenu4.add(jMenuItem11);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(actionsBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 827, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(actionsBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 506, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel actionsBar;
    protected javax.swing.JButton autoDetectAction;
    private javax.swing.JMenuItem autoDetectMenu;
    private javax.swing.JMenu configureMenu;
    protected javax.swing.JButton connectAction;
    protected javax.swing.JMenuItem connectMenu;
    protected static javax.swing.JTable dataTable;
    private javax.swing.JMenuItem deviceSettingMenu;
    protected javax.swing.JButton disconnectAction;
    private javax.swing.JMenuItem disconnectMenu;
    protected javax.swing.JButton exportAction;
    private javax.swing.JMenuItem exportMenu;
    protected javax.swing.JButton importAction;
    private javax.swing.JMenuItem importMenu;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenu jMenu8;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    protected javax.swing.JButton printAction;
    private javax.swing.JMenuItem printMenu;
    private javax.swing.JMenuItem setupWizardMenu;
    protected javax.swing.JButton startAction;
    private javax.swing.JMenu startMenu;
    protected javax.swing.JButton stopAction;
    private javax.swing.JMenuItem stopMenu;
    // End of variables declaration//GEN-END:variables

}
