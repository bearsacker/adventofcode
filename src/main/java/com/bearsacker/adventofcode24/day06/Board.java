package com.bearsacker.adventofcode24.day06;

import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.toRadians;

import java.util.HashSet;
import java.util.List;

import com.bearsacker.utils.Vec2;

public class Board {

    private boolean[][] cells;

    private int width;

    private int height;

    private Vec2 start;

    public Board(List<String> raw) {
        width = raw.get(0).length();
        height = raw.size();
        cells = new boolean[width][height];
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width; i++) {
                switch (raw.get(j).charAt(i)) {
                case '#':
                    cells[i][j] = true;
                    break;
                case '^':
                    start = new Vec2(i, j);
                default:
                    cells[i][j] = false;
                }
            }
        }
    }

    private Vec2 nextPosition(Vec2 position, int angle) {
        Vec2 next = new Vec2(position);
        next.y -= (int) (cos(toRadians(angle)));
        next.x += (int) (sin(toRadians(angle)));

        return next;
    }

    private boolean isInBounds(Vec2 position) {
        return position.x >= 0 && position.y >= 0 && position.x < width && position.y < height;
    }

    public int simulate() {
        Vec2 position = new Vec2(start);
        int angle = 0;
        HashSet<Vec2> positions = new HashSet<>();

        while (isInBounds(position)) {
            Vec2 next = nextPosition(position, angle);
            if (isInBounds(next) && cells[next.x][next.y]) {
                angle = (angle + 90) % 360;
            } else {
                position = next;
            }

            if (isInBounds(position)) {
                positions.add(position);
            }
        }

        return positions.size();
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Vec2 getStart() {
        return start;
    }

    public void setStart(Vec2 start) {
        this.start = start;
    }

}
