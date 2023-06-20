import java.io.Serializable;

public class Travel implements Serializable {
	private String name;
	private String from;
	private String in;
	private String length;
	private String date;

	public Travel() {
		name = "0";
		from = "0";
		in = "0";
		length = "0";
		date = "0";
	}

	@Override
	public String toString()
	{
		return name + " " + from + " " + in + " " + length + " " + date + " ";
	}

	public Travel(String name, String from, String in, String length, String date) {
		this.name = name;
		this.from = from;
		this.in = in;
		this.length = length;
		this.date = date;
	}

	public Travel(String str) {
		name = str.substring(0, 13);
		from = str.substring(14, 28);
		in = str.substring(29, 43);
		length = str.substring(44, 53);
		date = str.substring(54);
	}

	public void output() {
		System.out.format("%13s", name);
		System.out.format("%14s", from);
		System.out.format("%14s", in);
		System.out.format("%9s", length);
		System.out.format("%s", date);
		System.out.println();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getIn() {
		return in;
	}

	public void setIn(String in) {
		this.in = in;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
