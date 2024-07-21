
import java.util.ArrayList;
import java.util.Iterator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Combo;

public class Student_Management {

	protected Shell shell;
	private Text txtId;
	private Text txtFullName;
	private Text txtAddress;
	private Text txtClassId;
	private Text txtClassNameofClass;
	private Table tblEmployee;
	private Table tblClasses;
	private Combo txtClassname;

	/**
	 * Launch the application.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Student_Management window = new Student_Management();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		loadData();
		loadClassData();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(676, 531);
		shell.setText("SWT Application");
		shell.setLayout(new GridLayout(1, false));

		Composite composite = new Composite(shell, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.VERTICAL));
		GridData gd_composite = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_composite.heightHint = 130;
		gd_composite.widthHint = 581;
		composite.setLayoutData(gd_composite);

		CTabFolder tabFolder = new CTabFolder(composite, SWT.BORDER);
		tabFolder.setSelectionBackground(
				Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));

		CTabItem tbtmNewItem = new CTabItem(tabFolder, SWT.NONE);
		tbtmNewItem.setText("Student");

		Composite composite_1 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem.setControl(composite_1);
		composite_1.setLayout(new GridLayout(1, false));

		Composite composite_2 = new Composite(composite_1, SWT.NONE);
		composite_2.setLayout(new GridLayout(3, false));
		GridData gd_composite_2 = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_composite_2.heightHint = 109;
		gd_composite_2.widthHint = 667;
		composite_2.setLayoutData(gd_composite_2);
		composite_2.setBounds(0, 0, 64, 64);

		Label lblId = new Label(composite_2, SWT.NONE);
		lblId.setText("Id:");
		new Label(composite_2, SWT.NONE);

		txtId = new Text(composite_2, SWT.BORDER);
		GridData gd_txtId = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_txtId.widthHint = 77;
		txtId.setLayoutData(gd_txtId);

		Label lblFullname = new Label(composite_2, SWT.NONE);
		lblFullname.setText("FullName:");
		new Label(composite_2, SWT.NONE);

		txtFullName = new Text(composite_2, SWT.BORDER);
		GridData gd_txtFullName = new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1);
		gd_txtFullName.widthHint = 382;
		txtFullName.setLayoutData(gd_txtFullName);

		Label lblAddress = new Label(composite_2, SWT.NONE);
		lblAddress.setText("Address:");
		new Label(composite_2, SWT.NONE);

		txtAddress = new Text(composite_2, SWT.BORDER);
		txtAddress.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblClassName = new Label(composite_2, SWT.NONE);
		lblClassName.setText("Class Name:");
		new Label(composite_2, SWT.NONE);

		txtClassname = new Combo(composite_2, SWT.NONE);
		txtClassname.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Composite composite_3 = new Composite(composite_1, SWT.NONE);
		composite_3.setLayout(new GridLayout(15, false));
		GridData gd_composite_3 = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_composite_3.heightHint = 36;
		gd_composite_3.widthHint = 665;
		composite_3.setLayoutData(gd_composite_3);
		composite_3.setBounds(0, 0, 64, 64);

		Button btnFind = new Button(composite_3, SWT.NONE);
		btnFind.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ArrayList<Employees> data = null;
				if (txtId.getText().isEmpty() && txtFullName.getText().isEmpty()) {
					return;
				}
				if (!txtId.getText().isEmpty()) {
					Employees employees = new Employees(Integer.parseInt(txtId.getText()), txtFullName.getText(),
							txtAddress.getText(), txtClassname.getText());
					data = employees.findStudent();

				} else if (!txtFullName.getText().isEmpty()) {
					Employees employees = new Employees(txtFullName.getText());
					data = employees.findStudentWithName();
				}
				tblEmployee.removeAll();
				for (Iterator<Employees> iterator = data.iterator(); iterator.hasNext();) {
					Employees employee = (Employees) iterator.next();
					TableItem item = new TableItem(tblEmployee, SWT.CENTER);
					item.setText(new String[] { String.valueOf(employee.getId()), employee.getFull_name(),
							employee.getAddress(), employee.getClass_name() });
				}

			}
		});
		GridData gd_btnFind = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_btnFind.heightHint = 28;
		gd_btnFind.widthHint = 93;
		btnFind.setLayoutData(gd_btnFind);
		btnFind.setText("Find");
		new Label(composite_3, SWT.NONE);

		Button btnSave = new Button(composite_3, SWT.NONE);
		btnSave.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!txtId.getText().isEmpty()) {
					Employees employees = new Employees(Integer.parseInt(txtId.getText()), txtFullName.getText(),
							txtAddress.getText(), txtClassname.getText());
					int studentId = Integer.parseInt(txtId.getText());
					if (!isStudentIdExists(studentId)) {
						// Hiển thị thông báo nếu ID đã tồn tại
						MessageBox messageBox = new MessageBox(shell, SWT.OK | SWT.ICON_INFORMATION);
						messageBox.setText("Thông báo");
						messageBox.setMessage("ID đã tồn tại trong bảng!");
						messageBox.open();
						return; // Không thực hiện lưu dữ liệu nếu ID đã tồn tại
					}
					employees.insert();
					loadData();

				}

				else if (!txtClassId.getText().isEmpty()) {
					Classes classes = new Classes(Integer.parseInt(txtClassId.getText()), txtClassname.getText());
					classes.insert();
					loadClassData();
				}

			}
		});
		GridData gd_btnSave = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_btnSave.heightHint = 27;
		gd_btnSave.widthHint = 107;
		btnSave.setLayoutData(gd_btnSave);
		btnSave.setText("Save");
		new Label(composite_3, SWT.NONE);

		Button btnUpdate = new Button(composite_3, SWT.NONE);
		btnUpdate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!txtId.getText().isEmpty()) {
					Employees em = new Employees(Integer.valueOf(txtId.getText()), txtFullName.getText(),
							txtAddress.getText(), txtClassname.getText());
					em.update();
					loadData();
				}

				else if (!txtClassId.getText().isEmpty()) {
					Classes classes = new Classes(Integer.parseInt(txtClassId.getText()), txtClassname.getText());
					classes.update();
					loadClassData();
				}

			}
		});
		GridData gd_btnUpdate = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_btnUpdate.heightHint = 34;
		gd_btnUpdate.widthHint = 101;
		btnUpdate.setLayoutData(gd_btnUpdate);
		btnUpdate.setText("Update");
		new Label(composite_3, SWT.NONE);

		Button btnDelete = new Button(composite_3, SWT.NONE);
		btnDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!txtId.getText().isEmpty()) {
					Employees em = new Employees(Integer.valueOf(txtId.getText()), txtFullName.getText(),
							txtAddress.getText(), txtClassname.getText());
					em.delete();
					loadData();
				} else if (!txtClassId.getText().isEmpty()) {
					Classes classes = new Classes(Integer.parseInt(txtClassId.getText()), txtClassname.getText());
					classes.delete();
					loadClassData();
				}
			}
		});
		GridData gd_btnDelete = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_btnDelete.heightHint = 31;
		gd_btnDelete.widthHint = 115;
		btnDelete.setLayoutData(gd_btnDelete);
		btnDelete.setText("Delete");
		new Label(composite_3, SWT.NONE);
		new Label(composite_3, SWT.NONE);
		new Label(composite_3, SWT.NONE);
		new Label(composite_3, SWT.NONE);
		new Label(composite_3, SWT.NONE);

		Button btnRefresh = new Button(composite_3, SWT.NONE);
		GridData gd_btnRefresh = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnRefresh.widthHint = 72;
		gd_btnRefresh.heightHint = 31;
		btnRefresh.setLayoutData(gd_btnRefresh);
		btnRefresh.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				loadData();
			}
		});
		btnRefresh.setText("Refresh");
		new Label(composite_3, SWT.NONE);

		Button btnClear = new Button(composite_3, SWT.NONE);
		GridData gd_btnClear = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnClear.widthHint = 66;
		gd_btnClear.heightHint = 33;
		btnClear.setLayoutData(gd_btnClear);
		btnClear.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txtId.setText("");
				txtFullName.setText("");
				txtAddress.setText("");
				txtClassname.setText("");
			}
		});
		btnClear.setText("Clear");

		Composite composite_4 = new Composite(composite_1, SWT.NONE);
		composite_4.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gd_composite_4 = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_composite_4.heightHint = 215;
		gd_composite_4.widthHint = 665;
		composite_4.setLayoutData(gd_composite_4);
		composite_4.setBounds(0, 0, 64, 64);

		tblEmployee = new Table(composite_4, SWT.BORDER | SWT.FULL_SELECTION);
		tblEmployee.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Point p = new Point(e.x, e.y);
				TableItem item = tblEmployee.getItem(p);
				if (item != null) {
					txtId.setText(item.getText(0));
					txtFullName.setText(item.getText(1));
					txtAddress.setText(item.getText(2));
					txtClassname.setText(item.getText(3));
					txtClassId.setText("");
				}
			}
		});
		tblEmployee.setLinesVisible(true);
		tblEmployee.setHeaderVisible(true);

		TableColumn tblclmnNewColumn = new TableColumn(tblEmployee, SWT.NONE);
		tblclmnNewColumn.setWidth(54);
		tblclmnNewColumn.setText("ID");

		TableColumn tblclmnNewColumn_1 = new TableColumn(tblEmployee, SWT.NONE);
		tblclmnNewColumn_1.setWidth(176);
		tblclmnNewColumn_1.setText("FullName");

		TableColumn tblclmnNewColumn_2 = new TableColumn(tblEmployee, SWT.NONE);
		tblclmnNewColumn_2.setWidth(203);
		tblclmnNewColumn_2.setText("Address");

		TableColumn tblclmnClassname = new TableColumn(tblEmployee, SWT.NONE);
		tblclmnClassname.setWidth(169);
		tblclmnClassname.setText("ClassName");

		CTabItem tbtmNewItem_1 = new CTabItem(tabFolder, SWT.NONE);
		tbtmNewItem_1.setText("Classes");

		Composite composite_5 = new Composite(tabFolder, SWT.NONE);
		tbtmNewItem_1.setControl(composite_5);
		composite_5.setLayout(new GridLayout(1, false));

		Composite composite_6 = new Composite(composite_5, SWT.NONE);
		composite_6.setLayout(new GridLayout(3, false));
		GridData gd_composite_6 = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_composite_6.heightHint = 3;
		gd_composite_6.widthHint = 678;
		composite_6.setLayoutData(gd_composite_6);
		composite_6.setBounds(0, 0, 64, 64);

		Label lblClassId_2 = new Label(composite_6, SWT.NONE);
		lblClassId_2.setText("Class Id:");
		new Label(composite_6, SWT.NONE);

		txtClassId = new Text(composite_6, SWT.BORDER);
		txtClassId.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Label lblClassname = new Label(composite_6, SWT.NONE);
		lblClassname.setText("ClassName:");
		new Label(composite_6, SWT.NONE);

		txtClassNameofClass = new Text(composite_6, SWT.BORDER);
		txtClassNameofClass.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		Composite composite_7 = new Composite(composite_5, SWT.NONE);
		composite_7.setLayout(new GridLayout(21, false));
		GridData gd_composite_7 = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_composite_7.heightHint = -15;
		gd_composite_7.widthHint = 677;
		composite_7.setLayoutData(gd_composite_7);
		composite_7.setBounds(0, 0, 64, 64);

		Button btnFindClass = new Button(composite_7, SWT.NONE);
		btnFindClass.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ArrayList<Classes> data = null;
				if (txtClassId.getText().isEmpty() && txtClassNameofClass.getText().isEmpty()) {
					return;
				}
				if (!txtClassId.getText().isEmpty()) {
					Classes classes = new Classes(Integer.parseInt(txtClassId.getText()),
							txtClassNameofClass.getText());
					data = classes.findClass();

				} else if (!txtClassNameofClass.getText().isEmpty()) {
					Classes classes = new Classes(txtClassNameofClass.getText());
					data = classes.findClassWithName();
				}
				tblClasses.removeAll();
				for (Iterator<Classes> iterator = data.iterator(); iterator.hasNext();) {
					Classes classes = (Classes) iterator.next();
					TableItem item = new TableItem(tblClasses, SWT.CENTER);
					item.setText(new String[] { String.valueOf(classes.getClassId()), classes.getClass_name() });
				}
			}
		});
		GridData gd_btnFindClass = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_btnFindClass.widthHint = 56;
		gd_btnFindClass.heightHint = 31;
		btnFindClass.setLayoutData(gd_btnFindClass);
		btnFindClass.setText("Find");
		new Label(composite_7, SWT.NONE);

		Button btnSaveClass = new Button(composite_7, SWT.NONE);
		btnSaveClass.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Classes classes = new Classes(Integer.parseInt(txtClassId.getText()), txtClassNameofClass.getText());
				int classId = Integer.parseInt(txtClassId.getText());
				if (!isClassIdExists(classId)) {
					// Hiển thị thông báo nếu ID đã tồn tại
					MessageBox messageBox = new MessageBox(shell, SWT.OK | SWT.ICON_INFORMATION);
					messageBox.setText("Thông báo");
					messageBox.setMessage("ID đã tồn tại trong bảng!");
					messageBox.open();
					return; // Không thực hiện lưu dữ liệu nếu ID đã tồn tại
				}
				classes.insert();
				loadClassData();

			}
		});
		GridData gd_btnSaveClass = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_btnSaveClass.widthHint = 58;
		btnSaveClass.setLayoutData(gd_btnSaveClass);
		btnSaveClass.setText("Save");
		new Label(composite_7, SWT.NONE);

		Button btnUpdateClass = new Button(composite_7, SWT.NONE);
		btnUpdateClass.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Classes classes = new Classes(Integer.parseInt(txtClassId.getText()), txtClassNameofClass.getText());
				classes.update();
				loadClassData();
			}
		});
		GridData gd_btnUpdateClass = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_btnUpdateClass.widthHint = 65;
		btnUpdateClass.setLayoutData(gd_btnUpdateClass);
		btnUpdateClass.setText("Update");
		new Label(composite_7, SWT.NONE);
		new Label(composite_7, SWT.NONE);

		Button btnDeleteClass = new Button(composite_7, SWT.NONE);
		btnDeleteClass.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (!txtId.getText().isEmpty()) {
					Employees em = new Employees(Integer.valueOf(txtId.getText()), txtFullName.getText(),
							txtAddress.getText(), txtClassname.getText());
					em.delete();
					loadData();
				} else if (!txtClassId.getText().isEmpty()) {
					Classes classes = new Classes(Integer.parseInt(txtClassId.getText()), txtClassname.getText());
					classes.delete();
					loadClassData();
				}
			}
		});
		GridData gd_btnDeleteClass = new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1);
		gd_btnDeleteClass.widthHint = 67;
		gd_btnDeleteClass.heightHint = 37;
		btnDeleteClass.setLayoutData(gd_btnDeleteClass);
		btnDeleteClass.setText("Delete");
		new Label(composite_7, SWT.NONE);
		new Label(composite_7, SWT.NONE);
		new Label(composite_7, SWT.NONE);
		new Label(composite_7, SWT.NONE);
		new Label(composite_7, SWT.NONE);
		new Label(composite_7, SWT.NONE);
		new Label(composite_7, SWT.NONE);
		new Label(composite_7, SWT.NONE);
		new Label(composite_7, SWT.NONE);

		Button btnRefreshClass = new Button(composite_7, SWT.NONE);
		btnRefreshClass.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				loadClassData();
			}
		});
		GridData gd_btnRefreshClass = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnRefreshClass.heightHint = 32;
		btnRefreshClass.setLayoutData(gd_btnRefreshClass);
		btnRefreshClass.setText("Refresh");
		new Label(composite_7, SWT.NONE);
		new Label(composite_7, SWT.NONE);

		Button btnClearClass = new Button(composite_7, SWT.NONE);
		GridData gd_btnClearClass = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_btnClearClass.heightHint = 33;
		gd_btnClearClass.widthHint = 48;
		btnClearClass.setLayoutData(gd_btnClearClass);
		btnClearClass.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				txtClassId.setText("");
				txtClassNameofClass.setText("");
			}
		});
		btnClearClass.setText("Clear");

		Composite composite_8 = new Composite(composite_5, SWT.NONE);
		composite_8.setLayout(new FillLayout(SWT.HORIZONTAL));
		GridData gd_composite_8 = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
		gd_composite_8.heightHint = 240;
		gd_composite_8.widthHint = 675;
		composite_8.setLayoutData(gd_composite_8);
		composite_8.setBounds(0, 0, 64, 64);

		tblClasses = new Table(composite_8, SWT.BORDER | SWT.FULL_SELECTION);
		tblClasses.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				Point p = new Point(e.x, e.y);
				TableItem item = tblClasses.getItem(p);
				if (item != null) {
					txtClassId.setText(item.getText(0));
					txtClassNameofClass.setText(item.getText(1));
					txtId.setText("");
					txtFullName.setText("");
					txtAddress.setText("");
				}
			}
		});
		tblClasses.setLinesVisible(true);
		tblClasses.setHeaderVisible(true);

		TableColumn tblclmnNewColumn_3 = new TableColumn(tblClasses, SWT.NONE);
		tblclmnNewColumn_3.setWidth(205);
		tblclmnNewColumn_3.setText("ClassId");

		TableColumn tblclmnNewColumn_4 = new TableColumn(tblClasses, SWT.NONE);
		tblclmnNewColumn_4.setWidth(276);
		tblclmnNewColumn_4.setText("ClassName");
	}

	public void loadData() {
		ArrayList<Employees> data = new Employees().getListEmployees();
		tblEmployee.removeAll();
		for (Iterator<Employees> iterator = data.iterator(); iterator.hasNext();) {
			Employees employees = (Employees) iterator.next();
			TableItem item = new TableItem(tblEmployee, SWT.CENTER);
			item.setText(new String[] { String.valueOf(employees.getId()), employees.getFull_name(),
					employees.getAddress(), employees.getClass_name() });
		}
	}

	public void loadClassData() {
		ArrayList<Classes> data = new Classes().getListEmployees();
		tblClasses.removeAll();
		// Danh sách để lưu trữ tất cả các giá trị từ cột "ClassName"
		ArrayList<String> classNames = new ArrayList<>();
		for (Iterator<Classes> iterator = data.iterator(); iterator.hasNext();) {
			Classes classes = (Classes) iterator.next();
			TableItem item = new TableItem(tblClasses, SWT.CENTER);
			item.setText(new String[] { String.valueOf(classes.getClassId()), classes.getClass_name() });

			classNames.add(classes.getClass_name());

		}
		// Xóa tất cả các mục cũ trong combo
		txtClassname.removeAll();
		// Thêm tất cả các giá trị từ danh sách vào combo
		for (String className : classNames) {
			txtClassname.add(className);
		}
	}

	public boolean isStudentIdExists(int InputStudentId) {
		ArrayList<Employees> data = new Employees().getListEmployees();
		for (Iterator<Employees> iterator = data.iterator(); iterator.hasNext();) {
			Employees employees = (Employees) iterator.next();
			if (InputStudentId == employees.getId()) {
				return false;
			}
		}
		return true;
	}

	public boolean isClassIdExists(int InputclassId) {
		ArrayList<Classes> data = new Classes().getListEmployees();
		for (Iterator<Classes> iterator = data.iterator(); iterator.hasNext();) {
			Classes classes = (Classes) iterator.next();
			if (InputclassId == classes.getClassId()) {
				return false;
			}
		}
		return true;
	}
}
