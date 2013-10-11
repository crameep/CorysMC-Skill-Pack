package com.cory.Skills;

import org.bukkit.ChatColor;

import com.sucy.skill.api.ClassAttribute;
import com.sucy.skill.api.CustomClass;

/**
 * Ranged Control 
 */
public class Necro extends CustomClass {

    public static final String NAME = "Necro";

    /**
     * Constructor
     */
    public Necro() {

        // null for 2nd parameter means this is a starting class
        super(NAME, null, ChatColor.RED + NAME, 1, 40);

        // Class attributes
        setAttribute(ClassAttribute.HEALTH, 15, 2);
        setAttribute(ClassAttribute.MANA, 20, 1);

        // Default skills for a fighter
        // (Note: the x.NAME parameters are just the 
        // name strings of the skills that I have
        // a public static field for)
        addSkills(
                Animate.NAME
        );
    }
}