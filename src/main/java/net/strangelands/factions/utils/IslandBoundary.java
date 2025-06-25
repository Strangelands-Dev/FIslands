package net.strangelands.factions.utils;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.world.block.BaseBlock;
import com.sk89q.worldedit.world.block.BlockState;
import com.sk89q.worldedit.world.block.BlockType;
import com.sk89q.worldedit.world.block.BlockTypes;
import com.sk89q.worldguard.protection.regions.ProtectedCuboidRegion;
import net.strangelands.factions.Factions;
import org.bukkit.Location;
import org.bukkit.World;

import java.util.HashSet;
import java.util.Objects;

@SuppressWarnings("deprecation")
public class IslandBoundary {

    CuboidRegion region;
    World world;

    public IslandBoundary(World world, BlockVector3 c1, BlockVector3 c2) {
        c1.withY(256);
        c2.withY(256);
        region = new CuboidRegion(new BukkitWorld(world), c1, c2);
        this.world = world;
    }

    public IslandBoundary(World world, Location c1, Location c2) {
        region = new CuboidRegion(new BukkitWorld(world)
                , BlockVector3.at(c1.getX(), 256, c1.getZ())
                , BlockVector3.at(c2.getX(), 256, c2.getZ()));
        this.world = world;
    }

    public IslandBoundary(World world, ProtectedCuboidRegion pCRegion) {
        region = new CuboidRegion(new BukkitWorld(world)
                , pCRegion.getMinimumPoint().withY(256), pCRegion.getMaximumPoint().withY(256));
        this.world = world;
    }

    public void updateBoundary() {
        try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(new BukkitWorld(world), -1)) {
            HashSet<BaseBlock> blockSet = new HashSet<>();
            blockSet.add(idToBaseBlock(BlockTypes.RED_STAINED_GLASS));
            editSession.replaceBlocks(region, blockSet, idToBaseBlock(BlockTypes.AIR));
            editSession.makeCuboidWalls(region, idToBaseBlock(BlockTypes.RED_STAINED_GLASS));
        } catch (MaxChangedBlocksException ex) {
            ex.printStackTrace();
        }
    }

    public void destroyBoundary() {
        try (EditSession editSession = WorldEdit.getInstance().getEditSessionFactory().getEditSession(new BukkitWorld(world), -1)) {
            HashSet<BaseBlock> blockSet = new HashSet<>();
            blockSet.add(idToBaseBlock(BlockTypes.RED_STAINED_GLASS));
            editSession.replaceBlocks(region, blockSet, idToBaseBlock(BlockTypes.AIR));
        } catch (MaxChangedBlocksException ex) {
            ex.printStackTrace();
        }
    }

    public BaseBlock idToBaseBlock(BlockType type) {
        BlockType boundry = Objects.requireNonNull(type);
        BlockState state = boundry.getDefaultState();
        return state.toBaseBlock();
    }
}
