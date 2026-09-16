package com.metal.kit.vapor.Manager;


import com.badlogic.gdx.utils.Array;
import com.metal.kit.vapor.characters.Cctv;
import com.metal.kit.vapor.characters.Dog;
import com.metal.kit.vapor.characters.Enemy;
import com.metal.kit.vapor.characters.Guard;

/**
 * The enemy manager that handles the management of enemies in the game.
 * This class is responsible for creating, updating, and managing the resources of the enemies.
 * It stores the enemies in a list and allows access to them during gameplay.
 *
 * The `EnemyManager` manages different types of enemies including guards, CCTV cameras, dogs, and tower guards.
 */
public class EnemyManager {
    /**
     * The current level of the player.
     */
    private int playerLevel;

    /**
     * List of active enemies in the game.
     * This list stores all the currently active enemies.
     */
    private Array<Enemy> enemies;

    /**
     * Constructor for the EnemyManager class.
     * Initializes the enemy list and calls the method to create and position enemies in the game.
     */
    public EnemyManager(int playerLevel) {
        this.enemies = new Array<>();
        this.playerLevel = playerLevel;
        if (playerLevel == 1) {
            createEnemyLevelOne();
        } else if (playerLevel == 2) {
            createEnemyLevelTwo();
        }

    }

    /**
     * Creates and positions various types of enemies (guards, CCTV cameras, dogs, tower guards) in the game.
     * This method initializes multiple enemies with their positions, textures, and behaviors.
     */
    private void createEnemyLevelOne() {

        // GUARDS
        Enemy guard1 = new Guard("guard1", 10, 750, 1, 5, GameConfigManager.GUARD_SPEED, 10, 750, 800, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard1.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard1);

        Enemy guard2 = new Guard("guard2", 900, 650, 1, 5, GameConfigManager.GUARD_SPEED, 900, 650, 800, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard2.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard2);

        Enemy guard3 = new Guard("guard3", 1219, 275, 1, 5, GameConfigManager.GUARD_SPEED, 1219, 275, 600, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard3.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard3);

        Enemy guard4 = new Guard("guard4", 545, 0, 1, 5, GameConfigManager.GUARD_SPEED, 545, 0, 900, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard4.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard4);

        Enemy guard5 = new Guard("guard5", 1427, 472, 1, 5, GameConfigManager.GUARD_SPEED, 1427, 472, 470, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard5.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard5);

        Enemy guard6 = new Guard("guard6", 72, 472, 1, 5, GameConfigManager.GUARD_SPEED, 72, 472, 520, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard6.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard6);

        Enemy guard7 = new Guard("guard7", 1169, 222, 1, 5, GameConfigManager.GUARD_SPEED, 1169, 222, 300, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard7.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard7);

        Enemy guard8 = new Guard("guard8", 1404, 889, 1, 5, GameConfigManager.GUARD_SPEED, 1404, 889, 450, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard8.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard8);

        Enemy guard9 = new Guard("guard9", 42, 1539, 1, 5, GameConfigManager.GUARD_SPEED, 42, 1539, 300, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard9.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard9);

        Enemy guard10 = new Guard("guard10", 1043, 1619, 1, 5, GameConfigManager.GUARD_SPEED, 1043, 1619, 300, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard10.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard10);

        Enemy guard11 = new Guard("guard11", 72, 1130, 1, 5, GameConfigManager.GUARD_SPEED, 72, 1130, 700, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard11.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard11);

        Enemy guard12 = new Guard("guard12", 120, 1758, 1, 5, GameConfigManager.GUARD_SPEED, 120, 1758, 800, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard12.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard12);

        Enemy guard13 = new Guard("guard13", 1050, 1758, 1, 5, GameConfigManager.GUARD_SPEED, 1050, 1758, 800, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard13.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard13);

        Enemy guard14 = new Guard("guard14", 100, 1840, 1, 5, GameConfigManager.GUARD_SPEED, 100, 1840, 1600, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard14.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard14);

        Enemy guard15 = new Guard("guard15", 890, 884, 1, 5, GameConfigManager.GUARD_SPEED, 890, 884, 500, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard15.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard15);

        // CCTVs
        // -100 sens gauche ou 10 a droite
        Enemy cctv1 = new Cctv("cctv1", 250, 270, 1, 0, GameConfigManager.CCTV_SPEED, 250, 270, 100, 100, 100, 150,GameConfigManager.CCTV_SENS_LEFT);
        cctv1.initTexture(GameConfigManager.CCTV_TEXTURE_LEFT, GameConfigManager.CCTV_TEXTURE_LEFT, 6);
        enemies.add(cctv1);

        Enemy cctv2 = new Cctv("cctv2", 820, 270, 1, 0, GameConfigManager.CCTV_SPEED, 250, 570, 100, 100, 100, 150,GameConfigManager.CCTV_SENS_RIGHT);
        cctv2.initTexture(GameConfigManager.CCTV_TEXTURE_RIGHT, GameConfigManager.CCTV_TEXTURE_RIGHT, 6);
        enemies.add(cctv2);

        Enemy cctv3 = new Cctv("cctv3", 566, 399, 1, 0, GameConfigManager.CCTV_SPEED, 576, 409, 100, 100, 100, 150,GameConfigManager.CCTV_SENS_RIGHT);
        cctv3.initTexture(GameConfigManager.CCTV_TEXTURE_RIGHT, GameConfigManager.CCTV_TEXTURE_RIGHT, 6);
        enemies.add(cctv3);

        Enemy cctv4 = new Cctv("cctv4", 879, 399, 1, 0, GameConfigManager.CCTV_SPEED, 859, 409, 100, 100, 100, 150,GameConfigManager.CCTV_SENS_LEFT);
        cctv4.initTexture(GameConfigManager.CCTV_TEXTURE_LEFT, GameConfigManager.CCTV_TEXTURE_LEFT, 6);
        enemies.add(cctv4);

        Enemy cctv5 = new Cctv("cctv5", 853, 1456, 1, 0, GameConfigManager.CCTV_SPEED, 833, 1476, 100, 100, 100, 150,GameConfigManager.CCTV_SENS_LEFT);
        cctv5.initTexture(GameConfigManager.CCTV_TEXTURE_LEFT, GameConfigManager.CCTV_TEXTURE_LEFT, 6);
        enemies.add(cctv5);

        Enemy cctv6 = new Cctv("cctv6", 1717, 1016, 1, 0, GameConfigManager.CCTV_SPEED, 1727, 1026, 100, 100, 100, 150,GameConfigManager.CCTV_SENS_RIGHT);
        cctv6.initTexture(GameConfigManager.CCTV_TEXTURE_RIGHT, GameConfigManager.CCTV_TEXTURE_RIGHT, 6);
        enemies.add(cctv6);

        Enemy cctv7 = new Cctv("cctv2", 1332, 400, 1, 0, GameConfigManager.CCTV_SPEED, 1332, 400, 100, 100, 100, 150,GameConfigManager.CCTV_SENS_RIGHT);
        cctv7.initTexture(GameConfigManager.CCTV_TEXTURE_RIGHT, GameConfigManager.CCTV_TEXTURE_RIGHT, 6);
        enemies.add(cctv7);


        // DOGS
        Enemy dog1 = new Dog("dog1", 950, 756, 1, 2, GameConfigManager.DOG_SPEED, 900, 650, 800, 100, 200, 150, GameConfigManager.CCTV_SENS_LEFT);
        dog1.initTexture(GameConfigManager.DOG_TEXTURE_IDLE, GameConfigManager.DOG_TEXTURE_RUNNING, 6);
        enemies.add(dog1);

        Enemy dog2 = new Dog("dog2", 1417, 750, 1, 2, GameConfigManager.DOG_SPEED, 1417, 750, 490, 100, 200, 150, GameConfigManager.CCTV_SENS_LEFT);
        dog2.initTexture(GameConfigManager.DOG_TEXTURE_IDLE, GameConfigManager.DOG_TEXTURE_RUNNING, 6);
        enemies.add(dog2);

        Enemy dog3 = new Dog("dog3", 310, 90, 1, 2, GameConfigManager.DOG_SPEED, 310, 90, 550, 100, 200, 150, GameConfigManager.CCTV_SENS_RIGHT);
        dog3.initTexture(GameConfigManager.DOG_TEXTURE_IDLE, GameConfigManager.DOG_TEXTURE_RUNNING, 6);
        enemies.add(dog3);

        Enemy dog4 = new Dog("dog4", 1073, 1619, 1, 2, GameConfigManager.DOG_SPEED, 1073, 1619, 280, 100, 200, 150, GameConfigManager.CCTV_SENS_RIGHT);
        dog4.initTexture(GameConfigManager.DOG_TEXTURE_IDLE, GameConfigManager.DOG_TEXTURE_RUNNING, 6);
        enemies.add(dog4);



        // TOWER GUARDS
        Enemy towerGuard1 = new Cctv("towerGuard1", 525, 433, 1, 1, GameConfigManager.CCTV_SPEED, 10, 750, 100, 100, 200, 150, GameConfigManager.TOWER_GUARD_SENS_LEFT);
        towerGuard1.initTexture(GameConfigManager.TOWER_GUARD_TEXTURE_LEFT, GameConfigManager.TOWER_GUARD_TEXTURE_LEFT, 6);
        enemies.add(towerGuard1);

        Enemy towerGuard2 = new Cctv("towerGuard2", 915, 433, 1, 1, GameConfigManager.CCTV_SPEED, 10, 750, 800, 100, 200, 150, GameConfigManager.TOWER_GUARD_SENS_RIGHT);
        towerGuard2.initTexture(GameConfigManager.TOWER_GUARD_TEXTURE_RIGHT, GameConfigManager.TOWER_GUARD_TEXTURE_LEFT, 6);
        enemies.add(towerGuard2);

        Enemy towerGuard3 = new Cctv("towerGuard3", 1293, 433, 1, 1, GameConfigManager.CCTV_SPEED, 10, 750, 800, 100, 200, 150, GameConfigManager.TOWER_GUARD_SENS_LEFT);
        towerGuard3.initTexture(GameConfigManager.TOWER_GUARD_TEXTURE_LEFT, GameConfigManager.TOWER_GUARD_TEXTURE_LEFT, 6);
        enemies.add(towerGuard3);

        Enemy towerGuard4 = new Cctv("towerGuard4", 883, 1489, 1, 1, GameConfigManager.CCTV_SPEED, 10, 750, 800, 100, 200, 150, GameConfigManager.CCTV_SENS_RIGHT);
        towerGuard4.initTexture(GameConfigManager.TOWER_GUARD_TEXTURE_RIGHT, GameConfigManager.TOWER_GUARD_TEXTURE_LEFT, 6);
        enemies.add(towerGuard4);

        Enemy towerGuard5 = new Cctv("towerGuard5", 1806, 113, 1, 1, GameConfigManager.CCTV_SPEED, 10, 750, 800, 100, 200, 150, GameConfigManager.TOWER_GUARD_SENS_LEFT);
        towerGuard5.initTexture(GameConfigManager.TOWER_GUARD_TEXTURE_LEFT, GameConfigManager.TOWER_GUARD_TEXTURE_LEFT, 6);
        enemies.add(towerGuard5);

        Enemy towerGuard6 = new Cctv("towerGuard6", 1678, 1041, 1, 1, GameConfigManager.CCTV_SPEED, 10, 750, 800, 100, 200, 150, GameConfigManager.TOWER_GUARD_SENS_LEFT);
        towerGuard6.initTexture(GameConfigManager.TOWER_GUARD_TEXTURE_LEFT, GameConfigManager.TOWER_GUARD_TEXTURE_LEFT, 6);
        enemies.add(towerGuard6);

        Enemy towerGuard7 = new Cctv("towerGuard7", 1837, 1586, 1, 1, GameConfigManager.CCTV_SPEED, 10, 750, 800, 100, 200, 150, GameConfigManager.TOWER_GUARD_SENS_LEFT);
        towerGuard7.initTexture(GameConfigManager.TOWER_GUARD_TEXTURE_LEFT, GameConfigManager.TOWER_GUARD_TEXTURE_LEFT, 6);
        enemies.add(towerGuard7);

        Enemy towerGuard8 = new Cctv("towerGuard8", 880, 817, 1, 1, GameConfigManager.CCTV_SPEED, 10, 750, 800, 100, 200, 150, GameConfigManager.TOWER_GUARD_SENS_LEFT);
        towerGuard8.initTexture(GameConfigManager.TOWER_GUARD_TEXTURE_LEFT, GameConfigManager.TOWER_GUARD_TEXTURE_LEFT, 6);
        enemies.add(towerGuard8);

    }

    /**
     * Creates and positions various types of enemies (guards, CCTV cameras, dogs, tower guards) in the game.
     * This method initializes multiple enemies with their positions, textures, and behaviors.
     */
    private void createEnemyLevelTwo() {

        // GUARDS
        Enemy guard1 = new Guard("guard1", 930, 1209, 1, 5, GameConfigManager.GUARD_SPEED, 930, 1209, 400, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard1.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard1);

        Enemy guard2 = new Guard("guard2", 300, 1099, 1, 5, GameConfigManager.GUARD_SPEED, 300, 1099, 300, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard2.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard2);

        Enemy guard3 = new Guard("guard3", 1404, 918, 1, 5, GameConfigManager.GUARD_SPEED, 1404, 918, 200, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard3.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard3);

        Enemy guard4 = new Guard("guard4", 470, 1648, 1, 5, GameConfigManager.GUARD_SPEED, 470  , 1648, 350, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard4.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard4);

        Enemy guard5 = new Guard("guard5", 1471, 1648, 1, 5, GameConfigManager.GUARD_SPEED, 1471, 1648, 150, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard5.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard5);

        Enemy guard6 = new Guard("guard6", 980, 1457, 1, 5, GameConfigManager.GUARD_SPEED, 980, 1457, 800, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard6.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard6);

        Enemy guard7 = new Guard("guard7", 757, 710, 1, 5, GameConfigManager.GUARD_SPEED, 757, 710, 350, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard7.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard7);

        Enemy guard8 = new Guard("guard8", 1267, 581, 1, 5, GameConfigManager.GUARD_SPEED, 1267, 581, 500, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard8.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard8);

        Enemy guard9 = new Guard("guard9", 1340, 394, 1, 5, GameConfigManager.GUARD_SPEED, 1340, 394, 400, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard9.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard9);

        Enemy guard10 = new Guard("guard10", 817, 224, 1, 5, GameConfigManager.GUARD_SPEED, 817, 224, 800, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard10.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard10);

        Enemy guard11 = new Guard("guard11", 1347, 60, 1, 5, GameConfigManager.GUARD_SPEED, 1347, 60, 150, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard11.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard11);

        Enemy guard12 = new Guard("guard12", 360, 163, 1, 5, GameConfigManager.GUARD_SPEED, 400, 213, 250, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard12.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard12);

        Enemy guard13 = new Guard("guard13", 580, 1760, 1, 5, GameConfigManager.GUARD_SPEED, 580, 1760, 300, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard13.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard13);

        Enemy guard14 = new Guard("guard14", 1237, 1731, 1, 5, GameConfigManager.GUARD_SPEED, 1237, 1731, 800, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard14.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard14);

        Enemy guard15 = new Guard("guard15", 400, 410, 1, 5, GameConfigManager.GUARD_SPEED, 400, 410, 400, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard15.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard15);

        Enemy guard16 = new Guard("guard16", 204, 900, 1, 5, GameConfigManager.GUARD_SPEED, 204, 900, 600, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard16.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard16);

        Enemy guard17 = new Guard("guard17", 960, 960, 1, 5, GameConfigManager.GUARD_SPEED, 960, 960, 300, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard17.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard17);

        Enemy guard18 = new Guard("guard18", 100, 1340, 1, 5, GameConfigManager.GUARD_SPEED, 100, 1340, 500, 100, 200,150,GameConfigManager.CCTV_SENS_RIGHT);
        guard18.initTexture(GameConfigManager.GUARD_TEXTURE_IDLE, GameConfigManager.GUARD_TEXTURE_RUNNING, 6);
        enemies.add(guard18);

        // TOWER GUARDS
        Enemy towerGuard1 = new Cctv("towerGuard1", 260, 1810, 1, 1, GameConfigManager.CCTV_SPEED, 310, 1810, 100, 100, 200, 150, GameConfigManager.TOWER_GUARD_SENS_LEFT);
        towerGuard1.initTexture(GameConfigManager.TOWER_GUARD_TEXTURE_LEFT, GameConfigManager.TOWER_GUARD_TEXTURE_LEFT, 6);
        enemies.add(towerGuard1);

        Enemy towerGuard1Bis = new Cctv("towerGuard1Bis", 285, 1810, 1, 1, GameConfigManager.CCTV_SPEED, 310, 1810, 100, 100, 200, 150, GameConfigManager.TOWER_GUARD_SENS_RIGHT);
        towerGuard1Bis.initTexture(GameConfigManager.TOWER_GUARD_TEXTURE_RIGHT, GameConfigManager.TOWER_GUARD_TEXTURE_RIGHT, 6);
        enemies.add(towerGuard1Bis);

        Enemy towerGuard2 = new Cctv("towerGuard2", 912, 1810, 1, 1, GameConfigManager.CCTV_SPEED, 862, 1810, 100, 100, 200, 150, GameConfigManager.TOWER_GUARD_SENS_LEFT);
        towerGuard2.initTexture(GameConfigManager.TOWER_GUARD_TEXTURE_LEFT, GameConfigManager.TOWER_GUARD_TEXTURE_LEFT, 6);
        enemies.add(towerGuard2);

        Enemy towerGuard3 = new Cctv("towerGuard3", 1542, 1810, 1, 1, GameConfigManager.CCTV_SPEED, 1502, 1810, 100, 100, 200, 150, GameConfigManager.TOWER_GUARD_SENS_LEFT);
        towerGuard3.initTexture(GameConfigManager.TOWER_GUARD_TEXTURE_LEFT, GameConfigManager.TOWER_GUARD_TEXTURE_LEFT, 6);
        enemies.add(towerGuard3);

        Enemy towerGuard3Bis = new Cctv("towerGuard3Bis", 1567, 1810, 1, 1, GameConfigManager.CCTV_SPEED, 1502, 1810, 100, 100, 200, 150, GameConfigManager.TOWER_GUARD_SENS_RIGHT);
        towerGuard3Bis.initTexture(GameConfigManager.TOWER_GUARD_TEXTURE_RIGHT, GameConfigManager.TOWER_GUARD_TEXTURE_RIGHT, 6);
        enemies.add(towerGuard3Bis);

        Enemy towerGuard4 = new Cctv("towerGuard4", 82, 1552, 1, 1, GameConfigManager.CCTV_SPEED, 82, 1552, 100, 100, 200, 150, GameConfigManager.TOWER_GUARD_SENS_RIGHT);
        towerGuard4.initTexture(GameConfigManager.TOWER_GUARD_TEXTURE_RIGHT, GameConfigManager.TOWER_GUARD_TEXTURE_RIGHT, 6);
        enemies.add(towerGuard4);

        Enemy towerGuard5 = new Cctv("towerGuard5", 710, 1485, 1, 1, GameConfigManager.CCTV_SPEED, 710, 1485, 100, 100, 200, 150, GameConfigManager.TOWER_GUARD_SENS_LEFT);
        towerGuard5.initTexture(GameConfigManager.TOWER_GUARD_TEXTURE_LEFT, GameConfigManager.TOWER_GUARD_TEXTURE_LEFT, 6);
        enemies.add(towerGuard5);

        Enemy towerGuard5Bis = new Cctv("towerGuard5Bis", 735, 1485, 1, 1, GameConfigManager.CCTV_SPEED, 735, 1485, 100, 100, 200, 150, GameConfigManager.TOWER_GUARD_SENS_RIGHT);
        towerGuard5Bis.initTexture(GameConfigManager.TOWER_GUARD_TEXTURE_RIGHT, GameConfigManager.TOWER_GUARD_TEXTURE_RIGHT, 6);
        enemies.add(towerGuard5Bis);

        Enemy towerGuard6 = new Cctv("towerGuard6", 1217, 1491, 1, 1, GameConfigManager.CCTV_SPEED, 1217, 1491, 100, 100, 200, 150, GameConfigManager.TOWER_GUARD_SENS_LEFT);
        towerGuard6.initTexture(GameConfigManager.TOWER_GUARD_TEXTURE_LEFT, GameConfigManager.TOWER_GUARD_TEXTURE_LEFT, 6);
        enemies.add(towerGuard6);

        Enemy towerGuard6Bis = new Cctv("towerGuard6Bis", 1242, 1491, 1, 1, GameConfigManager.CCTV_SPEED, 1242, 1491, 100, 100, 200, 150, GameConfigManager.TOWER_GUARD_SENS_RIGHT);
        towerGuard6Bis.initTexture(GameConfigManager.TOWER_GUARD_TEXTURE_RIGHT, GameConfigManager.TOWER_GUARD_TEXTURE_RIGHT, 6);
        enemies.add(towerGuard6Bis);

        Enemy towerGuard7 = new Cctv("towerGuard7", 1800, 1585, 1, 1, GameConfigManager.CCTV_SPEED, 1800, 1585, 100, 100, 200, 150, GameConfigManager.TOWER_GUARD_SENS_LEFT);
        towerGuard7.initTexture(GameConfigManager.TOWER_GUARD_TEXTURE_LEFT, GameConfigManager.TOWER_GUARD_TEXTURE_LEFT, 6);
        enemies.add(towerGuard7);

        Enemy towerGuard7Bis = new Cctv("towerGuard7Bis", 1827, 1585, 1, 1, GameConfigManager.CCTV_SPEED, 1827, 1585, 100, 100, 200, 150, GameConfigManager.TOWER_GUARD_SENS_RIGHT);
        towerGuard7Bis.initTexture(GameConfigManager.TOWER_GUARD_TEXTURE_RIGHT, GameConfigManager.TOWER_GUARD_TEXTURE_RIGHT, 6);
        enemies.add(towerGuard7Bis);

        Enemy towerGuard8 = new Cctv("towerGuard8", 579, 625, 1, 1, GameConfigManager.CCTV_SPEED, 579, 625, 100, 100, 200, 150, GameConfigManager.TOWER_GUARD_SENS_LEFT);
        towerGuard8.initTexture(GameConfigManager.TOWER_GUARD_TEXTURE_LEFT, GameConfigManager.TOWER_GUARD_TEXTURE_LEFT, 6);
        enemies.add(towerGuard8);

        Enemy towerGuard8Bis = new Cctv("towerGuard8Bis", 604, 625, 1, 1, GameConfigManager.CCTV_SPEED, 604, 625, 100, 100, 200, 150, GameConfigManager.TOWER_GUARD_SENS_RIGHT);
        towerGuard8Bis.initTexture(GameConfigManager.TOWER_GUARD_TEXTURE_RIGHT, GameConfigManager.TOWER_GUARD_TEXTURE_RIGHT, 6);
        enemies.add(towerGuard8Bis);

        Enemy towerGuard9 = new Cctv("towerGuard9", 1850, 695, 1, 1, GameConfigManager.CCTV_SPEED, 1850, 695, 100, 100, 200, 150, GameConfigManager.TOWER_GUARD_SENS_LEFT);
        towerGuard9.initTexture(GameConfigManager.TOWER_GUARD_TEXTURE_LEFT, GameConfigManager.TOWER_GUARD_TEXTURE_LEFT, 6);
        enemies.add(towerGuard9);

    }

    /**
     * Retrieves the list of all created enemies.
     *
     * @return The list of active enemies in the game.
     */
    public Array<Enemy> getEnemies() {
        return enemies;
    }

    /**
     * Releases the resources allocated for the enemies.
     * This method should be called when the enemies are no longer needed, to clean up resources.
     */
    public void dispose() {
        for (Enemy enemy : enemies) {
            enemy.dispose();
        }
        enemies.clear();
    }
}
