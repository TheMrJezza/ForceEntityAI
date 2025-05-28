package dev.themrjezza.force_entity_ai;

import net.minecraft.world.entity.Mob;
import org.bukkit.craftbukkit.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ForceEntityAI extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    private void onChunkLoad(ChunkLoadEvent event) {
        for (final var entity : event.getChunk().getEntities()) {
            swapGoalSelector(entity);
        }
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    private void onEntitySpawn(EntitySpawnEvent event) {
        swapGoalSelector(event.getEntity());
    }

    private static void swapGoalSelector(Entity entity) {
        if (!(((CraftEntity) entity).getHandle() instanceof Mob mob)) {
            return;
        }

        if (mob.goalSelector instanceof ForcedAiGoalSelector) {
            return;
        }

        mob.goalSelector = new ForcedAiGoalSelector(mob.goalSelector);
    }
}
