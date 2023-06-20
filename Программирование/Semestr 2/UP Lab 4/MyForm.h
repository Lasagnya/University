#pragma once

namespace Project3 {

	using namespace System;
	using namespace System::ComponentModel;
	using namespace System::Collections;
	using namespace System::Windows::Forms;
	using namespace System::Data;
	using namespace System::Drawing;

	/// <summary>
	/// Сводка для MyForm
	/// </summary>
	public ref class MyForm : public System::Windows::Forms::Form
	{
	public:
		MyForm(void)
		{
			InitializeComponent();
			//
			//TODO: добавьте код конструктора
			//
		}

	protected:
		/// <summary>
		/// Освободить все используемые ресурсы.
		/// </summary>
		~MyForm()
		{
			if (components)
			{
				delete components;
			}
		}
	private: System::Windows::Forms::ListBox^ list;
	protected:

	protected:



	private: System::Windows::Forms::Button^ button1;
	private: System::Windows::Forms::TextBox^ txt1;
	private: System::Windows::Forms::TextBox^ txt2;
	private: System::Windows::Forms::PictureBox^ picture;






	private:
		/// <summary>
		/// Обязательная переменная конструктора.
		/// </summary>
		System::ComponentModel::Container ^components;

#pragma region Windows Form Designer generated code
		/// <summary>
		/// Требуемый метод для поддержки конструктора — не изменяйте 
		/// содержимое этого метода с помощью редактора кода.
		/// </summary>
		void InitializeComponent(void)
		{
			System::ComponentModel::ComponentResourceManager^ resources = (gcnew System::ComponentModel::ComponentResourceManager(MyForm::typeid));
			this->list = (gcnew System::Windows::Forms::ListBox());
			this->button1 = (gcnew System::Windows::Forms::Button());
			this->txt1 = (gcnew System::Windows::Forms::TextBox());
			this->txt2 = (gcnew System::Windows::Forms::TextBox());
			this->picture = (gcnew System::Windows::Forms::PictureBox());
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->picture))->BeginInit();
			this->SuspendLayout();
			// 
			// list
			// 
			this->list->FormattingEnabled = true;
			this->list->ItemHeight = 21;
			this->list->Items->AddRange(gcnew cli::array< System::Object^  >(3) { L"1", L"2", L"3" });
			this->list->Location = System::Drawing::Point(12, 17);
			this->list->Name = L"list";
			this->list->Size = System::Drawing::Size(147, 193);
			this->list->TabIndex = 3;
			this->list->SelectedIndexChanged += gcnew System::EventHandler(this, &MyForm::listBox1_SelectedIndexChanged);
			// 
			// button1
			// 
			this->button1->Location = System::Drawing::Point(191, 168);
			this->button1->Name = L"button1";
			this->button1->Size = System::Drawing::Size(116, 57);
			this->button1->TabIndex = 4;
			this->button1->Text = L"button1";
			this->button1->UseVisualStyleBackColor = true;
			this->button1->Click += gcnew System::EventHandler(this, &MyForm::button1_Click);
			// 
			// txt1
			// 
			this->txt1->Location = System::Drawing::Point(174, 17);
			this->txt1->Name = L"txt1";
			this->txt1->ReadOnly = true;
			this->txt1->Size = System::Drawing::Size(75, 29);
			this->txt1->TabIndex = 6;
			this->txt1->TextChanged += gcnew System::EventHandler(this, &MyForm::textBox1_TextChanged);
			// 
			// txt2
			// 
			this->txt2->Location = System::Drawing::Point(244, 17);
			this->txt2->Name = L"txt2";
			this->txt2->ReadOnly = true;
			this->txt2->Size = System::Drawing::Size(75, 29);
			this->txt2->TabIndex = 7;
			// 
			// picture
			// 
			this->picture->Image = (cli::safe_cast<System::Drawing::Image^>(resources->GetObject(L"picture.Image")));
			this->picture->Location = System::Drawing::Point(174, 48);
			this->picture->Name = L"picture";
			this->picture->Size = System::Drawing::Size(145, 114);
			this->picture->SizeMode = System::Windows::Forms::PictureBoxSizeMode::Zoom;
			this->picture->TabIndex = 8;
			this->picture->TabStop = false;
			this->picture->Click += gcnew System::EventHandler(this, &MyForm::picture_Click);
			// 
			// MyForm
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(9, 21);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->ClientSize = System::Drawing::Size(331, 237);
			this->Controls->Add(this->picture);
			this->Controls->Add(this->txt2);
			this->Controls->Add(this->txt1);
			this->Controls->Add(this->button1);
			this->Controls->Add(this->list);
			this->Font = (gcnew System::Drawing::Font(L"Segoe UI", 9.75F, System::Drawing::FontStyle::Regular, System::Drawing::GraphicsUnit::Point,
				static_cast<System::Byte>(204)));
			this->Margin = System::Windows::Forms::Padding(3, 4, 3, 4);
			this->Name = L"MyForm";
			this->Text = L"MyForm";
			this->Load += gcnew System::EventHandler(this, &MyForm::MyForm_Load);
			(cli::safe_cast<System::ComponentModel::ISupportInitialize^>(this->picture))->EndInit();
			this->ResumeLayout(false);
			this->PerformLayout();

		}
#pragma endregion
	private: System::Void listBox1_SelectedIndexChanged(System::Object^ sender, System::EventArgs^ e) {
		if (this->list->SelectedItem == "1")
		{
			this->txt1->BackColor = System::Drawing::Color::Black;
			txt2->Text = "32*32";
		}
		if (this->list->SelectedItem == "2")
		{
			this->txt1->BackColor = System::Drawing::Color::Green;
			txt2->Text = "64*64";
		}
		if (this->list->SelectedItem == "3")
		{
			this->txt1->BackColor = System::Drawing::Color::Red;
			txt2->Text = "48*48";
		}
	}
	private: System::Void textBox1_TextChanged(System::Object^ sender, System::EventArgs^ e) {
	}
private: System::Void button1_Click(System::Object^ sender, System::EventArgs^ e) {
	if(list->SelectedItem=="1")
		//MessageBox::Show("", "Картинка", MessageBoxButtons::OK, MessageBoxIcon::Warning);
		picture->Image = System::Drawing::Bitmap::FromFile("C:\\Users\\Stepa\\Desktop\\1.bmp");
	if (list->SelectedItem == "2")
		picture->Image = System::Drawing::Bitmap::FromFile("C:\\Users\\Stepa\\Desktop\\2.bmp");
	if (list->SelectedItem == "3")
		picture->Image = System::Drawing::Bitmap::FromFile("C:\\Users\\Stepa\\Desktop\\3.bmp");

}
private: System::Void picture_Click(System::Object^ sender, System::EventArgs^ e) {
	
}
private: System::Void MyForm_Load(System::Object^ sender, System::EventArgs^ e) {
}
};
}
