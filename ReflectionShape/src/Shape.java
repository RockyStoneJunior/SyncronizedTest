class Shape{
	public void print()
	{
		System.out.println(this);
	}
}

class Circle extends Shape{
	public static void main(String[] args) {
		Shape shape = new Circle();
		shape.print();
	}
}