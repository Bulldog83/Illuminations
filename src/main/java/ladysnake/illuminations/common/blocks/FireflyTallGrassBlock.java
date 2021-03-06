package ladysnake.illuminations.common.blocks;

import ladysnake.illuminations.common.entities.FireflyEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.TallPlantBlock;
import net.minecraft.entity.Entity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.WorldView;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class FireflyTallGrassBlock extends TallPlantBlock {
    public static final int MIN_FIREFLIES = FireflyTallGrassBlock.MIN_FIREFLIES;
    public static final int MAX_FIREFLIES = FireflyTallGrassBlock.MAX_FIREFLIES;

    public FireflyTallGrassBlock(Settings block$Settings_1) {
        super(block$Settings_1);
    }

    @Override
    public boolean hasRandomTicks(BlockState blockState_1) {
        return true;
    }

    @Override
    public int getTickRate(WorldView worldView) {
        return 1;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (!world.isClient && !world.isDay()) {
            boolean arePlayersNear = world.isPlayerInRange((double) pos.getX(), (double) pos.getY(), (double) pos.getZ(), 32);
            List<FireflyEntity> firefliesInRadius = world.getEntities(FireflyEntity.class, new Box(pos).expand(32), e -> true);

            if (arePlayersNear) {
                int firefliesToSpawn = 0;
                if (firefliesInRadius.size() == 0) {
                    firefliesToSpawn = ThreadLocalRandom.current().nextInt(MIN_FIREFLIES, MAX_FIREFLIES + 1);
                } else if (firefliesInRadius.size() < MAX_FIREFLIES) {
                    firefliesToSpawn = 1;
                }
                for (int i = 0; i < firefliesToSpawn; i++) {
                    Entity firefly = new FireflyEntity(world, pos.getX(), pos.getY(), pos.getZ());
                    world.spawnEntity(firefly);
                }
            }
        }
    }
}
