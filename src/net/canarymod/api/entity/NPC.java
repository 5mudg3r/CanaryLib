package net.canarymod.api.entity;

import net.canarymod.api.world.position.Location;
import net.canarymod.api.world.position.Vector3D;

/*
 * 
 */
public interface Npc extends EntityLiving {
    @Override
    public String getName();
    
    /**
     * Set the name of this Npc
     * @param name
     */
    public void setName(String name);
    
    /**
     * Teleport to the given coords within the Vector3D
     * @param vec3d
     */
    public void teleportTo(Vector3D vec3d);
    
    /**
     * Teleport to the given location
     * @param loc
     */
    public void teleportTo(Location loc);
    
    /**
     * Teleport to the given points
     * @param x
     * @param y
     * @param z
     */
    public void teleportTo(int x, int y, int z);
    
    /**
     * Despawn this npc and return its reference for further processing.
     * This does not DELETE this entity, it stays there, it just isn't spawned
     * @return
     */
    public Npc despawn();
    
    /**
     * Kill this NPC - that means: Actually removing this Entity from memory and the server
     */
    @Override
    public void kill();
    
    /**
     * Repeatedly called to manage visibility for this entity for single players
     */
    public void tick();
}
