package com.druwa.menu.model;

public class CafeMenuResponse {
    private long id;
    private long parentId;
    private String menuName;
    private String menuCost;
    private String menuCostEmp;

    public CafeMenuResponse(long id, long parentId, String menuName, String menuCost, String menuCostEmp) {
        this.id = id;
        this.parentId = parentId;
        this.menuName = menuName;
        this.menuCost = menuCost;
        this.menuCostEmp = menuCostEmp;
    }
}
