class Robot {

    int w, h, perimeter;
    int pos;           // current position on perimeter
    boolean moved;     // to handle initial direction case

    public Robot(int width, int height) {
        this.w = width - 1;
        this.h = height - 1;
        this.perimeter = 2 * (w + h);
        this.pos = 0;
        this.moved = false;
    }

    public void step(int num) {
        if (perimeter == 0) return;
        pos = (pos + num) % perimeter;
        moved = true;
    }

    public int[] getPos() {
        if (pos <= w) return new int[]{pos, 0};
        if (pos <= w + h) return new int[]{w, pos - w};
        if (pos <= 2 * w + h) return new int[]{w - (pos - (w + h)), h};
        return new int[]{0, h - (pos - (2 * w + h))};
    }

    public String getDir() {
        if (!moved) return "East";

        if (pos == 0) return "South";
        if (pos <= w) return "East";
        if (pos <= w + h) return "North";
        if (pos <= 2 * w + h) return "West";
        return "South";
    }
}