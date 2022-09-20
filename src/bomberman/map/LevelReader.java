package bomberman.map;

import java.io.InputStream;
import java.util.Scanner;

public class LevelReader {
    private TileMap map;

    public LevelReader(TileMap map) {
        this.map = map;
    }

    public void read(int level) {
        InputStream fileStream = getClass().getResourceAsStream("/levels/Level" + level + ".txt");
        assert fileStream != null;
        Scanner scanner = new Scanner(fileStream);
        scanner.nextInt();
        int rows = scanner.nextInt();
        int cols = scanner.nextInt();
        map.setCols(cols);
        map.setRows(rows);
        scanner.nextLine();

        for (int i = 0; i < rows; ++i) {
            String line = scanner.nextLine();
            for (int j = 0; j < cols; ++j) {
                char symbol = line.charAt(j);
                if (symbol == '#') {
                    map.addWall(j, i);
                } else {
                    map.addGrass(j, i);
                }
            }
        }
    }
}
