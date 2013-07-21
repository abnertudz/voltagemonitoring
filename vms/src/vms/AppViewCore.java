/*
 * AppViewCore.java
 */

package vms;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;
import vms.*;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import vms.block.panles.BlockPanel;
import vms.constants.StringConstants;
import vms.device.data.DataSamplerDevice;
import vms.factories.ActionListenerFactory;
import vms.listeners.dialogs.SetupWizardListener;
import vms.listeners.menubar.MenubarActionListener;
import vms.table.panels.ActionToolPanel;

/**
 * The application's main frame.
 */
public class AppViewCore extends FrameView {

    public AppViewCore(SingleFrameApplication app) {
        super(app);

        initComponents();
        initSystemProperties();
        initActionListeners();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
       // progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    //progressBar.setVisible(true);
                  //  progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                   // progressBar.setVisible(false);
                  //  progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                  //  progressBar.setVisible(true);
                //    progressBar.setIndeterminate(false);
                 //   progressBar.setValue(value);
                }
            }
        });
    }

    public void initSystemProperties()
    {

        Toolkit tk = Toolkit.getDefaultToolkit();
        int xSize = ((int) (tk.getScreenSize().getWidth()* 0.75) );
        int ySize = ((int) (tk.getScreenSize().getHeight() * 0.95));
        Dimension windowSize = new Dimension(xSize,ySize);

        JFrame appFrame = this.getFrame();
        appFrame.setTitle("Voltage Monitoring System");

        org.jdesktop.application.ResourceMap resource = org.jdesktop.application.Application.getInstance(vms.AppView.class).getContext().getResourceMap(AppViewCore.class);
        //  appFrame.setIconImage(Ima);
        //appFrame.setSize(windowSize);

        //appFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);


        DefaultTableCellRenderer dataCenterRenderer = new DefaultTableCellRenderer();
        dataCenterRenderer.setHorizontalAlignment( JLabel.CENTER );
        dataTable.setDefaultRenderer(String.class, dataCenterRenderer);

        for(int i =0; i < 8; i++)
        {
            blocksPanel.add(new BlockPanel());
        }
        blocksPanel.setLayout(new FlowLayout());
        //  blockPanel.add(new JButton("TEST"));
        // /
       // dataTable.getColumn("Actions").setCellEditor(new ActionToolPanelEditor());
      //  dataTable.getColumn("Actions").setCellRenderer(new ActionToolPanelRenderer());



        /*ViewCoreFirstColumnTableRenderer firstCol = new ViewCoreFirstColumnTableRenderer();
        //TableCellRenderer render = dataTable.getColumn("").getCellRenderer();

        dataTable.getColumn("Block").setCellRenderer(firstCol);
        dataTable.getColumn("Address").setCellRenderer(firstCol);
        dataTable.getColumn("Minimum Voltage (mV)").setCellRenderer(firstCol);
        dataTable.getColumn("Maximum Voltage (mV)").setCellRenderer(firstCol);
        dataTable.getColumn("Voltage Reading (mV)").setCellRenderer(firstCol);
        dataTable.getColumn("Current Reading (A)").setCellRenderer(firstCol);
        dataTable.getColumn("Status").setCellRenderer(firstCol);*/

    }
    
    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = AppView.getApplication().getMainFrame();
            aboutBox = new VmsAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        AppView.getApplication().show(aboutBox);
    }

    private void initActionListeners() {

        // Action Toolbars
        connectAction.addActionListener((ActionListener) ActionListenerFactory.getInstance("ActionToolbar"));
        connectAction.setActionCommand(StringConstants.CONNECT_ACTION);

        disconnectAction.addActionListener((ActionListener) ActionListenerFactory.getInstance("ActionToolbar"));
        disconnectAction.setActionCommand(StringConstants.DISCONNECT_ACTION);

        autoDetectAction.addActionListener((ActionListener) ActionListenerFactory.getInstance("ActionToolbar"));
        autoDetectAction.setActionCommand(StringConstants.AUTO_DETECT_ACTION);

       // exportAction.addActionListener((ActionListener) ActionListenerFactory.getInstance("ActionToolbar"));
      //  exportAction.setActionCommand(StringConstants.EXPORT_ACTION);

      //  importAction.addActionListener((ActionListener) ActionListenerFactory.getInstance("ActionToolbar"));
      //  importAction.setActionCommand(StringConstants.IMPORT_ACTION);

      //  printAction.addActionListener((ActionListener) ActionListenerFactory.getInstance("ActionToolbar"));
     //   printAction.setActionCommand(StringConstants.PRINT_ACTION);

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

        startMenu.addActionListener((MenubarActionListener) ActionListenerFactory.getInstance("Menubar"));
        startMenu.setActionCommand(StringConstants.START_ACTION);

        stopMenu.addActionListener((MenubarActionListener) ActionListenerFactory.getInstance("Menubar"));
        stopMenu.setActionCommand(StringConstants.STOP_ACTION);

        setupWizardMenu.addActionListener((SetupWizardListener) ActionListenerFactory.getInstance("SetupWizard"));
        setupWizardMenu.setActionCommand(StringConstants.SETUP_WIZARD);

        deviceSettingMenu.addActionListener((MenubarActionListener) ActionListenerFactory.getInstance("Menubar"));
        deviceSettingMenu.setActionCommand(StringConstants.DEVICE_SETTING);
    }

    public JTable getDataTable()
    {
        return dataTable;
    }

    public void populateDataTable(ArrayList<DataSamplerDevice> data)
    {
        DefaultTableModel tableModel = (DefaultTableModel)dataTable.getModel();
        Iterator iterate = data.iterator();
        int countX=0;

        while(iterate.hasNext())
        {
            DataSamplerDevice device = (DataSamplerDevice) iterate.next();
            Object[] newRow = {false,device.getDeviceAddress(),device.getDeviceBlockName(),device.getDeviceMinVoltage(),device.getDeviceMaxVoltage(),"0","0","Stand by",new ActionToolPanel()};
            tableModel.addRow(newRow);
            countX++;
        }
    }

    public void updateDataTable(HashMap dataMap)
    {
        DefaultTableModel tableModel = (DefaultTableModel)dataTable.getModel();
        int row = tableModel.getRowCount();
        Object address = 0;

        for(int x=0; x<row; x++)
        {
           address = tableModel.getValueAt(x, 1);
           DataSamplerDevice device = (DataSamplerDevice) dataMap.get((Integer)address);
           if(null != device)
           {
               tableModel.setValueAt(device.getDeviceVoltage(), x, 5);
               //tableModel.
           }
        }

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        autoDetectAction = new javax.swing.JButton();
        connectAction = new javax.swing.JButton();
        disconnectAction = new javax.swing.JButton();
        jToolBar2 = new javax.swing.JToolBar();
        stopAction = new javax.swing.JButton();
        startAction = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel8 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jSpinner3 = new javax.swing.JSpinner();
        jSpinner4 = new javax.swing.JSpinner();
        jLabel9 = new javax.swing.JLabel();
        jSpinner5 = new javax.swing.JSpinner();
        jLabel10 = new javax.swing.JLabel();
        jSpinner6 = new javax.swing.JSpinner();
        jLabel11 = new javax.swing.JLabel();
        jSpinner7 = new javax.swing.JSpinner();
        jLabel12 = new javax.swing.JLabel();
        jSpinner8 = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        blocksPanel = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jCheckBoxMenuItem5 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem6 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem7 = new javax.swing.JCheckBoxMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem2 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem3 = new javax.swing.JCheckBoxMenuItem();
        jCheckBoxMenuItem4 = new javax.swing.JCheckBoxMenuItem();
        jSeparator4 = new javax.swing.JSeparator();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        autoDetectMenu = new javax.swing.JMenuItem();
        connectMenu = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        disconnectMenu = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        setupWizardMenu = new javax.swing.JMenuItem();
        deviceSettingMenu = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        startMenu = new javax.swing.JMenuItem();
        stopMenu = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        mainPanel.setName("mainPanel"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(vms.AppView.class).getContext().getResourceMap(AppViewCore.class);
        autoDetectAction.setIcon(resourceMap.getIcon("autoDetectAction.icon")); // NOI18N
        autoDetectAction.setFocusable(false);
        autoDetectAction.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        autoDetectAction.setName("autoDetectAction"); // NOI18N
        autoDetectAction.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(autoDetectAction);

        connectAction.setIcon(resourceMap.getIcon("connectAction.icon")); // NOI18N
        connectAction.setText(resourceMap.getString("connectAction.text")); // NOI18N
        connectAction.setFocusable(false);
        connectAction.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        connectAction.setName("connectAction"); // NOI18N
        connectAction.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(connectAction);

        disconnectAction.setIcon(resourceMap.getIcon("disconnectAction.icon")); // NOI18N
        disconnectAction.setText(resourceMap.getString("disconnectAction.text")); // NOI18N
        disconnectAction.setFocusable(false);
        disconnectAction.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        disconnectAction.setName("disconnectAction"); // NOI18N
        disconnectAction.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(disconnectAction);

        jToolBar2.setRollover(true);
        jToolBar2.setName("jToolBar2"); // NOI18N

        stopAction.setIcon(resourceMap.getIcon("stopAction.icon")); // NOI18N
        stopAction.setText(resourceMap.getString("stopAction.text")); // NOI18N
        stopAction.setFocusable(false);
        stopAction.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        stopAction.setName("stopAction"); // NOI18N
        stopAction.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(stopAction);

        startAction.setIcon(resourceMap.getIcon("startAction.icon")); // NOI18N
        startAction.setText(resourceMap.getString("startAction.text")); // NOI18N
        startAction.setFocusable(false);
        startAction.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        startAction.setName("startAction"); // NOI18N
        startAction.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar2.add(startAction);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jToolBar2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(845, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jToolBar2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE))
                .addContainerGap())
        );

        jSeparator1.setName("jSeparator1"); // NOI18N

        jTabbedPane1.setTabPlacement(javax.swing.JTabbedPane.LEFT);
        jTabbedPane1.setName("jTabbedPane1"); // NOI18N

        jPanel6.setName("jPanel6"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "", "Address", "Block", "Minimum Voltage(mV)", "Minimum Voltage(mV)", "Voltage(mV)", "Current(A)", "Status", "Actions"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        dataTable.setName("dataTable"); // NOI18N
        jScrollPane1.setViewportView(dataTable);
        dataTable.getColumnModel().getColumn(0).setMinWidth(50);
        dataTable.getColumnModel().getColumn(0).setPreferredWidth(50);
        dataTable.getColumnModel().getColumn(0).setMaxWidth(50);
        dataTable.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("dataTable.columnModel.title0")); // NOI18N
        dataTable.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("dataTable.columnModel.title1")); // NOI18N
        dataTable.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("dataTable.columnModel.title2")); // NOI18N
        dataTable.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("dataTable.columnModel.title3")); // NOI18N
        dataTable.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("dataTable.columnModel.title4")); // NOI18N
        dataTable.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("dataTable.columnModel.title5")); // NOI18N
        dataTable.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("dataTable.columnModel.title6")); // NOI18N
        dataTable.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("dataTable.columnModel.title7")); // NOI18N
        dataTable.getColumnModel().getColumn(8).setHeaderValue(resourceMap.getString("dataTable.columnModel.title8")); // NOI18N

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel7.border.title"))); // NOI18N
        jPanel7.setName("jPanel7"); // NOI18N

        jButton6.setIcon(resourceMap.getIcon("jButton6.icon")); // NOI18N
        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setName("jButton6"); // NOI18N

        jButton7.setIcon(resourceMap.getIcon("jButton7.icon")); // NOI18N
        jButton7.setText(resourceMap.getString("jButton7.text")); // NOI18N
        jButton7.setName("jButton7"); // NOI18N

        jButton8.setIcon(resourceMap.getIcon("jButton8.icon")); // NOI18N
        jButton8.setText(resourceMap.getString("jButton8.text")); // NOI18N
        jButton8.setName("jButton8"); // NOI18N

        jButton9.setIcon(resourceMap.getIcon("jButton9.icon")); // NOI18N
        jButton9.setText(resourceMap.getString("jButton9.text")); // NOI18N
        jButton9.setName("jButton9"); // NOI18N

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton9)
                .addContainerGap(517, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton8)
                    .addComponent(jButton9))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 987, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 402, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel6.TabConstraints.tabTitle"), jPanel6); // NOI18N

        jPanel3.setName("jPanel3"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 987, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 471, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel3.TabConstraints.tabTitle"), jPanel3); // NOI18N

        jPanel5.setName("jPanel5"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Address", "Block", "Minimum Voltage", "Maximum Voltage", "Voltage", "Current", "Reading Started", "Reading Stopped", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setName("jTable1"); // NOI18N
        jScrollPane2.setViewportView(jTable1);
        jTable1.getColumnModel().getColumn(0).setHeaderValue(resourceMap.getString("jTable1.columnModel.title0")); // NOI18N
        jTable1.getColumnModel().getColumn(1).setHeaderValue(resourceMap.getString("jTable1.columnModel.title1")); // NOI18N
        jTable1.getColumnModel().getColumn(2).setHeaderValue(resourceMap.getString("jTable1.columnModel.title2")); // NOI18N
        jTable1.getColumnModel().getColumn(3).setHeaderValue(resourceMap.getString("jTable1.columnModel.title3")); // NOI18N
        jTable1.getColumnModel().getColumn(4).setHeaderValue(resourceMap.getString("jTable1.columnModel.title4")); // NOI18N
        jTable1.getColumnModel().getColumn(5).setHeaderValue(resourceMap.getString("jTable1.columnModel.title5")); // NOI18N
        jTable1.getColumnModel().getColumn(6).setHeaderValue(resourceMap.getString("jTable1.columnModel.title6")); // NOI18N
        jTable1.getColumnModel().getColumn(7).setHeaderValue(resourceMap.getString("jTable1.columnModel.title7")); // NOI18N
        jTable1.getColumnModel().getColumn(8).setHeaderValue(resourceMap.getString("jTable1.columnModel.title8")); // NOI18N

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel8.border.title"), javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.BELOW_BOTTOM)); // NOI18N
        jPanel8.setName("jPanel8"); // NOI18N

        jTextField1.setText(resourceMap.getString("jTextField1.text")); // NOI18N
        jTextField1.setName("jTextField1"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jTextField2.setName("jTextField2"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        jSpinner1.setName("jSpinner1"); // NOI18N

        jSpinner2.setName("jSpinner2"); // NOI18N

        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jSpinner3.setName("jSpinner3"); // NOI18N

        jSpinner4.setName("jSpinner4"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jSpinner5.setName("jSpinner5"); // NOI18N

        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        jSpinner6.setName("jSpinner6"); // NOI18N

        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        jSpinner7.setName("jSpinner7"); // NOI18N

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        jSpinner8.setName("jSpinner8"); // NOI18N

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addComponent(jLabel4)
                        .addGap(5, 5, 5)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(3, 3, 3)
                        .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinner5, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner6, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSpinner8, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSpinner7, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addComponent(jButton2))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(jSpinner5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jSpinner7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(3, 3, 3)
                                .addComponent(jLabel4))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)
                                    .addComponent(jSpinner6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jSpinner8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12)))))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel7))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(30, Short.MAX_VALUE)
                .addComponent(jButton2))
        );

        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 987, Short.MAX_VALUE)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addContainerGap(934, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 357, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab(resourceMap.getString("jPanel5.TabConstraints.tabTitle"), jPanel5); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        blocksPanel.setMaximumSize(new java.awt.Dimension(2024, 32767));
        blocksPanel.setName("blocksPanel"); // NOI18N

        javax.swing.GroupLayout blocksPanelLayout = new javax.swing.GroupLayout(blocksPanel);
        blocksPanel.setLayout(blocksPanelLayout);
        blocksPanelLayout.setHorizontalGroup(
            blocksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2024, Short.MAX_VALUE)
        );
        blocksPanelLayout.setVerticalGroup(
            blocksPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 469, Short.MAX_VALUE)
        );

        jScrollPane3.setViewportView(blocksPanel);

        jTabbedPane1.addTab(resourceMap.getString("jScrollPane3.TabConstraints.tabTitle"), jScrollPane3); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel4.border.title"))); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1057, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 56, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1069, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1069, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setMnemonic('F');
        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setIcon(resourceMap.getIcon("jMenuItem2.icon")); // NOI18N
        jMenuItem2.setMnemonic('I');
        jMenuItem2.setText(resourceMap.getString("jMenuItem2.text")); // NOI18N
        jMenuItem2.setName("jMenuItem2"); // NOI18N
        fileMenu.add(jMenuItem2);

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setIcon(resourceMap.getIcon("jMenuItem1.icon")); // NOI18N
        jMenuItem1.setMnemonic('E');
        jMenuItem1.setText(resourceMap.getString("jMenuItem1.text")); // NOI18N
        jMenuItem1.setName("jMenuItem1"); // NOI18N
        fileMenu.add(jMenuItem1);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem3.setIcon(resourceMap.getIcon("jMenuItem3.icon")); // NOI18N
        jMenuItem3.setMnemonic('P');
        jMenuItem3.setText(resourceMap.getString("jMenuItem3.text")); // NOI18N
        jMenuItem3.setName("jMenuItem3"); // NOI18N
        fileMenu.add(jMenuItem3);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(vms.AppView.class).getContext().getActionMap(AppViewCore.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setMnemonic('V');
        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        jMenu6.setText(resourceMap.getString("jMenu6.text")); // NOI18N
        jMenu6.setName("jMenu6"); // NOI18N

        jCheckBoxMenuItem5.setSelected(true);
        jCheckBoxMenuItem5.setText(resourceMap.getString("jCheckBoxMenuItem5.text")); // NOI18N
        jCheckBoxMenuItem5.setName("jCheckBoxMenuItem5"); // NOI18N
        jMenu6.add(jCheckBoxMenuItem5);

        jCheckBoxMenuItem6.setSelected(true);
        jCheckBoxMenuItem6.setText(resourceMap.getString("jCheckBoxMenuItem6.text")); // NOI18N
        jCheckBoxMenuItem6.setName("jCheckBoxMenuItem6"); // NOI18N
        jMenu6.add(jCheckBoxMenuItem6);

        jCheckBoxMenuItem7.setSelected(true);
        jCheckBoxMenuItem7.setText(resourceMap.getString("jCheckBoxMenuItem7.text")); // NOI18N
        jCheckBoxMenuItem7.setName("jCheckBoxMenuItem7"); // NOI18N
        jMenu6.add(jCheckBoxMenuItem7);

        helpMenu.add(jMenu6);

        jMenu7.setText(resourceMap.getString("jMenu7.text")); // NOI18N
        jMenu7.setName("jMenu7"); // NOI18N

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText(resourceMap.getString("jCheckBoxMenuItem1.text")); // NOI18N
        jCheckBoxMenuItem1.setName("jCheckBoxMenuItem1"); // NOI18N
        jMenu7.add(jCheckBoxMenuItem1);

        jCheckBoxMenuItem2.setSelected(true);
        jCheckBoxMenuItem2.setText(resourceMap.getString("jCheckBoxMenuItem2.text")); // NOI18N
        jCheckBoxMenuItem2.setName("jCheckBoxMenuItem2"); // NOI18N
        jMenu7.add(jCheckBoxMenuItem2);

        jCheckBoxMenuItem3.setSelected(true);
        jCheckBoxMenuItem3.setText(resourceMap.getString("jCheckBoxMenuItem3.text")); // NOI18N
        jCheckBoxMenuItem3.setName("jCheckBoxMenuItem3"); // NOI18N
        jMenu7.add(jCheckBoxMenuItem3);

        jCheckBoxMenuItem4.setSelected(true);
        jCheckBoxMenuItem4.setText(resourceMap.getString("jCheckBoxMenuItem4.text")); // NOI18N
        jCheckBoxMenuItem4.setName("jCheckBoxMenuItem4"); // NOI18N
        jMenu7.add(jCheckBoxMenuItem4);

        helpMenu.add(jMenu7);

        jSeparator4.setName("jSeparator4"); // NOI18N
        helpMenu.add(jSeparator4);

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setText(resourceMap.getString("aboutMenuItem.text")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        jMenu1.setMnemonic('n');
        jMenu1.setText(resourceMap.getString("jMenu1.text")); // NOI18N
        jMenu1.setName("jMenu1"); // NOI18N

        autoDetectMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        autoDetectMenu.setIcon(resourceMap.getIcon("autoDetectMenu.icon")); // NOI18N
        autoDetectMenu.setMnemonic('D');
        autoDetectMenu.setText(resourceMap.getString("autoDetectMenu.text")); // NOI18N
        autoDetectMenu.setName("autoDetectMenu"); // NOI18N
        jMenu1.add(autoDetectMenu);

        connectMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        connectMenu.setIcon(resourceMap.getIcon("connectMenu.icon")); // NOI18N
        connectMenu.setMnemonic('f');
        connectMenu.setText(resourceMap.getString("connectMenu.text")); // NOI18N
        connectMenu.setName("connectMenu"); // NOI18N
        jMenu1.add(connectMenu);

        jSeparator2.setName("jSeparator2"); // NOI18N
        jMenu1.add(jSeparator2);

        disconnectMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_MASK));
        disconnectMenu.setIcon(resourceMap.getIcon("disconnectMenu.icon")); // NOI18N
        disconnectMenu.setMnemonic('D');
        disconnectMenu.setText(resourceMap.getString("disconnectMenu.text")); // NOI18N
        disconnectMenu.setName("disconnectMenu"); // NOI18N
        jMenu1.add(disconnectMenu);

        menuBar.add(jMenu1);

        jMenu2.setMnemonic('C');
        jMenu2.setText(resourceMap.getString("jMenu2.text")); // NOI18N
        jMenu2.setName("jMenu2"); // NOI18N

        setupWizardMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        setupWizardMenu.setMnemonic('e');
        setupWizardMenu.setText(resourceMap.getString("setupWizardMenu.text")); // NOI18N
        setupWizardMenu.setName("setupWizardMenu"); // NOI18N
        jMenu2.add(setupWizardMenu);

        deviceSettingMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        deviceSettingMenu.setMnemonic('c');
        deviceSettingMenu.setText(resourceMap.getString("deviceSettingMenu.text")); // NOI18N
        deviceSettingMenu.setName("deviceSettingMenu"); // NOI18N
        jMenu2.add(deviceSettingMenu);

        jSeparator3.setName("jSeparator3"); // NOI18N
        jMenu2.add(jSeparator3);

        jMenuItem9.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem9.setMnemonic('p');
        jMenuItem9.setText(resourceMap.getString("jMenuItem9.text")); // NOI18N
        jMenuItem9.setName("jMenuItem9"); // NOI18N
        jMenu2.add(jMenuItem9);

        menuBar.add(jMenu2);

        jMenu3.setMnemonic('u');
        jMenu3.setText(resourceMap.getString("jMenu3.text")); // NOI18N
        jMenu3.setName("jMenu3"); // NOI18N

        startMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        startMenu.setIcon(resourceMap.getIcon("startMenu.icon")); // NOI18N
        startMenu.setMnemonic('r');
        startMenu.setText(resourceMap.getString("startMenu.text")); // NOI18N
        startMenu.setName("startMenu"); // NOI18N
        jMenu3.add(startMenu);

        stopMenu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        stopMenu.setIcon(resourceMap.getIcon("stopMenu.icon")); // NOI18N
        stopMenu.setMnemonic('t');
        stopMenu.setText(resourceMap.getString("stopMenu.text")); // NOI18N
        stopMenu.setName("stopMenu"); // NOI18N
        jMenu3.add(stopMenu);

        menuBar.add(jMenu3);

        jMenu4.setMnemonic('l');
        jMenu4.setText(resourceMap.getString("jMenu4.text")); // NOI18N
        jMenu4.setName("jMenu4"); // NOI18N

        jMenuItem12.setText(resourceMap.getString("jMenuItem12.text")); // NOI18N
        jMenuItem12.setName("jMenuItem12"); // NOI18N
        jMenu4.add(jMenuItem12);

        jMenuItem13.setText(resourceMap.getString("jMenuItem13.text")); // NOI18N
        jMenuItem13.setName("jMenuItem13"); // NOI18N
        jMenu4.add(jMenuItem13);

        menuBar.add(jMenu4);

        jMenu5.setMnemonic('H');
        jMenu5.setText(resourceMap.getString("jMenu5.text")); // NOI18N
        jMenu5.setName("jMenu5"); // NOI18N

        jMenuItem14.setText(resourceMap.getString("jMenuItem14.text")); // NOI18N
        jMenuItem14.setName("jMenuItem14"); // NOI18N
        jMenu5.add(jMenuItem14);

        menuBar.add(jMenu5);

        statusPanel.setName("statusPanel"); // NOI18N
        statusPanel.setPreferredSize(new java.awt.Dimension(1075, 24));

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 1069, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(statusPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(statusMessageLabel))
                    .addGroup(statusPanelLayout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 921, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(statusAnimationLabel)
                    .addComponent(jLabel2))
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(statusMessageLabel)
                        .addComponent(statusAnimationLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel2)))
                .addGap(11, 11, 11))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton autoDetectAction;
    private javax.swing.JMenuItem autoDetectMenu;
    private javax.swing.JPanel blocksPanel;
    private javax.swing.JButton connectAction;
    private javax.swing.JMenuItem connectMenu;
    protected static javax.swing.JTable dataTable;
    private javax.swing.JMenuItem deviceSettingMenu;
    private javax.swing.JButton disconnectAction;
    private javax.swing.JMenuItem disconnectMenu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem2;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem3;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem4;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem5;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem6;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JSpinner jSpinner5;
    private javax.swing.JSpinner jSpinner6;
    private javax.swing.JSpinner jSpinner7;
    private javax.swing.JSpinner jSpinner8;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem setupWizardMenu;
    private javax.swing.JButton startAction;
    private javax.swing.JMenuItem startMenu;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JButton stopAction;
    private javax.swing.JMenuItem stopMenu;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}
