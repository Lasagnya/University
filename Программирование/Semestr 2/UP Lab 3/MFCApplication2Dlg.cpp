
// MFCApplication2Dlg.cpp: файл реализации
//

#include "pch.h"
#include "framework.h"
#include "MFCApplication2.h"
#include "MFCApplication2Dlg.h"
#include "afxdialogex.h"
#include <string>
#include"MyDialog.h"
#ifdef _DEBUG
#define new DEBUG_NEW
#endif


// Диалоговое окно CAboutDlg используется для описания сведений о приложении

class CAboutDlg : public CDialogEx
{
public:
	CAboutDlg();

// Данные диалогового окна
#ifdef AFX_DESIGN_TIME
	enum { IDD = IDD_ABOUTBOX };
#endif

	protected:
	virtual void DoDataExchange(CDataExchange* pDX);    // поддержка DDX/DDV

// Реализация
protected:
	DECLARE_MESSAGE_MAP()
};

CAboutDlg::CAboutDlg() : CDialogEx(IDD_ABOUTBOX)
{
}

void CAboutDlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
}

BEGIN_MESSAGE_MAP(CAboutDlg, CDialogEx)
END_MESSAGE_MAP()


// Диалоговое окно CMFCApplication2Dlg



CMFCApplication2Dlg::CMFCApplication2Dlg(CWnd* pParent /*=nullptr*/)
	: CDialogEx(IDD_MFCAPPLICATION2_DIALOG, pParent)
{
	m_hIcon = AfxGetApp()->LoadIcon(IDR_MAINFRAME);
}

void CMFCApplication2Dlg::DoDataExchange(CDataExchange* pDX)
{
	CDialogEx::DoDataExchange(pDX);
}

BEGIN_MESSAGE_MAP(CMFCApplication2Dlg, CDialogEx)
	ON_WM_SYSCOMMAND()
	ON_WM_PAINT()
	ON_WM_QUERYDRAGICON()
	ON_COMMAND(1003, &CMFCApplication2Dlg::On1003)
	ON_COMMAND(1002, &CMFCApplication2Dlg::On1002)
	ON_BN_CLICKED(IDOK, &CMFCApplication2Dlg::OnBnClickedOk)
	ON_WM_RBUTTONDOWN()
	ON_WM_LBUTTONDBLCLK()
//	ON_WM_KEYDOWN()
ON_WM_MOUSEMOVE()
ON_WM_LBUTTONDOWN()
ON_WM_LBUTTONUP()
ON_COMMAND(1008, &CMFCApplication2Dlg::On1008)
ON_COMMAND(1006, &CMFCApplication2Dlg::On1006)
ON_COMMAND(1007, &CMFCApplication2Dlg::On1007)
ON_COMMAND(1004, &CMFCApplication2Dlg::On1004)
END_MESSAGE_MAP()


// Обработчики сообщений CMFCApplication2Dlg

BOOL CMFCApplication2Dlg::OnInitDialog()
{
	CDialogEx::OnInitDialog();

	// Добавление пункта "О программе..." в системное меню.

	// IDM_ABOUTBOX должен быть в пределах системной команды.
	ASSERT((IDM_ABOUTBOX & 0xFFF0) == IDM_ABOUTBOX);
	ASSERT(IDM_ABOUTBOX < 0xF000);

	CMenu* pSysMenu = GetSystemMenu(FALSE);
	if (pSysMenu != nullptr)
	{
		BOOL bNameValid;
		CString strAboutMenu;
		bNameValid = strAboutMenu.LoadString(IDS_ABOUTBOX);
		ASSERT(bNameValid);
		if (!strAboutMenu.IsEmpty())
		{
			pSysMenu->AppendMenu(MF_SEPARATOR);
			pSysMenu->AppendMenu(MF_STRING, IDM_ABOUTBOX, strAboutMenu);
		}
	}

	// Задает значок для этого диалогового окна.  Среда делает это автоматически,
	//  если главное окно приложения не является диалоговым
	SetIcon(m_hIcon, TRUE);			// Крупный значок
	SetIcon(m_hIcon, FALSE);		// Мелкий значок

	// TODO: добавьте дополнительную инициализацию
	menu.LoadMenu(IDR_MENU1);
	SetMenu(&menu);
	return TRUE;  // возврат значения TRUE, если фокус не передан элементу управления
}

void CMFCApplication2Dlg::OnSysCommand(UINT nID, LPARAM lParam)
{
	if ((nID & 0xFFF0) == IDM_ABOUTBOX)
	{
		CAboutDlg dlgAbout;
		dlgAbout.DoModal();
	}
	else
	{
		CDialogEx::OnSysCommand(nID, lParam);
	}
}

// При добавлении кнопки свертывания в диалоговое окно нужно воспользоваться приведенным ниже кодом,
//  чтобы нарисовать значок.  Для приложений MFC, использующих модель документов или представлений,
//  это автоматически выполняется рабочей областью.

void CMFCApplication2Dlg::OnPaint()
{
	if (IsIconic())
	{
		CPaintDC dc(this); // контекст устройства для рисования

		SendMessage(WM_ICONERASEBKGND, reinterpret_cast<WPARAM>(dc.GetSafeHdc()), 0);

		// Выравнивание значка по центру клиентского прямоугольника
		int cxIcon = GetSystemMetrics(SM_CXICON);
		int cyIcon = GetSystemMetrics(SM_CYICON);
		CRect rect;
		GetClientRect(&rect);
		int x = (rect.Width() - cxIcon + 1) / 2;
		int y = (rect.Height() - cyIcon + 1) / 2;

		// Нарисуйте значок
		dc.DrawIcon(x, y, m_hIcon);
	}
	else
	{
		CDialogEx::OnPaint();
	}
}

// Система вызывает эту функцию для получения отображения курсора при перемещении
//  свернутого окна.
HCURSOR CMFCApplication2Dlg::OnQueryDragIcon()
{
	return static_cast<HCURSOR>(m_hIcon);
}



void CMFCApplication2Dlg::On1003()
{
	MyDialog dlg;
	dlg.DoModal();
	// TODO: добавьте свой код обработчика команд
}


void CMFCApplication2Dlg::On1002()
{
	CDialogEx::OnCancel();
	// TODO: добавьте свой код обработчика команд
}


void CMFCApplication2Dlg::OnBnClickedOk()
{
	// TODO: добавьте свой код обработчика уведомлений
	AfxMessageBox(L"Была нажата кнопка", MB_OK);
//	CDialogEx::OnOK();
}


void CMFCApplication2Dlg::OnRButtonDown(UINT nFlags, CPoint point)
{
	// TODO: добавьте свой код обработчика сообщений или вызов стандартного
	CClientDC dc(this);
	int nHeight = -MulDiv(20, dc.GetDeviceCaps(LOGPIXELSY), 72);
	CFont font;
	font.CreateFont(nHeight, 0, 0, 0, 400, 0, 0, false, RUSSIAN_CHARSET, OUT_CHARACTER_PRECIS, CLIP_CHARACTER_PRECIS, DEFAULT_QUALITY, FF_DONTCARE, L"Garamond");
	dc.SelectObject(font);
	CString s = CTime::GetCurrentTime().Format("%H:%M");;
	dc.TextOut(point.x, point.y, s);
	font.DeleteObject();
	CDialogEx::OnRButtonDown(nFlags, point);
}


void CMFCApplication2Dlg::OnLButtonDblClk(UINT nFlags, CPoint point)
{
	// TODO: добавьте свой код обработчика сообщений или вызов стандартного
	CClientDC dc(this);
	dc.MoveTo(0, 0);
	dc.LineTo(point.x, point.y);
	CDialogEx::OnLButtonDblClk(nFlags, point);
}


BOOL CMFCApplication2Dlg::PreTranslateMessage(MSG* pMsg)
{
	// TODO: добавьте специализированный код или вызов базового класса
	if (pMsg->message == WM_KEYDOWN)
	{
		CString s=L"Была нажата клавиша ";
		pMsg->wParam;
		if (pMsg->wParam == VK_BACK)
		{
			s +="Backspace";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_TAB)
			{
			s += "Tab";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_RETURN)
			{
			s += "Enter";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_SHIFT)
			{
			s += "Shift";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_CONTROL)
			{
			s += "Ctrl";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_PAUSE)
			{
			s += "Pause";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_CAPITAL)
			{
			s += "Caps Lock";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_ESCAPE)
			{
			s += "Esc";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_SPACE)
			{
			s += "Space";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_PRIOR)
			{
			s += "Page Up";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_NEXT)
			{
			s += "Page Down";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_END)
			{
			s += "End";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_HOME)
			{
			s += "Home";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_LEFT)
			{
			s += "Left Arrow";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_UP)
			{
			s += "Up Arrow";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_RIGHT)
			{
			s += "Right Arrow";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_DOWN)
			{
			s += "Down Arrow";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_INSERT)
			{
			s += "Ins";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_SNAPSHOT)
			{
			s += "Print Screen";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_DELETE)
			{
			s += "Delete";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x30 || pMsg->wParam == VK_NUMPAD0)
			{
			s += "0";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x31 || pMsg->wParam == VK_NUMPAD1)
			{
			s += "1";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x32 || pMsg->wParam == VK_NUMPAD2)
			{
			s += "2";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x33 || pMsg->wParam == VK_NUMPAD3)
			{
			s += "3";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x34 || pMsg->wParam == VK_NUMPAD4)
			{
			s += "4";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x35 || pMsg->wParam == VK_NUMPAD5)
			{
			s += "5";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x36 || pMsg->wParam == VK_NUMPAD6)
			{
			s += "6";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x37 || pMsg->wParam == VK_NUMPAD7)
			{
			s += "7";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x38 || pMsg->wParam == VK_NUMPAD8)
			{
			s += "8";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x39 || pMsg->wParam == VK_NUMPAD9)
			{
			s += "9";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x41)
			{
			s += "A";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x42)
			{
			s += "B";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x43)
			{
			s += "C";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x44)
			{
			s += "D";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x45)
			{
			s += "E";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x46)
			{
			s += "F";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x47)
			{
			s += "G";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x48)
			{
			s += "H";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x49)
			{
			s += "I";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x4A)
			{
			s += "J";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x4B)
			{
			s += "K";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x4C)
			{
			s += "L";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x4D)
			{
			s += "M";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x4E)
			{
			s += "N";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x4F)
			{
			s += "O";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x50)
			{
			s += "P";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x51)
			{
			s += "Q";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x52)
			{
			s += "R";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x53)
			{
			s += "S";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x54)
			{
			s += "T";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x55)
			{
			s += "U";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x56)
			{
			s += "V";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x57)
			{
			s += "W";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x58)
			{
			s += "X";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x59)
			{
			s += "Y";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == 0x5A)
			{
			s += "Z";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_LWIN)
			{
			s += "Windows";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_MULTIPLY)
			{
			s += "*";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_ADD)
			{
			s += "+";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_SUBTRACT)
			{
			s += "-";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_DECIMAL)
			{
			s += ".";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_DIVIDE)
			{
			s += "/";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_F1)
			{
			s += "F1";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_F2)
			{
			s += "F2";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_F3)
			{
			s += "F3";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_F4)
			{
			s += "F4";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_F5)
			{
			s += "F5";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_F6)
			{
			s += "F6";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_F7)
			{
			s += "F7";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_F8)
			{
			s += "F8";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_F9)
			{
			s += "F9";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_F11)
			{
			s += "F11";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_NUMLOCK)	
		{
			s += "Num Lock";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_OEM_1)
			{
			s += ";";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_OEM_PLUS)
			{
			s += "+=";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_OEM_COMMA)
			{
			s += ",<";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_OEM_MINUS)
			{
			s += "-_";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_OEM_PERIOD)
			{
			s += ".>";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_OEM_2)
			{
			s += "/?";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_OEM_3)
			{
			s += "`~";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_OEM_4)
			{
			s += "[{";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_OEM_5)
			{
			s += "\|";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_OEM_6)
			{
			s += "]}";
			AfxMessageBox(s, MB_OK);
		}
		if (pMsg->wParam == VK_OEM_7)
			{
			s += "'\"";
			AfxMessageBox(s, MB_OK);
		}
	}
	return CDialogEx::PreTranslateMessage(pMsg);
}

void CMFCApplication2Dlg::OnMouseMove(UINT nFlags, CPoint point)
{
	// TODO: добавьте свой код обработчика сообщений или вызов стандартного
	GetClientRect(&rect);
	CClientDC dc(this);
	if (b3 && !b4)
	{
		COLORREF color;
		color = RGB(rand() % 255, rand() % 255, rand() % 255);
		CBrush brush(color);
		dc.SelectObject(brush);
		CPen pen(PS_SOLID, 20, color);
		dc.Ellipse(point.x - 10, point.y - 10, point.x + 10, point.y + 10);
		pen.DeleteObject();
		brush.DeleteObject();
	}
	if (b4)
	{
		CPen pen(PS_SOLID, 10, RGB(0, 0, 0, ));
		dc.SelectObject(pen);
		if (!b5)
		{
			start = point;
			b5 = 1;
			return;
		}
		dc.MoveTo(start);
		dc.LineTo(point);
		start = point;
		//dc.SetPixel(point, RGB(0, 0, 0, ));
		pen.DeleteObject();
	}
	CDialogEx::OnMouseMove(nFlags, point);
}


void CMFCApplication2Dlg::OnLButtonDown(UINT nFlags, CPoint point)
{
	// TODO: добавьте свой код обработчика сообщений или вызов стандартного
	SetCapture();
	b4 = 1;
	CDialogEx::OnLButtonDown(nFlags, point);
}


void CMFCApplication2Dlg::OnLButtonUp(UINT nFlags, CPoint point)
{
	// TODO: добавьте свой код обработчика сообщений или вызов стандартного
	ReleaseCapture();
	b5 = 0;
	b4 = 0;
	CDialogEx::OnLButtonUp(nFlags, point);
}


void CMFCApplication2Dlg::On1008()
{
	// TODO: добавьте свой код обработчика команд
	CClientDC dc(this);
	GetClientRect(&rect);
	int l, h;
	l = rand() % 100;
	h = rand() % 100;
	rect1.left = rect.right / 2 - rand() % rect.right / 10;
	rect1.top = rect.bottom / 2 - rand() % rect.bottom / 10;
	rect1.bottom = rect1.top - h;
	rect1.right = rect1.left - l;
	CBrush brush(RGB(rand() % 255, rand() % 255, rand() % 255));
	dc.SelectObject(brush);
	dc.Rectangle(&rect1);
	brush.DeleteObject();
}


void CMFCApplication2Dlg::On1006()
{
	// TODO: добавьте свой код обработчика команд
	if (b1)
	{
		menu.CheckMenuItem(1006, MF_UNCHECKED);
		b1 = 0;
	}
	else
	{
		menu.CheckMenuItem(1006, MF_CHECKED);
		b1 = 1;
	}
	if (b3) b3 = 0;
	else b3 = 1;
}


void CMFCApplication2Dlg::On1007()
{
	// TODO: добавьте свой код обработчика команд
	CClientDC dc(this);
	GetClientRect(&rect);
	CPoint a1, a2, a3, a4;
	a1.x = rand() % rect.right;
	a1.y = rand() % rect.bottom;
	a2.x = rand() % rect.right;
	a2.y = rand() % rect.bottom;
	a3.x = rand() % rect.right;
	a3.y = rand() % rect.bottom;
	a4.x = rand() % rect.right;
	a4.y = rand() % rect.bottom;
	CPen pen(PS_SOLID, 10, RGB(0, 0, 0));
	dc.SelectObject(pen);
	dc.MoveTo(a1);
	dc.LineTo(a2);
	dc.LineTo(a3);
	dc.LineTo(a4);
	pen.DeleteObject();
}


void CMFCApplication2Dlg::On1004()
{
	// TODO: добавьте свой код обработчика команд
	Invalidate();
}
