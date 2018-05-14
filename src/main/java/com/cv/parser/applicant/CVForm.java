package com.cv.parser.applicant;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.border.BevelBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JScrollPane;
import javax.swing.border.EtchedBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CVForm extends JPanel {
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtCity;
	private JTextField txtPostalCode;
	private JTextField txtPhone;
	private JTextField txtMobile;
	private JTextField txtEmail;
	private JTable tableEducation;
	private JTable tableWorks;
	private JTextField txtLanguages;

	/**
	 * Create the panel.
	 */
	public CVForm() {
		setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
		JPanel panel = new JPanel();
		
		JTabbedPane tabPanel = new JTabbedPane(JTabbedPane.TOP);
		
		JPanel panelButton = new JPanel();
		panelButton.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setFont(new Font("Consolas", Font.PLAIN, 18));
		
		JButton btnSearch = new JButton("Search");
		btnSearch.setFont(new Font("Consolas", Font.PLAIN, 18));
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.setFont(new Font("Consolas", Font.PLAIN, 18));
		btnUpdate.setEnabled(false);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setFont(new Font("Consolas", Font.PLAIN, 18));
		btnDelete.setEnabled(false);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Consolas", Font.PLAIN, 18));
		
		JButton btnSave = new JButton("Save");
		btnSave.setFont(new Font("Consolas", Font.PLAIN, 18));
		btnSave.setEnabled(false);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Consolas", Font.PLAIN, 18));
		btnCancel.setEnabled(false);
		
		JButton btnUpload = new JButton("Upload CV");
		btnUpload.setFont(new Font("Consolas", Font.PLAIN, 18));
		GroupLayout gl_panelButton = new GroupLayout(panelButton);
		gl_panelButton.setHorizontalGroup(
			gl_panelButton.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelButton.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panelButton.createParallelGroup(Alignment.LEADING)
						.addComponent(btnAdd, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
						.addComponent(btnUpload, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
						.addComponent(btnSearch, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
						.addComponent(btnUpdate, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
						.addComponent(btnDelete, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
						.addComponent(btnExit, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
						.addComponent(btnSave, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
						.addComponent(btnCancel, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_panelButton.setVerticalGroup(
			gl_panelButton.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelButton.createSequentialGroup()
					.addGap(13)
					.addComponent(btnUpload, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnAdd)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSearch)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnUpdate)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnDelete)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnExit)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnSave)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnCancel)
					.addContainerGap(75, Short.MAX_VALUE))
		);
		panelButton.setLayout(gl_panelButton);
		
		JLabel lblNewLabel_1 = new JLabel("CV Application");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		
		JLabel lblPleaseUploadCv = new JLabel("Please upload CV in Word Document or PDF only,   or  input candidate data manually.");
		lblPleaseUploadCv.setHorizontalAlignment(SwingConstants.CENTER);
		lblPleaseUploadCv.setFont(new Font("Tahoma", Font.BOLD, 14));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(tabPanel, GroupLayout.PREFERRED_SIZE, 917, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(panelButton, GroupLayout.PREFERRED_SIZE, 161, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(lblNewLabel_1, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(Alignment.LEADING, gl_panel.createSequentialGroup()
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(lblPleaseUploadCv, GroupLayout.PREFERRED_SIZE, 917, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(35, Short.MAX_VALUE))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblPleaseUploadCv, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(tabPanel, GroupLayout.PREFERRED_SIZE, 545, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(107)
							.addComponent(panelButton, GroupLayout.PREFERRED_SIZE, 332, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(39, Short.MAX_VALUE))
		);
		
		JPanel panelProfile = new JPanel();
		panelProfile.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		tabPanel.addTab("Profile", null, panelProfile, null);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel lblNewLabel_2 = new JLabel("Applicant Profile");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtFirstName = new JTextField();
		txtFirstName.setColumns(10);
		
		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JTextArea txtAddress = new JTextArea();
		
		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtCity = new JTextField();
		txtCity.setColumns(10);
		
		JLabel lblPostalCode = new JLabel("Postal Code");
		lblPostalCode.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtPostalCode = new JTextField();
		txtPostalCode.setColumns(10);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtPhone = new JTextField();
		txtPhone.setColumns(10);
		
		JLabel lblMobile = new JLabel("Mobile");
		lblMobile.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtMobile = new JTextField();
		txtMobile.setColumns(10);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		
		JLabel lblObhjective = new JLabel("Obhjective");
		lblObhjective.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JTextArea txtObjective = new JTextArea();
		
		JLabel lblLanguages = new JLabel("Languages");
		lblLanguages.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txtLanguages = new JTextField();
		txtLanguages.setColumns(10);
		GroupLayout gl_panelProfile = new GroupLayout(panelProfile);
		gl_panelProfile.setHorizontalGroup(
			gl_panelProfile.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelProfile.createSequentialGroup()
					.addGroup(gl_panelProfile.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panelProfile.createSequentialGroup()
							.addGap(59)
							.addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 648, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panelProfile.createSequentialGroup()
							.addGap(40)
							.addGroup(gl_panelProfile.createParallelGroup(Alignment.LEADING)
								.addComponent(lblFirstName, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLastName, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblAddress, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblCity, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPostalCode, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPhone, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblMobile, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblObhjective, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblLanguages, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE))
							.addGap(50)
							.addGroup(gl_panelProfile.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_panelProfile.createParallelGroup(Alignment.LEADING, false)
									.addComponent(txtLastName, GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
									.addComponent(txtFirstName, GroupLayout.DEFAULT_SIZE, 354, Short.MAX_VALUE)
									.addComponent(txtAddress, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE))
								.addComponent(txtCity, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPostalCode, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtMobile, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtObjective, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE)
								.addComponent(txtLanguages, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(58, Short.MAX_VALUE))
		);
		gl_panelProfile.setVerticalGroup(
			gl_panelProfile.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelProfile.createSequentialGroup()
					.addGap(25)
					.addComponent(lblNewLabel_2)
					.addGap(50)
					.addGroup(gl_panelProfile.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblFirstName)
						.addComponent(txtFirstName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelProfile.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLastName, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtLastName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelProfile.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblAddress, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtAddress, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelProfile.createParallelGroup(Alignment.LEADING)
						.addComponent(lblCity, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtCity, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelProfile.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPostalCode, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPostalCode, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelProfile.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblPhone, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtPhone, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelProfile.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblMobile, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtMobile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_panelProfile.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblEmail, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtEmail, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelProfile.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblObhjective, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtObjective, GroupLayout.PREFERRED_SIZE, 81, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panelProfile.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblLanguages, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(txtLanguages, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(23, Short.MAX_VALUE))
		);
		panelProfile.setLayout(gl_panelProfile);
		
		JPanel panelEduExp = new JPanel();
		tabPanel.addTab("Education & Experience", null, panelEduExp, null);
		
		JLabel lblNewLabel = new JLabel("Education");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JScrollPane spEduation = new JScrollPane();
		spEduation.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		spEduation.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		
		JLabel lblWorkExperiences = new JLabel("Work Experiences");
		lblWorkExperiences.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		GroupLayout gl_panelEduExp = new GroupLayout(panelEduExp);
		gl_panelEduExp.setHorizontalGroup(
			gl_panelEduExp.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelEduExp.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_panelEduExp.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 92, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblWorkExperiences, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
						.addComponent(spEduation, GroupLayout.DEFAULT_SIZE, 783, Short.MAX_VALUE)
						.addComponent(scrollPane_1))
					.addContainerGap(71, Short.MAX_VALUE))
		);
		gl_panelEduExp.setVerticalGroup(
			gl_panelEduExp.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panelEduExp.createSequentialGroup()
					.addGap(23)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(spEduation, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
					.addGap(35)
					.addComponent(lblWorkExperiences, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane_1, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(44, Short.MAX_VALUE))
		);
		
		tableWorks = new JTable();
		tableWorks.setModel(new DefaultTableModel(
			new Object[][] {
				{"2017", "2018", "Unpaid Internship", "Pratt Whitney Canada", "Develop using Share Point, HTML"},
				{"2015", "2015", "Internship", "Booxy", "Create App using Javascript, HTML, CSS"},
				{null, null, null, null, null},
			},
			new String[] {
				"From", "To", "Title & Company", "Company", "Desc"
			}
		));
		tableWorks.getColumnModel().getColumn(0).setPreferredWidth(70);
		tableWorks.getColumnModel().getColumn(2).setPreferredWidth(203);
		tableWorks.getColumnModel().getColumn(3).setPreferredWidth(124);
		tableWorks.getColumnModel().getColumn(4).setPreferredWidth(150);
		tableWorks.getColumnModel().getColumn(4).setMinWidth(150);
		scrollPane_1.setViewportView(tableWorks);
		
		tableEducation = new JTable();
		tableEducation.setModel(new DefaultTableModel(
			new Object[][] {
				{"2016", "2018", "Computer Science Concordia University", "Predicted to graduate in 2021"},
				{"2012", "2015", "Vanier College", "Dec in Computer Science"},
				{null, " ", null, null},
				{null, null, null, null},
			},
			new String[] {
				"From", "To", "Program (School Name)", "Description"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableEducation.getColumnModel().getColumn(0).setPreferredWidth(44);
		tableEducation.getColumnModel().getColumn(1).setPreferredWidth(38);
		tableEducation.getColumnModel().getColumn(2).setPreferredWidth(232);
		tableEducation.getColumnModel().getColumn(3).setPreferredWidth(258);
		spEduation.setViewportView(tableEducation);
		panelEduExp.setLayout(gl_panelEduExp);
		panel.setLayout(gl_panel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		

	}
}
