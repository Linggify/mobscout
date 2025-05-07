package de.ortelt.mc.mobscout.entity;

import de.ortelt.mc.mobscout.Mobscout;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class AnimalTrackEntity extends Entity {

    public static final String ENTITY_ID = "animal_track";

    public static final EntityDataAccessor<Float> DATA_TRACK_SIZE = SynchedEntityData.defineId(AnimalTrackEntity.class, EntityDataSerializers.FLOAT);
    public static final EntityDataAccessor<String> DATA_ENTITY_TRACK_TARGET = SynchedEntityData.defineId(AnimalTrackEntity.class, EntityDataSerializers.STRING);
    public static final EntityDataAccessor<Float> DATA_Y_ROTATION = SynchedEntityData.defineId(AnimalTrackEntity.class, EntityDataSerializers.FLOAT);

    public static RegistryObject<EntityType<?>> register(DeferredRegister<EntityType<?>> deferredRegister) {
        return deferredRegister.register(ENTITY_ID, () -> EntityType.Builder.of(AnimalTrackEntity::new, MobCategory.MISC)
                .fireImmune()
                .noSummon()
                .sized(1.0f, 0.0f)
                .setShouldReceiveVelocityUpdates(false)
                .build(Mobscout.MODID + ":" + ENTITY_ID));
    }

    public AnimalTrackEntity(EntityType<?> type, Level level) {
        super(type, level);
    }

    public void setTrackSize(float trackSize) {
        this.getEntityData().set(DATA_TRACK_SIZE, trackSize);
    }

    public float getTrackSize() {
        return this.getEntityData().get(DATA_TRACK_SIZE);
    }

    public void setTrackTarget(ResourceLocation target) {
        this.getEntityData().set(DATA_ENTITY_TRACK_TARGET, target.toString());
    }

    public ResourceLocation getTrackTarget() {
        var rss = this.getEntityData().get(DATA_ENTITY_TRACK_TARGET);
        return ResourceLocation.parse(rss);
    }

    public void setVisualRotationYInDegrees(float yRot) {
        this.getEntityData().set(DATA_Y_ROTATION, yRot);
    }

    @Override
    public float getVisualRotationYInDegrees() {
        return this.getEntityData().get(DATA_Y_ROTATION);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(DATA_TRACK_SIZE, 1.0f);
        builder.define(DATA_ENTITY_TRACK_TARGET, Mobscout.MODID + ":" + ENTITY_ID);
        builder.define(DATA_Y_ROTATION, 0.0f);
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag tag) {
        this.getEntityData().set(DATA_TRACK_SIZE, tag.getFloat("Size"));
        this.getEntityData().set(DATA_ENTITY_TRACK_TARGET, tag.getString("Target"));
        this.getEntityData().set(DATA_Y_ROTATION, tag.getFloat("Rotation"));
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        tag.putFloat("Size", this.getEntityData().get(DATA_TRACK_SIZE));
        tag.putString("Target", this.getEntityData().get(DATA_ENTITY_TRACK_TARGET));
        tag.putFloat("Rotation", this.getEntityData().get(DATA_Y_ROTATION));
    }
}
