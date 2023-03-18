package me.lucas.gamelib.utils;

public class Vector2D{

    private double x;
    private double y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }
    public int getXint() {
    	return (int) x;
    }

    public double getY() {
        return y;
    }
    public int getYint() {
    	return (int) y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Vector2D add(Vector2D other) {
        return new Vector2D(this.x + other.x, this.y + other.y);
    }

    public Vector2D subtract(Vector2D other) {
        return new Vector2D(this.x - other.x, this.y - other.y);
    }

    public Vector2D multiply(double scalar) {
        return new Vector2D(this.x * scalar, this.y * scalar);
    }

    public Vector2D divide(double scalar) {
        if (scalar == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        return new Vector2D(this.x / scalar, this.y / scalar);
    }

    public double dot(Vector2D other) {
        return this.x * other.x + this.y * other.y;
    }

    public double magnitude() {
        return Math.sqrt(this.x * this.x + this.y * this.y);
    }

    public Vector2D normalize() {
        double mag = this.magnitude();
        if (mag == 0) {
            return new Vector2D(0, 0);
        }
        return new Vector2D(this.x / mag, this.y / mag);
    }

    public double angle(Vector2D other) {
        double dot = this.dot(other);
        double mag1 = this.magnitude();
        double mag2 = other.magnitude();
        if (mag1 == 0 || mag2 == 0) {
            throw new IllegalStateException("Cannot compute angle between zero-length vectors");
        }
        return Math.acos(dot / (mag1 * mag2));
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

	public Vector2D multiply(Vector2D vector) {
		return new Vector2D(this.x * vector.x, this.y * vector.y);
	}
	
	public Vector2D distance(Vector2D vector) {
		return new Vector2D(Math.abs(this.x - vector.x), Math.abs(this.y - vector.y));
	}
}
