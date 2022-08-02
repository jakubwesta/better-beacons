package com.betbea.util;

import com.betbea.common.block.enity.BetterBeaconBlockEntity;
import com.betbea.common.block.enity.CrystalBlockEntity;

import java.util.ArrayList;
import java.util.HashMap;

public class AllColumnsData {
    private final HashMap<Integer, ColumnData> columns = new HashMap<>();
    private final BetterBeaconBlockEntity beaconBlockEntity;

    public AllColumnsData(BetterBeaconBlockEntity beacon) {
        for (int i = 0; i <= 11; i += 1) {
            columns.put(i, new ColumnData());
        }
        beaconBlockEntity = beacon;
    }

    public void setColumn(int index, ColumnMaterial material, CrystalQuality quality, CrystalBlockEntity entity) {
        entity.connectToBeacon(beaconBlockEntity);
        columns.replace(index, new ColumnData(material, quality, entity));
    }

    public void setInvalidColumn(int index) {
        if (columns.get(index).getCrystalBlockEntity().isPresent()) {
            columns.get(index).getCrystalBlockEntity().get().disconnectBeacon();
        }
        columns.replace(index, new ColumnData());
    }

    public ArrayList<ColumnData> getColumns() {
        ArrayList<ColumnData> columnData = new ArrayList<>();
        for (int i = 0; i <= 11; i += 1) {
            columnData.add(columns.get(i));
        }
        return columnData;
    }

    public ColumnData getColumn(int index) {
        return columns.get(index);
    }

    public BetterBeaconBlockEntity getBeaconBlockEntity() {
        return beaconBlockEntity;
    }
}