package de.ortelt.mc.mobscout.item;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

public class DecoyWhistle extends Item {
    public DecoyWhistle() {
        super(new Item.Properties().stacksTo(1));
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand p_41434_) {
        if(!level.isClientSide){
            player.sendSystemMessage(Component.literal("blowed the whistle"));
        }
        EntityType<?> entityType = EntityType.PIG;
        Entity entity = entityType.create(level);
        if (entity != null) {
            double x = player.getX();
            double y = player.getY();
            double z = player.getZ();
            entity.moveTo(x, y, z, level.random.nextFloat() * 360F, 0);
            level.addFreshEntity(entity);
            level.playSound(null, player.blockPosition(), SoundEvents.LLAMA_AMBIENT, SoundSource.PLAYERS, 1.0F, 1.0F);
        }

        return super.use(level, player, p_41434_);
    }
}
