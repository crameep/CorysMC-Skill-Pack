package com.cory.Skills;

import java.util.HashMap;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;

import com.sucy.skill.api.skill.ClassSkill;
import com.sucy.skill.api.skill.SkillAttribute;
import com.sucy.skill.api.skill.SkillShot;
import com.sucy.skill.api.skill.SkillType;

import de.ntcomputer.minecraft.controllablemobs.api.ControllableMob;
import de.ntcomputer.minecraft.controllablemobs.api.ControllableMobs;


/**
 *  Animates Dead Things
 */
public class Animate extends ClassSkill implements SkillShot {

    public static final String NAME = "Animate Dead";
    private static final String
            MOB = "Zombie",
    		DURATION = "Duration",
    		AMOUNT = "Amount";
    
    private HashMap<Player,ControllableMob<Zombie>> zombieMap;
    		

    private final SkillPack plugin;

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public Animate(SkillPack plugin) {
        super(NAME, SkillType.SKILL_SHOT, Material.ROTTEN_FLESH, 5);
        this.plugin = plugin;

        description.add("Raises zombies");
        description.add("from the dead");
        description.add("to fight for you");
        
        setAttribute(SkillAttribute.COOLDOWN, 20, -3);
        setAttribute(SkillAttribute.COST, 2, 0);
        setAttribute(SkillAttribute.LEVEL, 10, 2);
        setAttribute(SkillAttribute.MANA, 5, 0);
        setAttribute(MOB, 1, 0);
        setAttribute(DURATION, 5, 1);
        setAttribute(AMOUNT, 1, 1);
    }

    /**
     * Animate Dead
     *
     * @param player player casting the skill
     * @param i      skill level
     * @return       true
     */
    @Override
    public boolean cast(Player player, int level) {

        int mob = getAttribute(MOB, level);
        int duration = getAttribute(DURATION, level);
        int amount = getAttribute(AMOUNT, level);
       
        boolean worked = true;
        
        this.spawnZombie(player, player.getLocation().add(0, 1, 0));


        return worked;
    }
    
	private void spawnZombie(Player owner, Location spawnLocation) {
		Zombie zombie = spawnLocation.getWorld().spawn(spawnLocation, Zombie.class);
		ControllableMob<Zombie> controlledZombie = ControllableMobs.assign(zombie, true);
		this.zombieMap.put(owner, controlledZombie);
	}
}