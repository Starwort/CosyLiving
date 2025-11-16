# All parts of the project

## and where they are in this port

### Blocks

- [ ] Test Block (`block/custom/TestBlock.java`, registered `block/ModBlocks.java`)
- [x] Raspberry Bush (`block/custom/RaspberryBushBlock.java`, registered
  `block/ModBlocks.java`)
    - `content/block/RaspberryBushBlock.java`, registered `content/ModBlocks.java`
- [x] Coconut (`block/custom/CoconutBlock.java`, registered `block/ModBlocks.java`)
    - `content/block/CoconutBlock.java`, registered `content/ModBlocks.java`
    - Missing damage type for now
- [x] Coconut Plant (`block/custom/CoconutPlantBlock.java`, registered
  `block/ModBlocks.java`)
    - `content/block/CoconutPlantBlock.java`, registered `content/ModBlocks.java`
- [x] Coconut Sapling (`block/custom/CoconutSaplingBlock.java`, registered
  `block/ModBlocks.java`)
    - Anonymous subclass, registered `content/ModBlocks.java`
    - TreeGrower (`world/gen/coconut_tree/CoconutSaplingGenerator.java` ->
      `level/gen/CoconutTreeGrower.java`) not implemented yet
- [x] Coconut Leaves (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [ ] Coconut Leaves Corner (`block/custom/CoconutLeavesCornerBlock.java`, registered
  `block/ModBlocks.java`)
- [x] Cotton Shrub (`block/custom/CottonShrubBlock.java`, registered
  `block/ModBlocks.java`)
    - Anonymous subclass, registered `content/ModBlocks.java`
- [x] Potted Cotton Shrub (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Cotton Crop (`block/custom/CottonCropBlock.java`, registered
  `block/ModBlocks.java`)
    - Anonymous subclass, registered `content/ModBlocks.java`
- [x] Cotton Bale (`block/custom/CottonBaleBlock.java`, registered
  `block/ModBlocks.java`)
    - Anonymous subclass of `content/block/FlammableRotatedPillarBlock.java`, registered
      `content/ModBlocks.java`
- [x] Coconut Crate (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Raspberry Crate (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Glowberry Tart (`block/custom/SliceableBlock.java`, registered
  `block/ModBlocks.java`)
    - `content/block/SliceableFoodBlock.java`, registered `content/ModBlocks.java`
- [x] Raspberry Pie (`block/custom/SliceableBlock.java`, registered
  `block/ModBlocks.java`)
    - `content/block/SliceableFoodBlock.java`, registered `content/ModBlocks.java`
- [x] Cinnamon Pie (`block/custom/SliceableBlock.java`, registered
  `block/ModBlocks.java`)
    - `content/block/SliceableFoodBlock.java`, registered `content/ModBlocks.java`
- [x] Coconut Planks (registered `block/ModBlocks.java`)
    - Anonymous subclass added to set flammability characteristics, registered
      `content/ModBlocks.java`
- [x] Coconut Log (`block/custom/CoconutLogBlock.java`, registered
  `block/ModBlocks.java`)
    - `content/block/FlammableRotatedPillarBlock.java`, registered
      `content/ModBlocks.java`
    - Coconut-shaking behaviour not yet implemented
- [x] Coconut Wood (registered `block/ModBlocks.java`)
    - `content/block/FlammableRotatedPillarBlock.java`, registered
      `content/ModBlocks.java`
- [x] Stripped Coconut Log (registered `block/ModBlocks.java`)
    - `content/block/FlammableRotatedPillarBlock.java`, registered
      `content/ModBlocks.java`
- [x] Stripped Coconut Wood (registered `block/ModBlocks.java`)
    - `content/block/FlammableRotatedPillarBlock.java`, registered
      `content/ModBlocks.java`
- [x] Coconut Pressure Plate (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Coconut Trapdoor (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Coconut Stairs (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Coconut Button (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Coconut Slab (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Coconut Fence Gate (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Coconut Fence (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Coconut Door (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Potted Coconut Sapling (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Coconut Standing Sign (`TerraformSignBlock`, registered `block/ModBlocks.java`)
    - `content/block/CustomStandingSignBlock.java`, registered `content/ModBlocks.java`
    - Block entity at `content/block/entity/CustomSignBE.java`
- [x] Coconut Wall Sign (`TerraformWallSignBlock`, registered `block/ModBlocks.java`)
    - `content/block/CustomWallSignBlock.java`, registered `content/ModBlocks.java`
    - Block entity at `content/block/entity/CustomSignBE.java`
- [x] Coconut Hanging Sign (`TerraformHangingSignBlock`, registered
  `block/ModBlocks.java`)
    - `content/block/CustomHangingSignBlock.java`, registered `content/ModBlocks.java`
    - Block entity at `content/block/entity/CustomHangingSignBE.java`
- [x] Coconut Wall Hanging Sign (`TerraformWallHangingSignBlock`, registered
  `block/ModBlocks.java`)
    - `content/block/CustomWallHangingSignBlock.java`, registered
      `content/ModBlocks.java`
    - Block entity at `content/block/entity/CustomHangingSignBE.java`
- [x] Raspberry Rhodolite Block (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Raspberry Rhodolite Ore (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Deepslate Raspberry Rhodolite Ore (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Benitoite Block (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Benitoite Ore (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Deepslate Benitoite Ore (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`

### Items

- [x] Wand of Hunger (`item/custom/WandOfHungerItem.java`, registered
  `item/ModItems.java`)
    - Anonymous subclass of `content/item/TooltipItem.java`, registered
      `content/ModItems.java`
- [x] Raspberry Rhodolite (`item/custom/ModTooltipItem.java`, registered
  `item/ModItems.java`)
    - `content/item/TooltipItem.java`, registered `content/ModItems.java`
- [x] Benitoite (`item/custom/ModTooltipItem.java`, registered `item/ModItems.java`)
    - `content/item/TooltipItem.java`, registered `content/ModItems.java`
- [x] Cinnamon Stick (`item/custom/ModTooltipItem.java`, registered
  `item/ModItems.java`)
    - `content/item/TooltipItem.java`, registered `content/ModItems.java`
- [x] Gilded Cinnamon Stick (`item/custom/ModTooltipItem.java`, registered
  `item/ModItems.java`)
    - `content/item/TooltipItem.java`, registered `content/ModItems.java`
- [x] Charcoal Ink (`item/custom/CharcoalInkItem.java`, registered `item/ModItems.java`)
    - `content/item/CharcoalInkItem.java`, registered `content/ModItems.java`
- [x] Buckram (`item/custom/ModTooltipItem.java`, registered `item/ModItems.java`)
    - `content/item/TooltipItem.java`, registered `content/ModItems.java`
- [x] Coconut Boat (`TerraformBoatItemHelper`, registered `item/ModItems.java`)
    - `content/item/BoatItem.java`, registered `content/ModItems.java`
- [x] Coconut Chest Boat (`TerraformBoatItemHelper`, registered `item/ModItems.java`)
    - `content/item/BoatItem.java`, registered `content/ModItems.java`
- [x] Flower Crown (`item/custom/TooltipArmorItem.java`, registered
  `item/ModItems.java`)
    - `content/item/TooltipArmourItem.java`, registered `content/ModItems.java`
- [ ] Lady Beetle Spawn Egg (`SpawnEggItem`, registered `item/ModItems.java`)
    - Not implemented as mob also not implemented

#### Food items

- [x] Cinnamon Bun (`item/custom/ModTooltipItem.java`, registered `item/ModItems.java`)
    - `content/item/TooltipItem.java`, registered `content/ModItems.java`
- [x] Coconut Milk (`item/custom/BottledItem.java`, registered `item/ModItems.java`)
    - `content/item/food/BottledFoodItem.java`, registered `content/ModItems.java`
- [x] Heavy Cream (`item/custom/BottledItem.java`, registered `item/ModItems.java`)
    - `content/item/food/BottledFoodItem.java`, registered `content/ModItems.java`
- [x] Candy Apple (`item/custom/CandyAppleItem.java`, registered `item/ModItems.java`)
    - `content/item/food/CandyAppleItem.java`, registered `content/ModItems.java`
- [x] Golden Candy Apple (`item/custom/CandyAppleItem.java`, registered
  `item/ModItems.java`)
    - `content/item/food/CandyAppleItem.java`, registered `content/ModItems.java`
- [x] Enchanted Golden Candy Apple (`item/custom/CandyAppleItem.java`, registered
  `item/ModItems.java`)
    - `content/item/food/CandyAppleItem.java`, registered `content/ModItems.java`
- [x] Roasted Pumpkin Seeds (`item/custom/ModTooltipItem.java`, registered
  `item/ModItems.java`)
    - `content/item/TooltipItem.java`, registered `content/ModItems.java`
- [x] Roasted Melon Seeds (`item/custom/ModTooltipItem.java`, registered
  `item/ModItems.java`)
    - `content/item/TooltipItem.java`, registered `content/ModItems.java`
- [x] Glowberry Tart Slice (`item/custom/ModTooltipItem.java`, registered
  `item/ModItems.java`)
    - `content/item/TooltipItem.java`, registered `content/ModItems.java`
- [x] Raspberry Pie Slice (`item/custom/ModTooltipItem.java`, registered
  `item/ModItems.java`)
    - `content/item/TooltipItem.java`, registered `content/ModItems.java`
- [x] Cinnamon Pie Slice (`item/custom/ModTooltipItem.java`, registered
  `item/ModItems.java`)
    - `content/item/TooltipItem.java`, registered `content/ModItems.java`
- [x] Apple Sauce (`item/custom/BottledItem.java`, registered `item/ModItems.java`)
    - `content/item/food/BottledFoodItem.java`, registered `content/ModItems.java`
- [x] Watermelon Popsicle (`item/custom/FreezingItem.java`, registered
  `item/ModItems.java`)
    - `content/item/food/FrozenFoodItem.java`, registered `content/ModItems.java`
- [x] Honeycomb Ice Cream (`item/custom/FreezingItem.java`, registered
  `item/ModItems.java`)
    - `content/item/food/FrozenFoodItem.java`, registered `content/ModItems.java`
- [x] Coconut Ice Cream (`item/custom/FreezingItem.java`, registered
  `item/ModItems.java`)
    - `content/item/food/FrozenFoodItem.java`, registered `content/ModItems.java`
- [x] Raspberry Ice Cream (`item/custom/FreezingItem.java`, registered
  `item/ModItems.java`)
    - `content/item/food/FrozenFoodItem.java`, registered `content/ModItems.java`
- [x] Triple Ice Cream (`item/custom/FreezingItem.java`, registered
  `item/ModItems.java`)
    - `content/item/food/FrozenFoodItem.java`, registered `content/ModItems.java`
- [x] Villager Stew (`item/custom/ContainerItem.java`, registered `item/ModItems.java`)
    - `content/item/food/ResidueFoodItem.java`, registered `content/ModItems.java`
- [x] Raspberry Tea (`item/custom/BottledItem.java`, registered `item/ModItems.java`)
    - `content/item/food/BottledFoodItem.java`, registered `content/ModItems.java`
- [x] Herbal Tea (`item/custom/BottledItem.java`, registered `item/ModItems.java`)
    - `content/item/food/BottledFoodItem.java`, registered `content/ModItems.java`
- [x] Gilded Tea (`item/custom/BottledItem.java`, registered `item/ModItems.java`)
    - `content/item/food/BottledFoodItem.java`, registered `content/ModItems.java`
- [x] Hot Chocolate (`item/custom/BottledItem.java`, registered `item/ModItems.java`)
    - `content/item/food/BottledFoodItem.java`, registered `content/ModItems.java`
- [x] Hotter Chocolate (`item/custom/HotterChocolateItem.java`, registered
  `item/ModItems.java`)
    - `content/item/food/BottledFoodItem.java`, registered `content/ModItems.java`
- [x] Sleepy Tea (`item/custom/SleepyTeaItem.java`, registered `item/ModItems.java`)
    - `content/item/food/BottledFoodItem.java`, registered `content/ModItems.java`
- [x] Goopy Chorus (`item/custom/ModTooltipItem.java`, registered `item/ModItems.java`)
    - `content/item/TooltipItem.java`, registered `content/ModItems.java`
- [x] Myco Medley (`item/custom/ContainerItem.java`, registered `item/ModItems.java`)
    - `content/item/food/ResidueFoodItem.java`, registered `content/ModItems.java`
- [x] Berry Blend Smoothie (`item/custom/BottledItem.java`, registered
  `item/ModItems.java`)
    - `content/item/food/BottledFoodItem.java`, registered `content/ModItems.java`
- [x] Pina Glowada (`item/custom/PinaGlowadaItem.java`, registered `item/ModItems.java`)
    - Anonymous subclass of `content/item/TooltipItem.java`, registered
      `content/ModItems.java`
- [x] Raspberry Jam (`item/custom/BottledItem.java`, registered `item/ModItems.java`)
    - `content/item/food/BottledFoodItem.java`, registered `content/ModItems.java`
- [x] Apple Jam (`item/custom/BottledItem.java`, registered `item/ModItems.java`)
    - `content/item/food/BottledFoodItem.java`, registered `content/ModItems.java`
- [x] Sweetberry Jam (`item/custom/BottledItem.java`, registered `item/ModItems.java`)
    - `content/item/food/BottledFoodItem.java`, registered `content/ModItems.java`
- [x] Glowberry Jam (`item/custom/GlowberryJamItem.java`, registered
  `item/ModItems.java`)
    - `content/item/food/BottledFoodItem.java`, registered `content/ModItems.java`
- [x] Raspberry Jam Doughnut (`item/custom/ModTooltipItem.java`, registered
  `item/ModItems.java`)
    - `content/item/TooltipItem.java`, registered `content/ModItems.java`
- [x] Glowberry Jam Doughnut (`item/custom/ModTooltipItem.java`, registered
  `item/ModItems.java`)
    - `content/item/TooltipItem.java`, registered `content/ModItems.java`
- [x] Apple Jam Doughnut (`item/custom/ModTooltipItem.java`, registered
  `item/ModItems.java`)
    - `content/item/TooltipItem.java`, registered `content/ModItems.java`
- [x] Sweetberry Jam Doughnut (`item/custom/ModTooltipItem.java`, registered
  `item/ModItems.java`)
    - `content/item/TooltipItem.java`, registered `content/ModItems.java`
- [x] Cream Doughnut (`item/custom/ModTooltipItem.java`, registered
  `item/ModItems.java`)
    - `content/item/TooltipItem.java`, registered `content/ModItems.java`
- [x] Chocolate Bar (`item/custom/ModTooltipItem.java`, registered `item/ModItems.java`)
    - `content/item/TooltipItem.java`, registered `content/ModItems.java`
- [x] Red Sugar (`item/custom/ModTooltipItem.java`, registered `item/ModItems.java`)
    - `content/item/TooltipItem.java`, registered `content/ModItems.java`
- [x] Mao Croqui (`item/custom/ModMaoCroquiItem.java`, registered `item/ModItems.java`)
    - `content/item/food/MaoCroquiItem.java`, registered `content/ModItems.java`

#### Block items

- [ ] Test Block (registered `block/ModBlocks.java`)
- [x] Coconut (`item/custom/TooltipBlockItem.java`, registered `block/ModBlocks.java`)
    - `content/item/TooltipBlockItem.java`, registered `content/ModBlocks.java`
- [x] Coconut Sapling (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Coconut Leaves (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [ ] Coconut Leaves Corner (registered `block/ModBlocks.java`)
- [x] Coconut Planks (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Coconut Log (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Coconut Wood (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Stripped Coconut Log (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Stripped Coconut Wood (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Coconut Pressure Plate (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Coconut Trapdoor (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Coconut Stairs (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Coconut Button (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Coconut Slab (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Coconut Fence Gate (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Coconut Fence (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Coconut Door (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [ ] Potted Coconut Sapling (registered `block/ModBlocks.java`)
    - Intentionally omitted; this seems like an oversight
- [x] Raspberry Rhodolite Block (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Raspberry Rhodolite Ore (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Deepslate Raspberry Rhodolite Ore (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Benitoite Block (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Benitoite Ore (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Deepslate Benitoite Ore (registered `block/ModBlocks.java`)
    - Registered `content/ModBlocks.java`
- [x] Raspberry (`item/custom/ModAliasedBlockToolTipItem.java`, registered
  `item/ModItems.java`)
    - `content/item/ItemNameTooltipBlockItem.java`, registered `content/ModBlocks.java`
- [x] Glowberry Tart (`item/custom/ModAliasedBlockToolTipItem.java`, registered
  `item/ModItems.java`)
    - `content/item/ItemNameTooltipBlockItem.java`, registered `content/ModBlocks.java`
- [x] Raspberry Pie (`item/custom/ModAliasedBlockToolTipItem.java`, registered
  `item/ModItems.java`)
    - `content/item/ItemNameTooltipBlockItem.java`, registered `content/ModBlocks.java`
- [x] Cinnamon Pie (`item/custom/ModAliasedBlockToolTipItem.java`, registered
  `item/ModItems.java`)
    - `content/item/ItemNameTooltipBlockItem.java`, registered `content/ModBlocks.java`
- [x] Cotton Boll (`item/custom/ModTooltipSeedItem.java`, registered
  `item/ModItems.java`)
    - `content/item/ItemNameTooltipBlockItem.java` (deduped), registered
      `content/ModBlocks.java`
- [x] Cotton Shrub Item (`item/custom/TooltipBlockItem.java`, registered
  `item/ModItems.java`)
    - `content/item/TooltipBlockItem.java`, registered `content/ModBlocks.java`
- [x] Cotton Bale Item (`item/custom/TooltipBlockItem.java`, registered
  `item/ModItems.java`)
    - `content/item/TooltipBlockItem.java`, registered `content/ModBlocks.java`
- [x] Coconut Crate Item (`item/custom/TooltipBlockItem.java`, registered
  `item/ModItems.java`)
    - `content/item/TooltipBlockItem.java`, registered `content/ModBlocks.java`
- [x] Raspberry Crate Item (`item/custom/TooltipBlockItem.java`, registered
  `item/ModItems.java`)
    - `content/item/TooltipBlockItem.java`, registered `content/ModBlocks.java`
- [x] Coconut Sign Item (registered `item/ModItems.java`)
    - Registered `content/ModItems.java`
- [x] Hanging Coconut Sign Item (registered `item/ModItems.java`)
    - Registered `content/ModItems.java`

### Effects

- [x] Sleepy (`effect/SleepyEffect.java`, registered `effect/ModEffects.java`)
    - `content/effect/SleepyMobEffect.java`, registered `content/ModEffects.java`
- [x] Third Eye Open (`effect/ThirdEyeOpenEffect.java`, registered
  `effect/ModEffects.java`)
    - `content/effect/ThirdEyeOpenMobEffect.java`, registered `content/ModEffects.java`
- [x] Well Rested (`effect/WellRestedEffect.java`, registered `effect/ModEffects.java`)
    - `content/effect/WellRestedaMobEffect.java`, registered `content/ModEffects.java`

### Statistics

- [ ] LAND_ON_COTTON_BALE (registered `statistic/ModStatistics.java`)

### Damage Types

- [ ] Coconut Bonk (registered `util/ModDamageTypes.java`)
- [ ] Hotter Chocolate (registered `util/ModDamageTypes.java`)

### Tags

#### Block Tags

- [ ] Coconut Logs (registered `util/ModTags.java`)

#### Item Tags

- [ ] Coconut Logs (registered `util/ModTags.java`)
- [ ] Brewing Stand Input (registered `util/ModTags.java`)
- [ ] Brewing Stand Ingredient (registered `util/ModTags.java`)
- [ ] Jams (registered `util/ModTags.java`)
- [ ] Starter Items (registered `util/ModTags.java`)
- [ ] Cotton Items (registered `util/ModTags.java`)
- [ ] Ice Creams (registered `util/ModTags.java`)

#### Biome Tags

- [ ] Has Cotton Shrub Patches (registered `util/ModTags.java`)
- [ ] Has Raspberry Patches (registered `util/ModTags.java`)

### Misc

- [ ] Compostables Registry (`util/tools/CompostingTool.java`)
- [ ] Fuels Registry (`util/tools/ModFuelRegistry.java`)
- [x] Render Layer Registry (`util/tools/RenderLayerTool.java`)
    - Handled by making models use `minecraft:cutout` renderType
- [x] Strippable Blocks Registry (`util/tools/StrippableBlocksTool.java`)
    - Handled in `getToolModifiedState` of relevant blocks
- [ ] Configured Features (`world/ModConfiguredFeatures.java`)
- [ ] Tree Generation (`world/tree/ModTrunkPlacerTypes.java`,
  `world/tree/custom/CoconutTrunkPlacer.java`)
- [ ] Tea Recipes