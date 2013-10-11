package com.cory.Skills;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.sucy.skill.SkillAPI;
import com.sucy.skill.api.SkillPlugin;

import de.ntcomputer.minecraft.controllablemobs.api.ControllableMob;

public class SkillPack extends JavaPlugin implements SkillPlugin  {

	private SkillAPI skillAPI;
	private HashMap<Player,ControllableMob<Zombie>> zombieMap;
  
    @Override
    public void onEnable() {

        getLogger().info("Enabled");
        this.zombieMap = new HashMap<Player,ControllableMob<Zombie>>();
    }
    
    @Override
    public void onDisable() {
		for(ControllableMob<Zombie> controlledZombie: this.zombieMap.values()) {
			controlledZombie.getActions().die();
		}
		this.zombieMap.clear();
		this.zombieMap = null;
    }
    
	private void cleanZombie(Player owner) {
		if(this.zombieMap.containsKey(owner)) {
			ControllableMob<Zombie> controlledZombie = this.zombieMap.get(owner);
			controlledZombie.getActions().die();
			this.zombieMap.remove(owner);
		}
	}
	
	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		this.cleanZombie(event.getPlayer());
	}

	@Override
	public void registerSkills(SkillAPI skillAPI) {
		// TODO Auto-generated method stub
		this.skillAPI = skillAPI;
		
		skillAPI.addSkills(
			new Dash(this),
			new Stomp(this),
			new Might(this),
			new Animate(this)
				);
		getLogger().info("Registered Skills");
	}

	@Override
	public void registerClasses(SkillAPI skillAPI) {
		// TODO Auto-generated method stub
		
		skillAPI.addClasses(
				new Warrior(),
				new Necro()
				);
		getLogger().info("Registered Classes");
	}
}