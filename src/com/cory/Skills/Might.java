package com.cory.Skills;


import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.sucy.skill.api.skill.ClassSkill;
import com.sucy.skill.api.skill.PassiveSkill;
import com.sucy.skill.api.skill.SkillType;


/**
 * Mightes forward
 */
public class Might extends ClassSkill implements PassiveSkill {

    public static final String NAME = "Might";
    private static final String
            DURATION = "Duration",
    		REGEN = "Regen";
    		

    private final SkillPack plugin;

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public Might(SkillPack plugin) {
        super(NAME, SkillType.SKILL_SHOT, Material.LEATHER_BOOTS, 5);
        this.plugin = plugin;

        description.add("Might grants");
        description.add("food regen");

        setAttribute(DURATION, 3, 0);
        setAttribute(REGEN, 1, 1);
    }

    /**
     * Mightes forward
     *
     * @param player player casting the skill
     * @param i      skill level
     * @return       true
     */
    @Override
    public void onInitialize(Player player, int level) {

        int duration = getAttribute(DURATION, level);
        int regen = getAttribute(REGEN, level);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, duration * 20, regen / 5), true);
       
        
    }

	@Override
	public void onUpgrade(Player player, int level) {
        int duration = getAttribute(DURATION, level);
        int regen = getAttribute(REGEN, level);
        player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, duration * 20, regen / 5), true);
		
	}

	@Override
	public void stopEffects(Player player, int level) {
		// TODO Auto-generated method stub
		
	}
	
	@EventHandler
	public void onHit(EntityDamageByEntityEvent e) {
	    Entity damager = e.getDamager();
	    Entity victim = e.getEntity();
	 
	    Vector vVec = victim.getLocation().toVector().clone();
	    Vector dVec = damager.getLocation().toVector().clone();
	    // here we have normalized direction vector
	    Vector direction = vVec.subtract(dVec).normalize();
	 
	    final int POWER = 5;
	    // .add() moves vector slightly upwards and .multiply() makes it stronger
	    direction.add(new Vector(0.0D, POWER*0.2D, 0.0D)).multiply(POWER);
	    //now to apply velocity
	    victim.setVelocity(direction);
	}
}
