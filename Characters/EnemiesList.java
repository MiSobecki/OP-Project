import java.util.ArrayList;

/**
 * List of available enemies to fight for the player
 * 
 * @author MiSobecki
 *
 */
public class EnemiesList extends ArrayList<Enemy> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 297540105218619951L;

	public EnemiesList(Character character) {
		add(new Enemy(100, 5, 0, 1000, "Skeleton", 0, 10, 1, 10, "skeleton.jpg"));
		add(new Enemy(100, 7, 0, 2000, "Demon", 1, 20, 2, 20, "demon.jpg"));
		add(new Enemy(100, 10, 2, 3000, "Ghost", 2, 30, 3, 30, "ghost.jpg"));

		setLvls(character);
	}

	/**
	 * Sets enemies unlocked if Character lvl is big enough
	 * 
	 * @param character
	 */
	private void setLvls(Character character) {

		for (int i = 0; i <= character.getArenaLvl(); i++) {
			Enemy temp = get(i);
			temp.setLocked(false);
			set(i, temp);
		}
	}

}
