package ladysnake.illuminations.common.init;

import ladysnake.illuminations.common.entities.FireflyEntity;
import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.util.registry.Registry;

public class IlluminationsEntities {

    public static EntityType<FireflyEntity> FIREFLY;

    public static void init() {
        FIREFLY = Registry.register(Registry.ENTITY_TYPE, "illuminations:firefly", FabricEntityTypeBuilder.<FireflyEntity>create(EntityCategory.AMBIENT, FireflyEntity::new).size(EntityDimensions.changing(1.0f, 1.0f)).trackable(64, 1, true).build());
    }

}
