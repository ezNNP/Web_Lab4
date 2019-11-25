package ru.svg.entities;

import javax.persistence.*;

@Entity
@Table(schema = "s265077", name = "points")
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;

    private double x;
    private double y;
    private double r;
    private boolean in;
    private boolean correct;
    private int var;

    public Point() {

    }

    public Point(double x, double y, double r, boolean in, boolean correct, int var) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.in = in;
        this.correct = correct;
        this.var = var;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public boolean isIn() {
        return in;
    }

    public void setIn(boolean in) {
        this.in = in;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public int getVar() {
        return var;
    }

    public void setVar(int var) {
        this.var = var;
    }
}
