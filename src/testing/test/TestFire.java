package testing.test;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import engine.Game;
import engine.SpriteGroup;
import engine.definitions.ConstantMoverDefinition;
import engine.definitions.DirectionalFirerDefinition;
import engine.definitions.ModuleDefinition;
import engine.definitions.SpriteDefinition;
import engine.definitions.TrackingFirerDefinition;
import engine.profile.Profile;
import engine.rendering.GameGridConfigNonScaling;
import engine.sprite.ISprite;
import gameplayer.GamePlayer;
import util.Coordinate;
import util.TimeDuration;


/**
 * Test package to test firing modules
 * 
 * @author Dhrumil Timko
 *
 */

public class TestFire {

    private SpriteDefinition myProjectile;
    private List<SpriteDefinition> myTargets;
    private List<ISprite> myTowerList;
    private ConstantMoverDefinition myMover;
    private SpriteDefinition myTower;
    private DirectionalFirerDefinition myDirectionalFirer;
    private TrackingFirerDefinition myTrackingFirer;
    private Game myGame;
    private SpriteDefinition myEnemy;

    @Before
    public void setUp () {
        myGame = new Game(new GameGridConfigNonScaling(GamePlayer.PREFWIDTH, GamePlayer.PREFHEIGHT));
        createMover();
        createProjectile();
        createTower();
        myEnemy = createEnemy();
        createDirectionalFirer();
        createTrackingFirer();
        myTowerList = new ArrayList<ISprite>();
    }

    @After
    public void tearDown () {
        myGame = new Game(new GameGridConfigNonScaling(GamePlayer.PREFWIDTH, GamePlayer.PREFHEIGHT));
        myTowerList.clear();
        Iterator<ModuleDefinition> modules = myTower.getModuleDefinitions().iterator();

        while (modules.hasNext())
            myTower.remove(modules.next());
    }

    private void createMover () {
        myMover = new ConstantMoverDefinition();
        myMover.setSpeed(100);
    }

    private void createProjectile () {
        myProjectile = new SpriteDefinition();
        myProjectile.setMovementDefinition(myMover);
    }

    private SpriteDefinition createEnemy () {
        myEnemy = new SpriteDefinition();
        myEnemy.setLocation(new Coordinate(100,100));
        Profile testprofile = new Profile("Test");
        myEnemy.setProfile(testprofile);

        myTargets = new ArrayList<SpriteDefinition>();
        myTargets.add(myEnemy);
        return myEnemy;
    }

    private void createDirectionalFirer () {
        myDirectionalFirer = new DirectionalFirerDefinition();
        myDirectionalFirer.setProjectileDefinition(myProjectile);
        myDirectionalFirer.setWaitTime(1000);
        myDirectionalFirer.setGame(myGame);
        myDirectionalFirer.setAngle(120);
    }

    private void createTower () {
        myTower = new SpriteDefinition();
        myTower.setLocation(new Coordinate(0,0));
    }

    private void createTrackingFirer () {
        myTrackingFirer = new TrackingFirerDefinition();
        myTrackingFirer.setGame(myGame);
        myTrackingFirer.setProjectileDefinition(myProjectile);
        myTrackingFirer.setWaitTime(100);
        SpriteGroup myGroup = new SpriteGroup(myTargets);

        myTrackingFirer.setTargets(myGroup);
        myTower.addModule(myTrackingFirer);
    }

    @Test
    public void testFire () {
        myTower.addModule(myTrackingFirer);
        ISprite tower = myTower.create();
        myTowerList.add(tower);
        myGame.add(tower);

        tower.update(new TimeDuration(1000));
        assertEquals(1, myGame.getLevelManager().getCurrentLevel().getSprites().size());
    }

    @Test
    public void testDirectionalFirer () {
        myTower.addModule(myDirectionalFirer);
        myTowerList = new ArrayList<ISprite>();
        int bullets = 100;
        for (int i = 0; i < bullets; i++) {
            ISprite tower = myTower.create();
            myTowerList.add(tower);
            myGame.add(tower);
        }

        myTowerList.stream().forEach(sprite -> sprite.update(new TimeDuration(100000)));
        assertEquals(bullets, myGame.getLevelManager().getCurrentLevel().getSprites().size());
    }

    @Test
    public void testTrackingFirer () {
        myTower.addModule(myTrackingFirer);
        myTowerList = new ArrayList<ISprite>();
        int bullets = 50;
        for (int i = 0; i < bullets; i++) {
            ISprite tower = myTower.create();
            myTowerList.add(tower);
            myGame.add(tower);
        }

        myTowerList.stream().forEach(sprite -> sprite.update(new TimeDuration(100000)));
        assertEquals(bullets, myGame.getLevelManager().getCurrentLevel().getSprites().size());
    }

}
