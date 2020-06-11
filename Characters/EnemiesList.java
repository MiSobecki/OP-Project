import java.util.ArrayList;

public class EnemiesList extends ArrayList<Enemy> {

	public EnemiesList(Character character) {
		add(new Enemy(100, 5, 0, 1000, "Skeleton", 0, 10, 1, 10));
		add(new Enemy(100, 7, 0, 2000, "Demon", 1, 20, 2, 20));
		add(new Enemy(100, 10, 2, 3000, "Ghost", 2, 30, 3, 30));

		setLvls(character);
	}

	private void setLvls(Character character) {

		for (int i = 0; i <= character.getArenaLvl(); i++) {
			Enemy temp = get(i);
			temp.setLocked(false);
			set(i, temp);
		}
	}

}
