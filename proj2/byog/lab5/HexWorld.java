package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final long SEED = 2873199;
    private static final Random RANDOM = new Random(SEED);
    public static int cal_ROW_width(int size, int i){
        int effective_i = i;
        if(i >= size) {
            effective_i = 2 * size - i - 1;
        }
        return size + 2 * effective_i;
    }

    public static int Leftmost(int size, int i){
        int effective_i = i;
        if (i >= size) {
            effective_i = 2 * size - i - 1;
        }
        return - effective_i;
    }

    public static void addRow(TETile[][] world, int width, Position p, TETile t) {
        for (int xi = 0; xi < width; xi ++) {
            int Xcorrd = p.x + xi;
            int Ycorrd = p.y;
            world[Xcorrd][Ycorrd] = TETile.colorVariant(t, 32, 32, 32, RANDOM);
        }
    }


    public static void addHexagon(TETile[][] world, int size, Position p, TETile t){
        if (size < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2");
        }
        for (int yi = 0; yi < 2 * size; yi ++) {
            int width = cal_ROW_width(size, yi);
            int l = Leftmost(size, yi);
            int Xstar = p.x + l;
            int Ystar = p.y + yi;
            Position pstar = new Position(Xstar, Ystar);
            addRow(world, width, pstar, t);

        }
    }

    public static void draw_columns(TETile[][] world, int size, Position zerPos, TETile t, int colums, int num){
        int hex_num = num - Math.abs(colums);
        int x0 = zerPos.x + colums * (2 * size - 1);
        int y0 = zerPos.y + Math.abs(colums) * size;
        for (int i = 0; i < hex_num; i ++) {
            Position p = new Position(x0, y0 + 2 * size * i);
            addHexagon(world, size, p, t);
        }
    }

    // 按gitbook上要求绘制
    public static void draw_test(TETile[][] world){
        int num = 5;
        Position zeroPos = new Position(25, 25);
        int size = 3;
        TETile[] TETilelist = {Tileset.FLOWER, Tileset.MOUNTAIN, Tileset.SAND};

        for (int col = 0; col < 3; col ++){
            draw_columns(world, size, zeroPos, TETilelist[col], col, num);
            draw_columns(world, size, zeroPos, TETilelist[col], -col, num);
        }
    }

    public static void main(String args[]) {
        int width = 60;
        int height = 60;
        TERenderer ter = new TERenderer();
        ter.initialize(width, height);

        // initialize tiles
        TETile[][] world = new TETile[width][height];

        for (int x = 0; x < width; x += 1) {
            for (int y = 0; y < height; y += 1) {
                world[x][y] = Tileset.NOTHING;
            }
        }
//        TETile t = Tileset.WATER;
//        Position pp = new Position(20,20);
//        addHexagon(world, 3, pp, t);
        draw_test(world);
        ter.renderFrame(world);

    }

}
