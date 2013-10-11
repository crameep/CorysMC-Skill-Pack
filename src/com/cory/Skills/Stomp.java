package com.cory.Skills;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import com.sucy.skill.api.skill.ClassSkill;
import com.sucy.skill.api.skill.SkillAttribute;
import com.sucy.skill.api.skill.SkillShot;
import com.sucy.skill.api.skill.SkillType;


/**
 * Stompes forward
 */
public class Stomp extends ClassSkill implements SkillShot {

    public static final String NAME = "Stomp";
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
    public Stomp(SkillPack plugin) {
        super(NAME, SkillType.SKILL_SHOT, Material.IRON_BOOTS, 5);
        this.plugin = plugin;

        description.add("Leap into the air");
        description.add("and knockback enemies");

        setAttribute(SkillAttribute.COOLDOWN, 20, -3);
        setAttribute(SkillAttribute.COST, 2, 0);
        setAttribute(SkillAttribute.LEVEL, 10, 2);
        setAttribute(SkillAttribute.MANA, 5, 0);
        setAttribute(SPEED, 6, 0);
        setAttribute(DAMAGE, 3, 1);
        setAttribute(RADIUS, 2, 1);
    }

    /**
     * Stompes forward
     *
     * @param player player casting the skill
     * @param i      skill level
     * @return       true
     */
    @Override
    public boolean cast(Player player, int level) {

        int speed = getAttribute(SPEED, level);
        int radius = getAttribute(RADIUS, level);
        List<Entity> list = player.getNearbyEntities(radius, radius, radius);
        int damage = getAttribute(DAMAGE, level);
        boolean worked = false;
        Vector v = player.getLocation().getDirection().multiply(-1).setY(1);
        player.setVelocity(v);
               
        for (Entity entity : list) {
            if (entity instanceof LivingEntity) {
                LivingEntity target = (LivingEntity)entity;

                // Make sure the target can be attacked
                //if (target instanceof Player && !Protection.canPVP(player, (Player)target))
                //    continue;

                //target.damage(damage, player);
                Vector velocity1 = target.getLocation().subtract(player.getLocation()).toVector();
                velocity1.multiply(speed / velocity1.length());
                velocity1.setY(velocity1.getY() / 5 + 0.5);
                target.setVelocity(velocity1);
                worked = true;
            }
        }

        return worked;
    }
}