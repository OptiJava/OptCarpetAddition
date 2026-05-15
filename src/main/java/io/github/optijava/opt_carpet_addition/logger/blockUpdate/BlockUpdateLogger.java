package io.github.optijava.opt_carpet_addition.logger.blockUpdate;

import carpet.utils.Messenger;
import io.github.optijava.opt_carpet_addition.OptCarpetAddition;
import io.github.optijava.opt_carpet_addition.logger.AbstractLogger;
import io.github.optijava.opt_carpet_addition.logger.LoggerRegister;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.Block;

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

    public void logBlockUpdate(BlockPos updatingBlockPos, Block updatingBlock, Block changedBlock) {
        // 以下是我在旧版本研究的内容：
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

        try {
            super.log(() -> new Component[] {
                        Messenger.c("w Updating BlockPos: [", "m " + updatingBlockPos.getX() + " " + updatingBlockPos.getY() + " " + updatingBlockPos.getZ(), "w " + "] Updating Block: ", "m " + updatingBlock.getName().getString(), "w " + " Source Block(before change): ", "m " + changedBlock.getName().getString())
                    }
            );
        } catch (Exception e) {
            OptCarpetAddition.LOGGER.error("Unexpected exception occurred when logging block update.", e);
        }
    }
}
