package com.metal.kit.vapor.Manager;

/**
 * The Game Configuration Manager.
 * This class contains all the constants and parameters necessary for configuring the game.
 *
 * These constants are used throughout the game to ensure consistent configuration and avoid duplication of values
 * in the code. It also allows for easy modification of game settings without needing to change the source code in multiple places.
 */
public class GameConfigManager {

    /** The width of the game window. */
    public static final int WINDOW_WIDTH = 1536;

    /** The height of the game window. */
    public static final int WINDOW_HEIGHT = 864;

    /** Camera smoothing factor for smooth movement. */
    public static final float CAMERA_SMOOTHING = 0.1f;

    /** The sound played in the main menu. */
    public static final String MENU_SOUND = "sound/intro.mp3";

    /** The sound played when an item is picked up. */
    public static final String PICK_SOUND = "sound/pick.mp3";

    /** Background texture for the main menu. */
    public static final String MENU_BACKGROUND_TEXTURE = "menu/background_menu.png";

    /** Background texture for the options menu. */
    public static final String MENUOPTIONS_BACKGROUND_TEXTURE = "menu/background_options.png";

    /** Background texture shown when the game is over. */
    public static final String GAMEOVER_BACKGROUND_TEXTURE = "menu/background_gameover.png";

    /** Background texture shown when the game progresses to the next level. */
    public static final String NEXTLEVEL_BACKGROUND_TEXTURE = "menu/background_nextlevel.png";

    /** Background texture for top 10 scores. */
    public static final String TOPSCORES_BACKGROUND_TEXTURE = "menu/background_topscores.png";

    /** Speed of the player (in pixels per second). */
    public static final float PLAYER_SPEED = 200f;

    /** Range of the player's attack. */
    public static final float PLAYER_ATTACK_RANGE = 80f;

    /** Texture representing the guard's idle state. */
    public static final String GUARD_TEXTURE_IDLE = "guard-idle.png";

    /** Texture representing the guard's running state. */
    public static final String GUARD_TEXTURE_RUNNING = "guard-running.png";

    /** Speed of the guard's movement. */
    public static final float GUARD_SPEED = 40f;

    /** Texture representing the dog's idle state. */
    public static final String DOG_TEXTURE_IDLE = "dog-idle.png";

    /** Texture representing the dog's running state. */
    public static final String DOG_TEXTURE_RUNNING = "dog-running.png";

    /** Speed of the dog's movement. */
    public static final float DOG_SPEED = 40f;

    /** Sound played when the dog is alerted. */
    public static final String DOG_ALERT_SOUND = "sound/dog.mp3";

    /** Texture of the CCTV camera facing left. */
    public static final String CCTV_TEXTURE_LEFT = "cctv_left.png";

    /** Texture of the CCTV camera facing right. */
    public static final String CCTV_TEXTURE_RIGHT = "cctv_right.png";

    /** Sound played when the CCTV camera is triggered. */
    public static final String CCTV_ALERT_SOUND = "sound/alert.mp3";

    /** Speed of the CCTV camera's movement. */
    public static final float CCTV_SPEED = 0f;

    /** Orientation of the CCTV camera (facing left). */
    public static final float CCTV_SENS_LEFT = -100;

    /** Orientation of the CCTV camera (facing right). */
    public static final float CCTV_SENS_RIGHT = 10;

    /** Texture of the tower guard facing right. */
    public static final String TOWER_GUARD_TEXTURE_RIGHT = "tower-guard-pointing-right.png";

    /** Texture of the tower guard facing left. */
    public static final String TOWER_GUARD_TEXTURE_LEFT = "tower-guard-pointing-left.png";

    /** Orientation of the tower guard's detection (facing left). */
    public static final float TOWER_GUARD_SENS_LEFT = -180;

    /** Orientation of the tower guard's detection (facing right). */
    public static final float TOWER_GUARD_SENS_RIGHT = 120;

    /** Texture of the tank's running state */
    public static final String TANK_MOUNTED_RUNNING_TEXTURE = "tank-mounted-running.png";

    /** Texture of the hunter-turret's running state */
    public static final String HUNTER_TURRET_RUNING_TEXTURE = "hunter-turret-mounted-running.png";

    /** Texture of the "gun" weapon. */
    public static final String GUN_TEXTURE = "gun.png";

    /** Sound played when the gun is fired. */
    public static final String GUN_SOUND = "sound/gun.mp3";

    /** Texture of the "grenade" weapon. */
    public static final String GRENADE_TEXTURE = "grenade.png";

    /** Texture of the "knife" weapon. */
    public static final String KNIFE_TEXTURE = "knife.png";
}
