#include <windows.h>
#include <winuser.h>
LRESULT CALLBACK WndProc(HWND, UINT, WPARAM, LPARAM);
char szAppName[] = "Program";
HWND hwndDlg = NULL;
int WINAPI WinMain(HINSTANCE hInstance, HINSTANCE hPrevInstance,
	PSTR szCmdLine, int iCmdShow)
{
	HWND        hwnd;
	MSG         msg;
	WNDCLASSEX  wndclass;

	wndclass.cbSize = sizeof(wndclass);
	wndclass.style = CS_HREDRAW | CS_VREDRAW;
	wndclass.lpfnWndProc = WndProc;
	wndclass.cbClsExtra = 0;
	wndclass.cbWndExtra = 0;
	wndclass.hInstance = hInstance;
	wndclass.hIcon = LoadIcon(NULL, IDI_APPLICATION);
	wndclass.hCursor = LoadCursor(NULL, IDC_ARROW);
	wndclass.hbrBackground = (HBRUSH)GetStockObject(WHITE_BRUSH);
	wndclass.lpszMenuName = NULL;

	wndclass.lpszClassName = szAppName;
	wndclass.hIconSm = LoadIcon(NULL, IDI_APPLICATION);

	RegisterClassEx(&wndclass);

	hwnd = CreateWindow(szAppName,        // window class name
		"Program",   // window caption
		WS_OVERLAPPEDWINDOW,     // window style
		CW_USEDEFAULT,           // initial x position
		CW_USEDEFAULT,           // initial y position
		CW_USEDEFAULT,           // initial x size
		CW_USEDEFAULT,           // initial y size
		NULL,                    // parent window handle
		NULL,                    // window menu handle
		hInstance,               // program instance handle
		NULL);		   // creation parameters

	ShowWindow(hwnd, iCmdShow);
	UpdateWindow(hwnd);
	hwndDlg = (HWND)0;
	while (GetMessage(&msg, 0, 0, 0))
	{
		if (hwndDlg == 0 || !IsDialogMessage(hwndDlg, &msg))
		{
			TranslateMessage(&msg);
			DispatchMessage(&msg);
		}
	}
	return msg.wParam;
}

BOOL CALLBACK lpfnDlgProc(HWND hdlg, UINT msg, WPARAM wParam, LPARAM lParam)
{
	switch (msg)
	{ // Инициализация диалоговой панели
	case WM_INITDIALOG:
	{} return TRUE;
	case WM_COMMAND:
	{
		switch (wParam)
		{ // Еще один вариант завершения  работы немодального диалогового окна
		// Первый – в обработчике «WM_DESTROY»
		// Сообщение от кнопки "OK"
		case IDOK:
		{//{ сохранение необходимых данных ...}
		 // Сообщение от «Cancel» – завершение без сохранения
		}
		case IDCANCEL:
		{ // Уничтожаем диалоговую панель
			DestroyWindow(hdlg);
			return TRUE;
		}
		}
	}
	}
	return FALSE;
};


LRESULT CALLBACK WndProc(HWND hwnd, UINT iMsg, WPARAM wParam, LPARAM lParam)
{
	HDC         hdc;	// дескриптор контекста устройства
	PAINTSTRUCT ps;	//Структура PAINTSTRUCT содержит информацию для приложения. Эта информация может быть использована для отрисовки клиентской области окна, которым владеет приложение.
	RECT        rect; //Структура RECT определяет координаты верхнего левого и нижнего правого углов прямоугольника.
	static HMENU hMenu, hPopupMenu1;
	static HWND  hwndedit,
		hbutton; //кнопка
	static int		  len;  //длина 
	static char* str;  //данные, вводимые пользователем
	static bool able1 = false;
	static bool able2 = false;
	static bool able3 = false;
	static bool able4 = false;
	static bool able5 = false;
	static int x1, x2, y1, y2, x3, y3;
	int a = 0;
	switch (iMsg)
	{
	case WM_PAINT:
	{
		hdc = BeginPaint(hwnd, &ps);
		GetClientRect(hwnd, &rect);
		if (able1)
		{
			int nHeight = -MulDiv(20, GetDeviceCaps(hdc, LOGPIXELSY), 72);
			HFONT hfont = CreateFontA(nHeight, 0, 0, 0, 100, false, false, false, RUSSIAN_CHARSET, OUT_CHARACTER_PRECIS, CLIP_CHARACTER_PRECIS, DEFAULT_QUALITY, FF_DONTCARE, TEXT("Times New Roman"));
			SelectObject(hdc, hfont);
			SetTextColor(hdc, RGB(10, 10, 110));
			DrawText(hdc, "Белоушко Степан Игоревич", -1, &rect, DT_SINGLELINE | DT_CENTER | DT_TOP);
			DeleteObject(hfont);
			able1 = false;
		}
		TextOut(hdc, 20, 10, "Введи размер:", 13);
		if (able2)
		{
			TextOut(hdc, 20, 100, (LPSTR)str, len);
			HRGN rg;
			HBRUSH hbr;
			hbr = CreateHatchBrush(HS_HORIZONTAL, RGB(232, 17, 35)); //создание кисти - зелёные горизонатьльные полоски:
			HPEN hPen; //объявляем объект-перо
			hPen = CreatePen(PS_SOLID, 3, RGB(15, 124, 16)); //создаём перо синего цвета
			SelectObject(hdc, hPen); //но в одним момент времени может быть только 1 
			RoundRect(hdc, 300, 100, 500, 300, 500, 500);

			SelectObject(hdc, hbr);
			POINT star[11] = { {400,100}, {429,160}, {491,160}, {449,216}, {459,281}, {400,260}, {341,281}, {353,216}, {308,160}, {372,160}, {400,100} };
			rg = CreatePolygonRgn(star, 11, 1);
			FillRgn(hdc, rg, hbr);

			SelectObject(hdc, GetSysColorBrush(COLOR_WINDOW));
			POINT points[5] = { {371,160}, {429,160}, {449,216}, {400,260}, {353,216} };
			Polygon(hdc, points, 5);

			DeleteObject((HGDIOBJ)(HPEN)(hPen)); //удаление пера
			DeleteObject((HGDIOBJ)(HBRUSH)(hbr));  //удаление кисти
			able2 = false;
		}
		if (able3 || able4)
		{
			HPEN hPen;
			hPen = CreatePen(PS_SOLID, 2, RGB(15, 124, 16));
			SelectObject(hdc, hPen);
			if (able3)
			{
				MoveToEx(hdc, x1, y1, NULL);
				LineTo(hdc, x3, y3);
			}
			if (able4)
			{
				Rectangle(hdc, x1, y1, x2, y2);
				able4 = false;
			}
		}
		if (able5)
		{
			TextOut(hdc, 0, 0, "Выберите нужную операцию во вкладке \"Операции\" или введите размер A для фигуры в поле справа.", strlen("Выберите нужную операцию во вкладке \"Операции\" или введите размер A для фигуры в поле справа."));
			able5 = false;
		}
		EndPaint(hwnd, &ps);
		return 0;
	}
	
	case WM_CREATE:
	{
		hMenu = CreateMenu();
		hPopupMenu1 = CreatePopupMenu();
		AppendMenu(hMenu, MF_STRING, 1009, "File");
		AppendMenu(hMenu, MF_STRING|MF_POPUP, (UINT)hPopupMenu1, "Operations");
		AppendMenu(hPopupMenu1, MF_STRING, 1001, "Вывод текста в окно");
		AppendMenu(hPopupMenu1, MF_STRING, 1002, "Вывод графического изображения");
		AppendMenu(hPopupMenu1, MF_STRING, 1003, "Модальное окно с картинкой");
		SetMenu(hwnd, hMenu);
		hbutton = CreateWindow("button", "Press me", WS_CHILD | WS_VISIBLE, 20, 70, 70, 22, hwnd, (HMENU)1005, ((LPCREATESTRUCT)lParam)->hInstance, NULL);
		hwndedit = CreateWindow("edit", NULL, WS_CHILD | WS_VISIBLE | WS_BORDER | ES_LEFT, 20, 50, 200, 22, hwnd, (HMENU)2, ((LPCREATESTRUCT)lParam)->hInstance, NULL);
		return 0;
	}

	case WM_SETFOCUS:
		SetFocus(hwndedit);//установка курсора в текстовое поле
		return 0;

	case WM_SIZE:

		return 0;

	case WM_COMMAND:
	{
		if (LOWORD(wParam) == 1001)
		{
			CheckMenuItem(hPopupMenu1, 1002, MF_BYCOMMAND | MF_UNCHECKED);
			CheckMenuItem(hPopupMenu1, 1001, MF_BYCOMMAND | MF_CHECKED);
			able1 = true;
			InvalidateRect(hwnd, NULL, TRUE);
		}
		if (LOWORD(wParam) == 1002)
		{
			CheckMenuItem(hPopupMenu1, 1001, MF_BYCOMMAND | MF_UNCHECKED);
			CheckMenuItem(hPopupMenu1, 1002, MF_BYCOMMAND | MF_CHECKED);
			len = GetWindowTextLength(hwndedit);
			str = new char[len + 1];
			GetWindowText(hwndedit, str, len + 1);
			if (strspn(str, "0123456789") == strlen(str))
			{
				a = atoi(str);
				able2 = true;
				InvalidateRect(hwnd, NULL, TRUE);
			}
			else MessageBox(hwnd, "Неверный формат ввода размера A.", "Ошибка.", NULL);
		}
		if(LOWORD(wParam) == 1005)
		{
			len = GetWindowTextLength(hwndedit);
			str = new char[len + 1];
			GetWindowText(hwndedit, str, len + 1);
			if (strspn(str, "0123456789") == strlen(str))
			{
				a = atoi(str);
				able2 = true;
				InvalidateRect(hwnd, NULL, TRUE);
			}
			else MessageBox(hwnd, "Неверный формат ввода размера A.", "Ошибка.", NULL);
		}
		if (LOWORD(wParam) == 1003)
		{
			hwndDlg = CreateDialog(GetModuleHandle(NULL), MAKEINTRESOURCE(1003),
				hwnd, (DLGPROC)lpfnDlgProc);
			ShowWindow(hwndDlg, SW_SHOW);
		}
		return 0;
	}

	case WM_LBUTTONDOWN:
	{
		x1 = LOWORD(lParam);
		y1 = HIWORD(lParam);
		able3 = true;
	}
	return 0;

	case WM_MOUSEMOVE:
	{
		if (able3)
		{
			x3 = LOWORD(lParam);
			y3 = HIWORD(lParam);
			if (x1 != x3 || y1 != y3)
				InvalidateRect(hwnd, NULL, TRUE);
		}
	}
	return 0;

	case WM_LBUTTONUP:
	{
		able3 = false;
		x2 = LOWORD(lParam);
		y2 = HIWORD(lParam);
		if (x1 != x2 || y1 != y2)
		{
			able4 = true;
		}
		else able5 = true;
		InvalidateRect(hwnd, NULL, TRUE);
	}
	return 0;

	case WM_DESTROY: 
		DestroyWindow(hwndDlg);// разрушение немодального окна
		PostQuitMessage(0);
		return 0;
	}
	return DefWindowProc(hwnd, iMsg, wParam, lParam);
}