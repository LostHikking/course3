package ru.omsu.imit.course3.first.lab1;

public class Rectangle {
	private Point leftTop;
	private Point rightBottom;

	@Override
	public String toString() {
		return leftTop + "|" + rightBottom + "\n";
	}

	public Rectangle(Point leftTop, Point rightBottom) {
		this.leftTop = leftTop;
		this.rightBottom = rightBottom;
	}

	public Point getLeftTop() {
		return leftTop;
	}

	public void setLeftTop(Point leftTop) {
		this.leftTop = leftTop;
	}

	public Point getRightBottom() {
		return rightBottom;
	}

	public void setRightBottom(Point rightBottom) {
		this.rightBottom = rightBottom;
	}

	public static class Point{

		@Override
		public String toString() {
			return x + " " + y;
		}

		private int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public int getX() {
			return x;
		}

		public void setX(int x) {
			this.x = x;
		}

		public int getY() {
			return y;
		}

		public void setY(int y) {
			this.y = y;
		}
	}
}
