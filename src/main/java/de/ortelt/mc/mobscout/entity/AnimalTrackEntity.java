package de.ortelt.mc.mobscout.entity;

import de.ortelt.mc.mobscout.Mobscout;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class AnimalTrackEntity extends Entity {

    public static RegistryObject<EntityType<?>> register(DeferredRegister<EntityType<?>> deferredRegister) {
        return deferredRegister.register("animal_track", () -> EntityType.Builder.of(AnimalTrackEntity::new, MobCategory.MISC)
                .fireImmune()
                .noSummon()
                .sized(1.0f, 0.0f)
                .setShouldReceiveVelocityUpdates(false)
                .build(Mobscout.MODID + ":animal_track"));
    }

    public AnimalTrackEntity(EntityType<?> type, Level level) {
        super(type, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
    }
}
