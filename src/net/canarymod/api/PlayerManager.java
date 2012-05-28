package net.canarymod.api;

import java.util.ArrayList;

import net.canarymod.api.entity.Player;

public interface PlayerManager {
    /**
     * Update a mounted moving player
     * @param player
     */
    public void updateMountedMovingPlayer(Player player);
    
    /**
     * Add the given player to this player manager
     * @param player
     */
    public void addPlayer(Player player);
    
    /**
     * Removre the given player from this player manager
     * @param player
     */
    public void removePlayer(Player player);
    
    /**
     * Get a list of all players that are managed by this player manager
     * @return
     */
    public ArrayList<Player> getManagedPlayers();
    
    /**
     * Mark a block at this position for updating for this playerManager
     * @param x
     * @param y
     * @param z
     */
    public void markBlockNeedsUpdate(int x, int y, int z);
}
