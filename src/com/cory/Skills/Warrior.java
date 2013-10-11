package com.cory.Skills;

import org.bukkit.ChatColor;

import com.sucy.skill.api.ClassAttribute;
import com.sucy.skill.api.CustomClass;

/**
 * Melee bulky fighter
 */
public class Warrior extends CustomClass {

    public static final String NAME = "Warrior";

    /**
     * Constructor
     */
    public Warrior() {

        // null for 2nd parameter means this is a starting class
        super(NAME, null, ChatColor.RED + NAME, 0, 0);

        // Class attributes
        setAttribute(ClassAttribute.HEALTH, 20, 2);
        setAttribute(ClassAttribute.MANA, 15, 1);

        // Default skills for a fighter
        // (Note: the x.NAME parameters are just the 
        // name strings of the skills that I have
        // a public static field for)
        addSkills(
                Dash.NAME,
                Stomp.NAME,
                Might.NAME
        );
    }
}