package com.betbea.util;

import java.util.ArrayList;
import java.util.HashMap;

public class AllColumnsData {
    private final HashMap<Integer, ColumnData> columns = new HashMap<>();

    public AllColumnsData() {
        for (int i = 0; i <= 11; i += 1) {
            columns.put(i, new ColumnData());
        }
    }

    public void setColumn(int index, ColumnMaterial material, CrystalQuality quality) {
        columns.replace(index, new ColumnData(material, quality));
    }

    public void setInvalidColumn(int index) {
        columns.replace(index, new ColumnData());
    }

    public ArrayList<ColumnData> getColumns() {
        ArrayList<ColumnData> columnData = new ArrayList<>();
        for (int i = 0; i <= 11; i += 1) {
            columnData.add(columns.get(i));
        }
        return columnData;
    }
}