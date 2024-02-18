package io.github.optijava.opt_carpet_addition.logger.blockUpdate;

import io.github.optijava.opt_carpet_addition.logger.AbstractLogger;
import io.github.optijava.opt_carpet_addition.logger.LoggerRegister;
import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;

import java.lang.reflect.Field;

public class BlockUpdateLogger extends AbstractLogger {
    public static final BlockUpdateLogger INSTANCE;

    static {
        try {
            INSTANCE = new BlockUpdateLogger(LoggerRegister.class.getField("__blockUpdate"), "blockUpdate", null, null, false);
        } catch (NoSuchFieldException e) {
            throw new Error("Failed to init BlockUpdateLogger.", e);
        }
    }

    protected BlockUpdateLogger(Field acceleratorField, String logName, String def, String[] options, boolean strictOptions) {
        super(acceleratorField, logName, def, options, strictOptions);
    }

    public void logBlockUpdate(Block updatingBlock, BlockPos updatingBlockPos, Block sourceBlock, BlockPos centreBlockPos) {
        // 在1.17.1中，ServerWorld.updateNeighbor方法如下：
        // public void updateNeighbor(BlockPos pos, Block sourceBlock, BlockPos neighborPos)
        //                                    ^^^^         ^^^^^^^^^              ^^^^^^^
        //                                  被更新方块     源方块（更新源）     由源方块指定的更新的中心方块

        // 在1.19.3中，SimpleNeighborUpdater.updateNeighbor方法如下：
        // public void updateNeighbor(BlockState state, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify)
        //                                        ^^^^           ^^^         ^^^^^^^^               ^^^^^             ^^^^
        //                                           这俩都是被更新的              源                    中心           不知道干什么的
        // 在1.19.3中，ChainRestrictedNeighborUpdater.enqueue方法如下：
        // private void enqueue(BlockPos pos, Entry entry)
        //                               ^^          ^^^
        //                             被更新的      一个封装

        // TODO: logs
    }
}
