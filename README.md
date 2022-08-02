#Better Beacons

##Beacon mechanics

###Column's material power

Each properly made column placed on the right spot will give beacon it's material power.

Material power is calculated with formula: `columnMaterialPower = baseMaterialPower * crystalStrengthMultiplier`.
`baseMaterialPower` and `crystalStrengthMultiplier` can be set in config file.

First 3 columns of each type will work at `first3` power multiplier.
Next 3, (4, 5, 6) will work at `second3` multiplier, and so on... up to 12 columns.
Those values can be set in config as well.

####Basic config values:

Power of one column will be `1` with matte crystal, `1.75` with cleat and `2.5` with exquisite one.

Max amount of power for one material (12 exquisite crystals and 12 columns of same material) is equal:
`maxPower = 3 * 2.5 + 3 * 2.5 * 0.75 + 3 * 2.5 * 0.5 + 3 * 2.5 * 0.25`, which is equal to `18.75`

But with different materials (3x each one): `eachMaterialPower = 3 * 2.5 = 7.5`

***Basically the more columns of the same material you have, the less power they have.***