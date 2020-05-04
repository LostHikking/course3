package ru.omsu.imit.course3.first.lab1;

import java.util.Objects;

public class Rectangle {
	private Point leftTop;
	private Point rightBottom;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Rectangle rectangle = (Rectangle) o;

		if (!Objects.equals(leftTop, rectangle.leftTop)) return false;
		return Objects.equals(rightBottom, rectangle.rightBottom);
	}

	@Override
	public int hashCode() {
		int result = leftTop != null ? leftTop.hashCode() : 0;
		result = 31 * result + (rightBottom != null ? rightBottom.hashCode() : 0);
		return result;
	}

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
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;

			Point point = (Point) o;

			if (x != point.x) return false;
			return y == point.y;
		}

		@Override
		public int hashCode() {
			int result = x;
			result = 31 * result + y;
			return result;
		}

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
