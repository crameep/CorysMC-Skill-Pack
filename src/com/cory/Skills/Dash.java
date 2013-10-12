package com.cory.Skills;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.sucy.skill.api.skill.ClassSkill;
import com.sucy.skill.api.skill.SkillAttribute;
import com.sucy.skill.api.skill.SkillShot;
import com.sucy.skill.api.skill.SkillType;
import com.sucy.skill.api.util.Protection;


/**
 * Dashes forward
 */
public class Dash extends ClassSkill implements SkillShot {

    public static final String NAME = "Dash";
    private static final String
            SPEED = "Speed",
    		DAMAGE = "Damage",
    		RADIUS = "Radius";
    		

    private final SkillPack plugin;

    /**
     * Constructor
     *
     * @param plugin plugin reference
     */
    public Dash(SkillPack plugin) {
        super(NAME, SkillType.SKILL_SHOT, Material.LEATHER_BOOTS, 5);
        this.plugin = plugin;

        description.add("Dashes forward in");
        description.add("the faced direction");
        description.add("and damage and knockback");
        description.add("in a small radius.");

        setAttribute(SkillAttribute.COOLDOWN, 20, -3);
        setAttribute(SkillAttribute.COST, 2, 0);
        setAttribute(SkillAttribute.LEVEL, 10, 2);
        setAttribute(SkillAttribute.MANA, 5, 0);
        setAttribute(SPEED, 6, 0);
        setAttribute(DAMAGE, 3, 1);
        setAttribute(RADIUS, 0, 1);
    }

    /**
     * Dashes forward
     *
     * @param player player casting the skill
     * @param i      skill level
     * @return       true
     */
    @Override
    public boolean cast(final Player player, int level) {

        final int speed = getAttribute(SPEED, level);
        final int radius = getAttribute(RADIUS, level);
       
        final int damage = getAttribute(DAMAGE, level);
        boolean worked = false;
        Vector direction = player.getLocation().getDirection().normalize();
        Vector velocity = direction.multiply(speed / direction.length());
        velocity.setY(velocity.getY() / 5);
        player.setVelocity(velocity);
        Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
        	public void run() {
        	
                List<Entity> list = player.getNearbyEntities(radius, radius, radius);        
                for (Entity entity : list) {
                    if (entity instanceof LivingEntity) {
                        LivingEntity target = (LivingEntity)entity;

                        // Make sure the target can be attacked
                        if (target instanceof Player && !Protection.canAttack(player, (Player)target))
                            continue;

                        target.damage(damage, player);
                        Vector velocity1 = target.getLocation().subtract(player.getLocation()).toVector().normalize();
                        velocity1.multiply(speed / velocity1.length());
                        velocity1.setY(velocity1.getY() / 5 + 0.5);
                        target.setVelocity(velocity1);
                       
                    }
                }
        		
        	}
        }, 20 * 1);


        return true;
    }
}